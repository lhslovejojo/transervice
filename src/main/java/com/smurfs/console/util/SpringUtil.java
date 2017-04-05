package com.smurfs.console.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * javabean自动获取工具
 * 
 * @author qibo
 *
 */
public class SpringUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringUtil.applicationContext = applicationContext;
  }

  /**
   * 
   * @return
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 获取Javabean
   * 
   * @param beanName
   * @param clazz
   * @return
   */
  public static <T> T getBean(String beanName, Class<T> clazz) {
    return applicationContext.getBean(beanName, clazz);
  }

}
