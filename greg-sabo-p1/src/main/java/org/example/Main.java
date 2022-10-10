package org.example;

import io.javalin.Javalin;
import org.example.controllers.EmployeeController;
import org.example.model.Employee;
import org.example.repos.EmployeeRepo;
import org.example.services.EmployeeService;
import org.example.controllers.ManagerController;
import org.example.model.Manager;
import org.example.repos.ManagerRepo;
import org.example.services.ManagerService;
import org.example.controllers.ReimbursementController;
import org.example.model.Reimbursement;
import org.example.repos.ReimbursementRepo;
import org.example.services.ReimbursementService;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        EmployeeService employeeService = new EmployeeService();
        EmployeeController employeeController = new EmployeeController(employeeService);

        ManagerService managerService = new ManagerService();
        ManagerController managerController = new ManagerController(managerService);

        PostService postService = new PostService();
        PostController postController = new PostController(postService);

        //employee can log in, create reimbursement request, view past reimbursements
        app.get("/user", employeeController.getAllUsers);
        app.get("/user/{id}", employeeController.getUserById);
        app.post("/user", employeeController.createNewUser);
        app.put("/user", employeeController.updateUser);
        app.delete("/user", employeeController.deleteUser);

        //manager can log in, accept deny reimbursement request, view past reimbursements

    }
}