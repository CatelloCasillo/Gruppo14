/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planner;

import Planner.PlannerAbstract;
import MaintenanceActivity.MaintanceActivityFactory;
import MaintenanceActivity.MaintanceActivity;
import Maintainer.Maintainer;
import MaintenanceActivity.Site;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Gabriella
 * Class that represents the business logig beetween the data level and the GUI level
 * <p> 
 */
public class PlannerConcrete extends PlannerAbstract{
    
    public PlannerConcrete() {
        super();
    }
/**
 * This method allows to create an activity, and add it to the list and database 
 * @param category (enum) indicates the type of maintenance activity
 * @param id(String) indicates the id of activity to instantiate it
 * @param site(Site) indicates the site of activity to instantiate it
 * @param typology (String) indicates the typology of activity to instantiate it
 * @param activityDescription (String) indicates the description of activity to instantiate it
 * @param intervationTime (int) indicates the required time of activity to instantiate it
 * @param interruptible (boolean) indicates if activity is interruptible to instantiate it
 * @param week (int) indicates the week of activity to instantiate it
 * @param workspacenotes (String) indicates the workspace of activity to instantiate it
 * @return true if activity is created successfully false otherwise
 */
    @Override
    public boolean createActivity(MaintanceActivityFactory.Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String workspacenotes) {
        MaintanceActivity a= MaintanceActivityFactory.make(category, id, site, typology, activityDescription, intervationTime, interruptible, week, null, null, null, workspacenotes);
       if(repoActivity.insertNewMaintenanceActivity(a.getId(), a.getSite().getId(), a.getActivityDescription(), a.getIntervationTime(), a.isInterruptible(), a.getWeekNumber(), a.getWorkspacenotes(), a.getTypology(),category.toString())){
            activityList.add(a);
            return true;
       }
       return false;}
/**
 * This method allows take a maintenance activity in the list by id
 * @param idActivity id of activity
 * @return Maintenance Activity according id if exists, null otherwise
 */
    @Override
    public MaintanceActivity getMaintanceActivity(String idActivity) {
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(idActivity))
                return m;
        }
      return null;}
/**
 * This method create a list of typology  from database 
 * @return ArrayList of typology 
 */
    @Override
    public ArrayList<String> getTypology() {
       ArrayList<String> a = new ArrayList<>();
        try {
            ResultSet rst = repoUtilities.getTypologyTable();
            while (rst.next()) {
            String typology = repoActivity.getActivityTypology(rst);
            a.add(typology);
            }return a;
        }catch (SQLException ex) {
          Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }}
/**
 * This method search if an activity with specific id already exists 
 * @param id id of activity
 * @return true if activity with this id already exists, false otherwise
 */
    @Override
    public boolean idControl(String id) {
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(id)){
                return true;
            }
    }    return false;}
    /**
     * 
     * @param weekNumber the number of gregorian calendar week 
     * @return the number of activities planned in the specific week thare not EWO and not assigned yet
     */
    private int getNumberActivityInWeek(int weekNumber){ //
        int numAct=0;
        for (int i=0; i<this.activityList.size(); i++){
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == weekNumber && act.getMaintainerID() == null)
                if(act.getCategory().equals(MaintanceActivityFactory.Category.PLANNED))
                    numAct++;
        }
        return numAct;
    }
    /**
     * Costruisce una matrice di oggetti che rappresenta la tabella che deve essere visualizzata 
     * interfaccia per selezionare l'attività. Se sono presenti attività che non sono EWO e non sono già state assegnate
     * restituisce una matrice di oggetti che avrà una riga per ogni attività PLANNED e 4 colonne,le prime tre
     * che ne sintetizzano le informazioni la quarta che rappresenta il bottone per selezionarla.
     * @param currentWeek String che rappresenta il numero della settimana corrente
     * @return Se non sono presenti Attività PLANNED verrà restituita un Object[][] che contiene una sola riga 
     * altrimenti la matrice che contiene tutte le attività PLANNED pianificate per la corrente settimana
     */
    @Override
    public Object[][] getSelectionableActvity(String currentWeek) {
        int currentWeekNumber=parseInt(currentWeek.trim());
        int numRighe=this.getNumberActivityInWeek(currentWeekNumber);
        final int numAttr=4;
        Object attrTable[][]= new Object[numRighe][];
        int j=0;
        for (int i=0; i<this.activityList.size();i++){
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == currentWeekNumber && act.getMaintainerID() == null ){
                    if(act.getCategory().equals(MaintanceActivityFactory.Category.PLANNED)){
               
                String id= act.getId();
                Site site= act.getSite();
                String area= site.getArea();
                String factory =site.getFactory();
                String type= act.getTypology();
                String time= ""+act.getIntervationTime();
                attrTable[j++]=new Object[]{id,area+" "+factory,type,time,new JButton("Select")};
                }}
            else {
            }
        }
        if(numRighe==0){
            attrTable=new Object[1][];
            attrTable[0] = new Object[]{"","","","",""};
        }
        return attrTable;}
