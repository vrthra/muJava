////////////////////////////////////////////////////////////////////////////
// Module : ExtensionFilter.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.util;

import java.io.FilenameFilter;
import java.io.File;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class ExtensionFilter implements FilenameFilter
{
  String  mask = null;

  public ExtensionFilter()
  {
  }

  public ExtensionFilter(String str){
    mask = str;
  }

  public boolean accept(File dir, String name)
  {
    if(mask!=null){
      if( name.indexOf("$")>=0) return false;
      int index = name.lastIndexOf(".");
      String ext_name = name.substring(index+1,name.length());
      if(ext_name.equals(mask)){
	return true;
      }else{
	return false;
      }
    }else{
      return true;
    }
  }
}
