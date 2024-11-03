package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);

    // Add command fields for habitat registration
    addStringField("idHabitat", Prompt.habitatKey());
    addIntegerField("areaHabitat", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    // Gather input fields for habitat registration
    String idHabitat = stringField("idHabitat");
    int areaHabitat = integerField("areaHabitat");

    try {
      //Updates the area value
      _receiver.changeAreaHabitat(idHabitat, areaHabitat);
      _receiver.unsaveState();

    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }

  }
}
