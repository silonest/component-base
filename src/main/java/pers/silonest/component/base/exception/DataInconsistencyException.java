package pers.silonest.component.base.exception;

/**
 * 执行的结果不符合预期的结果.
 *
 * @author silonest
 */
public class DataInconsistencyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataInconsistencyException() {
    super();
  }

  public DataInconsistencyException(String message) {
    super(message);
  }

  public DataInconsistencyException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataInconsistencyException(Throwable cause) {
    super(cause);
  }
}
