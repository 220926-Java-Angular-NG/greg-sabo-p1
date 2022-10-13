package org.example.services;

import org.example.models.User;
import org.example.repo.UserRepo;
import org.example.utils.CRUDDaoInterface;

import java.util.ArrayList;
import java.util.List;
public class UserService {
        private UserRepo userRepo;

        // we are creating a new instance of our UserRepo
        public UserService(){
            userRepo = new UserRepo();
        }

        // here we are passing in an existing UserRepo
        public UserService(UserRepo userRepo){
            this.userRepo = userRepo;
        }

        // create

        public int createUser(User user){

            return userRepo.create(user);
        }

        //login
        public User loginUser(User user){
            return userRepo.login(user);
        }

        //read all

        public List<User> getAllUsers(){
            return userRepo.getAll();
        }

        //readById

        public User getUserById(int id){
            return userRepo.getById(id);
        }


        //update

        public User updateUser(User user){
            return userRepo.update(user);
        }


        //delete

        public boolean deleteUserByEmail(User user) {
            return userRepo.delete(user);
        }
}
