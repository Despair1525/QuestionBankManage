
import QuestionBank.*;
import java.io.IOException;
import java.util.*;
import java.util.*;
import Cms.*;
//he151274	0983569165
//hs153019	0881730654a
//he150883	0389621169
//he153125	0326484853
//ha153020	

//Anh|he151274|0983569165|acbd|1234|1
//Phuc Ha|hs153019|0881730654a|phucha|1234|1
//Tien|he153125|0326484853|quetien|1234|1
//Van|ha153020|0392843701|thaovan|1234|1
//Manh|he150883|0389621169|ducmanh|1234|1
public class Main {

    public static String id;
    public static ContestantMethod a = new ContestantMethod();
    public static Contest b = new Contest();
    public static ListProblems LP = new ListProblems();

    public static boolean check(String mess) {
        Scanner input = new Scanner(System.in);
        String choice;
        while (true) {
            System.out.print(mess);
            choice = input.nextLine().toUpperCase().trim();
            if (choice.compareTo("Y") == 0 || choice.compareTo("1") == 0) {
                return true;
            } else if (choice.compareTo("N") == 0 || choice.compareTo("0") == 0) {
                return false;
            } else {
                System.out.println("Please enter again !");
            }
        }

    }

    public static boolean check2() {
        Scanner input = new Scanner(System.in);
        String choice;
        while (true) {
            System.out.print("Do you want to save this contest ? (Y/N) : ");
            choice = input.nextLine().toUpperCase().trim();
            if (choice.compareTo("Y") == 0 || choice.compareTo("1") == 0) {
                return true;
            } else if (choice.compareTo("N") == 0 || choice.compareTo("0") == 0) {
                return false;
            } else {
                System.out.println("Please enter again !");
            }
        }

    }


    public static void login() throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("【﻿ｌｏｇｉｎ】");
        while (true) {
            System.out.print("Enter your id: ");
            id = input.nextLine().toLowerCase().trim();
            System.out.print("Enter your password: ");
            String pw = input.nextLine().trim();
            boolean check = a.login(id, pw);
            if (check) {
                break;
            } else {
                System.out.println("Please Check your ID or Password !");
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        
        Scanner input = new Scanner(System.in);

        LP.loadFile();
        
        login();

        boolean check;
        //------------------------------------------
        while (true) {
            System.out.println("====================ContestManagementSystem=====================");
            System.out.println("== Number of Question: "+LP.sizeOfQUestionBank);
            System.out.println("1.Show information of Contestant");
            System.out.println("--------Manage the question-----------");
            System.out.println("2.Add a new problem to the Question Bank (QB) ");
            System.out.println("3.Delete a problem by ID");
            System.out.println("4.A list of all available Question in the QB");
            System.out.println("5.Generate a new Contest");
            System.out.println("================================================================");
            System.out.println("6.Log Out");
            System.out.println("0.Exits");
            int choice = 0;
            while (true) {
                try {
                    System.out.print("Your choice: ");
                    choice = Integer.parseInt(input.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter number !");
                }
            }
            switch (choice) {
                case 1: {
                    a.showInfor(id);

                    check = check("Do you want to contunue process ? (Y/N): ");
                    if (!check) {
                        System.exit(0);
                    }
                    break;
                }

                case 2: {
                    LP.add();

                    check = check("Do you want to continue process ? (Y/N) : ");
                    if (check) {
                        break;
                    } else {
                        System.exit(0);
                    }
                }
//                case 3:{
//                     LP.delete();
//                    check = check("Do you want to continue process ? (Y/N) : ");
//                    if (check) {
//                        break;
//                    } else {
//                        System.exit(0);
//                    }
//                }
                case 4: {
                    LP.listOfProblem.display();
                    while (true) {
                        LP.case5();
                        if (check("Do you want to continue process ? (Y/N) : ")) {
                            break;
                        }
                        else{
                        System.exit(0);
                        }
                    }
                    break;
                }
                case 5: {
                    int size;
                    while (true) {
                        try {
                            System.out.print("Enter number of question: ");
                            size = Integer.parseInt(input.nextLine().trim());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter number !");
                        }
                    }
                    
                    b = b.GenerateContest(a.getNameInfo(),size);
                    b.display();
                    if (check2()) {
                        b.export();
                    }
                    if (!check("Do you want to continue process ? (Y/N) : ")) {
                        System.exit(0);
                    }
                    break;
                }
                case 6: {
                    login();
                    break;
                }    
                case 0: {
                    return;
                }
                
            }
           
        }
    }
}
