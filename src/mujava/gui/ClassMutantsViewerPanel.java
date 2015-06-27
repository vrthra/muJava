// Upsorn (05/18/2009): 
//     Note on NullPointerException & error on line 55 of GenMutantsMain.java 
//     - accidently initialize some variable? - FileTable? 
//     so that the list is empty??



////////////////////////////////////////////////////////////////////////////
// Module : ClassMutantsViewerPanel.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import mujava.MutationSystem;
import java.util.Vector;
import mujava.gui.util.CMSummaryTableModel;

/**
 * <p>Template for viewing class-level mutants: show original source code,
 *    source code of the selected mutants, summary of mutants </p>  
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
 */

public class ClassMutantsViewerPanel extends MutantsViewerPanel 
{
   private static final long serialVersionUID = 101L;

   public ClassMutantsViewerPanel() 
   {
      try 
      {
         jbInit();
      } catch(Exception ex) 
      {
         ex.printStackTrace();
      }
   }


   /**
    * Initialization ClassMutantsViewerPanel
    * @see mujava.gui.MutantsViewerPanel#jbInit()
    */
   void jbInit() throws Exception 
   {
      this.setLayout(new FlowLayout());

      StyleConstants.setForeground(red_attr, Color.red);
      StyleConstants.setForeground(blue_attr, Color.blue);
      StyleConstants.setForeground(black_attr, Color.black);

      /** summary table: containing the numbers of mutants by each
       *  class-level mutation operators along with the total number
       *  of mutants generated 
       */
      JPanel leftPanel = new JPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
      JLabel summaryL= new JLabel("* Summary *");
      leftPanel.add(summaryL);
      initSummaryTable();
      summaryTable.setEnabled(false);
      summaryPanel.getViewport().add(summaryTable);
      setSummaryTableSize();
      leftPanel.add(summaryPanel);
      leftPanel.add(totalLabel);
      summaryPanel.setBorder(new EmptyBorder(1, 1, 1, 1));

      JPanel rightPanel = new JPanel();
      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

      /** ComboBox for class (in which mutants will be generated) selection **/
      JPanel selectClassPanel = new JPanel();
      selectClassPanel.setLayout(new FlowLayout());
      JLabel selectClassLabel = new JLabel("   Select a class : ");
      selectClassPanel.add(selectClassLabel);
      //refreshEnv();
      classCB.setEditable(false);
      selectClassPanel.add(classCB);
      classCB.setPreferredSize(new Dimension(550, 25));
      classCB.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
             updateClassComboBox();
         }
      });

      /** show a list of mutants to be selected for viewing -- 
       *  click on the mutant to display the original source and
       *  mutated code 
       */      
      JPanel contentPanel = new JPanel();
      JScrollPane leftContentSP = new JScrollPane();
      leftContentSP.getViewport().add(mList, null);
      leftContentSP.setPreferredSize(new Dimension(100, 580));
      contentPanel.add(leftContentSP);
      mList.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(MouseEvent e)
         {
            mList_mouseClicked(e);
         }
      });

      JPanel rightContentPanel = new JPanel();
      rightContentPanel.setLayout(new BoxLayout(rightContentPanel, BoxLayout.PAGE_AXIS));

      /** show the line mutated */
      changeTF.setPreferredSize(new Dimension(550, 40));
      rightContentPanel.add(changeTF);
    
      /** show the source code of the original file and the mutant */
      originalSP.setPreferredSize(new Dimension(550, 270));
      mutantSP.setPreferredSize(new Dimension(550, 270));
      originalSP.setBorder(new TitledBorder("Original"));
      mutantSP.setBorder(new TitledBorder("Mutant"));
      mutantSP.getViewport().add(mutantTP, null);
      originalSP.getViewport().add(originalTP, null);
      rightContentPanel.add(originalSP);
      rightContentPanel.add(mutantSP);
      contentPanel.add(rightContentPanel);

      rightPanel.add(selectClassPanel);
//    rightPanel.add(selectMethodPanel);
      rightPanel.add(Box.createRigidArea(new Dimension(10, 10)));
      rightPanel.add(contentPanel);

      contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

      leftPanel.setPreferredSize(new Dimension(100, 500));
      this.add(leftPanel);
      this.add(rightPanel);

      refreshEnv();
   }

   /** Change contents for the newly selected class */
   void updateClassComboBox()
   {
      Object item = classCB.getSelectedItem();
      if (item == null)     
         return;
   
      target_dir = item.toString();
      if (target_dir == null)     
         return;
      
      if (isProperClass(target_dir))
      {
         MutationSystem.setJMutationPaths(target_dir);
         updateContents();
      }
      else
      {
         clearSourceContents();
         mList.setListData(new Vector());
         mList.repaint();
         showGeneratedMutantsNum(null);
      }
      this.repaint();
   }

   void setMutationType()
   {
      MutationSystem.MUTANT_PATH = MutationSystem.CLASS_MUTANT_PATH;
   }

   void setSummaryTableSize()
   {
      int temp = MutationSystem.cm_operators.length * 20;
      summaryPanel.setPreferredSize(new Dimension(150, temp));
      summaryPanel.setMaximumSize(new Dimension(150, temp));
   }

   void initSummaryTable()
   {
      CMSummaryTableModel tmodel = new CMSummaryTableModel();
      summaryTable = new JTable(tmodel);
      adjustSummaryTableSize(summaryTable, tmodel);
   }

   int getMutantType()
   {
      return MutationSystem.CM;
   }

   /**
    * Set a location where class-level mutants will be stored
    */
   void setMutantPath()
   {
      MutationSystem.MUTANT_PATH = MutationSystem.CLASS_MUTANT_PATH;
   }

   /**
    * Retrieve a path containing class-level mutants
    */
   String getMutantPath()
   {
      return MutationSystem.CLASS_MUTANT_PATH;
   }

   // name:   
   //      .
   void printGeneratedMutantNum(String[] operators, int[] num)
   {
    /*
    String[] operators = MutationSystem.cm_operators; //    
    int[] num = new int[operators.length]; //     
    for(int i=0; i<operators.length; i++){ // num  
      num[i] = 0;
    }
    if(name!=null){
      for(int i=0;i<name.length;i++){
        for(int j=0; j<operators.length;j++){ // ==>
         if(name[i].indexOf(operators[j]+"_")==0){ num[j]++;}  // ==>
        }
      }
    }
    */
    //   
      int total = 0;
      CMSummaryTableModel myModel = (CMSummaryTableModel)(summaryTable.getModel());
      for (int i=0; i<operators.length; i++)
      {
         myModel.setValueAt(new Integer(num[i]), i, 1);
         total = total + num[i];
      }
      totalLabel.setText("Total : "+ total);
   }
}

