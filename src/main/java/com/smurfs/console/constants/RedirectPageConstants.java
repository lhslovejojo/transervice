package com.smurfs.console.constants;

import java.io.File;

/**
 * name：RequestParamConstants.java<br>
 * describe：the constants parameters used by page jump<br>
 * modify：2016-8-18 14:35:38
 * 
 * @since 2016-8-18
 * @author qibo
 */
public interface RedirectPageConstants {
  /** main page */
  String MAIN = "main";
  /** index login page */
  String INDEX = "index";
  /** the prefix of the system pages */
  String PAGE_INDEX = "/WEB-INF/jsp/";
  /** login controller root path */
  String LOGIN_ROOT = "/login";
  /** login controller sub path-doLogin */
  String LOGIN_DOLOG = "/doLogin";
  
  String ADD_PG = "add";
  String ADD_PGC = "addPageController";
  String ADD_C = "addController";
  String MOD_PG = "modify";
  String MOD_PGC = "modifyPageController";
  String MOD_C = "modifyController";
  String QRY_PAGE = "list";
  String QRY_PGC = "queryController";
  String QRY_JSON = "jsonController";
  
  /*~~~~~~~~bankinf~~~~~~~~~*/
  /** bankinf controller root path */
  String BANKINF_ROOT = "bankinf";
  /** bankinf liset page */
  String BANKINF_LIST_PG = PAGE_INDEX + BANKINF_ROOT + File.separator + QRY_PAGE;
  /** bankinf add page */
  String BANKINF_ADD_PG = PAGE_INDEX + BANKINF_ROOT + File.separator + ADD_PG;
  /** bankinf modify page */
  String BANKINF_MOD_PG = PAGE_INDEX + BANKINF_ROOT + File.separator + MOD_PG;
  
}
