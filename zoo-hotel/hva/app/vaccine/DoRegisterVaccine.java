package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to register a new vaccine in the zoo hotel.
 * This command collects the necessary information to create a new vaccine
 * and registers it with the specified hotel receiver.
 */
class DoRegisterVaccine extends Command<Hotel> {

    /**
     * Constructs a DoRegisterVaccine command for the specified receiver.
     *
     * @param receiver The Hotel instance that this command will operate on.
     */
    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);

        // Add command fields for vaccine registration
        addStringField("idVacina", Prompt.vaccineKey());
        addStringField("nameVacina", Prompt.vaccineName());
        addStringField("allSpecies", Prompt.listOfSpeciesKeys());
    }

    /**
     * Executes the command to register a new vaccine.
     *
     * @throws CommandException if there is an error during execution,
     *         such as a duplicate vaccine key or an unknown species key.
     */
    @Override
    protected final void execute() throws CommandException {
        // Gather input fields for vaccine registration
        String idVaccine = stringField("idVacina");
        String nameVaccine = stringField("nameVacina");
        String allSpecies = stringField("allSpecies");

        try {
            // Register the new vaccine with the provided details
            _receiver.registerVaccine(idVaccine, nameVaccine, allSpecies.split(","));
            _receiver.unsaveState();
        } catch (CoreDuplicateVaccineKeyException e) {
            // Display an error message if the vaccine ID already exists
            throw new DuplicateVaccineKeyException(idVaccine);
        } catch (CoreUnknownSpeciesKeyException e) {
            // Display an error message if the species key is unknown
            throw new UnknownSpeciesKeyException(e.getSpecies());
        }
    }
}
