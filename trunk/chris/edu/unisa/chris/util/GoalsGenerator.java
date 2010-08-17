/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2002-2010,                                                *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *   This software was developed during my PhD studies at:               *
 *                                                                       *
 *   Knowledge Based Intelligent Engineering Systems Centre (KES)        *
 *   School of Electrical and Information Engineering                    *
 *   University of South Australia                                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of CHRIS.                                         *
 *                                                                       *
 *   CHRIS is free software: you can redistribute it and/or              *
 *   modify it under the terms of the GNU Lesser General Public Licence  *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   CHRIS is distributed in the hope that it will be useful,            *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU Lesser General Public License for more details.                 *
 *                                                                       *
 *   You should have received a copy of the GNU Lesser General Public    *
 *   License along with CHRIS.                                           *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */



/*
 * GoalsGenerator.java
 *
 * Created on 4 May 2005, 13:58
 */

package edu.unisa.chris.util;
import java.util.*;
import java.io.*;
import javax.swing.*;
import edu.unisa.chris.decision.GoalInfo;
/**
 *
 * @author  Christos Sioutis
 */
public class GoalsGenerator extends javax.swing.JFrame {
    GoalTree goalTree = new GoalTree();
    List goalLists = new LinkedList();
    List labels = new LinkedList();
    JFileChooser chooser = new JFileChooser();
    
    /** Creates new form GoalsGenerator */
    public GoalsGenerator() {
        for(int i=0; i< labels.size(); i++){
            labels.add(new JLabel("Level "+i));
        }
        try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception e){System.out.println(e);}
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        scrlGoals = new javax.swing.JScrollPane();
        pnlGoals = new javax.swing.JPanel();
        pnlInfo = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblParent = new javax.swing.JLabel();
        cmbParent = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        lblPriority = new javax.swing.JLabel();
        cmbPriority = new javax.swing.JComboBox();
        cmdAdd = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdEdit = new javax.swing.JButton();
        cmdUp = new javax.swing.JButton();
        cmdDown = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        mnuExport = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GoalsGenerator");
        setName("GoalsGenerator");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pnlGoals.setLayout(new java.awt.GridLayout(1, 5, 1, 0));

        scrlGoals.setViewportView(pnlGoals);

        getContentPane().add(scrlGoals, java.awt.BorderLayout.CENTER);

        pnlInfo.setLayout(new java.awt.GridBagLayout());

