/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CPA
 */
public class ServiceClass {
    
    public static ArrayList<Login> LoadUserLogin(String userType)
    {
        ArrayList<Login> list=new ArrayList<Login>();
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset;
            if(userType.equals("Admin"))
                rset = st.executeQuery("select ID as UserID, AdminName as UserName,(Case When Password Is Null Then '' Else Password end) as Password from AdminList order by UserName;");
            else if(userType.equals("Instructor"))
                rset = st.executeQuery("select ID as UserID, IName as UserName,(Case When Password Is Null Then '' Else Password end) as Password from Instructor order by UserName;");
            else if(userType.equals("Student"))
                rset = st.executeQuery("select StdID as UserID, StdName as UserName,(Case When Password Is Null Then '' Else Password end) as Password from Student order by UserName;");
            else
                rset = st.executeQuery("select 0 as UserID, 'Not Registered' as UserName,'' as Password;");
            while (rset.next()) {
                Login c=new Login();
                c.UserType=userType;
                c.UserID=rset.getInt("UserID");
                c.UserName=rset.getString("UserName");
                c.Password=rset.getString("Password");
                list.add(c);
            }                         
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading login data."+ex); 
        }
        return list;
    } 
    public static ArrayList<Course> LoadActiveCourse()
    {
        ArrayList<Course> clist=new ArrayList<Course>();
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select CID,CourseName,CourseStatus from courselist Where CourseStatus like 'Active' order by CourseName;");            
            while (rset.next()) {
                Course c=new Course();
                c.CID=rset.getInt("CID");
                c.CourseName=rset.getString("CourseName");
                c.Status=rset.getString("CourseStatus");
                clist.add(c);
            }                         
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading course."+ex); 
        }
        return clist;
    } 
    public static ArrayList<Course> LoadAllCourse()
    {
        ArrayList<Course> clist=new ArrayList<Course>();
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select CID,CourseName,CourseStatus from courselist order by CourseName;");            
            while (rset.next()) {
                Course c=new Course();
                c.CID=rset.getInt("CID");
                c.CourseName=rset.getString("CourseName");
                c.Status=rset.getString("CourseStatus");
                clist.add(c);
            }                         
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading course."+ex); 
        }
        return clist;
    } 
    public static ArrayList<Level> GetLevelsByCourseName(String coursename)
    {
       ArrayList<Level> list=new ArrayList<Level>();
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select LevelID,LevelName from LevelList Where CourseID=(Select CID from courseList Where CourseName Like '"+coursename+"') Order by LevelID;");            
            while (rset.next()) {
                Level level=new Level();
                level.LevelID=rset.getInt("LevelID");
                level.LevelName=rset.getString("LevelName");
                level.CourseName=coursename;
                list.add(level);
            }                                     
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading course."+ex); 
        }
       return list;
    }
}
class Course
{
      public int CID;
      public String CourseName;
      public String Status;
}
class Level
{
      public int LevelID;
      public String LevelName;
      public String CourseName;
}
class Login
{
   public int UserID;
   public String UserType;
   public String UserName;
   public String Password;
}
