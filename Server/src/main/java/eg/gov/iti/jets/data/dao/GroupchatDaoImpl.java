package eg.gov.iti.jets.data.dao;

import domains.GroupChat;
import domains.User;
import eg.gov.iti.jets.data.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupchatDaoImpl implements GroupchatDao{

    DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    Connection connection = dataBaseConnection.getConnection();
    @Override
    public int insertGroupchat(GroupChat groupChat) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into group_chat values (?,?,NULL,?)");
            preparedStatement.setString(1, groupChat.getId());
            preparedStatement.setString(2, groupChat.getGroupName());

            System.out.println("Too long for coloumn path " + groupChat.getGroupImagePath());
            preparedStatement.setString(3, groupChat.getGroupImagePath());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public GroupChat getGroupchatByName(String groupChatName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from group_chat where groupName = ?");
            preparedStatement.setString(1,groupChatName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                GroupChat groupChat  = new GroupChat(resultSet.getString(1),resultSet.getString(2));

                return groupChat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
