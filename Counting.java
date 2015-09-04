package voterid;
import javax.swing.*;
import java.sql.*;
public class Counting extends JFrame {
    JFrame jframe;
    JPanel graph2()
    {
        JPanel frame = new JPanel();
        frame.setLayout(null);
        
        JLabel party = new JLabel("Party Name");
        party.setLocation(0, 0);
        party.setSize(100, 30);
        party.setHorizontalAlignment(0);
        frame.add(party);
        
        JLabel Candidate = new JLabel("Candidate Name");
        Candidate.setLocation(100, 0);
        Candidate.setSize(100, 30);
        Candidate.setHorizontalAlignment(0);
        frame.add(Candidate);
        
        JLabel count = new JLabel("Counting");
        count.setLocation(200, 0);
        count.setSize(100, 30);
        count.setHorizontalAlignment(0);
        frame.add(count);
        
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "vote";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password;
        password = "****"; //password for MySQL login
        int x=50;
        try { 
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url+dbName,userName,password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM candidate");
            while (res.next()) {
                String pname = res.getString("partyname");
                String ln = res.getString("fname");
                String fn = res.getString("lname");
                String name = fn+ln;
                int  noV = res.getInt("noVotes");
                
                party = new JLabel(pname);
                party.setLocation(0, x);
                party.setSize(100, 30);
                party.setHorizontalAlignment(0);
                frame.add(party);
        
                Candidate = new JLabel(name);
                Candidate.setLocation(100, x);
                Candidate.setSize(100, 30);
                Candidate.setHorizontalAlignment(0);
                frame.add(Candidate);
        
                count = new JLabel(String.valueOf(noV));
                count.setLocation(200, x);
                count.setSize(100, 30);
                count.setHorizontalAlignment(0);
                frame.add(count);
                x=x+50;
            }
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("There is an exception");
        }
        frame.setOpaque(true);
        return frame;                
    }
    void graph()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe = new JFrame("TOtal Tally");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(300,500);
        jframe.setContentPane(graph2());
        jframe.setVisible(true);
    }
}
