////////////////////////////////////////////////////////////////////////////
// Module : EFD_Writer.java
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

public class EFD_Writer extends MutantCodeWriter
{
  TryStatement mutant = null;

  public EFD_Writer( String file_name,PrintWriter out ) {
	super(file_name,out);
  }

  public void setMutant(TryStatement p){
    mutant = p;
  }

    public void visit( TryStatement p )
	throws ParseTreeException
    {
      if(!(isSameObject(p,mutant))){
        super.visit(p);
      }else{
        writeTab();
        out.print( "try " );
        StatementList stmts = p.getBody();
	    writeStatementsBlock( stmts );
        CatchList catchlist = p.getCatchList();
        if (! catchlist.isEmpty()) {
	    catchlist.accept( this );
        }
        // -------------------------
        mutated_line = line_num;
        writeLog(" finally block is deleted.");
        // -------------------------
        out.println(); line_num++;
      }
    }
}
