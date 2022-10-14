package org.example.controller;

import io.javalin.http.Handler;
import org.example.models.Reimbursement;
import org.example.services.ReimbursementService;

import java.util.List;

public class ReimbursementController {

    ReimbursementService service;

    public ReimbursementController(){
        service = new ReimbursementService();
    }

    public ReimbursementController(ReimbursementService service){
        this.service = service;
    }

    //create

    public Handler createNewReimbursement = context -> {

        // first make a FlashCard object from the json sent in the request body
        Reimbursement newReimb = context.bodyAsClass(Reimbursement.class);

        // the service is used to call the methods in our repository
        int id = service.createReimbursement(newReimb);

        if(id>0){
            context.result("Your reimbursement was created!").status(202);
        } else {
            context.result("You need a description and/or to specify an amount for your reimbursement request. Your reimbursement could not be created.").status(404);
        }


    };

    //read all
    public Handler getAllReimbursements = context -> {

        List<Reimbursement> reimb = service.getAllReimbursement();

        if(reimb != null){
            context.json(reimb);
        } else {
            context.result("Could not load reimbursements").status(404);
        }

    };

    //read by id
    public Handler getReimbursementByStatus = context -> {

        String param = context.pathParam("id");

        try {
            int id = Integer.valueOf(param);
            List<Reimbursement> reimb = service.getReimbursementById(id);

            if(reimb != null){
                context.json(reimb).status(202);
            } else {
                context.result("Could not find reimbursement");
            }

        }catch(NumberFormatException nFException){

            System.out.println(nFException.getMessage());
        }


    };

    public Handler updateReimbursement = context -> {

        //creating a model of our flashcard using the json from the request body
        Reimbursement reimb = context.bodyAsClass(Reimbursement.class);

        // we are assigning the card we just created above to the updated card
        // we get from the db when we run our update method -
        reimb = service.updateReimbursement(reimb);

        if(reimb != null){
            context.json(reimb).status(202);

        }else{
            context.result("Unable to update reimbursement");
        }

    };

    //delete

    public Handler deleteReimbursement = context -> {

        Reimbursement reimb = context.bodyAsClass(Reimbursement.class);

        if(reimb != null){

            if(service.deleteReimbursement(reimb)){
                context.result("Reimbursement with id: " + reimb.getId() + " has been deleted");
            } else {
                context.result("Could not delete reimbursement");
            }

        } else {
            context.result("No reimbursement was added for deletion");
        }

    };

}
