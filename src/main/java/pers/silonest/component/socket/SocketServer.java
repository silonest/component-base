package pers.silonest.component.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {
  private ServerSocket serverSocket;
  private ListeningThread listeningThread;
  private MessageHandler messageHandler;

  public SocketServer(int port, MessageHandler handler) {
    messageHandler = handler;
    try {
      serverSocket = new ServerSocket(port);
      listeningThread = new ListeningThread(this, serverSocket);
      listeningThread.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setMessageHandler(MessageHandler handler) {
    messageHandler = handler;
  }

  public MessageHandler getMessageHandler() {
    return messageHandler;
  }

  @SuppressWarnings("deprecation")
  public void close() {
    try {
      if (serverSocket != null && !serverSocket.isClosed()) {
        listeningThread.stopRunning();
        listeningThread.suspend();
        listeningThread.stop();

        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
