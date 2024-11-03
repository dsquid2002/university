package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to add a new habitat to the zoo hotel.
 * This command gathers the necessary information to create a new habitat
 * and registers it with the specified receiver.
 */
class DoRegisterHabitat extends Command<Hotel> {

    /**
     * Constructs a DoRegisterHabitat command for the specified receiver.
     *
     * @param receiver The Hotel instance that this command will operate on.
     */
    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        
        // Add command fields for habitat registration
        addStringField("idHabitat", Prompt.habitatKey());
        addStringField("nameHabitat", Prompt.habitatName());
        addIntegerField("areaHabitat", Prompt.habitatArea());
    }

    /**
     * Executes the command to register a new habitat.
     *
     * @throws CommandException if there is an error during execution,
     *         such as a duplicate habitat key.
     */
    @Override
    protected void execute() throws CommandException {
        // Gather input fields for habitat registration
        String idHabitat = stringField("idHabitat");
        String nameHabitat = stringField("nameHabitat");
        int areaHabitat = integerField("areaHabitat");

        try {
            // Register the new habitat
            _receiver.registerHabitat(idHabitat, nameHabitat, areaHabitat);
            _receiver.unsaveState();
        } catch (CoreDuplicateHabitatKeyException e) {
            // Display an error message if the habitat ID already exists
            throw new DuplicateHabitatKeyException(idHabitat);
        }
    }
}
