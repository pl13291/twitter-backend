package challenge.repos;

import challenge.mappers.MessageMapper;
import challenge.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepoImpl implements MessageRepo {

    private final String FETCH_MESSAGES_FROM_USERS_BY_ID =
            "select messages.id, messages.person_id, messages.content, people.handle " +
            "from messages " +
            "inner JOIN people " +
            "on messages.person_id = people.id " +
            "WHERE person_id in (:id)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Message> getWallMessages(List<Long> usersId) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", usersId);

        return this.namedParameterJdbcTemplate.query(FETCH_MESSAGES_FROM_USERS_BY_ID, parameters,new MessageMapper());
    }
}
