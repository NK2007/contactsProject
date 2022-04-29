package com.company;
import java.sql.*;
import java.util.*;
public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mziuri";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Owner> owners = new ArrayList<>();
        Commands commands = new Commands();
        boolean logged_in = false;
        while (true) {
            if (! logged_in) {
                System.out.println("1. Register owner");
                System.out.println("2. Log in owner");
                System.out.println("3. Log out owner");
                System.out.println("4. Exit");
            }
            int x = in.nextInt();
            if (x == 4) {
                System.exit(0);
            } else if (x == 3) {
                logged_in = false;
            } else if (x == 1) {
                System.out.print("username : ");
                String username = in.next();
                System.out.print("password : ");
                String password = in.next();
                owners.add(new Owner(username, password));
                System.out.println("Owner registered !");
            } else if (x == 2) {
                System.out.print("username : ");
                String username = in.next();
                System.out.print("password : ");
                String password = in.next();
                for (Owner i : owners) {
                    if (i.getUsername().equals(username) && i.getPassword().equals(password)) {
                        logged_in = true;
                        break;
                    }
                }
                if (! logged_in) {
                    System.out.println("No such owner found ! Please try again !");
                }
            } else {
                System.out.println("Your input should be in range [1-4] ! Please try again !");
            }
            if (logged_in) {
                System.out.println("3. Log out owner");
                System.out.println("4. Exit");
                System.out.println("5. Edit contact");
                System.out.println("6. Delete contact");
                System.out.println("7. See contacts");
                int choice = in.nextInt();
                if (choice == 5) {
                    System.out.println("Here you can change contact_id !\nEnter owner_id : ");
                    int ownerId = in.nextInt(), newOwnerId = in.nextInt();
                    commands.edit_contact_by_owner_id(ownerId, newOwnerId);
                } else if (choice == 7) {
                    System.out.println("Which do you want to delete by ?");
                    System.out.println("A = name, B = idNum, C = mobile");
                    String c = in.next();
                    if (c.equals("A")) {
                        String inputName = in.next();
                        commands.see_contacts_by_name(inputName);
                    } else if (c.equals("B")) {
                        String inputIdNum = in.next();
                        commands.see_contacts_by_idNum(inputIdNum);
                    } else if (c.equals("C")) {
                        String inputMobile = in.next();
                        commands.see_contacts_by_mobile(inputMobile);
                    } else {
                        System.out.println("Please try again !");
                    }
                } else if (choice == 4) {
                    System.exit(0);
                } else if (choice == 3) {
                    logged_in = false;
                } else if (choice == 6) {
                    System.out.print("ownerId : ");
                    int inputOwnerId = in.nextInt();
                    commands.delete_contact_by_ownerId(inputOwnerId);
                } else {
                    System.out.println("Your input should be in range [5-6] ! Please try again !");
                }
            }
        }
    }
}