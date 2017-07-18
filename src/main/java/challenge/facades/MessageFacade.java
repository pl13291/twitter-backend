package challenge.facades;

import challenge.dtos.MessageDTO;
import challenge.models.Message;

import java.util.Collection;
import java.util.List;

public interface MessageFacade {

    List<MessageDTO> getMessages(String userHandle, String search);
}
