package org.example.controller;

import org.example.models.User;
import org.example.services.UserService;
import io.javalin.http.Handler;

public class UserController {

    UserService service;

    public UserController(){
        service = new UserService();
    }

    public UserController(UserService userService){
        service = userService;
    }

    //create
    public Handler createNewUser = context -> {

        // grab the object from the request body (postman)
        // send the incoming user to our service , so it can
        // reach out to our repo layer and execute the request

        User user = context.bodyAsClass(User.class); //change the json from postman to on object
        int id = service.createUser(user);

        if(id > 0){
            //valid number ( it represents the primary key )
            user.setId(id);
            context.json(user).status(200);
        } else {
            // something went wrong
            context.result("Need to enter a unique email/username. User not created.").status(400);
        }

    };

    //login--------------------------------------------------------------------------------
    public Handler loginUser = context ->{
        User user = context.bodyAsClass(User.class); //change the json from postman to on object
        String email = String.valueOf(service.loginUser(user));

        if(email != null){
            context.result("You are now logged in").status(200);
        } else {
            // something went wrong
            context.result("Unsuccessful Login").status(400);
        }
//        User username = context.formParam("username");
//        String password = context.formParam("password");

//        User loginResult = null;
//        loginResult = service.loginUser(username);
    };


    //readall
    public Handler getAllUsers = context -> {

        context.json(service.getAllUsers());
    };



    //read by id

    public Handler getUserById = context -> {
        String param = context.pathParam("id");

        try {
            //this is the id that we are getting from our url
            int id =  Integer.parseInt(param);
            User user = service.getUserById(id);

            if(user != null){
                context.json(user).status(202);
            } else {
                context.result("User not found").status(400);
            }

        }catch(NumberFormatException nFException){
            System.out.println(nFException.getMessage());
        }
    };

    //update


    public Handler updateUser = context -> {

        User user = context.bodyAsClass(User.class);

        user = service.updateUser(user);

        if(user != null){
            context.json(user).status(202);
        } else {
            context.result("Could not update user").status(400);
        }
    };



    //delete

    public Handler deleteUser = context -> {
        User user = context.bodyAsClass(User.class);

        if(user != null ){
            context.status(200).json(service.deleteUserByEmail(user));
        } else {
            context.status(400).result("Could not delete user");
        }
    };


}