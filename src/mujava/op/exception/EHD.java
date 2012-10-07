////////////////////////////////////////////////////////////////////////////
// Module : EHD.java
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

public class EHD extends mujava.op.util.Mutator
{
  public EHD(FileEnvironment file_env,ClassDeclaration cdecl,
    CompilationUnit comp_unit)
  {
	super( file_env, comp_unit );
  }

  public void visit( TryStatement p ) throws ParseTreeException
  {
    CatchList catchlist = p.getCatchList();
    if (! catchlist.isEmpty()) {
      int num = catchlist.size();
      if(num==1){
        StatementList finstmts = p.getFinallyBody();
        if(!finstmts.isEmpty()){
          outputToFile(catchlist.get(0));
        }
      }else{
        for(int i=0;i<num;i++){
          outputToFile(catchlist.get(i));
        }

      }
    }
  }

  public void outputToFile(CatchBlock p){
      if (comp_unit==null) return;
      String f_name;
      num++;
      f_name = getSourceName(this);
      String mutant_dir = getMuantID();

      try {
		 PrintWriter out = getPrintWriter(f_name);
		 EHD_Writer writer = new EHD_Writer( mutant_dir,out );
		 writer.setMutant(p);
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
