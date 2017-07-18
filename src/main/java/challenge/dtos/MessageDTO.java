package challenge.dtos;

public class MessageDTO {

    private long id;
    private long userId;
    private String userHandle;
    private String content;

    public MessageDTO() {}

    public MessageDTO(long id, long userId, String userHandle, String content) {
        this.id = id;
        this.userId = userId;
        this.userHandle = userHandle;
        this.content = content;
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

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
