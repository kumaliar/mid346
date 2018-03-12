package com.company;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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


    public static void getCD(String web) throws Exception {
        ArrayList<Section> classes = new ArrayList<Section>();
        Section class1 = new Section();
        String dP = "([0-9]*)\\.([0-9]*)";
        java.sql.Connection con = getConnection();
        Connection.Response res = Jsoup.connect(web).method(Connection.Method.POST).timeout(10 * 10000)
                .data("subject", "all")
                .data("display_close", "YES")
                .data("course_type", "ALL")
                .followRedirects(true).execute();

        Document doc = res.parse();
        Elements detailrows = doc.select("span.course_messages");
        Elements seats = doc.select("span.course_seats");
        Elements terms = doc.select("span.course_term");
        Elements rows = doc.select("tr.list_row");
        for (Element row : rows) {
            Elements cells = row.select("td").select("td");
            PreparedStatement posted = con.prepareStatement("INSERT INTO sections(department,courseId,semester,crn,discipline,courseNumber,section,type,title,hours,beginDate,endDate,url"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,)");

        }
    }
}
class Section{
    String department;
   String courseId,semester,crn,discipline,courseNumber,section,type,title,hours,beginDate,endDate,url;
    public Section(){
    department=null;
    }

    @Override
    public String toString() {
        return "Section{" +
                "department='" + department + '\'' +
                ", courseId='" + courseId + '\'' +
                ", semester='" + semester + '\'' +
                ", crn='" + crn + '\'' +
                ", discipline='" + discipline + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", section='" + section + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", hours='" + hours + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}