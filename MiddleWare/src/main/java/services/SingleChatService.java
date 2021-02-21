package services;

import domains.FileMessage;
import domains.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SingleChatService extends Remote {
    void sendMessage(Message message) throws RemoteException;
    void sendFileMessage(FileMessage fileMessage) throws RemoteException;
}
