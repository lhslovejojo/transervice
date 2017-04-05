package com.smurfs.console.constants;

/**
 * name：ReturnConstants.java<br>
 * describe：the returns constants<br>
 * modify：2016-8-18 14:40:38
 * 
 * @since 2016-8-18
 * @author qibo
 */
public interface ReturnConstants {
  /** log fail message */
  String LOGIN_MSG_FAIL = "0100010001";
  
  String SUCCESS = "000000";
  /**通用错误**/
  String FAIL = "999999";
  String DEFAULT = "999999";
  /**未找到要操作的数据库记录*/
  String DBRC_NO_EXIST = "010001";
}
