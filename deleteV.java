package voterid;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
public class deleteV extends JFrame implements ActionListener {
    JFrame jframe;
    JTextField vote;
    JButton submit,finish;
    void delV(String Voterid)
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
            String SQL="DELETE FROM voter WHERE voterid=\""+Voterid+"\"";
            st.executeUpdate(SQL);
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("There is an error");
        }
    }
    JPanel graph2()
    {
        JPanel frame = new JPanel();
        frame.setLayout(null);
        
        JLabel Voter = new JLabel("Voter Id");
        Voter.setLocation(0, 50);
        Voter.setSize(100, 30);
        Voter.setHorizontalAlignment(0);
        frame.add(Voter);
        
        vote=new JTextField(35);
        vote.setLocation(150,50);
        vote.setSize(100,30);
        frame.add(vote);
        
        submit=new JButton("Submit");
        submit.setLocation(50,100);
        submit.setSize(100,30);
        submit.addActionListener(this);
        frame.add(submit);
        
        finish=new JButton("Finish");
        finish.setLocation(200,100);
        finish.setSize(100,30);
        finish.addActionListener(this);
        frame.add(finish);
        
        frame.setOpaque(true);
        return frame;
    }
    void graph()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe = new JFrame("Delete Voter");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(350,200);
        jframe.setContentPane(graph2());
        jframe.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source=e.getSource();
        if(source==submit)
        {
         String voterid = vote.getText();
         delV(voterid);
         jframe.dispose();
         graph();
        }
        else if(source==finish)
        {
            jframe.dispose();
            userI u = new userI();
            u.AdminFrame();
        }
    }
}
