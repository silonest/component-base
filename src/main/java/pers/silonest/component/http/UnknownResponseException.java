package pers.silonest.component.http;

public class UnknownResponseException extends RuntimeException {

  private static final long serialVersionUID = -2855452861042588750L;

  public UnknownResponseException(String msg) {
    super(msg);
  }

  public UnknownResponseException(Throwable t) {
    super(t);
  }
}
