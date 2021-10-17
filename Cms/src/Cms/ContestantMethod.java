package Cms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ContestantMethod {

    public HashMap<String, String> c = new HashMap<String, String>();
    public ArrayList<Contestant> c1 = new ArrayList<>();
    public String s = "User data\\constestants.txt";
    public String nameInfo;

    public void LoadFile(String file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        c.clear();
        c1.removeAll(c1);
        String s;
        String s1[];
        String id, name, email, mobilephone;
        String password;
        int rank;
        while (true) {
            try {
                s = br.readLine();

                if (s == null) {
                    break;
                }
                if (s.compareToIgnoreCase("") == 0) {
                    break;
                }
                s1 = s.split("\\|");
                name = s1[0];
                id = s1[1];
                password = s1[2];
                email = s1[3];
                mobilephone = s1[4];
                rank = Integer.parseInt(String.valueOf(s1[5]));
                c.put(id, password);
                Contestant e = new Contestant(name, id, password, email, mobilephone, rank);
                c1.add(e);
            } catch (Exception e) {
            }

        }
        fr.close();
        br.close();
    }

    public void savefile(String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(file);
        Contestant m;
        for (int i = 0; i < c1.size(); i++) {
            m = c1.get(i);
            pw.print(c1.get(i).getName() + "|" + c1.get(i).getId() + "|" + c1.get(i).getPassword()
                    + "|" + c1.get(i).getEmail() + "|" + c1.get(i).getMobilephone() + "|" + c1.get(i).getRank() + "\n");
        }
        fw.close();
        pw.close();
    }

    public boolean check(String mess) {
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

    public void showTable(Contestant chane) {
        StringBuilder sb = new StringBuilder(chane.getPassword());
        for (int i = 2; i < sb.length() - 2; i++) {
            sb.setCharAt(i, '*');
        }
        System.out.printf("%15s|%15s|%30s|%15s|%4s|%15s\n", "ID", "Name", "Email", "Phone number", "Rank", "Password");
        System.out.printf("%15s|%15s|%30s|%15s|%4s|%15s\n", chane.getId(), chane.getName(), chane.getEmail(), chane.getMobilephone(), chane.getRank(), sb.toString());
    }

//   
    public boolean login(String id, String pw) throws IOException {
        LoadFile(s);
        if (c.containsKey(id)) {
            if (pw.equals(c.get(id))) {
                for (Contestant i : c1) {
                    if (i.getId().compareTo(id) == 0) {
                        setNameInfo(i.getName());
                    }
                }
                return true;
            }
        }
        return false;

    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void print() {
        for (String i : c.keySet()) {
            System.out.println(i + "\t" + c.get(i));
        }
    }

    public void print2() {
        for (int i = 0; i < c1.size(); i++) {
            System.out.println(c1.get(i));
        }
    }

    public void showInfor(String id) {
        int index = 0;
        for (int i = 0; i < c1.size(); i++) {
            if (id.equals(c1.get(i).getId())) {
                index = i;
                System.out.println("==========Contestant's Informations==========");
                System.out.println(c1.get(i).toString());
                System.out.println("==============================================");
                break;
            }
        }
    }
}
