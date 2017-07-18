package challenge.facades;

import challenge.dtos.BasicUserDTO;
import challenge.models.User;
import challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public List<BasicUserDTO> getFollowers(String userHandle) {
        List<User> followers = userService.getFollowers(userHandle);
        List<BasicUserDTO> followersDTO = new ArrayList<>();

        for (User user : followers) {
            followersDTO.add(user.getBasicUserDTO());
        }
        return followersDTO;
    }

    @Override
    public List<BasicUserDTO> getFollowing(String userHandle) {
        List<User> following = userService.getFollowing(userHandle);
        List<BasicUserDTO> followingDTO = new ArrayList<>();

        for (User user : following) {
            followingDTO.add(user.getBasicUserDTO());
        }
        return followingDTO;
    }

    @Override
    public int followUser(String userHandle) {
        return userService.followUser(userHandle);
    }

    @Override
    public Integer unFollowUser(String userHandle) {
        return userService.unFollowUser(userHandle);
    }

    @Override
    public Integer getShortestDistance(String userHandle) {
        return userService.getShortestDistance(userHandle);
    }
}
