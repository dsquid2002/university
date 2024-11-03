package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.*;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

@Override
  protected final void execute() throws CommandException {
    if(!_receiver.isSaved())
      if (Form.confirm(Prompt.saveBeforeExit())) {
        if(_receiver.hasFileAssociated())
          try {
            _receiver.save();
          } catch (MissingFileAssociationException | IOException e) {
            e.printStackTrace();
          }
        else {
          try {
            _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
          } catch (MissingFileAssociationException | IOException e) {
            e.printStackTrace();
          }
        }

      }
    _receiver.newHotel();
  }
}

