////////////////////////////////////////////////////////////////////////////
// Module : OpenJavaException.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class OpenJavaException extends Exception{

	private static final long serialVersionUID = 1L;
	
  public OpenJavaException() {
  }

  public OpenJavaException(String str) {
    super(str);
  }
}
