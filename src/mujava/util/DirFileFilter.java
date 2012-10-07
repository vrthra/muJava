////////////////////////////////////////////////////////////////////////////
// Module : DirFileFilter.java
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
import java.io.File;
import java.io.FilenameFilter;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class DirFileFilter implements FilenameFilter{

  public DirFileFilter() {
  }

  public boolean accept(File f)
  {
    if(f.isDirectory()){ return true;}
    else { return false; }
  }

  public boolean accept(File dir, String name)
  {
    File f = new File(dir,name);
    if(f.isDirectory()) return true;
    else return false;
  }

  public String getDescription()
  {
    return "Directory";
  }
}
