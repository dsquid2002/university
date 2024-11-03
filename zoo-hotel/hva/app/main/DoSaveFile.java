package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    
      if(!_receiver.isSaved()){
          if(!_receiver.hasFileAssociated()){
            String fileToAssociate = Form.requestString(Prompt.newSaveAs());
            try {
                  _receiver.saveAs(fileToAssociate);
                } catch (MissingFileAssociationException | IOException e) {
                  e.printStackTrace();
                }
          }
          else{
             try {
              _receiver.save();
            } catch (MissingFileAssociationException | IOException e) { 
              e.printStackTrace();
            }
          }
    }
  }
}

