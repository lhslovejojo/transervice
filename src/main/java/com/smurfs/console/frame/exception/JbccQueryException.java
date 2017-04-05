package com.smurfs.console.frame.exception;

public class JbccQueryException extends RuntimeException {
/**
 * jbcc 同步数据异常失败
 */
  private static final long serialVersionUID = 1L;
  private String code;
  private String msg;
  

  private Throwable able;

  public JbccQueryException(Throwable able) {
    this.able = able;
  }
  public JbccQueryException(String code,String msg) {
	this.code=code;
	this.msg=msg;
  }
  public JbccQueryException(String code,String msg,Throwable able) {
	this.code=code;
	this.msg=msg;
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

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

}
