package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.CoreUnknownHabitatKeyException;
import hva.core.exception.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
    addStringField("idSpecies", hva.app.animal.Prompt.speciesKey());
    addStringField("influence", Prompt.habitatInfluence());
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = stringField("idHabitat");
    String idSpecies = stringField("idSpecies");
    String influence = stringField("influence");

    while (!influence.equals("NEG") && !influence.equals("NEU") && !influence.equals("POS")){
      influence = Form.requestString(Prompt.habitatInfluence());
    }

    try {
      _receiver.changeInfluence(idHabitat, idSpecies, influence);
      _receiver.unsaveState();
    } catch (CoreUnknownHabitatKeyException  e) {
      throw new UnknownHabitatKeyException(idHabitat);
    } catch (CoreUnknownSpeciesKeyException  e) {
      throw new UnknownSpeciesKeyException(idSpecies);
    }
  }
}
