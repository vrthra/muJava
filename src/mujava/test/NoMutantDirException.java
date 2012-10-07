////////////////////////////////////////////////////////////////////////////
// Module : NoMutantDirException.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.test;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class NoMutantDirException extends Exception {
	private static final long serialVersionUID = 301L;
  public NoMutantDirException() { super(); }
  public NoMutantDirException(String s) { super(s); }
}
