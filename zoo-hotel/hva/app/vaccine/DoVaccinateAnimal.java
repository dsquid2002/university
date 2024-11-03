package hva.app.vaccine;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownAnimalKeyException;
import hva.core.exception.CoreUnknownVaccineKeyException;
import hva.core.exception.CoreUnknownVeterinarianKeyException;
import hva.core.exception.CoreVeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("idVacc", Prompt.vaccineKey());
    addStringField("idVet", Prompt.veterinarianKey());
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String idVacc = stringField("idVacc");
    String idVet = stringField("idVet");
    String idAnimal = stringField("idAnimal");

    try {
        // Does vaccination and unsaves hotel
        if (_receiver.addVaccinationAct(idVacc, idVet, idAnimal)){
          _display.addLine(hva.app.vaccine.Message.wrongVaccine(idVacc, idAnimal));
        }
        _receiver.unsaveState();
    } catch (CoreUnknownVaccineKeyException e) {
      throw new UnknownVaccineKeyException(idVacc);
    } catch (CoreUnknownVeterinarianKeyException e) {
      throw new UnknownVeterinarianKeyException(idVet);
    } catch (CoreVeterinarianNotAuthorizedException e) {
      throw new VeterinarianNotAuthorizedException(idVet, e.getSpecies());
    } catch (CoreUnknownAnimalKeyException e) {
      throw new UnknownAnimalKeyException(idAnimal);
    }
  }
}
