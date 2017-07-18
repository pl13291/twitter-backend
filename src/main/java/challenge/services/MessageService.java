package challenge.services;

import challenge.models.Message;

import java.util.List;

public interface MessageService {

    List<Message> getWallMessages(String userHandle, String search);
}
