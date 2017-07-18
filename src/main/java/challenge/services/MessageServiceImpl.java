package challenge.services;

import challenge.models.Message;
import challenge.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private UserService userService;

    private MessageRepo messageRepo;

    @Autowired
    public void setMessageRepo(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Message> getWallMessages(String userHandle, String search) {

        Long userId = userService.getUserId(userHandle);
        List<Long> usersId = userService.getUsersIdToDisplayWall(userId);
        List<Message> wallMessages = messageRepo.getWallMessages(usersId);

        return filterMessages(wallMessages, search);
    }

    //Please note filtering should probably happen on repo level with SQL query
    //but due to time constrains implementing H2 Native Full Text search was not plausible
    private List<Message> filterMessages(List<Message> wallMessages, String search) {
        List<Message> filteredMessages = new ArrayList<>();

        for (Message m : wallMessages) {
            if (m.getContent().contains(search) || m.getUserHandle().contains(search)) {
                filteredMessages.add(m);
            }
        }

        return filteredMessages;
    }
}
