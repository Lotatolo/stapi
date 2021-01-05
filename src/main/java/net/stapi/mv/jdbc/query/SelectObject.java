package net.stapi.mv.jdbc.query;

import net.stapi.mv.jdbc.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectObject {

    protected JDBCConnection jdbcConnection;
    protected String query;

    public void setJDBCConnection(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public JDBCConnection getJDBCConnection() {
        return jdbcConnection;
    }

    public abstract Object getResult(String[] valueQuery) throws SQLException;
}
