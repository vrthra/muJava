////////////////////////////////////////////////////////////////////////////
// Module : CodeChangeLog.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.util;

import java.io.*;
import mujava.MutationSystem;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class CodeChangeLog {

  static final String logFile_name = "mutation_log";
  static PrintWriter log_writer;

  public static void openLogFile(){
    try{
      File f = new File(MutationSystem.MUTANT_PATH,logFile_name);
      FileWriter fout = new FileWriter(f);
      log_writer = new PrintWriter(fout);
    }catch(IOException e){
      System.err.println("[IOException] Can't make mutant log file." + e);
    }
  }

  public static void writeLog(String str){
    log_writer.println(str);
  }

  public static void closeLogFile(){
    log_writer.flush();
    log_writer.close();
  }
}
