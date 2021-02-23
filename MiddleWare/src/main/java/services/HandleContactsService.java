package services;

import domains.Contact;
import domains.Invitation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HandleContactsService extends Remote {
    int addNewContact(Contact contact, Invitation invitation) throws RemoteException;
    List<Contact> getUserContacts(String userPhone) throws RemoteException;
    List<Invitation> getUserInvitation(String userPhone) throws RemoteException;
}
