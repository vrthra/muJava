////////////////////////////////////////////////////////////////////////////
// Module : EMSummaryTableModel.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.gui.util;

/**
 * <p>Template containing summary of exception-related mutants generated</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class EMSummaryTableModel extends SummaryTableModel 
{
   private static final long serialVersionUID = 202L;
   
   int getOperatorType() {    return EMO;    }
}
