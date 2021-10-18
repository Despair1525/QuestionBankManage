package QuestionBank;

import java.io.*;
import java.util.*;

public class ListProblems {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphaUpperCase = alpha.toUpperCase();
    public static String[] CategoryList = {"PRO", "SSC", "SWQ", "SWR", "VNR"};
    private static final String digits = "0123456789";
    private static String name[] = new String[]{"Mạnh", "Minh", "Việt Anh", "Tiến", "Dương", "Anh", "Giang", "Thương", "Nam", "My", "Đạt", "Huyền", "Phúc", "Hiệp", "Thành", "Hà"};
    private static String Fname[] = new String[]{"Nguyễn", "Phan", "Cao", "Uchiha", "Phạm", "Bá", "Đào"};
    private static String Mname[] = new String[]{"Đức", "Thu", "Mạnh", "Lê", "Huyền", "Nguyệt", "Dương"};
    private static final String ALPHA_NUMERIC = alphaUpperCase + digits;

    public final String fname = "question bank\\Qbs.txt";
     public final String dir = "question bank\\";
    public ArrayList<Problem> list = new ArrayList<>();
    public RedBlackTree listOfProblem = new RedBlackTree();
    public final Random generator = new Random();
    public int sizeOfQUestionBank;

    
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
    int randomNum(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    String[] takeInfo(String str){
        str =str.split("[.]")[0];
        String[] part = str.split("(?<=\\D)(?=\\d)");
        return part;
    };

    public void loadFile() throws IOException {
        int count=0;
        list.clear();
        String s;
        String[] a;
        String xId, xDate, xName, xShortDes, xLink, xAuthor;
        String xCategory ;
        Problem x = new Problem();
        File folder = new File("question bank");
        File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
        if (file.isFile()) {
            String f=file.getName();
            if(f.equals("Qbs.txt")){
            continue;
            };  
         String check= takeInfo(f)[0];
        String fname =dir+""+f;
        FileReader fr = new FileReader(fname);
        BufferedReader br = new BufferedReader(fr);
         while (true) {
            try {
                s = br.readLine();
                if (s == null || s.compareTo("") == 0) {
                    break;
                }
                a = s.split("[|]");
                xId = check+count;
                xDate = reverseDate(java.time.LocalDate.now().toString());
                xName = a[0].trim();
                String key =a[1].trim();
                xCategory = check;
                x = new Problem(xId, xDate, xName,key, xCategory);
                list.add(x);
                listOfProblem.insert(x);
                count +=1;
            } catch (Exception e) {
            }
        }
        sizeOfQUestionBank=count;
        fr.close();
        br.close();
    }
}    
    }

    
    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter(fname, false);
        PrintWriter pw = new PrintWriter(fw);
        Problem x;
//        sort();
        for (int i = 0; i < list.size(); i++) {
            x = list.get(i);
            pw.printf("%15s | %10s | %10s | %10s |%s\n", x.getId(), x.getDate(), x.getCategory(),x.getName(), x.getKey());
        }
        fw.close();
        pw.close();
    }

    public void case5() throws IOException {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("1.Show full Problem by ID");
        System.out.println("2.Exits");
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
                System.out.println("Enter ID: ");
                String ID = input.nextLine().toUpperCase();
                Problem r =listOfProblem.find(ID);
                if(r == null){
                    System.out.println("ID not found !");
                    break;
                }else{
                    showProblem(r);
                };
            }
            case 2: {
                return;
            }
        }
    }

    public void showProblem(Problem r) {
        System.out.printf("%15s|%20s|%20s|%s\n", "ID", "Date","Category", "Key");
        System.out.printf("%15s|%20s|%20s|%s\n", r.getId(), r.getDate(), r.getCategory(),r.Key);
        Contest a = new Contest();
        System.out.println("ALL Description: ");
        a.displayString(r.name);
    }

   
   

