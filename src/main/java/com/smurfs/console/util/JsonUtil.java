package com.smurfs.console.util;

import com.google.gson.Gson;

public class JsonUtil {
  public static String gsonString(Object obj) {
    Gson gson = new Gson();
    return gson.toJson(obj);
  }
}
