package challenge.services;

import challenge.models.User;
import challenge.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Long getUserId(String userHandle) {
        return userRepo.getUser(userHandle).getId();
    }

    @Override
    public List<Long> getUsersIdToDisplayWall(Long id) {
        List<Long> result = new ArrayList<>();

        result.add(id);
        result.addAll(userRepo.getFollowingIds(id));

        return result;
    }

    @Override
    public List<User> getFollowers(String userHandle) {
        List<User> followers = new ArrayList<>();

        Long userId = getUserId(userHandle);
        followers.addAll(userRepo.getFollowers(userId));

        return followers;
    }

    @Override
    public List<User> getFollowing(String userHandle) {
        List<User> following = new ArrayList<>();

        Long userId = getUserId(userHandle);
        following.addAll(userRepo.getFollowing(userId));
        return following;
    }

    @Override
    public int followUser(String userHandle) {
        String loggedInUserHandle = getLoggedUserHandle();

        Long currentUserId = getUserId(loggedInUserHandle);
        Long userToFollowId = getUserId(userHandle);

        return userRepo.followUser(currentUserId, userToFollowId);
    }

    @Override
    public Integer unFollowUser(String userHandle) {
        String loggedInUserHandle = getLoggedUserHandle();

        Long currentUserId = getUserId(loggedInUserHandle);
        Long userToUnFollowId = getUserId(userHandle);

        return userRepo.unFollowUser(currentUserId, userToUnFollowId);
    }

    @Override
    public Integer getShortestDistance(String userHandle) {
        String loggedInUserHandle = getLoggedUserHandle();

        Long loggedInUserHandleId = getUserId(loggedInUserHandle);
        Long userHandleId = getUserId(userHandle);

        return BFS(loggedInUserHandleId, userHandleId);
    }

    //Implemented slightly modified Breath-First-Search algorithm
    //keeping tracks of levels (depth of the tree)
    private Integer BFS(Long loggedInUserId, Long userHandleId) {
        int level = 0;
        Queue<Long> queue = new LinkedList<>();
        List<Long> visited = new ArrayList<>();

        visited.add(loggedInUserId);
        queue.add(loggedInUserId);

        while (!queue.isEmpty()) {
           List<Long> inspectedLevel = new ArrayList<>();
           inspectedLevel.addAll(queue);

           if (inspectedLevel.contains(userHandleId)) {
               return level;
           }
           else {
               level = level + 1;
               queue.clear();
               visited.addAll(inspectedLevel);

               for (Long l : inspectedLevel) {
                   List<Long> following = userRepo.getFollowingIds(l);
                   for (Long f : following) {
                       if (!visited.contains(f) || !queue.contains(f)) {
                           queue.add(f);
                       }
                   }
               }
           }
        }
        return 0;
    }

    private String getLoggedUserHandle() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in user from spring security
    }
}
