package pers.silonest.component.base.courier;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Courier {
  private boolean status = true;
  private String code;
  private String cause;
  private String notice;

  public Courier() {}

  public Courier(boolean status, String code, String cause, String notice) {
    this.status = status;
    this.code = code;
    this.cause = cause;
    this.notice = notice;
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
}
