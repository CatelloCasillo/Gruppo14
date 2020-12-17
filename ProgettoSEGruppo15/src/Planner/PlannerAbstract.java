/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planner;

import MaintenanceActivity.MaintanceActivityFactory;
import MaintenanceActivity.MaintanceActivity;
import Maintainer.Maintainer;
import MaintenanceActivity.Site;
import Repository.RepositoryActivity;
import Repository.RepositoryActivityInterface;
import Repository.RepositoryAvailability;
import Repository.RepositoryAvailabilityInterface;
import Repository.RepositoryMaintainer;
import Repository.RepositoryMaintainerInterface;
import Repository.RepositorySite;
import Repository.RepositorySiteInterface;
import Repository.RepositoryUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriella
 */
public abstract class PlannerAbstract implements PlannerInterface {
protected RepositoryActivityInterface repoActivity;
protected RepositoryAvailabilityInterface repoAvailability;
protected RepositoryMaintainerInterface repoMaintainer;
protected RepositorySiteInterface repoSite;
protected RepositoryUtilities repoUtilities;

protected ArrayList<MaintanceActivity> activityList;
protected ArrayList<Site> siteList;
protected ArrayList<Maintainer> maintainers;

    protected PlannerAbstract() {
        this.repoActivity= new RepositoryActivity();
        this.repoAvailability= new RepositoryAvailability();
        this.repoMaintainer= new RepositoryMaintainer();
        this.repoSite= new RepositorySite();
        this.repoUtilities= new RepositoryUtilities();
        this.initSiteList();
        this.initActivityList();
    }

    protected final void initActivityList(){
        this.activityList = new ArrayList<>();
        MaintanceActivityFactory.Category categoria;
        try {
            ResultSet rst = repoActivity.getInformationOfMaintenanceActivity();
            while (rst.next()) {
                String id = repoActivity.getActivityID(rst);
                
                String siteID = repoSite.getSiteID(rst);
                //trovare il site nella lista
                //System.out.println("siteId = "+siteID);
                Site site = this.findSiteInList(siteID,this.siteList);
                //--
                
                String procedureID = repoUtilities.getProcedureID(rst);
                String fileSMP = repoUtilities.getFileSMP(rst);
                String workspace= repoActivity.getWorkSpacenotes(rst);
                int weekNumber = repoActivity.getActivityWeekNumber(rst);
                int intervationTimeNumber = repoActivity.getActivityInterventionTime(rst);
                String typology = repoActivity.getActivityTypology(rst);
                String activityDescription = repoActivity.getActivityDescription(rst);
                boolean interruptible = repoActivity.isInterruptibleActivity(rst);
                String maintainerID=repoMaintainer.getMaintainerID(rst);
                String category= repoActivity.getPlannedActivity(rst).toUpperCase();
                if(category.equals("PLANNED")){
                    categoria= MaintanceActivityFactory.Category.PLANNED;
                }else {
                     categoria= MaintanceActivityFactory.Category.EWO;
                }
                MaintanceActivity mainActivity= MaintanceActivityFactory.make(categoria, id, site, typology, activityDescription, intervationTimeNumber, interruptible, weekNumber, procedureID, fileSMP, maintainerID, workspace);
                this.activityList.add(mainActivity);
           } 
        } catch (SQLException ex) {
          Logger.getLogger(PlannerAbstract.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    protected final void initSiteList(){
      this.siteList = new ArrayList<>();
      try {
        ResultSet rst = repoSite.getSiteTable();
        while (rst.next()) {
          String id = repoSite.getSiteID(rst);
          String factory = repoSite.getFactorySite(rst);
          String area = repoSite.getAreaSite(rst);
          Site site = new Site(id, factory, area);
          this.siteList.add(site);
        } 
      } catch (SQLException ex) {
          Logger.getLogger(PlannerAbstract.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }
@Override
    public Site findSiteInList(String idSite, ArrayList<Site> siteList) {
        String [] id= idSite.split(":");
          for (Site s : siteList) {
              if (s.getId().trim().equals(id[0].trim())) {
                  return s;
              }
          }
          return null;
    }
}
