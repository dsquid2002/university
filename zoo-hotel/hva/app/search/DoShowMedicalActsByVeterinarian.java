package hva.app.search;

import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("idVet", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {

    String idVet = stringField("idVet");

    try {
      String displayString = _receiver.doShowMedicalActs(idVet);
      if (displayString != null)_display.addLine(displayString);
    } catch (CoreUnknownVeterinarianKeyException e) {
      throw new UnknownVeterinarianKeyException(idVet);
    }
  }
}
