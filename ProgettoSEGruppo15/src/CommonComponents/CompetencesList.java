/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import PrimoPackege.Planner;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JList;

/**
 *
 * @author Catello
 */
public class CompetencesList extends JList<String>{
     public CompetencesList() {
        super();
     }

    public CompetencesList(Planner p, String id) {
        super();
        this.setModel(new AbstractListModel<String>() {
            String[] strings = toArrayString(p.getCompetencesList(id));
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i].toString();}
            private String[] toArrayString(ArrayList<String> list){
                String [] array = new String[list.size()];
                for(int i=0; i<list.size();i++){
                    array[i]=list.get(i);
                }
                return array;
            }
        });
    }
    
}
