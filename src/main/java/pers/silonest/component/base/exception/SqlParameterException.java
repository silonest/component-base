package pers.silonest.component.base.exception;

/**
 * sql语句参数错误.
 *
 * @author silonest
 */
public class SqlParameterException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SqlParameterException() {
    super();
  }

  public SqlParameterException(String message) {
    super(message);
  }

  public SqlParameterException(String message, Throwable cause) {
    super(message, cause);
  }

  public SqlParameterException(Throwable cause) {
    super(cause);
  }
}
