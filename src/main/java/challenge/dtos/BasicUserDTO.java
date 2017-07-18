package challenge.dtos;

public class BasicUserDTO {

    private long id;
    private String handle;
    private String name;

    public BasicUserDTO() {}

    public BasicUserDTO(long id, String handle, String name) {
        this.id = id;
        this.handle = handle;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
