/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;

import CommonComponents.CommonTableElements.DefaultHeaderRenderer;
import CommonComponents.CommonTableElements.MaintenerColumnRenderer;
import CommonComponents.CommonTableElements.NoEditableTableModel;
import static CommonComponents.CommonTableElements.RenderingUtility.colorPicker;
import CommonComponents.CommonTableElements.SkillColumnRenderer;
import Navigator.Navigator;
import PrimoPackege.Planner;
import PrimoPackege.PlannerAbstract;
import PrimoPackege.PlannerConcrete;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Catello
 */
public class ActivityAssignmentGUI extends javax.swing.JFrame {
    private String selectedActvity;
    private int intervetionTime;
    private String selectedMaintainer;
    private PlannerAbstract planner;
    private String skillCompliance;   
    private String maintainerName;
    private boolean cleaned;
    private String selectedDayOfWeek;
    private ArrayList<Integer> numericSlots;
    private ArrayList <Integer>tempNumericSlots;
    private int [] actFractTime;
    private int codeError;
    private SelectionState state;
    private Object [] slots;
    
    /**
     * Creates new form AssignActivityGUI
     */
    
    private String indexConverter(int index){
        switch(index){
            case 0: return "8:00 - 9:00";
            case 1: return "9:00 - 10:00";
            case 2: return "10:00 - 11:00";
            case 3: return "11:00 - 12:00";
            case 4: return "12:00 - 13:00";
            case 5: return "14:00 - 15:00";
            case 6: return "15:00 - 16:00";
            case 7: return "16:00 - 17:00";
            default: return "";
        }
    }
    
    private void calculateAssignment(){
        int [] selectedIndex={};
        String output=new String();
        selectedIndex =this.jTable1.getSelectedColumns();
        int remainigTime=0;
        boolean validSelection=false;
        tempNumericSlots = new ArrayList<>();
        tempNumericSlots.addAll(numericSlots);
        for(int index : selectedIndex){
            if(index>1){
                remainigTime+=tempNumericSlots.get(index-2);
                validSelection=true;
            }
            /*else{
                validSelection=false;
                break;
            }*/
        }
        if(!validSelection){
            this.jTextArea1.setText("Invalid Selection");
            //codeError = 1;
            this.state = new NoMinutesCells();
            return;
        }
        if(remainigTime < this.intervetionTime){
            this.jTextArea1.setText("Invalid Selection");
            //codeError = 2;
            this.state=new NotEnoughTime();
            return;
        }
        int timeToAssing=this.intervetionTime;
        this.actFractTime=new int[8];
        String selectionInfo = "The activity will be split as the following fashon:\n";
        for(int index: selectedIndex){
            if(tempNumericSlots.get(index-2)>=timeToAssing){
            tempNumericSlots.set(index-2,tempNumericSlots.get(index-2) - timeToAssing);
            actFractTime[index-2]=timeToAssing;
            System.out.println(tempNumericSlots);
            String interval=indexConverter(index-2);
            selectionInfo+=interval+": "+timeToAssing+" min\n";
            for(int j=0;j<8;j++)
                System.out.print(actFractTime[j]+", ");
            break;
            }
            else{
                timeToAssing-=tempNumericSlots.get(index-2);
                actFractTime[index-2]=tempNumericSlots.get(index-2);
                String interval=indexConverter(index-2);
                selectionInfo+=interval+": "+ tempNumericSlots.get(index-2)+" min\n";
                tempNumericSlots.set(index-2, 0);    
            }
        }
        this.jTextArea1.setText(selectionInfo);
        //codeError=-1;
        this.state =new CorrectSelection(planner, selectedMaintainer, selectedDayOfWeek, selectedActvity, parseInt(this.weekNumber1.getText().trim()), actFractTime, tempNumericSlots, this);
    }
    
