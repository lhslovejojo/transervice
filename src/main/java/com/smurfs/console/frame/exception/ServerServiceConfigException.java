package com.smurfs.console.frame.exception;

public class ServerServiceConfigException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  private Throwable able;
  
  public ServerServiceConfigException (String code) {
    this.code = code;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code
   *          the code to set
   */
  public void setCode(String code) {
    this.code = code;
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ServerServiceConfigException [code=" + code + ", message=" + message + ", able=" + able + "]";
  }

}
