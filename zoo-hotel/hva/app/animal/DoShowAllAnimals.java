package hva.app.animal;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to show all animals registered in the zoo hotel.
 * This command retrieves the list of animals, sorts them by ID, 
 * and displays their information.
 */
class DoShowAllAnimals extends Command<Hotel> {

    /**
     * Constructs a DoShowAllAnimals command for the specified receiver.
     *
     * @param receiver The Hotel instance that this command will operate on.
     */
    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    /**
     * Executes the command to show all registered animals.
     * Retrieves all animals, sorts them using an ID comparator,
     * and displays their information.
     */
    @Override
    protected final void execute() {
        // Check if there are any animals registered
        String displayString = _receiver.doShowAllAnimals();
        if (displayString != null) _display.addLine(displayString);
    }
}