    public ActivityAssignmentGUI(PlannerAbstract p, String maintenerName,String skillCompliance,String selectedDayWeek,String activityInfo, String notes,LocalDate selectedDate, String selectedActvity, Color percentageColor, String percentage, String selectedMaintainerId, int estimatedActivityTime) {
        this.selectedActvity=selectedActvity;
        this.numericSlots=new ArrayList<>();
        this.planner=p;
        this.selectedDayOfWeek=selectedDayWeek;
        this.cleaned = false;
        this.intervetionTime=estimatedActivityTime;
        this.maintainerName=maintenerName;
        this.skillCompliance=skillCompliance;
        this.selectedMaintainer = selectedMaintainerId;
        this.actFractTime=new int[8];
        //this.codeError=0;
        this.state = new NoSelection();
        initComponents();
        this.setLocationRelativeTo(null);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int [] selectedIndex={};
                String output=new String();
                if((!e.getValueIsAdjusting()) && (!ActivityAssignmentGUI.this.cleaned)){
                        selectedIndex = ActivityAssignmentGUI.this.jTable1.getSelectedColumns();
                        for(int index : selectedIndex)
                            output+=index+", ";
                        //System.out.println(output);
                        calculateAssignment();
                }
        if(ActivityAssignmentGUI.this.cleaned)
            ActivityAssignmentGUI.this.cleaned=false;
                
        }
        
        });
        
        this.jTextPane1.setText(notes);
        this.dayWeek1.setDayWeek(selectedDate);
        this.activityInfoLabel1.setText(activityInfo);
        this.labelForWeekDay1.setText(selectedDayWeek);
        this.jLabel1.setBackground(percentageColor);
        this.jLabel1.setText(percentage);
        this.labelLight2.setText("AVAILABILY OF "+this.maintainerName.toUpperCase());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBase1 = new CommonComponents.PanelBase();
        forwardButton1 = new CommonComponents.ForwardButton();
        forwardButton2 = new CommonComponents.ForwardButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        labelLight1 = new CommonComponents.LabelLight();
        jPanel1 = new javax.swing.JPanel();
        labelLight2 = new CommonComponents.LabelLight();
        jLabel1 = new javax.swing.JLabel();
        labelForWeekNumber1 = new CommonComponents.LabelForWeekNumber();
        labelForWeekDay1 = new CommonComponents.LabelForWeekDay();
        weekNumber1 = new CommonComponents.WeekNumber();
        dayWeek1 = new CommonComponents.DayWeek();
        activtyAssign1 = new CommonComponents.ActivtyAssign();
        activityInfoLabel1 = new CommonComponents.ActivityInfoLabel();
        closeButton1 = new CommonComponents.CloseButton();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        operationButton1 = new CommonComponents.OperationButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        slots=planner.getMaintainerDailyAvailability(this.selectedMaintainer, this.selectedDayOfWeek);
        for (Object slot :slots){
            String [] s=slot.toString().trim().split(" ");
            this.numericSlots.add(parseInt(s[0]));
        }
        jTable1 = new CommonComponents.MaintainerDailyTable(planner, this.maintainerName,this.skillCompliance,slots);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        forwardButton1.setText("SEND");
        forwardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButton1ActionPerformed(evt);
            }
        });

        forwardButton2.setText("CLEAR SELECTION");
        forwardButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButton2ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane2.setViewportView(jTextPane1);

        jPanel1.setBackground(new java.awt.Color(245, 171, 53));
        jPanel1.setPreferredSize(new java.awt.Dimension(402, 50));

        labelLight2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelLight2.setText("AVAILABILITY OF PIPPO");

        jLabel1.setBackground(new java.awt.Color(255, 255, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("80%");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        weekNumber1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        dayWeek1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        operationButton1.setText("Go back to maintainer selection");
        operationButton1.setFont(new java.awt.Font("Cooper Black", 0, 14)); // NOI18N
        operationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jScrollPane4.setBorder(null);
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout panelBase1Layout = new javax.swing.GroupLayout(panelBase1);
        panelBase1.setLayout(panelBase1Layout);
        panelBase1Layout.setHorizontalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelLight1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBase1Layout.createSequentialGroup()
                                        .addComponent(forwardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBase1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelForWeekDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(activityInfoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelForWeekDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(forwardButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(forwardButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void forwardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButton1ActionPerformed
        //String [] fraction= content.split(" ");
        
       /* JPanel panel=new JPanel();
        panel.setOpaque(true);
        panel.setBackground(new Color(248,148,6));
        if(codeError==1){
            JOptionPane.showMessageDialog(panel,  "You must select the cells that contain time ","Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
        if(codeError==2){
            JOptionPane.showMessageDialog(panel,  "Not enough time","Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
        if(codeError==-1){
            boolean okOp1=planner.updateMaintainerAvailability(this.selectedMaintainer, this.selectedDayOfWeek, this.tempNumericSlots);
            boolean okOp2=planner.assignActivityFraction(this.selectedActvity, this.selectedMaintainer, this.selectedDayOfWeek, parseInt(this.weekNumber1.getText().trim()), actFractTime);
            if(okOp1 && okOp2){
                planner.updateActivityToMaintainer(this.selectedActvity, this.selectedMaintainer);
                JOptionPane.showMessageDialog(panel,  "Now you will return to select other actvity","Activity assigned succefully", JOptionPane.INFORMATION_MESSAGE);
                Navigator nav = Navigator.getInstance(planner);
                nav.changeToSelectActivityWindow(this);
            }
            else
                 JOptionPane.showMessageDialog(panel,  "Operazione sul repository non riusciuta","Errore", JOptionPane.ERROR_MESSAGE);
        }*/
       this.state.confirmSelection();
    }//GEN-LAST:event_forwardButton1ActionPerformed

    private void forwardButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButton2ActionPerformed
        this.cleaned=true;
        //this.codeError=0;
        this.state = new NoSelection();
        this.jTextArea1.setText("");
        this.jTable1.clearSelection();
    }//GEN-LAST:event_forwardButton2ActionPerformed

    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
        Navigator nav=Navigator.getInstance(planner);
        nav.changeToMaintainerSelectionWindow(this);
        /*MaintainerSelectionGUI maintainerSelection= new MaintainerSelectionGUI(planner,selectedActvity,this.activityInfoLabel1.getText(),this.jTextPane1.getText());
        maintainerSelection.setVisible(true);
        maintainerSelection.pack();
        maintainerSelection.setLocationRelativeTo(null);
        maintainerSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_operationButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActivityAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActivityAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActivityAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActivityAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ActivityAssignmentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.ActivityInfoLabel activityInfoLabel1;
    private CommonComponents.ActivtyAssign activtyAssign1;
    private CommonComponents.CloseButton closeButton1;
    private CommonComponents.DayWeek dayWeek1;
    private CommonComponents.ForwardButton forwardButton1;
    private CommonComponents.ForwardButton forwardButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private CommonComponents.MaintainerDailyTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private CommonComponents.LabelForWeekDay labelForWeekDay1;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber1;
    private CommonComponents.LabelLight labelLight1;
    private CommonComponents.LabelLight labelLight2;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.WeekNumber weekNumber1;
    // End of variables declaration//GEN-END:variables
}
