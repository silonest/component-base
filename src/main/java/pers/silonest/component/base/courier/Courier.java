package pers.silonest.component.base.courier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Courier<T> {

  private boolean status;
  private String code;
  private String cause;
  private String notice;
  private T content;

  public boolean getStatus() {
    return status;
  }

  public Courier<T> status(boolean status) {
    this.status = status;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Courier<T> code(String code) {
    this.code = code;
    return this;
  }

  public String getCause() {
    return cause;
  }

  public Courier<T> cause(String cause) {
    this.cause = cause;
    return this;
  }

  public String getNotice() {
    return notice;
  }

  public Courier<T> notice(String notice) {
    this.notice = notice;
    return this;
  }

  public Object getContent() {
    return content;
  }

  public Courier<T> content(T content) {
    this.content = content;
    return this;
  }
}
