package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.core.exception.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);

    addStringField("idAnimal", Prompt.animalKey());
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {

    String idAnimal = stringField("idAnimal");
    String idHabitat = stringField("idHabitat");

    try {
      _receiver.changeHabitat(idAnimal, idHabitat);
      _receiver.unsaveState();
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(idAnimal);
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
    
  }
}
