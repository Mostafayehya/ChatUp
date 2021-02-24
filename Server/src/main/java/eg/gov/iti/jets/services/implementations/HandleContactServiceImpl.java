package eg.gov.iti.jets.services.implementations;

import clientInterface.ClientCallbacks;
import domains.Contact;
import domains.Invitation;
import eg.gov.iti.jets.data.dao.*;
import eg.gov.iti.jets.io.Server;
import services.HandleContactsService;
import utilities.FilesUtilities;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class HandleContactServiceImpl extends UnicastRemoteObject implements HandleContactsService {
    ContactDao contactDao;
    UserDao userDao;
    InvitationDao invitationDao;
    Map<String, ClientCallbacks> onlineUsers;



    public HandleContactServiceImpl() throws RemoteException {
        contactDao = new ContactDaoImpl();
        userDao = new UserDaoImpl();
        invitationDao=new InvitationDaoImpl();
        onlineUsers = Server.getInstance().getOnlineClients();
    }

    @Override
    public int addNewContact(Contact contact, Invitation invitation) throws RemoteException {

        if(userDao.getUserByPhone(contact.getContactPhoneNumber())==null){
            return -2;
        }
        if(contactDao.getContact(contact.getUserPhoneNumber(),contact.getContactPhoneNumber())!=null){
            return -3;
        }

        ClientCallbacks client = onlineUsers.get(invitation.getReceiverPhoneNumber());
        if (client != null) {
            Invitation invitationWithSenderInfo= invitationDao.getSenderInfo(invitation.getSenderPhoneNumber(), invitation.getReceiverPhoneNumber());
            File file = new File(invitationWithSenderInfo.getSenderimage());
            System.out.println(invitationWithSenderInfo.getSenderimage());
            byte[] senderImageBytes = FilesUtilities.convertFileToByteArray(file,FilesUtilities.getFileExtension(file));
            invitationWithSenderInfo.setSenderrImage(senderImageBytes);
            client.receiveInvetation(invitationWithSenderInfo);
        }
        return invitationDao.insertInvitation(invitation);

    }
    @Override
    public void acceptInvitation(Invitation invitation)
    {
        Contact contact=new Contact();
        contact.setContactPhoneNumber(invitation.getSenderPhoneNumber());
        contact.setUserPhoneNumber(invitation.getReceiverPhoneNumber());
          contactDao.insertContact(contact);
        contact.setContactPhoneNumber(invitation.getReceiverPhoneNumber());
        contact.setUserPhoneNumber(invitation.getSenderPhoneNumber());
        contactDao.insertContact(contact);
        invitationDao.deleteinvitation(invitation);
       ClientCallbacks c= onlineUsers.get(invitation.getSenderPhoneNumber());
       if(c!= null){
           try {
              c.acceptInvitation();
           }catch (RemoteException e)
           {
               e.printStackTrace();
           }

       }


    }
    @Override
    public void rejectInvitation(Invitation invitation)
    {
        invitationDao.deleteinvitation(invitation);
    }


    @Override
    public List<Contact> getUserContacts(String userPhone)
    {
        List<Contact>contacts =  contactDao.getContacts(userPhone);
        for(int i=0;i<contacts.size();i++){
            if(!contacts.get(i).getImage().equals("")){
                System.out.println("Contact has image");
                System.out.println(contacts.get(i).getImage());
                System.out.println(getClass().getResource(contacts.get(i).getImage()));
                File file = new File(contacts.get(i).getImage());
                byte[] contactImageBytes = FilesUtilities.convertFileToByteArray(file,FilesUtilities.getFileExtension(file));
                contacts.get(i).setContactImage(contactImageBytes);
            }
        }
        return contacts;
    }

    @Override
    public List<Invitation> getUserInvitation(String userPhone) throws RemoteException {
        List<Invitation>invitations =  invitationDao.getInvitations(userPhone);
       for (int i=0; i<invitations.size();i++)
       {
           if(!invitations.get(i).getSenderimage().equals("")){
               System.out.println("Contact has image");

               File file = new File(invitations.get(i).getSenderimage());
               byte[] contactImageBytes = FilesUtilities.convertFileToByteArray(file,FilesUtilities.getFileExtension(file));
               invitations.get(i).setSenderrImage(contactImageBytes);
           }
       }
    return invitations;
    }
}
