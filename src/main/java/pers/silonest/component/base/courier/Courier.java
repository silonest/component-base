package pers.silonest.component.base.courier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Courier<T> {

  private boolean status = true;
  private String code;
  private String cause;
  private String notice;
  private T content;

  public Courier() {

  }

  Courier(boolean status, String code, String cause, String notice, T content) {
    this.status = status;
    this.code = code;
    this.cause = cause;
    this.notice = notice;
    this.content = content;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean getStatus() {
    return status;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  public String getCause() {
    return cause;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

  public String getNotice() {
    return notice;
  }

  public void setContent(T content) {
    this.content = content;
  }

  public Object getContent() {
    return content;
  }
}
