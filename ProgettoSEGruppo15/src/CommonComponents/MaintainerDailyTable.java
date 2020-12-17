/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import CommonComponents.CommonTableElements.DefaultHeaderRenderer;
import CommonComponents.CommonTableElements.HourPercentageRenderer;
import CommonComponents.CommonTableElements.MaintenerColumnRenderer;
import CommonComponents.CommonTableElements.NoEditableTableModel;
import CommonComponents.CommonTableElements.SkillColumnRenderer;
import PrimoPackege.Planner;
import PrimoPackege.PlannerAbstract;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Catello
 * Crea un tabella con 10 colonne e righe pari al numero di manutentori memorizzati
 * <p>
 * Le celle di header hanno sfondo arancione, bordo bianco e il testo visualizzato al suo interno è nero
 * La prima cella di header contiene "Maintainer" , la seconda "skill", le altre contengono l'indicazione della
 * fascia oraria nel seguente formato "Availab. 9:00 - 10:=="
 * <p>
 * La prima colonna ha le celle di sfondo rosa scuro se l'indice della sua riga è pari rosa chiaro altrimenti. Contiene i nomi
 * dei menutentori
 * <p>
 * La seconda colonna contiene la skill compliance del mnutentore in questione relativa all'attività selezionata. Questa deve essere
 * nel forma "x1/x2" pena NumberFormatException. Lo sfondo della cella cambia a seconda della percetuale di adeguatezza del Munutentore
 * <p>
 * Le rimanenti otto colonne contentogo il tempo rimanente in minuti all'interno della fascia oraria del manutentore corrispodente
 * Il contenuto è nel formato "x min" pena NumberFormatException. Il coloro di sfondo della cella viene impostato a seconda
 * della percetuale di minuti rimanenti all'interno della fascia oraria che rappresenta.
 * <p>
 * Dopo aver effetutato un'operazione di selezione sulle celle questo avranno sfondo nero e testo bianco 
 * 
 */
public class MaintainerDailyTable extends JTable{
    private static final String header [] = {
                    "Maintainer",
                    "Skills",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>8:00 - 9:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>9:00 - 10:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>10:00 - 11:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>11:00 - 12:00</span></div></html>\"",

                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>13:00 - 14:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>14:00 - 15:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>15:00 - 16:00</span></div></html>\"",
                    "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>16:00 - 17:00</span></div></html>\"",
    };
    /**
     * Crea una tabella con un sola riga corrispodente al manutentore passaoto come parametro
     * @param p  L'oggetto che rappresenta il Planner che sta operando attualmente
     * @param maintainerName Nome del manutentore a cui si vuole assegnare l'attività
     * @param skillCompliance Stringa contenente il numero di skill del manutentore rispetto quelle necessarie per eseguire l'attività
     * @param slots Array che contiene la disponibilità rimanente per ogni fascia oraria
     */
    public MaintainerDailyTable(PlannerAbstract p, String maintainerName, String skillCompliance , Object [] slots) {
        super();
        this.setColumnSelectionAllowed(true);
        this.setRowSelectionAllowed(false);
        this.setSelectionBackground(new java.awt.Color(153, 0, 153));
        this.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.setModel(new NoEditableTableModel(
                new Object [][] {
                    {maintainerName, skillCompliance, slots[0], slots[1],slots[2], slots[3], slots[4], slots[5],slots[6],slots[7]}
                },
               header
            ));
        this.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        TableColumn firstColumn = this.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(150);
        firstColumn.setCellRenderer(new MaintenerColumnRenderer());
        TableColumn secondColumn = this.getColumnModel().getColumn(1);
        secondColumn.setCellRenderer(new SkillColumnRenderer());
        for(int i=2;i<10;i++){
            TableColumn column=this.getColumnModel().getColumn(i);
            column.setCellRenderer(new HourPercentageRenderer());
        }
        
    }
    public MaintainerDailyTable() {
        super();
        this.setColumnSelectionAllowed(true);
        this.setRowSelectionAllowed(false);
        this.setSelectionBackground(new java.awt.Color(153, 0, 153));
        this.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.setModel(new NoEditableTableModel(
                new Object [][] {
                    {"", "3/5", "60 min", "60 min","60 min","60 min", "60 min", "60 min","60 min","60 min"}
                },
               header
            ));
        this.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        TableColumn firstColumn = this.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(150);
        firstColumn.setCellRenderer(new MaintenerColumnRenderer());
        TableColumn secondColumn = this.getColumnModel().getColumn(1);
        secondColumn.setCellRenderer(new SkillColumnRenderer());
        for(int i=2;i<10;i++){
            TableColumn column=this.getColumnModel().getColumn(i);
            column.setCellRenderer(new HourPercentageRenderer());
        }
        
    }
    public void setNoEditableModel(NoEditableTableModel model){
        
    }
    
}
