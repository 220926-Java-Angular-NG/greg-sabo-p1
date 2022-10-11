package org.example.repo;

import org.example.models.User;
import org.example.utils.CRUDDaoInterface;
import org.example.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementRepo implements CRUDDaoInterface {
    Connection connection;

    public ReimbursementRepo(){
        try{
            connection = ConnectionManager.getConnection();
        }catch (SQLException sqlException){
            sqlException.getMessage();
        }
    }

    @Override
    public int create(Object o) {
        return 0;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
