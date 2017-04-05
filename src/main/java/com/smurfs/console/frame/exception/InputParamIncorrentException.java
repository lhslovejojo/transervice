package com.smurfs.console.frame.exception;

public class InputParamIncorrentException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String key;

  private String message;

  private Throwable able;

  public InputParamIncorrentException(String code, String message) {
    this.key = code;
    this.message = message;
  }

  /**
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * @param key
   *          the key to set
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the able
   */
  public Throwable getAble() {
    return able;
  }

  /**
   * @param able
   *          the able to set
   */
  public void setAble(Throwable able) {
    this.able = able;
  }

}
