package hva.app.search;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException; 

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {

    String idHabitat = stringField("idHabitat");

    // Check if there are any animals registered
    try {
      String displayString = _receiver.doShowAllAnimals(idHabitat);
      if (displayString != null) _display.addLine(displayString);
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
    
  }
}
