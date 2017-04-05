package com.smurfs.console.frame.exception;

public class RequestOtherServerException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Throwable able;

  public RequestOtherServerException(Throwable able) {
    this.able = able;
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
    return "MessageConvertException [able=" + able + "]";
  }

}