//    public void delete() throws IOException {
//        String xId;
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter Id: ");
//        xId = input.nextLine().toUpperCase();
//        if (!checkId(xId)) {
//            int x = FindId(xId);
//            list.remove(x);
//            saveFile();
//            System.out.println(" |Delete sucssess !|");
//        } else {
//            System.out.println("Id not Found !");
//        }
//    }
//
//    public boolean check(String mess) {
//        Scanner input = new Scanner(System.in);
//        String choice;
//        while (true) {
//            System.out.print(mess);
//            choice = input.nextLine().toUpperCase().trim();
//            if (choice.compareTo("Y") == 0 || choice.compareTo("1") == 0) {
//                return true;
//            } else if (choice.compareTo("N") == 0 || choice.compareTo("0") == 0) {
//                return false;
//            } else {
//                System.out.println("Please enter again !");
//            }
//        }
//
//    }

    
    public boolean checkDate(String s) {
        String[] list = s.split("-");
        if (list.length != 3) {
            return false;
        }
        for (String i : list) {
            try {
                int x = Integer.parseInt(i.trim());
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public Problem getProblem(String s) {
        Problem r = new Problem();
        for (Problem i : list) {
            if (i.getId().compareTo(s) == 0) {
                r = i;
            }
        }
        return r;
    }

    public void add() throws IOException { // 3.Add a new problem to the Question Bank (QB) 
        Scanner input = new Scanner(System.in);
        String xId, xDate, xName, xShortDes, xLink, xAuthor;
        double xMark;
        String xCategory;
        System.out.print("Enter problem Id: ");
        xId = input.nextLine().toUpperCase();
        Problem r= listOfProblem.find(xId);
        if (r==null) {
            try {
                Contest lol = new Contest();
                xDate = lol.date;
                System.out.print("Enter problem : ");
                xName = input.nextLine();
                int range =xName.length()/3;
                xShortDes = xName.substring(0,range);
                System.out.print("Enter problem Key: ");
                xLink = input.nextLine();
                System.out.print("Enter problem Category: ");
                xCategory = input.nextLine();
                Problem x = new Problem(xId, xDate, xName, xLink,xCategory);
                
                list.add(x);
                listOfProblem.insert(x);
                sizeOfQUestionBank +=1;
                saveFile();
            } catch (Exception e) {
                System.out.println("ERROR : " + e.toString());
            }

        } else {
            System.err.println("WARNING:Id problems had exits please enter another Id!");
        }
    }



/// Generate Data 
   
   
    public String GenerateName() {
        int a = randomNum(0, (name.length - 1));
        int b = randomNum(0, (Fname.length - 1));
        int c = randomNum(0, (Mname.length - 1));
        StringBuilder s = new StringBuilder();
        s.append(Fname[b] + " " + Mname[c] + " " + name[a]);
        return s.toString();
    }

   

   
    int checkContains(int a,int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i] ==a) return i;
        };
    return -1;
    };
    public ArrayList<Problem> GenerateProblemList(int sizeList) {
        Problem x= new Problem();
        int[] indexLis = new int[sizeList];
        int count=0;
        for(int i=0;i<sizeList;i++){
            indexLis[i]=-1;
    };
        ArrayList<Problem> a = new ArrayList<>();
        while(count != sizeList){
        int num = randomNum(0, (sizeOfQUestionBank- 1));
        if(checkContains(num, indexLis)==-1){
            x =this.list.get(num);
            indexLis[count]= num;
            count +=1;
            a.add(x);
        };
        
        };
        
        Collections.shuffle(a);
        return a;
    }
public String GenerateCode() {
        StringBuilder s = new StringBuilder();
        Random random = new Random();
        int num = randomNum(0, alphaUpperCase.length() - 1);
        char b = alphaUpperCase.charAt(num);
        s.append(b);
        for (int i = 0; i < 2; i++) {
            num = randomNum(0, digits.length() - 1);
            b = digits.charAt(num);
            s.append(b);
        }
        return s.toString();
    }
}
