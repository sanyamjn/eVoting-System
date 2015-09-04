package voterid;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class userI extends JFrame implements ActionListener{
    JLabel User;
    JPanel titlePanel1,buttonPanel1,TextPanel1,titlePanel2,buttonPanel2,TextPanel2;
    JPanel titlePanel3,titlePanel4;
    JButton admin,voter,submit1,submit2,addcan,addvoter,delV,delC,delAC,finish1,count,delAV;
    JButton vote[]=new JButton[9];
    JTextField useri;
    JPasswordField pass;
    static JFrame frame;
    String[] party=new String[9];
    vote v=new vote();
    
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "vote";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password="****"; //password for MySQL login
        
    JPanel Voter()
            {
                JPanel totalGUI = new JPanel();
                totalGUI.setLayout(null);
                
                titlePanel1 = new JPanel();
                titlePanel1.setLayout(null);
                titlePanel1.setLocation(10, 0);
                titlePanel1.setSize(110, 300);
                totalGUI.add(titlePanel1);
        
                User = new JLabel("User Type");
                User.setLocation(0, 0);
                User.setSize(100, 30);
                User.setHorizontalAlignment(0);
                titlePanel1.add(User);
        
                JLabel UserId = new JLabel("Voter Id");
                UserId.setLocation(0, 50);
                UserId.setSize(100, 30);
                UserId.setHorizontalAlignment(0);
                titlePanel1.add(UserId);
                
                JLabel Password = new JLabel("Password");
                Password.setLocation(0, 100);
                Password.setSize(100, 30);
                Password.setHorizontalAlignment(0);
                titlePanel1.add(Password);
        
                buttonPanel1 = new JPanel();
                buttonPanel1.setLayout(null);
                buttonPanel1.setLocation(110, 0);
                buttonPanel1.setSize(300, 30);
                totalGUI.add(buttonPanel1);

                voter = new JButton("Voter");
                voter.setLocation(50, 0);
                voter.setSize(100, 30);
                voter.addActionListener(this);
                buttonPanel1.add(voter);

                admin = new JButton("Admin");
                admin.setLocation(170, 0);
                admin.setSize(100, 30);
                admin.addActionListener(this);
                buttonPanel1.add(admin);
                
                TextPanel1 = new JPanel();
                TextPanel1.setLayout(null);
                TextPanel1.setLocation(110,30);
                TextPanel1.setSize(300, 270);
                totalGUI.add(TextPanel1);
                
                useri = new JTextField(35);
                useri.setLocation(70,20);
                useri.setSize(100, 30);
                TextPanel1.add(useri);
                
                pass = new JPasswordField(35);
                pass.setLocation(70,70);
                pass.setSize(100, 30);
                TextPanel1.add(pass);
                
                submit1 = new JButton("Submit");
                submit1.setLocation(70, 150);
                submit1.setSize(100, 30);
                submit1.addActionListener(this);
                TextPanel1.add(submit1);

                totalGUI.setOpaque(true);
                return totalGUI;
                
            }
    
    JPanel Admin()
            {
                JPanel totalGUI = new JPanel();
                totalGUI.setLayout(null);
                
                titlePanel2 = new JPanel();
                titlePanel2.setLayout(null);
                titlePanel2.setLocation(10, 0);
                titlePanel2.setSize(110, 300);
                totalGUI.add(titlePanel2);
        
                User = new JLabel("User Type");
                User.setLocation(0, 0);
                User.setSize(100, 30);
                User.setHorizontalAlignment(0);
                titlePanel2.add(User);
        
                JLabel UserId = new JLabel("Username");
                UserId.setLocation(0, 50);
                UserId.setSize(100, 30);
                UserId.setHorizontalAlignment(0);
                titlePanel2.add(UserId);
                
                JLabel Password = new JLabel("Password");
                Password.setLocation(0, 100);
                Password.setSize(100, 30);
                Password.setHorizontalAlignment(0);
                titlePanel2.add(Password);
        
                buttonPanel2 = new JPanel();
                buttonPanel2.setLayout(null);
                buttonPanel2.setLocation(110, 0);
                buttonPanel2.setSize(300, 30);
                totalGUI.add(buttonPanel2);

                voter = new JButton("Voter");
                voter.setLocation(50, 0);
                voter.setSize(100, 30);
                voter.addActionListener(this);
                buttonPanel2.add(voter);

                admin = new JButton("Admin");
                admin.setLocation(170, 0);
                admin.setSize(100, 30);
                admin.addActionListener(this);
                buttonPanel2.add(admin);
                
                TextPanel2 = new JPanel();
                TextPanel2.setLayout(null);
                TextPanel2.setLocation(110,30);
                TextPanel2.setSize(300, 270);
                totalGUI.add(TextPanel2);
                
                useri = new JTextField(35);
                useri.setLocation(70,20);
                useri.setSize(100, 30);
                TextPanel2.add(useri);
                
                pass = new JPasswordField(35);
                pass.setLocation(70,70);
                pass.setSize(100, 30);
                TextPanel2.add(pass);
                
                submit2 = new JButton("Submit");
                submit2.setLocation(70, 150);
                submit2.setSize(100, 30);
                submit2.addActionListener(this);
                TextPanel2.add(submit2);

                totalGUI.setOpaque(true);
                return totalGUI;
                
            }
    
    JPanel votingW()
    {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
                
        titlePanel3 = new JPanel();
        titlePanel3.setLayout(null);
        titlePanel3.setLocation(0, 0);
        titlePanel3.setSize(550, 310);
        totalGUI.add(titlePanel3);
        
        JLabel Cand=new JLabel("Candidate Name");
        Cand.setLocation(0, 0);
        Cand.setSize(100, 30);
        Cand.setHorizontalAlignment(0);
        titlePanel3.add(Cand);
        
        JLabel Party=new JLabel("PartyName");
        Party.setLocation(100,0);
        Party.setSize(150,30);
        Party.setHorizontalAlignment(0);
        titlePanel3.add(Party);
        int x=35,i;
        try { 
            
            Class.forName(driver).newInstance();
            try (Connection conn = DriverManager.getConnection(url+dbName,userName,password)) {
                Statement st = conn.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM candidate");
                i=0;
                while (res.next()) {
                    party[i] = res.getString("partyname");
                    String fname = res.getString("fname");
                    String lname = res.getString("lname");
                    String name = fname+lname;
                    Cand=new JLabel(name);
                    Cand.setLocation(0, x);
                    Cand.setSize(100, 30);
                    Cand.setHorizontalAlignment(0);
                    titlePanel3.add(Cand);
                    
                    Party=new JLabel(party[i]);
                    Party.setLocation(100,x);
                    Party.setSize(150,30);
                    Party.setHorizontalAlignment(0);
                    titlePanel3.add(Party);
                    
                    vote[i] = new JButton("Vote");
                    vote[i].setLocation(250,x);
                    vote[i].setSize(300, 30);
                    vote[i].addActionListener(this);
                    titlePanel3.add(vote[i]);
                    i++;x=x+30;
                }
            }
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println("There is an exception");
        }
        totalGUI.setOpaque(true);
        return totalGUI;
    }
    
    JPanel adminW(){
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        
        totalGUI.setLocation(0,0);
        totalGUI.setSize(380,300);
        
        addcan=new JButton("Add Candidate");
        addcan.setLocation(30,30);
        addcan.setSize(150,30);
        addcan.addActionListener(this);
        totalGUI.add(addcan);
        
        addvoter=new JButton("Add Voter");
        addvoter.setLocation(200,30);
        addvoter.setSize(150,30);
        addvoter.addActionListener(this);
        totalGUI.add(addvoter);
        
        delC=new JButton("Delete Candidate");
        delC.setLocation(30,100);
        delC.setSize(150,30);
        delC.addActionListener(this);
        totalGUI.add(delC);
        
        delV=new JButton("Delete Voter");
        delV.setLocation(200,100);
        delV.setSize(150,30);
        delV.addActionListener(this);
        totalGUI.add(delV);
        
        delAV=new JButton("Delete All Voter");
        delAV.setLocation(30,170);
        delAV.setSize(150,30);
        delAV.addActionListener(this);
        totalGUI.add(delAV);
        
        delAC=new JButton("Delete All Candidate");
        delAC.setLocation(200,170);
        delAC.setSize(150,30);
        delAC.addActionListener(this);
        totalGUI.add(delAC);
        
        count=new JButton("Count");
        count.setLocation(30,230);
        count.setSize(150,30);
        count.addActionListener(this);
        totalGUI.add(count);
        
        finish1=new JButton("Finish");
        finish1.setLocation(200,230);
        finish1.setSize(150,30);
        finish1.addActionListener(this);
        totalGUI.add(finish1);
        
        totalGUI.setOpaque(true);
        return totalGUI;
    }
    static void VotingFrame(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Voting System");

        userI demo = new userI();
        frame.setContentPane(demo.votingW());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 310);
        frame.setVisible(true);
    }
    
    static void AdminFrame(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Voting System");

        //Create and set up the content pane.
        userI demo = new userI();
        frame.setContentPane(demo.adminW());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380,300);
        frame.setVisible(true);
    }
    static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Voting System");

        //Create and set up the content pane.
        userI demo = new userI();
        frame.setContentPane(demo.Voter());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 310);
        frame.setVisible(true);
    }
    static void createAndShowGUI2() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Voting System");

        //Create and set up the content pane.
        userI demo = new userI();
        frame.setContentPane(demo.Admin());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 310);
        frame.setVisible(true);
    }
    void wrongpassmsg()
    {
        JOptionPane.showMessageDialog(null,"Wrong Password","Message Dialog",
        JOptionPane.PLAIN_MESSAGE); setVisible(true);
        frame.dispose();
    }
    void wrongusermsg()
    {
        JOptionPane.showMessageDialog(null,"Wrong Username","Message Dialog",
        JOptionPane.PLAIN_MESSAGE); setVisible(true);
        frame.dispose();
    }
    void AlreadyVoted()
    {
        JOptionPane.showMessageDialog(null,"You have already voted","Message Dialog",
        JOptionPane.PLAIN_MESSAGE); setVisible(true);
        frame.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if (source==admin)
        {        
            frame.dispose();
            createAndShowGUI2();
        }
        else if(source==voter)
        {
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==submit1)
        {
            try { 
                frame.dispose();
                Class.forName(driver).newInstance();
                Connection conn = DriverManager.getConnection(url+dbName,userName,password);
                Statement st = conn.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM voter");
                while (res.next()) {
                    String voterid = res.getString("voterid");
                    String pass1 = res.getString("pass");
                    String voterid2 = useri.getText();
                    String pass2= pass.getText();
                    int i=0;
                    if(voterid.equals(voterid2))
                    {
                        i=1;
                        if(pass1.equals(pass2))
                        {
                            int voted=res.getInt("voted");
                            if(voted==0)
                            {   
                                st.executeUpdate("UPDATE voter SET voted=1 where voterid="
                                    +"\""+voterid+"\"");
                                frame.dispose();
                                VotingFrame();
                            }
                            else
                            {
                                frame.dispose();
                                AlreadyVoted();
                                createAndShowGUI();
                                break;
                            }
                        }
                        else
                        {
                            wrongpassmsg();
                            frame.dispose();
                            createAndShowGUI();
                            break;
                        }
                    }
                    if(i==0)
                    {
                        wrongusermsg();
                        createAndShowGUI();
                    }
                }
                conn.close();
            }
            catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
            {
                System.out.println("There is an exception");
            }
        }
        else if(source==submit2)
        {
            String admin1 = "admin";
            String pass1 = "admin";
            String admin2 = useri.getText();
            String pass2= pass.getText();
            int i=0;
            if(admin1.equals(admin2))
            {
                i=1;
                if(pass1.equals(pass2))
                {
                    frame.dispose();
                    AdminFrame();
                }
                else
                {
                    wrongpassmsg();
                    createAndShowGUI2();
                }
            }
            if(i==0)
            {
                wrongusermsg();
                createAndShowGUI();
            }
        }
        else if(source==finish1)
        {
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[1])
        {
            v.vote(party[1]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[2])
        {
            v.vote(party[2]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[3])
        {
            v.vote(party[3]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[4])
        {
            v.vote(party[4]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[5])
        {
            v.vote(party[5]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[6])
        {
            v.vote(party[6]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[7])
        {
            v.vote(party[7]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[8])
        {
            v.vote(party[8]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==vote[0])
        {
            v.vote(party[0]);
            frame.dispose();
            createAndShowGUI();
        }
        else if(source==addcan)
        {
            frame.dispose();
            addcan a=new addcan();
            a.graph();
        }
        else if(source==addvoter)
        {
            frame.dispose();
            addvoter a=new addvoter();
            a.graph();
        }
        else if(source==delV)
        {
           deleteV d=new deleteV();
           frame.dispose();
           d.graph();
        }
        else if(source==delC)
        {
           deleteC d=new deleteC();
           frame.dispose();
           d.graph();
        }
        else if(source==delAC)
        {
            try { 
                Class.forName(driver).newInstance();
                Connection conn = DriverManager.getConnection(url+dbName,userName,password);
                Statement st = conn.createStatement();
                st.executeQuery("TRUNCATE candidate");
                conn.close();
            }
            catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex)
            {
                System.out.println("There is an exception");
            }
        }
        else if(source==delAV)
        {
            try { 
                Class.forName(driver).newInstance();
                Connection conn = DriverManager.getConnection(url+dbName,userName,password);
                Statement st = conn.createStatement();
                st.executeQuery("TRUNCATE voter");
                conn.close();
            }
            catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex)
            {
                System.out.println("There is an exception");
            }
        }
        else if(source==count)
        {
         Counting c= new Counting();
         c.graph();
        }
    }
}
