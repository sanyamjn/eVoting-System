package voterid;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class addvoter extends JFrame implements ActionListener{
    JButton submit,finish;
    JFrame jframe;
    JTextField vid,fn,ln,address;
    private static final String CHAR_LIST = 
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    void addvote(String vid,String fn, String ln,String address,String pass)
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
            String sql="INSERT INTO voter(voterid,fname,lname,address,pass)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, vid);
            preparedStmt.setString (2, fn);
            preparedStmt.setString (3, ln);
            preparedStmt.setString (4, address);
            preparedStmt.setString (5, pass);
            preparedStmt.execute();
            conn.close();
            JOptionPane.showMessageDialog(null,pass,"Message Dialog",
            JOptionPane.PLAIN_MESSAGE); setVisible(true);
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
        
        JLabel voterid = new JLabel("Voter Id");
        voterid.setLocation(0, 50);
        voterid.setSize(100, 30);
        voterid.setHorizontalAlignment(0);
        frame.add(voterid);
        
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
        
        JLabel add = new JLabel("ADDRESS");
        add.setLocation(0,200);
        add.setSize(100, 30);
        add.setHorizontalAlignment(0);
        frame.add(add);
        
        vid = new JTextField(35);
        vid.setLocation(150,50);
        vid.setSize(100, 30);
        frame.add(vid);
        
        fn = new JTextField(35);
        fn.setLocation(150,100);
        fn.setSize(100, 30);
        frame.add(fn);
        
        ln = new JTextField(35);
        ln.setLocation(150,150);
        ln.setSize(100, 30);
        frame.add(ln);
        
        address = new JTextField(35);
        address.setLocation(150,200);
        address.setSize(100, 30);
        frame.add(address);
        
        submit=new JButton("Submit");
        submit.setLocation(50,250);
        submit.setSize(100,30);
        submit.addActionListener(this);
        frame.add(submit);
        
        finish=new JButton("Finish");
        finish.setLocation(200,250);
        finish.setSize(100,30);
        finish.addActionListener(this);
        frame.add(finish);
        frame.setOpaque(true);
        return frame;
    }
    void graph()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        jframe = new JFrame("Add candidate");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(370,400);
        jframe.setContentPane(graph2());
        jframe.setVisible(true);
        
    }
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    public String generateRandomString(){
         
        String randStr = new String();
        for(int i=0; i<7; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr=randStr+ch;
        }
        return randStr.toString();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source=e.getSource();
        if(source==submit)
        {
         String voter = vid.getText();
         String fname = fn.getText();
         String lname = ln.getText();
         String add=address.getText();
         addvote(voter,fname,lname,add,generateRandomString());
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
