/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;

import Navigator.Navigator;
import PrimoPackege.Planner;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Catello
 */
public class CorrectSelection implements SelectionState{
    private Planner planner;
    private String selectedMaintainer;
    private String selectedDayOfWeek;
    private String selectedActvity;
    private int weekNumber;
    private int [] actFractTime;
    private ArrayList<Integer>tempNumericSlots;
    private JFrame currentWindow;

    public CorrectSelection(Planner planner, String selectedMaintainer, String selectedDayOfWeek, String selectedActvity, int weekNumber, int[] actFractTime, ArrayList<Integer> tempNumericSlots, JFrame currentWindow) {
        this.planner = planner;
        this.selectedMaintainer = selectedMaintainer;
        this.selectedDayOfWeek = selectedDayOfWeek;
        this.selectedActvity = selectedActvity;
        this.weekNumber = weekNumber;
        this.actFractTime = actFractTime;
        this.tempNumericSlots = tempNumericSlots;
        this.currentWindow = currentWindow;
    }
    
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
