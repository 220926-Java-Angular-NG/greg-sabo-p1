package org.example.repo;

import org.example.models.Reimbursement;
import org.example.utils.CRUDDaoInterface;
import org.example.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
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
        return 0;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Reimbursement getById(int id) {
        return null;
    }

    @Override
    public Reimbursement update(Reimbursement reimbursement) {
        return null;
    }


    @Override
    public boolean delete(Reimbursement reimbursement) {
        return false;
    }

}
