package challenge.models;

import challenge.dtos.BasicUserDTO;

import java.util.List;

public class User {

    private long id;
    private String handle;
    private String name;
    private List<User> followers;
    private List<User> following;

    public User() {}

    public User(long id, String handle, String name) {
        this.id = id;
        this.handle = handle;
        this.name = name;
    }

    public User(long id, String handle, String name, List<User> followers, List<User> following) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.followers = followers;
        this.following = following;
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", handle='" + handle + '\'' +
                ", name='" + name + '\'' +
                ", followers=" + followers.toString() +
                ", following=" + following.toString() +
                '}';
    }

    public BasicUserDTO getBasicUserDTO() {
        BasicUserDTO userDTO = new BasicUserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setHandle(handle);

        return userDTO;
    }
}
