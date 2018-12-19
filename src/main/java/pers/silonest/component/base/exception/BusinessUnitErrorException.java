package pers.silonest.component.base.exception;

/**
 * 业务单元错误.程序中业务逻辑中的某个业务单元出现了错误，抛出该异常。
 *
 * @author silonest
 */
public class BusinessUnitErrorException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BusinessUnitErrorException() {
    super();
  }

  public BusinessUnitErrorException(String message) {
    super(message);
  }

  public BusinessUnitErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessUnitErrorException(Throwable cause) {
    super(cause);
  }
}
