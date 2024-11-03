package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import java.util.Collections;
import hva.core.Tree;
import javax.swing.text.AbstractDocument;


//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    String displayString = _receiver.doShowAllHabitats();
    if (displayString != null) _display.addLine(displayString);
  }
}