        lblName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlInfo.add(lblName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlInfo.add(txtName, gridBagConstraints);

        lblParent.setText("Parent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlInfo.add(lblParent, gridBagConstraints);

        cmbParent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "_Agent_" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlInfo.add(cmbParent, gridBagConstraints);

        jLabel1.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlInfo.add(jLabel1, gridBagConstraints);

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Procedural", "Parallel", "Optional", "Resident", "Either", "IfElse" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlInfo.add(cmbType, gridBagConstraints);

        lblPriority.setText("Priority");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlInfo.add(lblPriority, gridBagConstraints);

        cmbPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlInfo.add(cmbPriority, gridBagConstraints);

        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlInfo.add(cmdAdd, gridBagConstraints);

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlInfo.add(cmdDelete, gridBagConstraints);

        cmdEdit.setText("Edit");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlInfo.add(cmdEdit, gridBagConstraints);

        cmdUp.setText("Up");
        cmdUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlInfo.add(cmdUp, gridBagConstraints);

        cmdDown.setText("Down");
        cmdDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDownActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlInfo.add(cmdDown, gridBagConstraints);

        getContentPane().add(pnlInfo, java.awt.BorderLayout.SOUTH);

        mnuFile.setText("File");
        mnuNew.setText("New");
        mnuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewActionPerformed(evt);
            }
        });

        mnuFile.add(mnuNew);

        mnuOpen.setText("Open");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });

        mnuFile.add(mnuOpen);

        mnuSave.setText("Save");
        mnuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveActionPerformed(evt);
            }
        });

        mnuFile.add(mnuSave);

        mnuSaveAs.setText("Save As");
        mnuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveAsActionPerformed(evt);
            }
        });

        mnuFile.add(mnuSaveAs);

        mnuExport.setText("Export");
        mnuExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExportActionPerformed(evt);
            }
        });

        mnuFile.add(mnuExport);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });

        mnuFile.add(mnuExit);

        jMenuBar1.add(mnuFile);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-600)/2, (screenSize.height-300)/2, 600, 300);
    }//GEN-END:initComponents

    private void mnuExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExportActionPerformed
        int returnVal = chooser.showSaveDialog(this);    
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Saving: " + file.getName() + ".");
            try{
                FileWriter writer = new FileWriter(file);
                writer.write(goalTree.toGraph());
                writer.flush();
                writer.close();
            }
            catch(IOException e){
                System.out.println(e);
            }
        }        
    }//GEN-LAST:event_mnuExportActionPerformed

    private void mnuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveAsActionPerformed
        int returnVal = chooser.showSaveDialog(this);    
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Saving: " + file.getName() + ".");
            try{
                FileWriter writer = new FileWriter(file);
                writer.write(goalTree.toString());
                writer.flush();
                writer.close();
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_mnuSaveAsActionPerformed

    private void mnuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveActionPerformed
        File file = chooser.getSelectedFile();
        if(file==null){
            int returnVal = chooser.showSaveDialog(this);    
            if (returnVal == JFileChooser.APPROVE_OPTION) 
                file = chooser.getSelectedFile();
        }
        if(file!=null){
            System.out.println("Saving: " + file.getName() + ".");
            try{
                FileWriter writer = new FileWriter(file);
                writer.write(goalTree.toString());
                writer.flush();
                writer.close();
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_mnuSaveActionPerformed

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            System.out.println("Opening: " + file.getName() + ".");            
            try{
                StringBuffer sb = new StringBuffer();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while(line!=null){
                    sb.append(line);
                    line = reader.readLine();
                }
                reader.close();
                if(sb.length()>0){
                    goalTree = new GoalTree();
                    goalTree.parse(sb.toString());
                    clearGoals();
                    updateCmbParent();
                }
                    
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void cmdDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDownActionPerformed
        List selections = reTokenize(getSelections());
        if(selections.size()>0){
            if(selections.size()==1){
                goalTree.down(((String)selections.get(0)), "_Agent_", null);
            }
            else if(selections.size()==2)
                goalTree.down(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),"_Agent_");
            else
                goalTree.down(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),((String)selections.get(selections.size()-3)));
            clearGoals();
            updateCmbParent();
        }
    }//GEN-LAST:event_cmdDownActionPerformed

    private void cmdUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpActionPerformed
        List selections = reTokenize(getSelections());
        if(selections.size()>0){
            if(selections.size()==1){
                goalTree.up(((String)selections.get(0)), "_Agent_", null);
            }
            else if(selections.size()==2)
                goalTree.up(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),"_Agent_");
            else
                goalTree.up(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),((String)selections.get(selections.size()-3)));
            clearGoals();
            updateCmbParent();
        }        
    }//GEN-LAST:event_cmdUpActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        if(txtName.getText().length()==0)
            return;
        String parent = (String)cmbParent.getSelectedItem();
        String name = txtName.getText();
        int priority = Integer.parseInt((String)cmbPriority.getSelectedItem());
        int type = cmbType.getSelectedIndex();
        
        List selections = reTokenize(getSelections());
        if(selections.size()>0){
            if(selections.size()==1){
                goalTree.edit(((String)selections.get(0)), "_Agent_", null, name, priority, type);
            }
            else if(selections.size()==2)
                goalTree.edit(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),"_Agent_",name,priority,type);
            else
                goalTree.edit(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),((String)selections.get(selections.size()-3)),name,priority,type);
            clearGoals();
            updateCmbParent();
        }
        
    }//GEN-LAST:event_cmdEditActionPerformed
    
    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        List selections = reTokenize(getSelections());
        if(selections.size()>0){
            if(selections.size()==1){
                goalTree.remove(((String)selections.get(0)), "_Agent_", null);
            }
            else if(selections.size()==2)
                goalTree.remove(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),"_Agent_");
            else
                goalTree.remove(((String)selections.get(selections.size()-1)),((String)selections.get(selections.size()-2)),((String)selections.get(selections.size()-3)));
            clearGoals();
            updateCmbParent();
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed
    
    private List getSelections(){
        List selections = new LinkedList();
        for(int i=0; i<goalLists.size(); i++){
            Object selection = ((JList)goalLists.get(i)).getSelectedValue();
            if(selection==null)
                return selections;
            selections.add(selection);
        }
        return selections;
    }
    
    private List reTokenize(List selections){
        List list = new LinkedList();
        for(int i=0; i<selections.size(); i++){
            list.add(reTokenize(selections.get(i)));
        }
        return list;
    }
    
    private String reTokenize(Object object){
        return (new StringTokenizer(object.toString())).nextToken();
    }
    
    
    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        if(txtName.getText().length()==0)
            return;
        String parent = (String)cmbParent.getSelectedItem();
        goalTree.add(txtName.getText(),
        (String)cmbParent.getSelectedItem(),
        Integer.parseInt((String)cmbPriority.getSelectedItem()),
        cmbType.getSelectedIndex());
        clearGoals();
        updateCmbParent();
        
    }//GEN-LAST:event_cmdAddActionPerformed
    
    private void updateInfo(){
        List selections = reTokenize(getSelections());
        GoalTree.Node goal=null;
        String name=null;
        String parent=null;
        if(selections.size()>0){
            if(selections.size()==1){
                parent = "_Agent_";
                goal = goalTree.findNode(((String)selections.get(0)), parent, goalTree.root);
                
            }
            else{
                name = ((String)selections.get(selections.size()-1));
                parent = ((String)selections.get(selections.size()-2));
                goal = goalTree.findNode(name,parent,goalTree.root);
            }
        }
        if(goal!=null && parent!=null){
            txtName.setText(goal.name);
            cmbParent.setSelectedItem(parent);
            cmbType.setSelectedIndex(goal.type);
            cmbPriority.setSelectedItem(""+goal.priority);
        }
        
    }
    
    
    private void updateCmbParent(){
        cmbParent.removeAllItems();
        List goals = goalTree.getAllGoals();
        for(int i=0; i<goals.size(); i++){
            cmbParent.addItem(goals.get(i));
        }
    }
    
    private void clearGoals(){
        if(goalLists.size()==0){
            addList("_Agent_",null);
        }
        else{
            goalLists.clear();
            pnlGoals.removeAll();
            addList("_Agent_",null);
        }
    }
    
    private void updateGoals(JList source){
        int index = goalLists.indexOf(source);
        for(int i=index; i<goalLists.size(); i++){
            Object selectedParent = ((JList)goalLists.get(i)).getSelectedValue();
            if(selectedParent==null)
                return;
            else{
                Object selectedGrandParent;
                if(i>0)
                    selectedGrandParent = ((JList)goalLists.get(i-1)).getSelectedValue();
                else
                    selectedGrandParent = "_Agent_";
                if(selectedGrandParent==null)
                    return;
                else{
                    String parent = (new StringTokenizer(selectedParent.toString())).nextToken();
                    String grandParent = (new StringTokenizer(selectedGrandParent.toString())).nextToken();
                    
                    if(goalTree.getChildren(parent,grandParent).size()>0){
                        if(goalLists.size()==i+1)
                            addList(parent,grandParent);
                        else
                            updateList(i+1,parent,grandParent);
                    }
                    else{
                        for(int j=i+1; j<goalLists.size(); j++)
                            removeList(j);
                    }
                    
                }
            }
        }
    }
    
    
    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {
        updateGoals((JList)evt.getSource());
        updateInfo();
    }
    
    private void updateList(int index, String name, String parent){
        JList list = (JList)goalLists.get(index);
        list.setListData(new Vector(goalTree.getChildren(name,parent)));
    }
    
    private void removeList(int index){
        pnlGoals.remove((JList)goalLists.remove(index));
        pnlGoals.repaint();
    }
    
    private void addList(String name, String parent){
        JList newList = new JList();
        goalLists.add(newList);
        pnlGoals.add(newList);
        newList.setListData(new Vector(goalTree.getChildren(name,parent)));
        newList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
    }
    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed
    
    private void mnuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewActionPerformed
        goalTree = new GoalTree();
        clearGoals();
        updateCmbParent();
    }//GEN-LAST:event_mnuNewActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new GoalsGenerator().show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbParent;
    private javax.swing.JComboBox cmbPriority;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdDown;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblParent;
    private javax.swing.JLabel lblPriority;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuExport;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JPanel pnlGoals;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JScrollPane scrlGoals;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
    
}
