package net.stapi.mv.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnection {
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);
    private Connection connection;

    private String cid;
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pwd);
            } catch (SQLException e) {
                logger.error("Error create jdbc connection with url {} and user {}", url, user, e);
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                logger.error("Not found driver class {}", driver, e);
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (connection.isClosed())
                    connection = DriverManager.getConnection(url, user, pwd);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            logger.error("Error closing jdbc connection with url {} and user {}", url, user, e);
        }
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
