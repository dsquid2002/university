package hva.app.search;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    String idAnimal = stringField("idAnimal");

    try {
        String displayString = _receiver.doShowAllVaccinations(idAnimal);
      if (displayString != null) _display.addLine(displayString);
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(idAnimal);
    }
  }
}
