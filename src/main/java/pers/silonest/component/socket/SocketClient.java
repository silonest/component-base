package pers.silonest.component.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
  private Socket socket;

  public SocketClient(InetAddress ip, int port) {
    try {
      socket = new Socket(ip, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void println(String message) {
    PrintWriter writer;
    try {
      writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      writer.println(MessageFlag.pureMessage + message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * This function blocks.
   */
  public String readLine() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  /*
   * Ready for use.
   */
  public void close() {
    try {
      // Send a message to tell the server to close the connection.
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      writer.println(MessageFlag.connectionClosed);

      if (socket != null && !socket.isClosed())
        socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
