////////////////////////////////////////////////////////////////////////////
// Module : GenMutantsMain.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui;

import mujava.MutationSystem;

/**
 * <p>GUI program (main interface) for generating mutants </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class GenMutantsMain
{
   private static final long serialVersionUID = 102L;

   /** Panel for generating mutants. */
   MutantsGenPanel genPanel;

   TraditionalMutantsViewerPanel tvPanel;

   public GenMutantsMain()
   {
     try
     {
       genPanel = new MutantsGenPanel();
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
   }
}
