package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("idAnimal", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String idAnimal = stringField("idAnimal");

    try {
      // Searches for the animal
      _display.addLine(_receiver.showAnimalSatisfaction(idAnimal));
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(idAnimal);
    }    
  }
}
