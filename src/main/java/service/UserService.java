package service;

import dao.DBException;
import dao.UsersDAO;
import model.User;

import java.util.ArrayList;

public class UserService {
    private static UserService instance;
    private UsersDAO usersDAO;

    public UserService() {
        usersDAO = new UsersDAO();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void updateUser(String id, String login, String password) {
        try {
            usersDAO.updateUser(id, login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String id) {
        User user = null;
        try {
            return usersDAO.getUser(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(String id) {
        try {
            usersDAO.deleteUser(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String name, String login, String password) {
        try {
            usersDAO.insertUser(name, login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            return usersDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void createTable() {
        try {
            usersDAO.createTable();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}