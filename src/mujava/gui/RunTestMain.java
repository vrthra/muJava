////////////////////////////////////////////////////////////////////////////
// Module : RunTestMain.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import mujava.MutationSystem;
import mujava.util.Debug;

/**
 * <p>GUI program for running mutants against test cases</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class RunTestMain extends JFrame
{
   private static final long serialVersionUID = 107L;

   JTabbedPane testTabbedPane = new JTabbedPane();

   /** Panel for running mutants */
   RunTestPanel runPanel;

   /** Panel for viewing detail of class mutants */
   ClassMutantsViewerPanel cvPanel;

   /** Panel for viewing detail of traditional mutants */
   TraditionalMutantsViewerPanel tvPanel;

   /** Panel for viewing detail of exceltion-related mutants (not used) */
   //ExceptionMutantsViewerPanel evPanel;

   public RunTestMain() 
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

  /** <p> Main program for running mutants (no parameter required for run).</p>
   *  <p>- supporting functions: (1) selection of test suite of Java class format,
   *  (2) selection of a Java f whose mutants are to run </p> */
   public static void main(String[] args) 
   {
      Debug.setDebugLevel(2);
      MutationSystem.setJMutationStructure();
      RunTestMain main = new RunTestMain();
      main.pack();
      main.setVisible(true);
   }

   private void jbInit() throws Exception 
   {
      runPanel = new RunTestPanel();
      cvPanel = new ClassMutantsViewerPanel();
      tvPanel = new TraditionalMutantsViewerPanel();
      //evPanel = new ExceptionMutantsViewerPanel();

      this.getContentPane().add(testTabbedPane, BorderLayout.CENTER);
      testTabbedPane.add("TestCase Runner", runPanel);
      testTabbedPane.add("Traditional Mutants Viewer", tvPanel);
      testTabbedPane.add("Class Mutants Viewer", cvPanel);
      //testTabbedPane.add("Exception Mutants Viewer", evPanel);

      this.addWindowListener(new java.awt.event.WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            this_windowClosing(e);
         }
      });
   }
  
   void this_windowClosing(WindowEvent e)
   {
      System.exit(0);
   }
}
