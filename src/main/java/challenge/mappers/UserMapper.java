package challenge.mappers;

import challenge.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setHandle(rs.getString("handle"));
        user.setName(rs.getString("name"));

        return user;
    }
}
