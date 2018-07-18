package pers.silonest.component.base.exception;

public class JsonParserException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public JsonParserException() {}

  public JsonParserException(String msg) {
    super(msg);
  }

  public JsonParserException(Throwable e) {
    super(e);
  }
}
