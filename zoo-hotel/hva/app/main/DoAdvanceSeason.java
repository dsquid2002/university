package hva.app.main;

import hva.core.HotelManager;
import hva.core.Tree;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);

  }

  @Override
  protected final void execute() {
    Hotel hotel = _receiver.getHotel();
    int num = hotel.advanceSeason();

    for (Tree tree : hotel.getAllTrees()) {
      tree.getNextSeason();
    }

    hotel.unsaveState();
    
    _display.addLine(num);
  }
}
