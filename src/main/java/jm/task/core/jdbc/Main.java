package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Nascofe", "Gold", (byte) 100);
        user.saveUser("Nikola", "Tesla", (byte) 100);
        user.saveUser("Ronald", "Mc`Donald", (byte) 100);
        user.saveUser("Santa", "Clous", (byte) 100);
        user.saveUser("Adolf", "Rihter", (byte) 100);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();


    }


}
