////////////////////////////////////////////////////////////////////////////
// Module : InheritanceINFO.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.util;

import java.util.Vector;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class InheritanceINFO {

  InheritanceINFO direct_parent = null;
  Vector direct_child = new Vector();
  String class_name = null;
  String parent_class_name = null;

  public InheritanceINFO(String my_name,String parent_name) {
    class_name = my_name;
    parent_class_name = parent_name;
  }

  public void addChild(InheritanceINFO child){
    direct_child.add(child);
  }

  public void setParent(InheritanceINFO parent){
    direct_parent = parent;
  }

  public String getParentName(){
    return parent_class_name;
  }

  public InheritanceINFO getParent(){
    return direct_parent;
  }
  public Vector getChilds(){
    return direct_child;
  }

  public String getClassName(){
    return class_name;
  }
}
