package voterid;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
public class addcan extends JFrame implements ActionListener {
    JButton submit,finish;
    JTextField cid,fn,ln;
    JFrame jframe;
    void addcan(String pname,String fn, String ln)
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
            String sql="INSERT INTO candidate(partyname,fname,lname)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, pname);
            preparedStmt.setString (2, fn);
            preparedStmt.setString (3, ln);
            preparedStmt.execute();
            conn.close();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    JPanel graph2()
    {
        JPanel frame = new JPanel();
        frame.setLayout(null);
        
        JLabel partyname = new JLabel("Party Name");
        partyname.setLocation(0, 50);
        partyname.setSize(100, 30);
        partyname.setHorizontalAlignment(0);
        frame.add(partyname);
        
        JLabel fname = new JLabel("First Name");
        fname.setLocation(0,100);
        fname.setSize(100, 30);
        fname.setHorizontalAlignment(0);
        frame.add(fname);
        
        JLabel lname = new JLabel("Last Name");
        lname.setLocation(0,150);
        lname.setSize(100, 30);
        lname.setHorizontalAlignment(0);
        frame.add(lname);
        
        cid = new JTextField(35);
        cid.setLocation(150,50);
        cid.setSize(100, 30);
        frame.add(cid);
        
        fn = new JTextField(35);
        fn.setLocation(150,100);
        fn.setSize(100, 30);
        frame.add(fn);
        
        ln = new JTextField(35);
        ln.setLocation(150,150);
        ln.setSize(100, 30);
        frame.add(ln);
        
        submit=new JButton("Submit");
        submit.setLocation(50,200);
        submit.setSize(100,30);
        submit.addActionListener(this);
        frame.add(submit);
        
        finish=new JButton("Finish");
        finish.setLocation(200,200);
        finish.setSize(100,30);
        finish.addActionListener(this);
        frame.add(finish);
        frame.setOpaque(true);
        return frame;
    }
    void graph()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe = new JFrame("Add Candidate");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(370,400);
        jframe.setContentPane(graph2());
        jframe.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source=e.getSource();
        if(source==submit)
        {
         String party = cid.getText();
         String fname = fn.getText();
         String lname = ln.getText();
         addcan(party,fname,lname);
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
