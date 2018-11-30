package pers.silonest.component.socket;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * socket连接.
 *
 */
public class Connection {
  private Socket socket;

  /**
   * 创建socket连接.
   * 
   * @param socket
   */
  public Connection(Socket socket) {
    this.socket = socket;
  }

  /**
   * 向客户端传输信息.
   * 
   * @param message
   */
  public void println(String message) {
    try {
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      writer.println(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
