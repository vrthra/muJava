////////////////////////////////////////////////////////////////////////////
// Module : compileTestcase.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava;

import mujava.MutationSystem;
import mujava.util.Debug;
import mujava.util.ExtensionFilter;
import com.sun.tools.javac.Main;
import java.io.*;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class compileTestcase 
{
   public static void main(String[] args)
   {
      Debug.setDebugLevel(3);
      File f = new File(MutationSystem.TESTSET_PATH);
      String[] s = f.list(new ExtensionFilter("java"));
      String[] pars = new String[2+s.length];
      pars[0] = "-classpath";
      pars[1] = MutationSystem.CLASS_PATH;

      for (int i=0; i<s.length; i++)
      {
         pars[i+2] = MutationSystem.TESTSET_PATH + "/" + s[i];
      }
      try
      {
         // result = 0 : SUCCESS,   result = 1 : FALSE
         int result = Main.compile(pars,new PrintWriter(System.out));
         if (result == 0)
         {
            Debug.println("Compile Finished");
         }
      } catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
