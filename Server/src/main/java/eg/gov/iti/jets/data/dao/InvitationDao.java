package eg.gov.iti.jets.data.dao;


import domains.Invitation;
import domains.User;

import java.util.List;

public interface InvitationDao {
    int insertInvitation(Invitation invitation);
    List<Invitation> getInvitations(String userPhone);

}