/**
 * This method allows to delete an activity with specific id, both in the list and in the database. 
 * Control if activity is already assigned to restore information
 * @param idActivity (String) id of activity
 * @param row (int) index of activity in the list
 * @return true if delete successful, false otherwise
 */
    @Override
    public boolean deleteActivity(String idActivity, int row) {
        MaintanceActivity act = getMaintanceActivity(idActivity);
        if(act!=null){
            if(act.getMaintainerID()==null){
             if(repoActivity.deleteMaintenanceActivity(idActivity)){
                activityList.remove(row);
                return true;
            }
            }else{
                if(repoAvailability.deleteAssignedActivity(idActivity)){
                    activityList.remove(row);
                    return true;
                }
            }
        }
        return false;
    }
/**
 * This method allows to modify the activity with a specific id, both in the list and in the database
 * @param row (int) index of activity in the list
 * @param id(String) indicates the id ofactivity 
 * @param site(Site) indicates the site of activity 
 * @param typology (String) indicates the typology of activity 
 * @param description (String) indicates the description of activity 
 * @param time (int) indicates the required time of activity
 * @param inter (boolean) indicates if activity is interruptible
 * @param week (int) indicates the week of activity
 * @return true if the activity is modified successful, false otherwise
 */
    @Override
    public boolean updateActivity(int row, String id, String site, String typology, String description, int time, boolean inter, int week) {
       Site s= this.findSiteInList(site, siteList);
        if(s != null && repoActivity.updateMaintenanceActivity(id, s.getId() ,typology, description, time, inter, week)){
            activityList.get(row).setSite(s);
            activityList.get(row).setTypology(typology);
            activityList.get(row).setActivityDescription(description);
            activityList.get(row).setIntervationTime(time);
            activityList.get(row).setInterruptible(inter);
            activityList.get(row).setWeek(week);
            return true;
        }
        return false;}
/**
 * 
 * @return the list of maintenance activity activity 
 */
    @Override
    public ArrayList<MaintanceActivity> getActivityList() {
        return activityList;
    }
/**
 * 
 * @return the list of the site already created
 */
    @Override
    public ArrayList<Site> getSiteList() {
        return siteList;
    }

    @Override
    public ArrayList<String> getCompetencesList(String activityId) {
        ResultSet rst = repoActivity.getCompetencesOfActivity(activityId);
        ArrayList<String> skillList = new ArrayList<>();
        try {  
          while(rst.next()){
          skillList.add(rst.getString(1));
          }
          return skillList;
        } catch (SQLException ex) {
          Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
          return skillList;
        }
    }
/**
 * This method allows to known all the competencies of a specific typology
 * @param typology(String) indicates the typology of activity 
 * @return an arrayList of String that indicates all competencies associated to a specific typology 
 */
    @Override
    public ArrayList<String> getCompetenceTypology(String typology) {
        ResultSet rst= repoUtilities.getCompetenceOfTypology(typology);
        ArrayList<String> competenceTypology= new ArrayList<>();
        try {  
          while(rst.next()){
            competenceTypology.add(rst.getString(1));  
          }
          return competenceTypology;
        }catch (SQLException ex) {
          Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }}
