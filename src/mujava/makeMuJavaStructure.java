////////////////////////////////////////////////////////////////////////////
// Module : makeJMutationStructure.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava;

import java.io.*;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class makeMuJavaStructure {

  public static void main(String[] args) {
    MutationSystem.setJMutationStructure();
    makeDir(new File(MutationSystem.SYSTEM_HOME));
    makeDir(new File(MutationSystem.SRC_PATH));
    makeDir(new File(MutationSystem.CLASS_PATH));
    makeDir(new File(MutationSystem.MUTANT_HOME));
    makeDir(new File(MutationSystem.TESTSET_PATH));
  }

  static void makeDir(File dir){
    System.out.println("\nMake " + dir.getAbsolutePath() + " directory...");
    boolean newly_made = dir.mkdir();
    if(!newly_made){
      System.out.println(dir.getAbsolutePath() + " directory exists already.");
    }else{
      System.out.println("Making " + dir.getAbsolutePath() + " directory " + " ...done.");
    }
  }
}
