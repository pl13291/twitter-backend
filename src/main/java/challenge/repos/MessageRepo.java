package challenge.repos;

import challenge.models.Message;

import java.util.List;

public interface MessageRepo {

    public List<Message> getWallMessages(List<Long> usersId);
}
