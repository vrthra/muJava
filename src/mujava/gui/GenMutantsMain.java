////////////////////////////////////////////////////////////////////////////
// Module : GenMutantsMain.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui;

import javax.swing.*;
import java.awt.event.*;
import mujava.MutationSystem;

/**
 * <p>GUI program (main interface) for generating mutants </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class GenMutantsMain extends JFrame 
{
   private static final long serialVersionUID = 102L;

   JTabbedPane mutantTabbedPane = new JTabbedPane();

   /** Panel for generating mutants. */
   MutantsGenPanel genPanel;

   /** Panel for viewing details of class mutants. */
   ClassMutantsViewerPanel cvPanel;

   /** Panel for viewing details of traditional mutants.  */
   TraditionalMutantsViewerPanel tvPanel;

   public GenMutantsMain() 
   {
      try 
      {
         jbInit();
      }
      catch (Exception e) 
      {
         e.printStackTrace();
      }
   }

   /** <p> Main program for generating mutants (no parameter required for run).</p>
    *  <p>- supporting functions: 
    *       (1) selection of Java source files to apply,
    *       (2) selection of mutation operators to apply </p> 
 * @throws Exception 
    */
   public static void main (String[] args) throws Exception 
   { 
      System.out.println("The main method starts");
      MutationSystem.setJMutationStructure();
      MutationSystem.recordInheritanceRelation();
      GenMutantsMain main = new GenMutantsMain();
      main.pack();
      main.setVisible(true);
   } 

   /** <p> Initialize GenMutantsMain </p> */
   private void jbInit() throws Exception 
   {
      genPanel = new MutantsGenPanel(this);
      cvPanel = new ClassMutantsViewerPanel();
      tvPanel = new TraditionalMutantsViewerPanel();

      mutantTabbedPane.add("Mutants Generator", genPanel);
      mutantTabbedPane.add("Traditional Mutants Viewer", tvPanel);
      mutantTabbedPane.add("Class Mutants Viewer", cvPanel);
      this.getContentPane().add(mutantTabbedPane);

      this.addWindowListener( new java.awt.event.WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            this_windowClosing(e);
         }
      } );
   }

   void this_windowClosing (WindowEvent e)
   {
      System.exit(0);
   }
}
