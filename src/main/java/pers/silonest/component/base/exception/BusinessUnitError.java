package pers.silonest.component.base.exception;

/**
 * 业务单元错误.程序中业务逻辑中的某个业务单元出现了错误，抛出该异常。
 *
 * @author silonest
 */
public class BusinessUnitError extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BusinessUnitError() {
    super();
  }

  public BusinessUnitError(String message) {
    super(message);
  }

  public BusinessUnitError(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessUnitError(Throwable cause) {
    super(cause);
  }
}
