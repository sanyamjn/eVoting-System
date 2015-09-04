package voterid;
import java.sql.*;
class vote {
    void vote(String party)
    {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "vote";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password;
        password = "****"; //password for MySQL login
        try { 
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url+dbName,userName,password);
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE candidate SET noVotes=(noVotes+1) where partyname="
            +"\""+party+"\"");
            conn.close();
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            e.printStackTrace();
        }
    }
}
