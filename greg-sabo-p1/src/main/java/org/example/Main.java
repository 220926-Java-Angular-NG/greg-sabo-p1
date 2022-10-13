package org.example;

import io.javalin.Javalin;
import org.example.controller.ReimbursementController;
import org.example.controller.UserController;

import org.example.models.User;
import org.example.repo.UserRepo;
import org.example.services.ReimbursementService;
import org.example.services.UserService;

import org.example.models.Reimbursement;
import org.example.repo.ReimbursementRepo;
//import org.example.services.ReimbursementService;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        UserService userService = new UserService();
        UserController userController = new UserController(userService);


        ReimbursementService reimbursementService = new ReimbursementService();
        ReimbursementController reimbursementController = new ReimbursementController(reimbursementService);

        //user can log in, create reimbursement request, view past reimbursements
        app.get("/user", userController.getAllUsers);
//        app.post("login", userController.login);
        app.get("/user/{id}", userController.getUserById);
        app.post("/user", userController.createNewUser);
        app.put("/user", userController.updateUser);
        app.delete("/user", userController.deleteUser);


        //flashcard uris
        app.get("/reimbursement", reimbursementController.createNewReimbursement);
        app.post("/reimbursement",reimbursementController.createNewReimbursement);

    }
}