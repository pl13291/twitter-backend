package challenge.facades;

import challenge.dtos.MessageDTO;
import challenge.models.Message;
import challenge.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageService messageService;

    @Override
    public List<MessageDTO> getMessages(String userHandle, String search) {
        List<Message> messages = messageService.getWallMessages(userHandle, search);
        List<MessageDTO> messageDTOList = new ArrayList<>();

        for (Message m : messages) {
            messageDTOList.add(m.getMessageDTO());
        }
        return messageDTOList;
    }
}
