package pers.silonest.component.util;

public class JsonParserException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public JsonParserException(String msg) {
    super(msg);
  }

  public JsonParserException(Throwable e) {
    super(e);
  }

  public JsonParserException() {}
}
