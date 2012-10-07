////////////////////////////////////////////////////////////////////////////
// Module : EFD.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.exception;

import java.io.*;
import openjava.mop.*;
import openjava.ptree.*;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class EFD extends mujava.op.util.Mutator
{
  public EFD(FileEnvironment file_env,ClassDeclaration cdecl,
    CompilationUnit comp_unit)
  {
	super( file_env, comp_unit );
  }

    public void visit( TryStatement p )
	throws ParseTreeException
    {
      CatchList catch_list = p.getCatchList();
      StatementList finstmts = p.getFinallyBody();
      if((!catch_list.isEmpty())&&(!finstmts.isEmpty())){
        outputToFile(p);
      }
    }

   public void outputToFile(TryStatement original){
      if (comp_unit==null) return;

      String f_name;
      num++;
      f_name = getSourceName(this);
      String mutant_dir = getMuantID();

      try {
		 PrintWriter out = getPrintWriter(f_name);
		 EFD_Writer writer = new EFD_Writer( mutant_dir,out );
		 writer.setMutant(original);
		 comp_unit.accept( writer );
		 out.flush();  out.close();
      } catch ( IOException e ) {
	    System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) {
	    System.err.println( "errors during printing " + f_name );
	    e.printStackTrace();
      }
   }
}
