package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);

    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idTree", Prompt.treeKey());
    addStringField("nameTree", Prompt.treeName());
    addIntegerField("ageTree", Prompt.treeAge());
    addIntegerField("baseDifficulty", Prompt.treeDifficulty());
    addStringField("treeType", Prompt.treeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");
    String idTree = stringField("idTree");
    String nameTree = stringField("nameTree");
    int ageTree = integerField("ageTree");
    int baseDifficulty = integerField("baseDifficulty");
    String treeType = stringField("treeType");
    Form f = new Form();

    while (!treeType.equals("PERENE") && !treeType.equals("CADUCA")){
      treeType = f.requestString(Prompt.treeType());
    }

    try {
      _receiver.registerTree(idHabitat, idTree, nameTree, ageTree, 
                            baseDifficulty, treeType);
      _receiver.unsaveState();
      _display.addLine(_receiver.getTreeById(idTree).toString());
    } catch (CoreDuplicateTreeKeyException e) {
      throw new DuplicateTreeKeyException(idTree);
    } catch (CoreUnknownHabitatKeyException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    } 
  }
}
