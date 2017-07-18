package challenge.models;

import challenge.dtos.MessageDTO;

public class Message {

    private long id;
    private long userId;
    private String content;
    private String userHandle;

    public Message() {}

    public Message(long id, long userId, String content, String userHandle) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.userHandle = userHandle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", userHandle='" + userHandle + '\'' +
                '}';
    }

    public MessageDTO getMessageDTO() {
        MessageDTO message = new MessageDTO();

        message.setId(id);
        message.setUserId(userId);
        message.setUserHandle(userHandle);
        message.setContent(content);

        return message;
    }
}
