////////////////////////////////////////////////////////////////////////////
// Module : ETD_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.exception;

import java.io.*;
import openjava.ptree.*;
import mujava.op.util.MutantCodeWriter;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class ETD_Writer extends MutantCodeWriter
{
  ThrowStatement mutant = null;

  public ETD_Writer( String file_name,PrintWriter out ) {
	super(file_name,out);
  }

  public void setMutant(ThrowStatement p){
    mutant = p;
  }

  public void visit( ThrowStatement p ) throws ParseTreeException
  {
    if(isSameObject(p,mutant)){
        // -------------------------
        mutated_line = line_num;
        writeLog(p.getExpression().toString()+ " is deleted.");
        // -------------------------
      // NO Writing
    }else{
      super.visit(p);
    }
  }
}
