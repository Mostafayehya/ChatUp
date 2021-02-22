package eg.gov.iti.jets.data.dao;

import domains.Contact;
import domains.Invitation;
import domains.Mode;
import domains.Status;
import eg.gov.iti.jets.data.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitationDaoImpl implements InvitationDao{
    DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    Connection connection = dataBaseConnection.getConnection();
    @Override
    public int insertInvitation(Invitation invitation) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into invitation(senderPhone,receiverPhone) values (?,?)");
            preparedStatement.setString(1,invitation.getSenderPhoneNumber());
            preparedStatement.setString(2,invitation.getReceiverPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Invitation> getContacts(String userPhone) {
        List<Invitation> invitations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        System.out.println(userPhone);
        try {
            preparedStatement = connection.prepareStatement("select ID,senderPhone from invitation " +
                    "where receiverPhone = ?");
            preparedStatement.setString(1,userPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("has invitation");
                Invitation invitation = new Invitation(resultSet.getInt("ID"),resultSet.getString("senderPhone"),userPhone);
                invitations.add(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(invitations.size());
        return invitations;
    }
}
