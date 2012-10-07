////////////////////////////////////////////////////////////////////////////
// Module : ETC_Writer.java
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

public class ETC_Writer extends MutantCodeWriter
{
  AllocationExpression original = null;
  AllocationExpression mutant = null;

  public ETC_Writer( String file_name,PrintWriter out ) {
	super(file_name,out);
  }

  public void setMutant(AllocationExpression a,AllocationExpression b){
    original = a;
    mutant = b;
  }


  public void visit( AllocationExpression p ) throws ParseTreeException
  {
    if(isSameObject(p,original)){
      super.visit(mutant);
        // -------------------------
        mutated_line = line_num;
        writeLog(p.toString() + "  -->  " + mutant.toString());
        // -------------------------
    }else{
      super.visit(p);
    }
  }
}
