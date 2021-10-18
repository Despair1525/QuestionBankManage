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
    public String id,Date,name,ShortDes,Key;
    public String Category;
public Problem() {
        this.id = "";
        this.Date = "";
        this.name = "";
        this.ShortDes = "";
        this.Key = "";
        this.Category = "";
    }
    public Problem(String id, String Date, String name, String key, String Category) {
        this.id = id;
        this.Date = Date;
        this.name = name;
        this.ShortDes = name.substring(0,name.length()/3);
        this.Key = key;
        this.Category = Category;
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

    public String getKey() {
        return Key;
    }

    public void setKey(String link) {
        this.Key = link;
    }

   

    public String getCategory() {
        return this.Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
    
    String[] takeInfo(String str){
        String[] part = str.split("(?<=\\D)(?=\\d)");
        return part;
    };
    
    @Override
    public String toString() {
        return String.format("%15s|%10s|%10s|%50s|%s\n", getId(), getDate(),getCategory(),getShortDes(),getKey());
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
