package mujava.openjava.extension;

import java.util.Enumeration;
import java.util.Vector;

import openjava.mop.ClosedEnvironment;
import openjava.mop.Environment;

public class ExtendedClosedEnvironment extends ClosedEnvironment{
	
   /* public ExtendedClosedEnvironment() {
       parent = null;//no op
    }*/

    public ExtendedClosedEnvironment( Environment env ) {
        super(env);
    }
	
    public String[] getAccessibleVariables(){
        Enumeration e = symbol_table.keys();
        Vector v = new Vector();
        while(e.hasMoreElements()){
          v.add((String)(e.nextElement(
  		  )));
        }
        int num = v.size();
        String[] results;
        if(num>0){
          results = new String[num];
          for(int i=0;i<num;i++){
            results[i] = (String)(v.get(i));
          }
          return results;
        }
        return null;
      }
}
