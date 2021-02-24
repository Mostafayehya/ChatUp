package eg.gov.iti.jets.data.dao;

import domains.GroupChat;

public interface GroupchatDao {

    int insertGroupchat(GroupChat contact);

    GroupChat getGroupchatByName(String groupChatName);

}
