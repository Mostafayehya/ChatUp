package eg.gov.iti.jets.data.dao;

import domains.Contact;
import domains.Invitation;

import java.util.List;

public interface InvitationDao {
    int insertInvitation(Invitation invitation);
    List<Invitation> getContacts(String userPhone);
}

