package challenge.repos;

import challenge.models.User;

import java.util.List;

public interface UserRepo {

    void create(String handle, String name);

    User getUser(Long id);

    User getUser(String userHandle);

    List<User> getUsers();

    List<User> getFollowers(Long id);

    List<Long> getFollowingIds(Long id);

    List<User> getFollowing(Long id);

    int followUser(Long currentUserId, Long userToFollowId);

    Integer unFollowUser(Long currentUserId, Long userToUnFollowId);

    List<Long> getFollowersIds(Long id);
}
