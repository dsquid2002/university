package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");

    try {
      String displayString = _receiver.doShowTreesInHabitat(idHabitat);
      if (displayString != null) _display.addLine(displayString);
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}
