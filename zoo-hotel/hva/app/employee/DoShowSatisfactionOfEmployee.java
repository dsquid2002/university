package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.CoreUnknownEmployeeKeyException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idEmployee = stringField("idEmployee");

    try {
      _display.addLine(_receiver.showEmployeeSatisfaction(idEmployee));
    } catch (CoreUnknownEmployeeKeyException e) {
      throw new UnknownEmployeeKeyException(idEmployee);
    }
  }
}
