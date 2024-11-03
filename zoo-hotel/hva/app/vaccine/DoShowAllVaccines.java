package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to display all registered vaccines in the zoo hotel.
 * This command retrieves the list of vaccines and shows their details.
 */
class DoShowAllVaccines extends Command<Hotel> {

    /**
     * Constructs a DoShowAllVaccines command for the specified receiver.
     *
     * @param receiver The Hotel instance that this command will operate on.
     */
    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
    }

    /**
     * Executes the command to display all registered vaccines.
     * This method retrieves the vaccines from the receiver, sorts them
     * by their ID, and displays each vaccine's details.
     */
    @Override
    protected final void execute() {
        // Retrieve all vaccines and sort them by ID
        String displayString = _receiver.doShowAllVaccines();
        if (displayString != null) _display.addLine(displayString);
    }
}
