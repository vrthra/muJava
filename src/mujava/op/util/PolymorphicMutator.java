////////////////////////////////////////////////////////////////////////////
// Module : MutantCodeWriter.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.util;

import openjava.mop.*;
import openjava.ptree.*;
import java.lang.reflect.*;

/**
 * <p>Mutant Generator for the mutation operator that are related with polymorphism. </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
*/

public class PolymorphicMutator extends Mutator
{
   protected boolean hasHidingVariable(String childClass, String parentClass)
   {
      try
      {
         Class child_class = Class.forName(childClass);
         Class parent_class = Class.forName(parentClass);
         Field[] child_fs = child_class.getDeclaredFields();
         Field[] parent_fs = parent_class.getDeclaredFields();
        
         for (int i=0; i<child_fs.length; i++)
         {
            for (int j=0; j<parent_fs.length; j++)
            {
               if (sameSignature(child_fs[i], parent_fs[j])) 
            	  return true;
            }
         }
      } catch (ClassNotFoundException e)
      {
         return false;
      }
      return false;
   }

   protected boolean sameSignature(Field f1, Field f2)
   {
      if (!(f1.getName().equals(f2.getName()))) 
    	 return false;
    
      if (!(f1.getType().getName().equals(f2.getType().getName()))) 
    	 return false;
    
      return true;
   }

   protected boolean sameSignature(Method m1, Method m2)
   {
      if (!(m1.getName().equals(m2.getName()))) 
    	 return false;
    
      if (!m1.getReturnType().getName().equals(m2.getReturnType().getName())) 
    	 return false;
    
      Class[] par1 = m1.getParameterTypes();
      Class[] par2 = m2.getParameterTypes();
      
      if ((par1 == null) && (par2 == null)) 
    	 return true;
    
      if ( ((par1 == null) && (par2 != null)) || ((par1 != null) && (par2 == null)) ) 
    	 return false;
    
      if (par1.length != par2.length) 
    	 return false;
    
      for (int i=0; i<par1.length; i++)
      {
         if (!(par1[i].getName().equals(par2[i].getName()))) 
        	return false;
      }
      return true;
   }

   public PolymorphicMutator( Environment env , CompilationUnit comp_unit ) 
   {
      super( env, comp_unit );
   }
}