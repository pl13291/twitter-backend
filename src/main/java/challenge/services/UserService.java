package challenge.services;

import challenge.models.User;

import java.util.List;

public interface UserService {

    Long getUserId(String userHandle);

    List<Long> getUsersIdToDisplayWall(Long id);

    List<User> getFollowers(String userHandle);

    List<User> getFollowing(String userHandle);

    int followUser(String userHandle);

    Integer unFollowUser(String userHandle);

    Integer getShortestDistance(String userHandle);
}
