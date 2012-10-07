////////////////////////////////////////////////////////////////////////////
// Module : MutantDirFilter.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.FilenameFilter;
import java.io.File;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class MutantDirFilter implements FilenameFilter
{

  public MutantDirFilter()
  {
  }

  public boolean accept(File dir, String name)
  {
	if( (name.indexOf("_")==3) || (name.indexOf("_")==4) )
    {
      return true;
    }
    else return false;
  }
}
