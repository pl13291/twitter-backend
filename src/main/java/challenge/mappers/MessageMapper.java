package challenge.mappers;

import challenge.models.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("messages.id"));
        message.setUserId(rs.getLong("messages.person_id"));
        message.setContent(rs.getString("messages.content"));
        message.setUserHandle(rs.getString("people.handle"));
        return message;
    }
}
