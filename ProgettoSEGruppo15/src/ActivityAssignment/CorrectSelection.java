/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;

import Navigator.Navigator;
import Planner.PlannerInterface;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Catello
 * Rappresenta una selezione effettuata correttamente sulle celle della tabella
 */
public class CorrectSelection implements SelectionState{
    private PlannerInterface planner;
    private String selectedMaintainer;
    private String selectedDayOfWeek;
    private String selectedActvity;
    private int weekNumber;
    private int [] actFractTime;
    private ArrayList<Integer>tempNumericSlots;
    private JFrame currentWindow;
/**
 * 
 * @param planner L'oggetto che rappresenta il Planner che sta operando attualmente
 * @param selectedMaintainer Identificativo del manutentore al quale si vuole assegnare l'attività
 * @param selectedDayOfWeek Stringa del giorno della settimana in cui si vuole assegnare l'attività
 * @param selectedActvity Identificativo dell'attività che si vuole assegnare
 * @param weekNumber Numero della settimana in cui si vuole assegnare l'attività
 * @param actFractTime Array di interi che contiene quanto tempo di dell' attività da assegnare in ogni fascia oraria
 * @param tempNumericSlots ArrayList di interi che contiene la nuova disponibilità per ogni manutentore
 * @param currentWindow La finestra attualmente visualizzata
 */
    public CorrectSelection(PlannerInterface planner, String selectedMaintainer, String selectedDayOfWeek, String selectedActvity, int weekNumber, int[] actFractTime, ArrayList<Integer> tempNumericSlots, JFrame currentWindow) {
        this.planner = planner;
        this.selectedMaintainer = selectedMaintainer;
        this.selectedDayOfWeek = selectedDayOfWeek;
        this.selectedActvity = selectedActvity;
        this.weekNumber = weekNumber;
        this.actFractTime = actFractTime;
        this.tempNumericSlots = tempNumericSlots;
        this.currentWindow = currentWindow;
    }
    /**
     * Assegna l'attività selezionata al munutentore selezionato rispecchiando la divisione della stessa ottenuta tramite la 
     * selezione del Planner. 
     * <p>
     * Aggiorna la disponibilità del muanuentore alla luce della nuova attività a lui assegnata
     * <p>
     * Se entrambe le operazioni vengono effettuate correttamente viene mostrato una finestra che comunica al planner il successo delle stesse
     * Dopo la sua conferma verrà riderezionato verso SelectActivityGUI
     * <p>
     * Se una delle due operazioni non viene effettuata correttamente verrà mostrato una finestra che informa il Planner del fallimento delle stesse
     * ed è invitato a effetturare una nuova selezione
     */
    @Override
    public void confirmSelection() {
         boolean okOp1=planner.updateMaintainerAvailability(this.selectedMaintainer, this.selectedDayOfWeek, this.tempNumericSlots);
            boolean okOp2=planner.assignActivityFraction(this.selectedActvity, this.selectedMaintainer, this.selectedDayOfWeek, weekNumber, actFractTime);
            if(okOp1 && okOp2){
                planner.updateActivityToMaintainer(this.selectedActvity, this.selectedMaintainer);
                JOptionPane.showMessageDialog(new JPanel(),  "Now you will return to select other actvity","Activity assigned succefully", JOptionPane.INFORMATION_MESSAGE);
                Navigator nav = Navigator.getInstance(planner);
                nav.changeToSelectActivityWindow(currentWindow);
            }
            else
                 JOptionPane.showMessageDialog(new JPanel(),  "Operazione sul repository non riusciuta","Errore", JOptionPane.ERROR_MESSAGE);
    }
    
}
