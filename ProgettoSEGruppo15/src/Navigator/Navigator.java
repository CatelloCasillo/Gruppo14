/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Navigator;

import EWOassignment.EwoAssignmentGUI;
import MantainerSelection.ActivityAssignmentGUI;
import MantainerSelection.MaintainerSelectionGUI;
import Maintainer.Maintainer;
import MaintenanceActivity.MaintanceActivity;
import Planner.PlannerInterface;
import SelectionGUI.SelectActivityGUI;
import VerifyGUI.VerifyActivityGUI;
import java.awt.Color;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import plannerGUI.ActivityEWO;
import plannerGUI.CreateAnActivityGUI;
import plannerGUI.FirstPagePlannerGUI;
import plannerGUI.TicketList;
import plannerGUI.ViewActivityGUI;

/**
 *
 * @author Catello
 * Rappresenta l'entità che permette di navigare tra le varie finestre sviluppate ed è stato
 * implementato seguendo il design patter Singleton
 * 
 */
public class Navigator {
    private static Navigator instance;
    private PlannerInterface p;
    private MaintanceActivity selectedActivity;
    private String selectedActivityInfo;
    private File f [];
    
    /**
     * Crea una nuova istanza di Navigator se non ne è mai stata creata una altrimenti
     * restituisce instance creato precedentemente
     * @param p il planner che sta usando l'interfaccia
     * @return Istanza del Navigator
     */
    public synchronized static Navigator getInstance(PlannerInterface p) {
        if (instance == null) {
            instance = new Navigator(p);
        }
        return instance;
    }
    /**
     * Costruttore privato per permettere il constrollo sull'istanziazione del navigator
     * @param p il planner che sta usando l'interfaccia
     */
    private Navigator(PlannerInterface p) {
        this.p = p;
        this.f = new File("src\\Tickets").listFiles();
    }
    
    /**
     * Aggiunge alla visualizzazione attuale VerifyActivityActivity GUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente 
     * @param activityID identificativo che rappresenta l'attività per il quale il Planner vuole verificare le informazioni
     */
    public void changeToVerifyActivityWindow(JFrame currentWindow, String activityID){
        selectedActivity = p.getMaintanceActivity(activityID);
        selectedActivityInfo = selectedActivity.getId()+" - "+selectedActivity.getSite().getArea()+" "+selectedActivity.getSite().getFactory()+" - "+selectedActivity.getTypology()+" - "+selectedActivity.getIntervationTime()+"'";
        VerifyActivityGUI verify = new VerifyActivityGUI(p, selectedActivity.getId(), selectedActivityInfo);
        changeInterface(verify,currentWindow);
    }
    /**
     * Aggiunge alla visualizzazione attuale la FirstPagePlannerGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     */
    public void changeToWelcomeWindow(JFrame currentWindow){
        FirstPagePlannerGUI welcome= new FirstPagePlannerGUI(p);
        changeInterface(welcome,currentWindow);
    }
    /**
     * 
     * Aggiunge alla visualizzazione attuale la CreateAnActivityGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     */
    public void changeToCreateActivityWindow(JFrame currentWindow){
        CreateAnActivityGUI creation= new CreateAnActivityGUI(p);
        changeInterface(creation,currentWindow);
    }
    
    /**
     * Aggiunge alla visualizzazione attuale la SelectActivityGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     *
     */
    public void changeToSelectActivityWindow(JFrame currentWindow){
        SelectActivityGUI activitySelection= new SelectActivityGUI(p);
        changeInterface(activitySelection,currentWindow);
    }
    /**
     * Aggiunge alla visualizzazione attuale la ViewActivityGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     *
     */
    public void changeToViewActivityWindow(JFrame currentWindow){
        ViewActivityGUI activityView= new ViewActivityGUI(p);
        changeInterface(activityView,currentWindow);
    }
    /**
     * Aggiunge alla visualizzazione attuale la MaintainerSelectionGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     *
     */
    public void changeToMaintainerSelectionWindow(JFrame currentWindow){
        MaintainerSelectionGUI maintainerSelection= new MaintainerSelectionGUI(p,selectedActivity.getId(),selectedActivityInfo);
        changeInterface(maintainerSelection,currentWindow);
    }
    /**
     * 
     * Aggiunge alla visualizzazione attuale la AcitivityAssignmentGUI e rimuove la finestra corrente
     * @param currentWindow JFrame che rappresenta la finestra corrente
     * @param skillCompliance Numero di skill adeguate all'attivatà selezionata possedute dal manutentore/ Numero di skill 
     * necessarie allo svolgimente della procedure dell'attività
     * @param selectedWeekDay
     * @param selectedDate
     * @param percentageColor
     * @param percentage
     * @param selectedRow 
     */
    public void changeToActivityAssignmentWindow(JFrame currentWindow, String mantainerName, String skillCompliance, String selectedWeekDay,LocalDate selectedDate,Color percentageColor, String percentage, int selectedRow){
        Maintainer m = p.getSelectedMaintainer(selectedRow);
        String selectedDayOfWeek=selectedWeekDay.substring(0, 1)+selectedWeekDay.substring(1).toLowerCase();
        ActivityAssignmentGUI assignment= new ActivityAssignmentGUI(p, mantainerName, skillCompliance, selectedDayOfWeek, selectedActivityInfo ,this.selectedActivity.getWorkspacenotes(), selectedDate,this.selectedActivity.getId(),percentageColor, percentage,m.getId(), this.selectedActivity.getIntervationTime() );
        changeInterface(assignment,currentWindow);
    }
    
    public void changeToActivityEWO(JFrame currentWindow, String s0, String s1, String s2, String s3){
        ActivityEWO a= new ActivityEWO(p, s0, s1, s2, s3);
        changeInterface(a, currentWindow);
    }
    
    public void changeToTicketList(JFrame currentWindow){
        TicketList ticket= new TicketList(p, f);
        changeInterface(ticket, currentWindow);
    }
    
    public void changeToEWOassignment(JFrame currentWindow,String site,String id,String typology,String workspacenote,List<String> skills){
        EwoAssignmentGUI a= new EwoAssignmentGUI(p, site,id,typology, workspacenote, skills);
        changeInterface(a, currentWindow);
    }
    /**
     * Elimina dalla visualizzazione currentWindow ed ci aggiunge nextWindow (centrodola sullo schermo)
     * @param nextWindow finestra da visualizzare successivamente
     * @param currentWindow finestra corrente
     */
    private void changeInterface(JFrame nextWindow, JFrame currentWindow){
        nextWindow.setVisible(true);
        nextWindow.pack();
        nextWindow.setLocationRelativeTo(null);
        nextWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentWindow.dispose();
    }
}
