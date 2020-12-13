/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Navigator;

import MantainerSelection.ActivityAssignmentGUI;
import MantainerSelection.MaintainerSelectionGUI;
import PrimoPackege.Maintainer;
import PrimoPackege.MaintanceActivity;
import PrimoPackege.Planner;
import SelectionGUI.SelectActivityGUI;
import VerifyGUI.VerifyActivityGUI;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JFrame;
import plannerGUI.CreateAnActivityGUI;
import plannerGUI.FirstPagePlannerGUI;
import plannerGUI.ViewActivityGUI;

/**
 *
 * @author Catello
 */
public class Navigator {
    private static Navigator instance;
    private Planner p;
    private MaintanceActivity selectedActivity;
    private String selectedActivityInfo;
    
    public synchronized static Navigator getInstance(Planner p) {
        if (instance == null) {
            instance = new Navigator(p);
        }
        return instance;
    }

    private Navigator(Planner p) {
        this.p = p;
    }
    
    
    public void changeToVerifyActivityWindow(JFrame currentWindow, String activityID){
        selectedActivity = p.getMaintanceActivity(activityID);
        selectedActivityInfo = selectedActivity.getId()+" - "+selectedActivity.getSite().getArea()+" "+selectedActivity.getSite().getFactory()+" - "+selectedActivity.getTypology()+" - "+selectedActivity.getIntervationTime()+"'";
        //VerifyActivityGUI verify = new VerifyActivityGUI(p, selectedActivity.getId(), selectedActivity.getIntervationTime(), selectedActivity.getSite().getArea()+" "+selectedActivity.getSite().getFactory(),selectedActivity.getTypology());
        VerifyActivityGUI verify = new VerifyActivityGUI(p, selectedActivity.getId(), selectedActivityInfo);
        changeInterface(verify,currentWindow);
    }
    public void changeToWelcomeWindow(JFrame currentWindow){
        FirstPagePlannerGUI welcome= new FirstPagePlannerGUI(p);
        changeInterface(welcome,currentWindow);
    }
    public void changeToCreateActivityWindow(JFrame currentWindow){
        CreateAnActivityGUI creation= new CreateAnActivityGUI(p);
        changeInterface(creation,currentWindow);
    }
    public void changeToSelectActivityWindow(JFrame currentWindow){
        SelectActivityGUI activitySelection= new SelectActivityGUI(p);
        changeInterface(activitySelection,currentWindow);
    }
    public void changeToViewActivityWindow(JFrame currentWindow){
        ViewActivityGUI activityView= new ViewActivityGUI(p);
        changeInterface(activityView,currentWindow);
    }
    public void changeToMaintainerSelectionWindow(JFrame currentWindow){
        MaintainerSelectionGUI maintainerSelection= new MaintainerSelectionGUI(p,selectedActivity.getId(),selectedActivityInfo);
        changeInterface(maintainerSelection,currentWindow);
    }
    public void changeToActivityAssignmentWindow(JFrame currentWindow, String mantainerName, String skillCompliance, String selectedWeekDay,LocalDate selectedDate,Color percentageColor, String percentage, int selectedRow){
        Maintainer m = p.getSelectedMaintainer(selectedRow);
        String selectedDayOfWeek=selectedWeekDay.substring(0, 1)+selectedWeekDay.substring(1).toLowerCase();
        ActivityAssignmentGUI assignment= new ActivityAssignmentGUI(p, mantainerName, skillCompliance, selectedDayOfWeek, selectedActivityInfo ,this.selectedActivity.getWorkspacenotes(), selectedDate,this.selectedActivity.getId(),percentageColor, percentage,m.getId(), this.selectedActivity.getIntervationTime() );
        changeInterface(assignment,currentWindow);
    }
    
    private void changeInterface(JFrame nextWindow, JFrame currentWindow){
        nextWindow.setVisible(true);
        nextWindow.pack();
        nextWindow.setLocationRelativeTo(null);
        nextWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentWindow.dispose();
    }
}
