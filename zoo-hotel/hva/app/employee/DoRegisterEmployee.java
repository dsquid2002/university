package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel>{

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
    addStringField("nameEmployee", Prompt.employeeName());
    addStringField("type", Prompt.employeeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idEmployee = stringField("idEmployee");
    String nameEmployee = stringField("nameEmployee");
    String type = stringField("type");

    while (!type.equals("VET") && !type.equals("TRT")){
      type = Form.requestString(Prompt.employeeType());
    }

    try {
      _receiver.registerEmployee(idEmployee, nameEmployee, type);
      _receiver.unsaveState();
    } catch (CoreDuplicateEmployeeKeyException  e) {
      throw new DuplicateEmployeeKeyException(idEmployee);
    }
  }
}
