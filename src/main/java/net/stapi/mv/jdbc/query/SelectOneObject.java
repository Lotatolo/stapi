package net.stapi.mv.jdbc.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectOneObject<T extends Object> extends SelectObject {
    private static final Logger logger = LoggerFactory.getLogger(SelectOneObject.class);

    public T getResult(String[] valueQuery) throws SQLException {
        Connection connection = jdbcConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY)) {
            for(int i = 0; i < valueQuery.length; i++)
                statement.setString(i+1, valueQuery[i]);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            } else {
                logger.error("Select: {}, with value: {}. Not return result", query, valueQuery);
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.error("Error execute query: {}. With value: {}", query, valueQuery, e);
            throw new SQLException(e);
        }
    }

}
