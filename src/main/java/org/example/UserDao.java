package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user){
        jdbcTemplate.update("INSERT INTO users (name) VALUES (?)",user.getName());
    }
    public void createUsers(List<String> names){
        for (String name:names) {
            jdbcTemplate.update("INSERT INTO users (name) VALUES (?)",name);
        }
    }

    public void deleteUser (Long id){
        jdbcTemplate.update("DELETE FROM users WHERE ID=?",id);
    }

    public void deleteUserByName(String name){
        jdbcTemplate.update("DELETE FROM users where name=?",name);
    }

    public void deleteAllUsers(){
        jdbcTemplate.update("DELETE FROM users");
    }
    public User getUser(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?)",new Object[]{id},(rs,rowNum) ->
                new User(rs.getLong("id"),rs.getString("name")));
    }
    public User getUserByName(String name){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE name = ?",new Object[]{name},(rs,rowNum) ->
        new User(rs.getLong("id"),rs.getString("name")));
    }
    public List<User> getAllUsers(){
        return jdbcTemplate.query("SELECT * from Users",(rs,rowNum) ->
                new User(rs.getLong("id"),rs.getString("name")));
    }
}

