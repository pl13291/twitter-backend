package challenge.facades;

import challenge.dtos.BasicUserDTO;

import java.util.List;

public interface UserFacade {
    List<BasicUserDTO> getFollowers(String userHandle);

    List<BasicUserDTO> getFollowing(String userHandle);

    int followUser(String userHandle);

    Integer unFollowUser(String userHandle);

    Integer getShortestDistance(String userHandle);
}
