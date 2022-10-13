package org.example.services;

import org.example.models.Reimbursement;
import org.example.repo.ReimbursementRepo;

import java.util.List;

public class ReimbursementService {
    private ReimbursementRepo reimbursementRepo;

    public ReimbursementService(){
        reimbursementRepo = new ReimbursementRepo();
    }

    public ReimbursementService(ReimbursementRepo reimbursementRepo){
        this.reimbursementRepo = reimbursementRepo;
    }

    //create
    public int createReimbursement(Reimbursement reimbursement){

        return reimbursementRepo.create(reimbursement);
    }

    // read All

    public List<Reimbursement> getAllReimbursement(){
        return reimbursementRepo.getAll();
    }


    //read by id

    public Reimbursement getReimbursementById( int id){
        return reimbursementRepo.getById(id);
    }


    //update
    public Reimbursement updateReimbursement( Reimbursement reimbursement){

        return reimbursementRepo.update(reimbursement);
    }

    //delete

    public  boolean deleteReimbursement(Reimbursement reimbursement){

        return reimbursementRepo.delete(reimbursement);
    }
}
