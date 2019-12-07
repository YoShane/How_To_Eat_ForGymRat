/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendBear;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author shane
 */
public class DBconnct {

    String DB_URL = "jdbc:mariadb://localhost:3306/bear_fit";
    Connection con = null;
    Statement sta = null;
    int numRows = 0;
    ResultSet rs = null;
    String error_msg = "";

    public DBconnct() {
        try {
            con = DriverManager.getConnection(DB_URL, "ididididi", "pwpwpwpw");
            //sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sta = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("資料庫操作出問題:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public Statement getSta() {
        return sta;
    }

    public ArrayList<String> getFoodList(String type) {

        ArrayList<String> food_list = new ArrayList<String>();

        String sql = String.format("select *\n" +
        "from food_list\n" +
        "inner join type_food on food_list.fType = tType\n" +
        "where tName like '%s'",type);
        System.out.println(sql);

        try {
            rs = sta.executeQuery(sql);
            if (rs.next() == false) {
                System.out.println("查無資料");
            } else {

                do { //開始輸出
                    food_list.add(rs.getString("fName"));
                } while (rs.next());

                //System.out.println(msg);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫操作出問題:" + ex.toString());
        }
        return food_list;
    }
    
     public String getRandomFoodNum(String type) {

        ArrayList<String> food_list = new ArrayList<String>();

        String sql = String.format("select fId\n" +
        "from food_list\n" +
        "inner join type_food on food_list.fType = tType\n" +
        "where tName like '%s'",type);
        //System.out.println(sql);

        try {
            rs = sta.executeQuery(sql);
            if (rs.next() == false) {
                System.out.println("查無資料");
            } else {
                do { //開始輸出
                    food_list.add(rs.getString("fId"));
                } while (rs.next());
                //System.out.println(msg);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫操作出問題:" + ex.toString());
        }
        
        int size = food_list.size();
        Random rnd = new Random();
        int select = rnd.nextInt(size);
        String getOne = food_list.get(select);
        
        return getOne;
    }
     
     public String getRandomFoodNum(String type, String type2) {

        ArrayList<String> food_list = new ArrayList<String>();

        String sql = String.format("select fId\n" +
        "from food_list\n" +
        "inner join type_food on food_list.fType = tType\n" +
        "where tName like '%s' or tName like '%s'",type,type2);
        //System.out.println(sql);

        try {
            rs = sta.executeQuery(sql);
            if (rs.next() == false) {
                System.out.println("查無資料");
            } else {
                do { //開始輸出
                    food_list.add(rs.getString("fId"));
                } while (rs.next());
                //System.out.println(msg);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫操作出問題:" + ex.toString());
        }
        
        int size = food_list.size();
        Random rnd = new Random();
        int select = rnd.nextInt(size);
        String getOne = food_list.get(select);
        
        return getOne;
    }
     
     public Food getFood(String id) {

        String sql = String.format("select *\n" +
        "from food_list\n" +
        "where fId = '%s'",id);
        Food food = new Food(); 
        //System.out.println(sql);

        try {
            rs = sta.executeQuery(sql);
            if (rs.next() == false) {
                System.out.println("查無資料");
            } else {

                //開始輸出
                food = new Food(rs.getInt("fId"),rs.getString("fName"),rs.getString("fUnit"),rs.getInt("fWeight"),
                        rs.getDouble("fProtein"),rs.getDouble("fFat"),rs.getDouble("fCarbohydrate"), rs.getInt("fLimit"), rs.getString("fType"));

                //System.out.println(msg);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫操作出問題:" + ex.toString());
        }
        
        return  food;
    }
}
