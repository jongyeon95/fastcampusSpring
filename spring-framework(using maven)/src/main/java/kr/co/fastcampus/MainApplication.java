package kr.co.fastcampus;

import org.slf4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class MainApplication {
    private static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.h2.Driver");
            String url ="jdbc:h2:mem:test;MODE=MySQL";
            connection= DriverManager.getConnection(url,"sa","");
            statement =connection.createStatement();

            connection.setAutoCommit(false);
            statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null,primary key(id))");
            statement.executeUpdate("insert into member(username,password) values ('jongyeon','1234')");

            ResultSet resultSet= statement.executeQuery("select id, username, password from member");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username= resultSet.getString("username");
                String password=resultSet.getString("password");

                logger.info("id : "+id+", username: "+ username + ", password :" + password);
            }
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
       // SpringApplication.run(MainApplication.class, args);
    }

}
