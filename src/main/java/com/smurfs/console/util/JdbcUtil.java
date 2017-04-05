package com.smurfs.console.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: JdbcUtil
 * @Description: jdbc连接关闭
 * @author qibo
 * @date 2017-02-06
 *
 */
public class JdbcUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtil.class);

  /**
   * 关闭jdbc数据库连接
   * 
   * @param conn
   * @param stat
   * @param rs
   */
  public static void release(Connection conn, Statement stat, ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (Exception ex) {
      LOGGER.error("", ex);
    } finally {
      try {
        if (stat != null) {
          stat.close();
        }
      } catch (Exception ex) {
        LOGGER.error("", ex);
      } finally {
        try {
          if (conn != null) {
            conn.close();
          }
        } catch (Exception ex) {
          LOGGER.error("", ex);
        }
      }
    }
  }
}
