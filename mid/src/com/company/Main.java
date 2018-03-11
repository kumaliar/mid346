package com.company;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {
        String web = "https://aps2.missouriwestern.edu/schedule/default.asp?tck=201910";
        getCD(web);
    }

    public static java.sql.Connection getConnection() throws Exception {
        try {
            String dbFile = "jdbc:sqlite:schedule .db";
            java.sql.Connection conn = DriverManager.getConnection(dbFile);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Connected!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return null;
    }


    public static void getCD(String web) throws Exception{
        ArrayList<Section> classes= new ArrayList<Section>();
        Section class1=new Section();
        String dP = "([0-9]*)\\.([0-9]*)";
        java.sql.Connection con=getConnection();
        Connection.Response res=  Jsoup.connect(web).method(Connection.Method.POST).timeout(10*10000)
            .data("subject","all")
                .data("display_close","YES")
                .data("course_type","ALL")
                .followRedirects(true).execute();

        Document doc=res.parse();
    }
}
class Section{

    public Section(){

    }

        }