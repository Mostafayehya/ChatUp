package eg.gov.iti.jets.data.dao;

import domains.*;
import eg.gov.iti.jets.data.DataBaseConnection;

import java.sql.*;
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
    public List<Invitation> getInvitations(String userPhone) {
        List<Invitation> invitations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        System.out.println(userPhone);
        try {
            preparedStatement = connection.prepareStatement("select name,senderPhone,phoneNumber,picture from invitation,user " +
                    "where phoneNumber = senderPhone and receiverPhone = ?");
            preparedStatement.setString(1,userPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("has invitation");
                Invitation invitation = new Invitation(resultSet.getString("senderPhone"),userPhone,resultSet.getString("picture"),resultSet.getString("name"));
               invitations.add(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(invitations.size());
        return invitations;
    }

    @Override
    public Invitation getSenderInfo(String userPhone,String recieverPhone) {
        Invitation invitation=null;

        try {

                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = statement.executeQuery("select name,picture from user where phoneNumber = " + userPhone);
            if (rs.next()) {
                invitation=new Invitation();
                System.out.println(userPhone);
                invitation.setSenderPhoneNumber(userPhone);
                invitation.setReceiverPhoneNumber(recieverPhone);
                invitation.setSenderName(rs.getString("name"));
                invitation.setSenderimage(rs.getString("picture"));
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return invitation;
    }

    @Override
    public int deleteinvitation(Invitation invitation) {

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("delete  from invitation where senderphone=? and receiverPhone=?");
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


}
