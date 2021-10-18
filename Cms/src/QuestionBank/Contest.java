package QuestionBank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Contest {

    public String id, nameMake;
    public String timeStamp = java.time.LocalTime.now().toString();
    public ArrayList<Problem> QuestionList = new ArrayList<Problem>();
    public String date = reverseDate(java.time.LocalDate.now().toString());
    public ListProblems a = new ListProblems();
    public ArrayList<Contest> testBank = new ArrayList<>();
    public final String f_e = "File Export";

    public Contest() {
        this.id = "";
        this.nameMake = "";

    }

  

    public Contest(String id, String nameMake, ArrayList<Problem> QuestionList) {
        this.id = id.trim();
        this.nameMake = trim(nameMake);
        this.QuestionList = QuestionList;
    }

    public String trim(String s) {
        StringTokenizer st = new StringTokenizer(s, " ");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken() + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String reverseDate(String date) {
        String list[] = date.split("-");
        StringBuilder s = new StringBuilder();
        for (int i = (list.length - 1); i >= 0; i--) {
            if (i == 0) {
                s.append(list[i]);
                break;
            }
            s.append(list[i] + "-");
        }
        return s.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameMake() {
        return nameMake;
    }

    public void setNameMake(String nameMake) {
        this.nameMake = nameMake;
    }

    public ArrayList<Problem> getQuestionList() {
        return QuestionList;
    }

    public void setQuestionList(ArrayList<Problem> QuestionList) {
        this.QuestionList = QuestionList;
    }

    public Contest GenerateContest(String name,int num) throws IOException {
        a.loadFile();
        int r = 0;
        String idX = a.GenerateCode();
        ArrayList<Problem> Lproblem = a.GenerateProblemList(num);
        for (Problem i : Lproblem) {
            r += 1;
        }
        Contest a = new Contest(idX, name, Lproblem);
        return a;
    }

    public void displayString(String s) {
        String list[] = s.split(" ");
        int cout = 0;
        System.out.print("\t");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
            cout++;
            if (cout == 10) {
                System.out.println("");
                System.out.print("\t");
                cout = 0;
            }
        }
        System.out.println("");
    }

    public void display() {
        int cout = 1;
        System.out.println("--------------------TEST-----------------------------");
        System.out.println("ID: " + getId());
        System.out.println("Date: " + getDate());
        System.out.println("Maker name: " + getNameMake());
        System.out.println("");
        for (Problem i : QuestionList) {
            System.out.println("------------");
            System.out.println("|Question " + cout + "| " + i.id.toUpperCase());
            System.out.println("------------");
            System.out.print("\tDescription: ");
            displayString(i.name);
            System.out.println("");
            cout++;
        }
        System.out.println("----------------------------------------------");
    }
// --------------------Test Bank -------------------------
//|Time Stamp| ID Test | Date  | Total Mark |  Maker Name  

   

   

    public void export() throws IOException {
        Scanner input = new Scanner(System.in);
        String name = new String();
        File file = null;
        boolean isCreat = false;
        String Testname=this.id+ "Test.txt";
        try {
            file = new File("test\\"+Testname);
            isCreat = file.createNewFile();
            if (isCreat) {
                try (FileWriter fw = new FileWriter(file)) {
                    PrintWriter pw = new PrintWriter(fw);
                    int cout = 1;
                    pw.println("--------------------TEST-----------------------------");
                    pw.println("ID: " + getId());
                    pw.println("Date: " + getDate());
                    pw.println("Maker name: " + getNameMake());
                    pw.println("");
                    int r = 0;
                    for (Problem i : QuestionList) {
                        pw.println("------------");
                        pw.println("|Question " + cout + "| " + i.getId().toUpperCase());
                        pw.println("------------");
                        pw.print("\tDescription: ");
                        String list[] = i.name.split(" ");
                        int cout2 = 0;
                        for (int j = 0; j < list.length; j++) {
                            pw.print(list[j] + " ");
                            cout2++;
                            if (cout2 == 10) {
                                pw.println("");
                                pw.print("\t");
                                cout = 0;
                            }

                        }

                        pw.println("");
                        cout++;
                        r += 1;
                    }
                    pw.println("Total: " + r);
                    pw.println("----------------------------------------------");
                    pw.close();
                }
                String dir= System.getProperty("user.dir");
                System.out.println(dir);
                System.out.println("||Save File Succsess !||");
                System.out.println("File has been saved at: " +dir + "\\test\\" + Testname);
                System.out.println("");
            } else {
                System.out.println("File has exits !");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