/**
 * This method allows to read all fields in a specific file
 * @param f indicates the selected file that must be read
 * @return a string vector that represented all the fields read in the file, or null if the action is not completed
 */
    @Override
    public String[] getStringInFile(File f) {
        BufferedReader b;
        String s[];
        Reader r; 
      try {
          r= new FileReader(f);
          b=new BufferedReader(r);
          String c=b.readLine();
          if(f == null){
              return null;
          }
          else{
              s= c.split(", ");
          }
      } catch (IOException ex) {
          Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
        return s;}
    /**
     * Calcola quante skill possiede un certo manutentore rispetto a quelle che sono necessarie per
     * eseguire la procedure associata ad un'attività
     * @param selectedActivityId L'id di un'attività memorizzata
     * @param selectedMaintainerId L'id di un mantainer memorizzato
     * @return Restituisce una stringa nel formato "skillMaintainer/skillAttività"
     */
    private String skillCompliance(String selectedActivityId, String selectedMaintainerId){
        ResultSet rst1 = repoActivity.getCompetencesOfActivity(selectedActivityId.trim());
        ResultSet rst2 = repoMaintainer.getCompetencesOfMaintainer(selectedMaintainerId);
        ArrayList<String> maintainerCompetences = new ArrayList<>();
        ArrayList<String> activityCompetences = new ArrayList<>();
        try {
            while(rst1.next()){
                activityCompetences.add(repoUtilities.getCompetenceID(rst1));
            }
            while(rst2.next()){
                maintainerCompetences.add(repoUtilities.getCompetenceID(rst2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
        }
        final int skillneeded = activityCompetences.size();
        int match=0;
        for(String maintainerCompetence : maintainerCompetences)        
            for(String activityCompetence : activityCompetences)
                if(maintainerCompetence.equals(activityCompetence))
                    match++;
        return match+"/"+skillneeded;
    }
    /**
     * Costruisce un calendario che per ogni manutentore visualizza il suo nome, la sua adeguatezza
     * a svolgere l'attività rappresentata da selectedActvityId,e la sua occupazione percentuale in ogni
     * giorno della settimana
     * @param selectedActvityId L'ID corrispondente di un'attività di manutenzione
     * @return Object[][] il calendario della settimana corrente per tutti i manutentori memorizzati
     */
    @Override
    public Object[][] getMaintainerWeekCalendar(String selectedActvityId) {
       maintainers = new ArrayList<>();
        ResultSet rst = repoMaintainer.getMaintainerTable();
        int maintainersNumber=0;
        try {
            while(rst.next()){
                String maintainerId=repoMaintainer.getMaintainerID(rst);
                String maintainerName=repoMaintainer.getMaintainerName(rst);
                maintainers.add(new Maintainer(maintainerId,maintainerName));
                maintainersNumber++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        Object[][] AvailabilityWeekTable= new Object[maintainersNumber][];
        
        for(int i=0;i<maintainersNumber;i++){
            Maintainer m=maintainers.get(i);
            ArrayList<String>WeekAvailability = new ArrayList<>();
            for(String day : days)
                WeekAvailability.add(m.getDayAvailability(day));
            AvailabilityWeekTable[i] = new Object[]{m.getName(),skillCompliance(selectedActvityId, m.getId()), WeekAvailability.get(0), WeekAvailability.get(1), WeekAvailability.get(2), WeekAvailability.get(3), WeekAvailability.get(4), WeekAvailability.get(5), WeekAvailability.get(6)};
        }
        return AvailabilityWeekTable;}
    /**
     * Recupera dal reposotiry la disponibilità in minuti per il manutentore specificato da selectedMaintainerId
     * per ognuna delle fasce orarie previste(dalle 8:00 alle 12:00 e dalle 13:00 alle 17:00
     * @param selectedMaintainerId Stringa che rappresenta l'id del maintainer del quale si vuole conoscere l'occupazione giornaliera
     * @param selectedDay Stringa che rappresenta un giorno della settimana nel formato "Monday"
     * @return Object[] che contiene per in ogni cella i minuti rimanenti nella fascia oraria per un dato 
     * manutentore. Se il manutentore specificato non esiste o il giorno della settimana è specificato nel formato 
     * sbagliato viene restituito un array di 8 null
     */
    @Override
    public Object[] getMaintainerDailyAvailability(String selectedMaintainerId, String selectedDay) {
        ResultSet rst= repoMaintainer.getMaintainer(selectedMaintainerId);
        try {
            rst.next();
        } catch (SQLException ex) {
            Logger.getLogger(PlannerConcrete.class.getName()).log(Level.SEVERE, null, ex);
        }
        String mantId = repoMaintainer.getMaintainerID(rst);
        String mantName = repoMaintainer.getMaintainerName(rst);
        if(mantId!=null){
            Maintainer m = new Maintainer(repoMaintainer.getMaintainerID(rst),repoMaintainer.getMaintainerName(rst));
            return m.getSlotsAvailability(selectedDay);
        }
        else{
            Object [] empty = {null, null, null, null, null, null, null, null, null};
            return empty;
        }
    }
    /**
     * 
     * @param selectedIndex indice della lista maintainers
     * @return Restituisce il manutentore memorizzato nelle posizione selectedIndex-1 della lista altrimenti se
     * selected index è maggiore di 0 o maggiore della capacità della lista o lista dei manutentori non è ancora stata
     * creata allora viene restituito null
     */
    @Override
    public Maintainer getSelectedMaintainer(int selectedIndex) {
        if(selectedIndex<0 || this.maintainers==null || selectedIndex>=this.maintainers.size())
            return null;
        return maintainers.get(selectedIndex);
    }
    /**
     * Aggiorna la disponabilità in ogni fascia oraria di uno specifico manutentore in un specifico
     * giorno della settimana corrente
     * @param maintainerID Stringa che rappresenta l'id di un manutentore 
     * @param day Stringa che rappresenta un giorno della settimana
     * @param updatedtimeSlots ArrayList Integer che contiene 8 slot con le disponibilità
     * rimanenti in minuti per ogni fascia oraria
     * @return true se l'operazione di aggiornamento sul repository va a buon fine false altrimenti. Questo
     * potrebbe accadere se non si specifica day nel formato "Monday", se il manutentore specificato non esiste e
     * se le disponibilità contenute in updatetimeSlots sono minori di 0 o maggiori di 60
     * @exception IndexOfboundsException se updatetimeSlots contiene meno di 8 slot
     */
    @Override
    public boolean updateMaintainerAvailability(String maintainerID, String day, ArrayList<Integer> updatedtimeSlots) {
        if(repoAvailability.updateMaintainerAvailabilityCurrentWeek(maintainerID, day, updatedtimeSlots.get(0), updatedtimeSlots.get(1), updatedtimeSlots.get(2),updatedtimeSlots.get(3), updatedtimeSlots.get(4), updatedtimeSlots.get(5),updatedtimeSlots.get(6),updatedtimeSlots.get(7)))
            return true;
        return false;
    }
  /**
   * Memorizza in quali fasce orarie è stata divisa l'attività assegnata
   * @param activityID Stringa che rappresenta l'id di un attività
   * @param maintainerID Stringa che rappresenta l'id di un manutentore
   * @param day Stringa che rappresenta un giorno della settimana
   * @param weekNumber Int che rappresenta il numero di una settimane in un anno
   * @param fractions un array di interi che contiene per ogni fascia oraria il numeri di minuti assegnati
   * di una specifica attività.
   * @return true se l'operazione di aggiornamento sul repository va a buon fine false altrimenti. Questo
     * potrebbe accadere se non si specifica day nel formato "Monday", se il manutentore specificato non esiste,
     * se l'attività specificata non esiste e se le frazioni di attività contenute in fraction 
     * sono minori di 0 o maggiori di 60
     * @exception IndexOfboundsException se updatetimeSlots contiene meno di 8 fasce orarie
   */
    @Override
    public boolean assignActivityFraction(String activityID, String maintainerID, String day, int weekNumber, int[] fractions) {
        if(repoAvailability.assignActivity(activityID, maintainerID, day,weekNumber, fractions[0], fractions[1], fractions[2], fractions[3], fractions[4], fractions[5], fractions[6], fractions[7]))
            return true;
        return false;
    }
    /**
     * Aggiorna sul repository e localmente le note associate ad specifica attività
     * @param activityId Stringa che rappresenta l'id di un attività
     * @param notes Stringa che rappresenta le note legate ad unìattività
     * @return true se l'aggioramento va a buon fine false altrimenti. Ciò accade se activityId non 
     * corrisponde all'identificativo di nessuna delle attività attualmente memorizzate
     */
    @Override
    public boolean updateNotes(String activityId, String notes) {
        MaintanceActivity act = this.getMaintanceActivity(activityId);
        if(repoActivity.updateWorkspceNotes(activityId, notes)){
            act.setWorkspacenotes(notes);
            return true;
        }
        return false;
    }
    /**
     * Assegna l'attività al munutentore localmente.
     * @param activityId Identificativo dell'attività che si vuole assegnare al manutentore
     * @param maintainerId Identificativo del manutentore al quale si vuole assegnare l'attività
     */
    @Override
    public void updateActivityToMaintainer(String activityId, String maintainerId) {
       MaintanceActivity act =this.getMaintanceActivity(activityId);
        act.setMaintainerID(maintainerId);
    }
    
}
