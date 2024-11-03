package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
  @Override
  protected final void execute() {

    Hotel hotel = _receiver.getHotel();
    _display.addLine(hotel.computeAllSatisfaction());
    
  }
}
