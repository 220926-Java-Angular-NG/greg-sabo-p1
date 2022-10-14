package org.example.repo;

import org.example.models.User;
import org.example.utils.CRUDDaoInterface;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements CRUDDaoInterface<User>{
    Connection connection;

    public UserRepo(){
        try{
            connection = ConnectionManager.getConnection();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public int create(User user) {
        try{
            String sql = "INSERT INTO users (id, first_name, last_name, email, pass_word, roleId) VALUES (default,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getRoleId());

            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // calling rs.next() iterates us into the first row
            rs.next();
            return rs.getInt("id");

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    public User login(User user){
        try{
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,user.getEmail());
            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("pass_word").equals(user.getPassword())){
                return new User(rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("pass_word")
                );

            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<User>();

        try {

            String sql = "SELECT * FROM users";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setRoleId(rs.getInt("roleId"));

                users.add(user);

            }

            return users;

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }


    public User getById(int id) {

        try {

            String sql = "SELECT * FROM users WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            // we are returning a user
            // therefore we have to create a new instance of a user from the database

            User user = new User();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
            }

            return user;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {

        try {

            String sql = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getEmail());
            pstmt.setInt(2,user.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()){
                user.setEmail(rs.getString("email"));
            }

            return user;

        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(User user) {
        try {


            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getEmail());

            //returns boolean
            pstmt.execute();
            return true;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
}
