////////////////////////////////////////////////////////////////////////////
// Module : ETC.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.exception;

import java.io.*;
import openjava.mop.*;
import openjava.ptree.*;
import java.util.Vector;
import mujava.util.InheritanceINFO;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class ETC extends mujava.op.util.Mutator
{
  public ETC(FileEnvironment file_env,ClassDeclaration cdecl,
    CompilationUnit comp_unit)
  {
	super( file_env, comp_unit );
  }

  public void visit( ThrowStatement p ) throws ParseTreeException
  {
    genETC(p);
  }

  void genETC(ThrowStatement p){
    try{
      Expression expr = p.getExpression();
      if(expr instanceof AllocationExpression){
        AllocationExpression original= (AllocationExpression)expr;
        String exp_type = original.getClassType().getName();
        InheritanceINFO inf = mujava.MutationSystem.getInheritanceInfo(exp_type);
        if(inf==null) return;
         Vector v = inf.getChilds();
          if(v.size()>0){
            for(int i=0;i<v.size();i++){
              InheritanceINFO my = (InheritanceINFO)(v.get(i));
              AllocationExpression mutant = (AllocationExpression)original.makeRecursiveCopy();
              mutant.setClassType(new TypeName(my.getClassName()));
              outputToFile(original,mutant);
            }
          }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

   public void outputToFile(AllocationExpression original,AllocationExpression mutant){
      if (comp_unit==null) return;

      String f_name;
      num++;
      f_name = getSourceName(this);
      String mutant_dir = getMuantID();

      try {
		 PrintWriter out = getPrintWriter(f_name);
		 ETC_Writer writer = new ETC_Writer( mutant_dir,out );
		 writer.setMutant(original,mutant);
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
