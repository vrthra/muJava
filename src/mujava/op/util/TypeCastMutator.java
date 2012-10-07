////////////////////////////////////////////////////////////////////////////
// Module : TypeCastMutator.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.util;

import java.lang.reflect.*;
import openjava.mop.*;
import openjava.ptree.*;


/**
 * <p>Mutant Generator for the mutation operators that are related with polymorphism</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class TypeCastMutator  extends PolymorphicMutator
{
   protected MethodCall currentMethodCall = null;

   public TypeCastMutator(FileEnvironment file_env, CompilationUnit comp_unit)
   {
  	  super( file_env, comp_unit );
   }

   protected boolean hasHidingVariableOrOverridingMethod(String childClass, String parentClass)
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
      
         Method[] child_ms = child_class.getDeclaredMethods();
         Method[] parent_ms = parent_class.getDeclaredMethods();
      
         for (int i=0; i<child_ms.length; i++)
         {
            for (int j=0; j<parent_ms.length; j++)
            {
               if (sameSignature(child_ms[i], parent_ms[j])) 
            	  return true;
            }
         }
      } catch (ClassNotFoundException e)
      {
         return false;
      }
      return false;
   }

   protected boolean isNonAbstractOverridingMethodCall(String childClass, String parentClass, String method_name, Class[] pars)
   {
      try 
      {
         Class child_class = Class.forName(childClass);
         Class parent_class = Class.forName(parentClass);
         Method child_ms = child_class.getDeclaredMethod(method_name,pars);
         Method parent_ms = parent_class.getDeclaredMethod(method_name,pars);
       
         if (Modifier.isAbstract(parent_ms.getModifiers())) 
        	return false;
      
         if ((child_ms != null) && (parent_ms != null)) 
        	return true;
    
      } catch ( ClassNotFoundException e){
         return false;
      }catch(NoSuchMethodException e2){
         return false;
      }
      return false;
   }

   protected Class[] getParameterTypes(MethodCall p) throws ParseTreeException, ClassNotFoundException
   {
      ExpressionList list = p.getArguments();
      if (list == null) 
    	 return null;
    
      int num = list.size();
      Class[] result = new Class[num];
     
      for (int i=0; i<num; i++)
      {
         result[i] = Class.forName(getType(list.get(i)).getName());
      }
      return result;
   }

}
