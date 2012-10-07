////////////////////////////////////////////////////////////////////////////
// Module : EHC_Writer.java
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

public class EHC_Writer extends MutantCodeWriter
{
  CatchBlock mutant = null;
  String exception_name = "";

  public EHC_Writer( String file_name,PrintWriter out ) {
	super(file_name,out);
  }

  public void setMutant(CatchBlock p,String mutated_name){
    mutant = p;
    exception_name = mutated_name;
  }


  public void visit( CatchBlock p ) throws ParseTreeException
  {
    if(isSameObject(p,mutant)){
        // -------------------------
        mutated_line = line_num;
        writeLog(" catch block for " +  p.getParameter().getTypeSpecifier().getName()+ " is deleted.");
        // -------------------------
        out.print( " catch " );
        out.print( "( " );
        Parameter param = p.getParameter();
        out.print(exception_name + " " + param.getVariable());
        out.print( " ) " );
        StatementList stmts = p.getBody();
	    writeStatementsBlock( stmts );
    }else{
      super.visit(p);
    }
  }
}

