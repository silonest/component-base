package pers.silonest.component.socket;

public interface MessageHandler {
  public void onReceive(Connection connection, String message);
}
