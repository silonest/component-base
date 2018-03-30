package pers.silonest.component.base.exception;

/**
 * SQL语义有误.
 *
 * @author silonest
 */
public class SqlSemanticException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SqlSemanticException() {
    super();
  }

  public SqlSemanticException(String message) {
    super(message);
  }

  public SqlSemanticException(String message, Throwable cause) {
    super(message, cause);
  }

  public SqlSemanticException(Throwable cause) {
    super(cause);
  }
}
