package org.example.repo;

import org.example.models.Reimbursement;
import org.example.utils.CRUDDaoInterface;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepo implements CRUDDaoInterface<Reimbursement> {
    Connection connection;

    public ReimbursementRepo(){
        try{
            connection = ConnectionManager.getConnection();
        }catch (SQLException sqlException){
            sqlException.getMessage();
        }
    }

    @Override
    public int create(Reimbursement reimbursement) {
        try {
            String sql = "INSERT INTO reimbursement (id,amount,description,author_id,status) VALUES (default,?,?,?,?)";

            // not when we are using the method .executeUpdate()
            // we are not given a ResultSet therefore we need to instruct the DB to return one to us
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setDouble(1, reimbursement.getAmount());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3,reimbursement.getAuthorId());
            pstmt.setInt(4, reimbursement.getStatus());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();

            return rs.getInt(1);


        } catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return 0;

    }

    @Override
    public List<Reimbursement> getAll() {

        List<Reimbursement> reimb = new ArrayList<Reimbursement>();

        try {
            String sql = "SELECT * FROM reimbursement";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setId(rs.getInt("id"));
                reimbursement.setAmount(rs.getDouble("amount"));
                reimbursement.setDescription(rs.getString("description"));
                reimbursement.setAuthorId(rs.getInt("author_id"));
                reimbursement.setStatus(rs.getInt("status"));
                reimb.add(reimbursement);
            }
            return reimb;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;

    }

    @Override
    public Reimbursement getById(int id) {
        try {

            String sql = "SELECT * FROM reimbursement WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            Reimbursement reimbursement = new Reimbursement();

            while(rs.next()){
                reimbursement.setId(rs.getInt("id"));
                reimbursement.setAmount(rs.getDouble("amount"));
                reimbursement.setDescription(rs.getString("description"));
                reimbursement.setAuthorId(rs.getInt("author_id"));
                reimbursement.setStatus(rs.getInt("status"));

            }

            return reimbursement;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Reimbursement update(Reimbursement reimbursement) {

        try {
            String sql = "UPDATE reimbursement SET status = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, reimbursement.getStatus());
            pstmt.setInt(2,reimbursement.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()){
                reimbursement.setStatus(rs.getInt("status"));
            }

            return reimbursement;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }


    @Override
    public boolean delete(Reimbursement reimbursement) {

        try{
            String sql = "DELETE FROM reimbursement WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,reimbursement.getId());

            //note that false is returned because a resultSet is NOT returned when this statement is executed
            pstmt.execute();

            return true;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;

    }

}
