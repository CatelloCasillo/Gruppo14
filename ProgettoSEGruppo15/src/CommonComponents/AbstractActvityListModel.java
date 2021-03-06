/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;
import Planner.PlannerInterface;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
/**
 *
 * @author Catello
 * Modello per una JList utile a visualizzare la lista delle competenze associate ad attività
 */
public class AbstractActvityListModel extends AbstractListModel<String> {
    private String[] strings;
    
    public AbstractActvityListModel(PlannerInterface p, String id){
        strings = toArrayString(p.getCompetencesList(id));
    }
    
    public int getSize(){ 
        return strings.length; 
    }
    
    public String getElementAt(int i){ 
        return strings[i].toString();
    }
    /**
     * Converte un ArrayList di Stringhe in un vettore di Stringhe
     * @param list ArrayList di Stringhe contenenti la lista dei nomi delle competenze da visualizzare
     * @return Un vettore di Stringe che contiene esattamente gli stessi elementi di list nelle stesse posizioni
     * Se list è vuoto anche il vettore restituito lo sarà
     */
    private String[] toArrayString(ArrayList<String> list){
        String [] array = new String[list.size()];
        for(int i=0; i<list.size();i++){
            array[i]=list.get(i);
        }
        return array;
    }
}
