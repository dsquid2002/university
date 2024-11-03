package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreNoResponsibilityException;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
    addStringField("respKey", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idEmployee = stringField("idEmployee");
    String idResp = stringField("respKey");

    try {
      _receiver.rmResponsibility(idEmployee, idResp);
      _receiver.unsaveState();
    } catch (CoreUnknownEmployeeKeyException e){
      throw new UnknownEmployeeKeyException(idEmployee);
    } catch (CoreNoResponsibilityException e) {
      throw new NoResponsibilityException(idEmployee, idResp);
    }
  }
}
