package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.*;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to register a new animal in the zoo hotel.
 * This command checks if the species exists and registers 
 * an animal with the specified ID, name, species, and habitat.
 */
class DoRegisterAnimal extends Command<Hotel> {

    /**
     * Constructs a DoRegisterAnimal command for the specified receiver.
     *
     * @param receiver The Hotel instance that this command will operate on.
     */
    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        
        // Add command fields for animal registration
        addStringField("idAnimal", Prompt.animalKey());
        addStringField("nameAnimal", Prompt.animalName());
        addStringField("idSpecies", Prompt.speciesKey());
    }

    /**
     * Executes the command to register a new animal.
     *
     * @throws CommandException if there is an error during execution,
     *         such as an unknown habitat or duplicate animal key.
     */
    @Override
    protected final void execute() throws CommandException {
        // Initial fields
        String idAnimal = stringField("idAnimal");
        String nameAnimal = stringField("nameAnimal");
        String idSpecies = stringField("idSpecies");
        Form f = new Form();

        // Check if the species exists
        if (!_receiver.isThereSpecies(idSpecies)) {
            // Request the name of the species if it doesn't exist
            String speciesName = f.requestString(Prompt.speciesName());
            try{
                _receiver.registerSpecies(idSpecies, speciesName);
            } catch (CoreDuplicateKSpeciesKeyException e){
                // In case parsed file is wrong we need to throw it in core
                // It will never be thrown when interacting with the user
                throw  new UnknownSpeciesKeyException(speciesName);
            }
            _receiver.unsaveState();
        }

        // Request habitat ID
        String habitatId = f.requestString(hva.app.habitat.Prompt.habitatKey());

        try {
            // Registers the animal
            _receiver.registerAnimal(idAnimal, nameAnimal, idSpecies, habitatId);
        } catch (CoreUnknownHabitatKeyException e) {
            throw new UnknownHabitatKeyException(habitatId);
        } catch (CoreDuplicateAnimalKeyException e) {
            throw new DuplicateAnimalKeyException(idAnimal);
        }
    }
}
