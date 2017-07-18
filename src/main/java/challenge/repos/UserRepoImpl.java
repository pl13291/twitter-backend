package challenge.repos;

import challenge.mappers.UserMapper;
import challenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepoImpl implements UserRepo {

    private final String FETCH_USER_BY_ID = "select * from people where id = :id";
    private final String FETCH_USER_BY_HANDLE = "select * from people where handle = :userHandle";
    private final String FETCH_FOLLOWING_IDS_BY_USER_ID = "select person_id from followers where follower_person_id = :id";
    private final String FETCH_FOLLOWERS_BY_USER_ID = "select people.id, people.handle, people.name " +
            "from people INNER JOIN followers " +
            "ON people.id = followers.follower_person_id " +
            "WHERE followers.person_id = :id";
    private final String FETCH_FOLLOWING_BY_USER_ID = "select people.id, people.handle, people.name " +
            "from people INNER JOIN followers " +
            "ON people.id = followers.person_id " +
            "WHERE followers.follower_person_id = :id";
    private final String FOLLOW_USER_QUERY = "INSERT INTO followers(person_id, follower_person_id) " +
            "SELECT :userToFollowId as person_id, :currentUserId as follower_person_id " +
            "FROM followers " +
            "WHERE (person_id = :userToFollowId and follower_person_id = :currentUserId) " +
            "HAVING COUNT(*) = 0";
    private final String UNFOLLOW_USER_QUERY = "DELETE FROM followers " +
            "WHERE follower_person_id = :currentUserId AND person_id = :userToUnFollowId";
    private final String FETCH_FOLLOWERS_IDS_BY_USER_ID = "select people.id from people INNER JOIN followers " +
            "ON people.id = followers.follower_person_id " +
            "WHERE followers.person_id = :id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void create(String handle, String name) {
    }

    @Override
    public User getUser(Long id) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        return (User) namedParameterJdbcTemplate.queryForObject(FETCH_USER_BY_ID, parameters, new UserMapper());
    }

    @Override
    public User getUser(String userHandle) {
        Map parameters = new HashMap();
        parameters.put("userHandle", userHandle);

        return (User) namedParameterJdbcTemplate.queryForObject(FETCH_USER_BY_HANDLE, parameters, new UserMapper());
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<User> getFollowers(Long id) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.query(FETCH_FOLLOWERS_BY_USER_ID, parameters, new UserMapper());
    }

    @Override
    public List<Long> getFollowingIds(Long id) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.queryForList(FETCH_FOLLOWING_IDS_BY_USER_ID,parameters, Long.class);
    }

    @Override
    public List<User> getFollowing(Long id) {
         Map parameters = new HashMap();
         parameters.put("id", id);

         return namedParameterJdbcTemplate.query(FETCH_FOLLOWING_BY_USER_ID, parameters, new UserMapper());
    }

    @Override
    public int followUser(Long currentUserId, Long userToFollowId) {
        Map parameters = new HashMap();
        parameters.put("currentUserId", currentUserId);
        parameters.put("userToFollowId", userToFollowId);

        return namedParameterJdbcTemplate.update(FOLLOW_USER_QUERY, parameters);
    }

    @Override
    public Integer unFollowUser(Long currentUserId, Long userToUnFollowId) {
        Map parameters = new HashMap();
        parameters.put("currentUserId", currentUserId);
        parameters.put("userToUnFollowId", userToUnFollowId);

        return namedParameterJdbcTemplate.update(UNFOLLOW_USER_QUERY, parameters);
    }

    @Override
    public List<Long> getFollowersIds(Long id) {
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.queryForList(FETCH_FOLLOWERS_IDS_BY_USER_ID, parameters, Long.class);
    }
}
