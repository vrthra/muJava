////////////////////////////////////////////////////////////////////////////
// Module : TestResult.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.test;

import java.util.Vector;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @update by Nan Li May 2012
 * @version 1.0
  */

public class TestResult
{
    //all mutants in a class  	
	public Vector mutants = new Vector();
	//killed mutants in a class
	public Vector killed_mutants = new Vector();
	//live mutants in a class
	public Vector live_mutants = new Vector();
	//mutation score
	public int mutant_score = 0;
  
  public void setMutants(){
	  mutants = new Vector();	
  }
}
