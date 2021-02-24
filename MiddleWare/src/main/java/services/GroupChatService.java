package services;

import domains.GroupChat;
import domains.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GroupChatService extends Remote {

    void sendMessage(Message message) throws RemoteException;

    int createGroup(GroupChat groupChat) throws RemoteException;

    List<GroupChat> getGroupChats() throws RemoteException;

}
