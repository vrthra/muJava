////////////////////////////////////////////////////////////////////////////
// Module : EHD_Writer.java
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

public class EHD_Writer extends MutantCodeWriter
{
  CatchBlock mutant = null;

  public EHD_Writer( String file_name,PrintWriter out ) {
	super(file_name,out);
  }

  public void setMutant(CatchBlock p){
    mutant = p;
  }


  public void visit( CatchBlock p ) throws ParseTreeException
  {
    if(isSameObject(p,mutant)){
        // -------------------------
        mutated_line = line_num;
        writeLog(" catch block for " +  p.getParameter().getTypeSpecifier().getName()+ " is deleted.");
        // -------------------------          }else{
    }else{
      super.visit(p);
    }
  }
}
