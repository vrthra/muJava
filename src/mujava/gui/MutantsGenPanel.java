////////////////////////////////////////////////////////////////////////////
// Module : MutantsGenPanel.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui;

import java.util.*;
import java.io.*;
import mujava.*;
import mujava.util.Debug;


/**
 * <p>Template for generating mutants</p>
 * <p>  supporting function:
 *      (1) choose Java program(s) to be tested,
 *      (2) choose mutation operator(s) to applied </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class MutantsGenPanel
{
  private static final long serialVersionUID = 103L;

  // Upsorn (05/18/2009): add button for mutation operators' description
  //   JButton descriptionB = new JButton("Mutation operators\' description");


  public MutantsGenPanel()
  {
    String file_list[] = (String[]) MutationSystem.getNewTragetFiles().toArray(new String[0]);
    if (file_list == null || file_list.length == 0)
    {
      System.err.println("[ERROR] No class is selected.");
      return;
    }

    String[] class_ops = MutationSystem.cm_operators;
    String[] traditional_ops = MutationSystem.tm_operators;

    if ( (class_ops == null || class_ops.length == 0) &&
        (traditional_ops == null || traditional_ops.length == 0) )
    {
      System.out.println("[Error] no operators is selected. ");
      return;
    }

    for (int i=0; i<file_list.length; i++)
    {
      // file_name = ABSTRACT_PATH - MutationSystem.SRC_PATH
      // For example: org/apache/bcel/Class.java
      String file_name = file_list[i];
      try
      {
        //System.out.println(i + " : " + file_name);
        // [1] Examine if the target class is interface or abstract class
        //     In that case, we can't apply mutation testing.

        // Generate class name from file_name
        String temp = file_name.substring(0,file_name.length()-".java".length());
        String class_name="";

        for (int j=0; j<temp.length(); j++)
        {
          if ( (temp.charAt(j) == '\\') || (temp.charAt(j) == '/') )
          {
            class_name = class_name + ".";
          }
          else
          {
            class_name = class_name + temp.charAt(j);
          }
        }

        int class_type = MutationSystem.getClassType(class_name);

        if (class_type == MutationSystem.NORMAL)
        {   // do nothing?
        }
        else if (class_type == MutationSystem.MAIN)
        {
          System.out.println(" -- "  + file_name + " class contains 'static void main()' method.");
          System.out.println("    Pleas note that mutants are not generated for the 'static void main()' method");
        }
        //Added on 1/19/2013, no mutants will be generated for a class having only one main method
        else if(class_type == MutationSystem.MAIN_ONLY){
          System.out.println("Class " + file_name + " has only the 'static void main()' method and no mutants will be generated.");
          break;
        }
        else
        {
          switch (class_type)
          {
            case MutationSystem.INTERFACE :
              System.out.println(" -- Can't apply because " + file_name+ " is 'interface' ");
              break;
            case MutationSystem.ABSTRACT :
              System.out.println(" -- Can't apply because " + file_name+ " is 'abstract' class ");
              break;
            case MutationSystem.APPLET :
              System.out.println(" -- Can't apply because " + file_name+ " is 'applet' class ");
              break;
            case MutationSystem.GUI :
              System.out.println(" -- Can't apply because " + file_name+ " is 'GUI' class ");
              break;
          }
          deleteDirectory();
          continue;
        }

        // [2] Apply mutation testing
        setMutationSystemPathFor(file_name);

        //File[] original_files = new File[1];
        //original_files[0] = new File(MutationSystem.SRC_PATH,file_name);

        File original_file = new File(MutationSystem.SRC_PATH, file_name);

        /*AllMutantsGenerator genEngine;
          genEngine = new AllMutantsGenerator(original_file,class_ops,traditional_ops);
          genEngine.makeMutants();
          genEngine.compileMutants();*/

        ClassMutantsGenerator cmGenEngine;

        //do not generate class mutants if no class mutation operator is selected
        if(class_ops != null){
          cmGenEngine = new ClassMutantsGenerator(original_file,class_ops);
          cmGenEngine.makeMutants();
          cmGenEngine.compileMutants();
        }

        //do not generate traditional mutants if no class traditional operator is selected
        if(traditional_ops != null){
          TraditionalMutantsGenerator tmGenEngine;
          //System.out.println("original_file: " + original_file);
          //System.out.println("traditional_ops: " + traditional_ops);
          tmGenEngine = new TraditionalMutantsGenerator(original_file,traditional_ops);
          tmGenEngine.makeMutants();
          tmGenEngine.compileMutants();
        }

      } catch (OpenJavaException oje)
      {
        System.out.println("[OJException] " + file_name + " " + oje.toString());
        //System.out.println("Can't generate mutants for " +file_name + " because OpenJava " + oje.getMessage());
        deleteDirectory();
      } catch(Exception exp)
      {
        System.out.println("[Exception] " + file_name + " " + exp.toString());
        exp.printStackTrace();
        //System.out.println("Can't generate mutants for " +file_name + " due to exception" + exp.getClass().getName());
        //exp.printStackTrace();
        deleteDirectory();
      } catch(Error er)
      {
        System.out.println("[Error] " + file_name + " " + er.toString());
        System.out.println("MutantsGenPanel: ");
        er.printStackTrace();

        //System.out.println("Can't generate mutants for " +file_name + " due to error" + er.getClass().getName());
        deleteDirectory();
      }
    }
    System.out.println("------------------------------------------------------------------");
    System.out.println("All files are handled");
  }


  void deleteDirectory()
  {
    File originalDir = new File(MutationSystem.MUTANT_HOME + "/" + MutationSystem.DIR_NAME
        + "/" + MutationSystem.ORIGINAL_DIR_NAME);
    while (originalDir.delete())
    {    // do nothing?
    }

    File cmDir = new File(MutationSystem.MUTANT_HOME + "/" + MutationSystem.DIR_NAME
        + "/" + MutationSystem.CM_DIR_NAME);
    while (cmDir.delete())
    {    // do nothing?
    }

    File tmDir = new File (MutationSystem.MUTANT_HOME + "/" + MutationSystem.DIR_NAME
        + "/" + MutationSystem.TM_DIR_NAME);
    while (tmDir.delete())
    {    // do nothing?
    }

    File myHomeDir = new File(MutationSystem.MUTANT_HOME + "/" + MutationSystem.DIR_NAME);
    while (myHomeDir.delete())
    {    // do nothing?
    }
  }


  void setMutationSystemPathFor(String file_name)
  {
    try
    {
      String temp;
      temp = file_name.substring(0, file_name.length()-".java".length());
      temp = temp.replace('/', '.');
      temp = temp.replace('\\', '.');
      int separator_index = temp.lastIndexOf(".");

      if (separator_index >= 0)
      {
        MutationSystem.CLASS_NAME=temp.substring(separator_index+1, temp.length());
      }
      else
      {
        MutationSystem.CLASS_NAME = temp;
      }

      String mutant_dir_path = MutationSystem.MUTANT_HOME + "/" + temp;
      File mutant_path = new File(mutant_dir_path);
      mutant_path.mkdir();

      String class_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.CM_DIR_NAME;
      File class_mutant_path = new File(class_mutant_dir_path);
      class_mutant_path.mkdir();

      String traditional_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.TM_DIR_NAME;
      File traditional_mutant_path = new File(traditional_mutant_dir_path);
      traditional_mutant_path.mkdir();

      String original_dir_path = mutant_dir_path + "/" + MutationSystem.ORIGINAL_DIR_NAME;
      File original_path = new File(original_dir_path);
      original_path.mkdir();

      MutationSystem.CLASS_MUTANT_PATH = class_mutant_dir_path;
      MutationSystem.TRADITIONAL_MUTANT_PATH = traditional_mutant_dir_path;
      MutationSystem.ORIGINAL_PATH = original_dir_path;
      MutationSystem.DIR_NAME = temp;
    } catch(Exception e)
    {
      System.err.println(e);
    }
  }
}

