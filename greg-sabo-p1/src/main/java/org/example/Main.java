package org.example;

import io.javalin.Javalin;
import org.example.controller.ReimbursementController;
import org.example.controller.UserController;

import org.example.services.ReimbursementService;
import org.example.services.UserService;

import org.example.models.Reimbursement;
import org.example.repo.ReimbursementRepo;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8081);

        UserService userService = new UserService();
        UserController userController = new UserController(userService);


        ReimbursementService reimbursementService = new ReimbursementService();
        ReimbursementController reimbursementController = new ReimbursementController(reimbursementService);

        //user can log in, create reimbursement request, view past reimbursements
        app.get("/user", userController.getAllUsers);
        app.post("/login", userController.loginUser);
        app.get("/user/{id}", userController.getUserById);
        app.post("/user", userController.createNewUser);
        app.put("/user", userController.updateUser);
        app.delete("/user", userController.deleteUser);


        //flashcard uris
        app.get("/reimbursement", reimbursementController.getAllReimbursements);
        app.get("/reimbursementByStatus/{id}", reimbursementController.getReimbursementByStatus);
        app.post("/reimbursement",reimbursementController.createNewReimbursement);
        app.put("/reimbursement", reimbursementController.updateReimbursement);

    }
}