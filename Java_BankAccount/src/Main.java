import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    ArrayList<usersAccount> users = new ArrayList<>();

    Random rand = new Random(new Date().getTime());

    usersAccount loggedInUserAccount = new usersAccount(null, 0, 0, null);

    int userI;

    public static void main(String[] args) {

        new Main();
    }

    Main() {
        loadUsers();
        boolean loginFlag = false;
        while(true) {
            while (!loginFlag) {
                System.out.println("##### WELCOME TO THE BANK #####");
                System.out.println("1-Create Account\n2-Login\n3-Exit\nChoose with number:");
                int Choice = input.nextInt();
                switch (Choice) {
                    case 1:
                        System.out.println("------------------");
                        CreateAccount();
                        break;
                    case 2:
                        System.out.println("------------------");
                        Login();
                        loginFlag = true;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("invalid entry, Enter again.");
                }


            }
            while (true) {
                System.out.println("=================");
                System.out.println("## WELCOME TO THE PANEL ##");
                System.out.println("1-Increase Balance \n2-Transfer Balance\n3-Buy credit\n4-Delete Account\n5-Log out\nChoose with number:");
                int Choice = input.nextInt();
                switch (Choice) {
                    case 1:
                        System.out.println("------------------");
                        increaseBalance(loggedInUserAccount);
                        break;
                    case 2:
                        System.out.println("------------------");
                        transferBalance(loggedInUserAccount);
                        break;
                    case 3:
                        System.out.println("------------------");
                        buyCredit(loggedInUserAccount);
                        break;
                    case 4:
                        System.out.println("------------------");
                        deleteAccount(loggedInUserAccount);
                    case 5:
                        new Main();
                            break;
                    default:
                        System.out.println("invalid entry, Enter again.");
                }
                break;
            }

        }
    }

    private void deleteAccount(usersAccount loggedInUserAccount) {
        users.remove(userI);
        saveUsers();
    }

    private void buyCredit(usersAccount loggedInUserAccount) {
        String number = input.nextLine();
        while (true) {
            System.out.println("Enter your number(0 to exit):");
            number = input.nextLine();
                if(number.equals(0))
                    return;
            if (number.length() == 11 && number.charAt(0) == '0' && number.charAt(1) == '9')
                break;
            else{
                System.out.println("Wrong number,please try again");
                continue;
            }
        }
        int amount;
        while (true){
            System.out.println("Choose or Enter the amount:");
            System.out.println("1-1000\n2-2000\n3-5000\n4-10000\n5-20000\n6-50000\n");
                int choice=input.nextInt();

                switch (choice){
                    case 1:
                        amount=1000;
                        break;
                    case 2:
                        amount=2000;
                        break;
                    case 3:
                        amount=5000;
                        break;
                    case 4:
                        amount=10000;
                        break;
                    case 5:
                        amount=20000;
                        break;
                    case 6:
                        amount=50000;
                    default:
                        amount=choice;
                        break;
                }
                break;
             }
        while (true) {
            if (users.get(userI).getBalance() > amount && amount<=100000)
                break;
            else
              System.out.println("Error,Insufficient balance,try again");
            amount= input.nextInt();
        }
        System.out.println("the amount: "+ amount);
        System.out.println("the number: "+number);
        System.out.println("1-accept\n2-Exit");
        while(true) {
            switch (input.nextInt()) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("wrong entry, try again");
                    continue;
            }
            break;
        }
        users.get(userI).setBalance(users.get(userI).getBalance()-amount);
        System.out.println("Phone mobile charged successfully");
        saveUsers();

    }



    private void transferBalance(usersAccount loggedInUserAccount) {
        int bankingcode ;
        int destinationAccount;
        label:
           while (true) {
               System.out.println("Enter the the destination account's banking-code: ");
                bankingcode = input.nextInt();


               for (int i = 0; i < users.size(); i++) {
                   if (users.get(i).getAccountNumber() == bankingcode) {
                       destinationAccount = i;
                       break label;
                   }
               }



               while (true) {
                   System.out.println("Error,no account was founded\n1-Try again\n2-Exit");
                   switch (input.nextInt()) {
                       case 2:
                           return;
                       case 1:
                           continue label;

                       default:
                           System.out.println("Wrong Entry,try again");

                   }
               }
           }
           System.out.println("Enter the amount :");
           long increaseDesBalance ;
           while (true) {
               increaseDesBalance = input.nextLong();
               if (users.get(userI).getBalance() > increaseDesBalance)
                   break;
               System.out.println("Error,Insufficient balance,try again");

           }
           System.out.println("-------");
           System.out.println("The banking-code of the destination account: " + users.get(destinationAccount).getAccountNumber());
           System.out.println("The username of the destination account : " + users.get(destinationAccount).getOwnerName());
           System.out.println("The amount : " + increaseDesBalance);

           while (true) {
               System.out.println("1-Accept transfer\n2-Exit");
               switch (input.nextInt()) {
                   case 1:
                       break;
                   case 2:
                       return;
                   default:
                       System.out.println("Error,wrong entry");
                       continue;
               }
               break;
           }
           users.get(destinationAccount).setBalance(users.get(destinationAccount).getBalance() + increaseDesBalance);
           users.get(userI).setBalance(users.get(userI).getBalance() - increaseDesBalance);
           System.out.println("Transfer Completed");
           saveUsers();


    }

    private void increaseBalance(usersAccount loggedInUserAccount) {
        System.out.println("Enter the Amount");
         long increaseBalance=input.nextLong();
         users.get(userI).setBalance(loggedInUserAccount.getBalance()+increaseBalance);
        System.out.println("Amount increased successfully");
         saveUsers();

    }

    private void Login() {
        int bankingCode;
        String password;
        System.out.println("Enter your banking code(0 to exit)");
            bankingCode= input.nextInt();
            switch(bankingCode){
                case 0:
                    return;

                default:
                    break;
            }
        usersAccount LoggedinUserAccount = null;
        PasswordLabel:
        while (true) {
            System.out.println("Password:");
            password = input.next();


            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getAccountNumber()==(bankingCode)) {
                    if (users.get(i).checkPassword(password)) {
                        LoggedinUserAccount = users.get(i);
                        userI=i;
                        break PasswordLabel;
                    }
                    System.out.println("wrong password. Enter again:");
                    continue PasswordLabel;
                }
            }
        }
    }





    private void CreateAccount() {
        String name = "";

        while (true) {
            System.out.println("Enter your first name:\n");
            input.nextLine();
            name = input.nextLine().toLowerCase().trim();
            System.out.println("now,Enter your last name:");
            name += input.nextLine().toLowerCase().trim();


            String pattern = "^[a-zA-Z ]+$";
            if (!name.matches(pattern)) {
                System.out.println("Error,Only english alphabets");
                continue;
            }
            break;
        }
        String password="";
        while(true) {
            System.out.println("Enter your password:\n#passord must only contain english alphabets and numbers\n#at least 8 characters");
            password = input.nextLine();
            String pattern = "^[a-zA-Z0-9]+$";
                if (password.matches(pattern)&&password.length()>=8)
                    break;
                else {
                    System.out.println("Not an expected password,try again");
                    continue;
                }
            }
        int fourDigitNumber;
        fourdigitlabel:
        while(true) {
            fourDigitNumber = rand.nextInt(1000, 10000);
            for (usersAccount i : users) {
                if (i.getAccountNumber()==fourDigitNumber)
                    continue fourdigitlabel;
            }
            break;
        }
        System.out.println("Your banking-code is: "+fourDigitNumber+"\nEnter to continue");
        input.nextLine();
            users.add(new usersAccount(name,fourDigitNumber,10000L,password));
            saveUsers();

    }


    private void loadUsers() {
        try {
            File myFile = new File("Users.txt");
            Gson gson = new Gson();
            if (myFile.exists()) {
                Scanner myFileReader = new Scanner(myFile);
                users.clear();
                while (myFileReader.hasNextLine()) {
                    users.add(gson.fromJson(myFileReader.nextLine(), usersAccount.class));
                }
                myFileReader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException, " + e.getMessage());
        }

    }
    private void saveUsers() {
        try {
            File myFile = new File("Users.txt");
            Gson gson = new Gson();
            FileWriter myFileWriter = new FileWriter(myFile);
            if (myFile.exists()) {
                for (int i = 0; i < users.size(); i++) {
                    myFileWriter.write(gson.toJson(users.get(i)));
                    if (i != users.size() - 1) {
                        myFileWriter.write("\n");
                    }
                }
                myFileWriter.close();
            } else {
                myFile.createNewFile();
                for (int i = 0; i < users.size(); i++) {
                    myFileWriter.write(gson.toJson(users.get(i)));
                    if (i != users.size() - 1) {
                        myFileWriter.write("\n");
                    }
                }
                myFileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException, " + e.getMessage());
        }
    }
}