package QuestionBank;

import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smileymask
 */
public class Problem implements Comparable<Problem>{
    public static String[] CategoryList= {"analysis","figure","Greedy algorithm","Dynamic programming","graph"};
    public String id,Date,name,ShortDes,link,author;
    public double mark;
    public int Category;
public Problem() {
        this.id = "";
        this.Date = "";
        this.name = "";
        this.ShortDes = "";
        this.link = "";
        this.mark = 0;
        this.Category = 0;
        this.author="";
    }
    public Problem(String id, String Date, String name, String ShortDes, String link,String author,double mark, int Category) {
        this.id = id;
        this.Date = Date;
        this.name = name;
        this.ShortDes = ShortDes;
        this.link = link;
        this.mark = mark;
        this.Category = Category;
        this.author=author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDes() {
        return ShortDes;
    }

    public void setShortDes(String ShortDes) {
        this.ShortDes = ShortDes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getCategory() {
        return CategoryList[Category-1];
    }

    public void setCategory(int Category) {
        this.Category = Category;
    }
    
    String[] takeInfo(String str){
        String[] part = str.split("(?<=\\D)(?=\\d)");
        return part;
    };
    
    @Override
    public String toString() {
        return String.format("%5s|%10s|%20s|%25s|%5.1f|%20s|%s\n", getId(), getDate(), getName(), getAuthor(),getMark(),getCategory(),getLink());
    }

    @Override
    public int compareTo(Problem o) {
        String[] values1 = takeInfo(this.id);
        String[] values2 = takeInfo(o.id);
        if( values1[0].equals(values2[0])){
            int a = Integer.parseInt(values1[1]);
            int b = Integer.parseInt(values2[1]);
            if (a==b) return 0;
            if(a<b) return -1;
            if(a>b) return 1;
        };
        int r=values1[0].compareTo(values2[0]);
        if(r<0) return -1;
        return 1;
    }
 
    
    
}
