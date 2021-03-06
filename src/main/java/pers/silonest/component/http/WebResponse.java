package pers.silonest.component.http;

public class WebResponse<T> {
  private int statusCode;
  private T content;

  public WebResponse(int statusCode, T content) {
    this.statusCode = statusCode;
    this.content = content;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }
}
