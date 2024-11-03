package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if(!_receiver.isSaved())
      if (Form.confirm(Prompt.saveBeforeExit())) {  
        if(_receiver.hasFileAssociated())
          try {
            _receiver.save();
          } catch (MissingFileAssociationException | IOException e) {
            throw new FileOpenFailedException(e);
          }
        else { 
          try {
            _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
          } catch (MissingFileAssociationException | IOException e) {
            throw new FileOpenFailedException(e);
          }
        }
      }
    String displayString = Form.requestString(Prompt.openFile());
    try {
      _receiver.load(displayString); 
    } catch (UnavailableFileException  efe) {
      throw new FileOpenFailedException(efe);
    }
  } 
}
