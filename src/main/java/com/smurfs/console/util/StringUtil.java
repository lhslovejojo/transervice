package com.smurfs.console.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
  public static boolean isNull(Object object) {
    boolean flag = false;
    if (null == object) {
      flag = true;
    } else {
      flag = StringUtils.isBlank(String.valueOf(object));
    }
    return flag;
  }

  public static String joinToIn(List<String> list) {
    StringBuilder builder = new StringBuilder();
    builder.append("('").append(StringUtils.join(list, "','")).append("')");
    return builder.toString();
  }

}
