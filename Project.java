import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

class Student implements Serializable
{
    String name,age,sem,roll,cpi;
    Student(String name, String age, String sem, String roll, String cpi)
    {
        this.name = name;
        this.age = age;
        this.sem = sem;
        this.roll = roll;
        this.cpi = cpi;
    }
    String getDetails() {
		return ""+name+" "+age+" "+sem+" "+roll+" "+cpi;
				
	}
}

class Menu extends JFrame implements ActionListener
{
	JLabel l;  
    JButton b1, b2;
    
    Menu()
    {
    	super("WELCOME!");  
    	
        l=new JLabel("Choose");  
        l.setBounds(50,70,150,20);
          
        b1=new JButton("ADMIN");  
        b1.setBounds(50,150,80,30); 
        
        b1.addActionListener(this); 

        b2=new JButton("USER");  
        b2.setBounds(50,200,80,30); 
        
        b2.addActionListener(this);
        
        
        add(l);   
        add(b1);
        add(b2);
        
        setSize(300,400);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(Menu.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    { 
        if(e.getActionCommand()=="ADMIN")
        {
            new Admin();
        }
        else
        {
            new User();
        }
    }
}

class Admin extends JFrame implements ActionListener
{
    JLabel l;  
    JButton b1, b2, b3;
    Admin()
    {
        super("Welcome Admin!");  
    	
        l = new JLabel("Choose");  
        l.setBounds(50,70,150,20);
          
        b1=new JButton("Add Details");  
        b1.setBounds(50,150,150,30); 
        
        b1.addActionListener(this); 

        b2=new JButton("Delete Details");  
        b2.setBounds(50,200,150,30); 
        
        b2.addActionListener(this);
        
        b3=new JButton("Modify Details");  
        b3.setBounds(50,250,150,30); 
        
        b3.addActionListener(this); 
        
        add(l);   
        add(b1);
        add(b2);
        add(b3);
        
        setSize(300,500);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(Admin.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    { 
        if(e.getActionCommand()=="Add Details")
        {
            new AddDetails();
        }
        else if(e.getActionCommand()=="Modify Details")
        {
            new ModifyDetails();
        }
        else
        {
            new DeleteDetails();
        }
    }
}
class DeleteDetails extends JFrame implements ActionListener
{
    JLabel l;
    JButton b;
    JTextField tf;
    ArrayList<Student> list = new ArrayList<Student>();

    DeleteDetails()
    {
        super("Delete Student Details");  
    	
        l=new JLabel("Enter Roll Number of Student");  
        l.setBounds(100,20,300,40);

        tf=new JTextField();  
        tf.setBounds(100,70,200,20);

        b=new JButton("Enter");  
        b.setBounds(150,230,150,30); 
        b.addActionListener(this); 

        add(l); add(tf); add(b);

        setSize(400,300);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(AddDetails.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        int flag=0;
        try {
            FileInputStream fin = new FileInputStream("Student.ser");
            
            ObjectInputStream oim = new ObjectInputStream(fin);
            
            while (true) {
                Student ex = (Student) oim.readObject(); 
                if((ex.roll).equals(tf.getText().strip()))
                {
                    flag = 1;
                }
                else
                    list.add(ex);
            }
            
        }
        catch (Exception eof) {
            tf.setText("");
            if(flag==0)
                JOptionPane.showMessageDialog(this,"Roll Number does not exist");
            else
                JOptionPane.showMessageDialog(this,"The Student has been Removed from Records");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(Student S : list)
            {
                System.out.println(S.getDetails());
                out.writeObject(S);   
            } 
            out.close();
            fileOut.close();
         }
         catch(Exception x)
         {
            JOptionPane.showMessageDialog(this,x.toString());  
         }
         /* 
         try {
    	      	  
            FileInputStream fin = new FileInputStream("Student.ser");
            
            ObjectInputStream oim = new ObjectInputStream(fin);
            
            while (true) {
                Student ex = (Student) oim.readObject(); 
                System.out.println(ex.getDetails());
            }
            
        }
        catch (Exception eof) {
          System.out.println(" .... End of File ... ");  
        }*/
    }
}

class ModifyDetails extends JFrame implements ActionListener
{
    JLabel l, l1,l2,l3,l4,l5;  
    JButton b;
    JTextField tf1,tf2,tf3,tf4,tf5;
    ArrayList<Student> list = new ArrayList<Student>();

    ModifyDetails()
    {
        super("Modify Student Details");  
    	
        l=new JLabel("ENTER MODIFIED STUDENT DETAILS");  
        l.setBounds(100,20,300,40);
        
        l1=new JLabel("Enter Full Name:");  
        l1.setBounds(50,70,150,20);;  
        tf1=new JTextField();  
        tf1.setBounds(150,70,200,20);

        l2=new JLabel("Enter Age:");  
        l2.setBounds(50,100,150,20);;  
        tf2=new JTextField();  
        tf2.setBounds(150,100,200,20);

        l3=new JLabel("Enter Semester:");  
        l3.setBounds(50,130,150,20);;  
        tf3=new JTextField();  
        tf3.setBounds(150,130,200,20);

        l4=new JLabel("Enter Roll No:");  
        l4.setBounds(50,160,150,20);;  
        tf4=new JTextField();  
        tf4.setBounds(150,160,200,20);

        l5=new JLabel("Enter CPI:");  
        l5.setBounds(50,190,150,20);;  
        tf5=new JTextField();  
        tf5.setBounds(150,190,200,20);

        b=new JButton("SUBMIT");  
        b.setBounds(150,230,150,30); 
        
        b.addActionListener(this); 

        add(l);   add(l1); add(l2) ; add(l3); add(l4); add(l5);
        add(b);
        add(tf1); add(tf2) ; add(tf3) ; add(tf4) ; add(tf5) ;
        
        setSize(500,450);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(AddDetails.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {   
        int flag = 0;
        try {
            FileInputStream fin = new FileInputStream("Student.ser");
            
            ObjectInputStream oim = new ObjectInputStream(fin);
            
            while (true) {
                Student ex = (Student) oim.readObject(); 
                if((ex.roll).equals(tf4.getText().strip()))
                {
                    flag = 1;
                    String name,age,sem,roll,cpi;
                    name = tf1.getText();
                    age = tf2.getText();
                    sem = tf3.getText();
                    roll = tf4.getText();
                    cpi = tf5.getText();

                    list.add(new Student(name, age, sem, roll, cpi));
                }
                else
                {
                    list.add(ex);
                }
            }
            
        }
        catch (Exception eof) {
            if(flag==0)
                JOptionPane.showMessageDialog(this,"Roll Number does not exist");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(Student S : list)
                out.writeObject(S);    
            out.close();
            fileOut.close();
            if(flag==1)
                JOptionPane.showMessageDialog(this,"Student Data is Modified!");  
         }
         catch(Exception x)
         {
            JOptionPane.showMessageDialog(this,x.toString());  
         }
         tf1.setText("");
         tf2.setText("");
         tf3.setText("");
         tf4.setText("");
         tf5.setText("");
    }
}
class AddDetails extends JFrame implements ActionListener
{
    JLabel l, l1,l2,l3,l4,l5;  
    JButton b;
    JTextField tf1,tf2,tf3,tf4,tf5;
    ArrayList<Student> list = new ArrayList<Student>();

    AddDetails()
    {
        super("New Student Details");  
    	
        l=new JLabel("ENTER NEW STUDENT DETAILS");  
        l.setBounds(100,20,300,40);
        
        l1=new JLabel("Enter Full Name:");  
        l1.setBounds(50,70,150,20);;  
        tf1=new JTextField();  
        tf1.setBounds(150,70,200,20);

        l2=new JLabel("Enter Age:");  
        l2.setBounds(50,100,150,20);;  
        tf2=new JTextField();  
        tf2.setBounds(150,100,200,20);

        l3=new JLabel("Enter Semester:");  
        l3.setBounds(50,130,150,20);;  
        tf3=new JTextField();  
        tf3.setBounds(150,130,200,20);

        l4=new JLabel("Enter Roll No:");  
        l4.setBounds(50,160,150,20);;  
        tf4=new JTextField();  
        tf4.setBounds(150,160,200,20);

        l5=new JLabel("Enter CPI:");  
        l5.setBounds(50,190,150,20);;  
        tf5=new JTextField();  
        tf5.setBounds(150,190,200,20);

        b=new JButton("SUBMIT");  
        b.setBounds(150,230,150,30); 
        
        b.addActionListener(this); 

        add(l);   add(l1); add(l2) ; add(l3); add(l4); add(l5);
        add(b);
        add(tf1); add(tf2) ; add(tf3) ; add(tf4) ; add(tf5) ;
        
        setSize(500,450);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(AddDetails.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {   
        String name,age,sem,roll,cpi;
        name = tf1.getText();
        age = tf2.getText();
        sem = tf3.getText();
        roll = tf4.getText();
        cpi = tf5.getText();

        
        Student s = new Student(name, age, sem, roll, cpi);
        list.add(s);
        try {
            FileOutputStream fileOut = new FileOutputStream("Student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(Student S : list)
                out.writeObject(S);    
            out.close();
            fileOut.close();
            JOptionPane.showMessageDialog(this,"Given Data is Stored!");  
         }
         catch(Exception x)
         {
            JOptionPane.showMessageDialog(this,x.toString());  
         }
         tf1.setText("");
         tf2.setText("");
         tf3.setText("");
         tf4.setText("");
         tf5.setText("");
     }
}


class User extends JFrame implements ActionListener
{
    JLabel l;  
    JButton b1, b2,b3;
    JTextField tf1;
    JTextArea tf2;
    User()
    {
        super("Welcome User!");  
    	
        l=new JLabel("VIEW DETAILS BY");  
        l.setBounds(50,70,150,20);
          
        b1=new JButton("Roll Number");  
        b1.setBounds(50,150,150,30); 
        
        b1.addActionListener(this); 

        b2=new JButton("Name");  
        b2.setBounds(50,200,150,30); 
        
        b2.addActionListener(this);

        b3=new JButton("Submit");  
        b3.setBounds(50,300,150,30); 
        b3.setEnabled(false);
        b3.addActionListener(this);
        
        tf1 = new JTextField("Enter Details Here");
        tf1.setBounds(50,250,150,30); 
        tf1.setEditable(false);

        tf2 = new JTextArea("Details Shown here");
        tf2.setBounds(50,350,150,100); 
        tf2.setEditable(false);

        

        add(l);   
        add(b1);
        add(b2); add(b3);
        add(tf1); add(tf2);
        
        setSize(300,600);  
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(User.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    { 
        if((e.getActionCommand()).equals("Roll Number"))
        {
            tf1.setEditable(true);
            tf1.setText("Enter Roll Number");
            b3.setEnabled(true);
        }
        else if((e.getActionCommand()).equals("Name"))
        {
            tf1.setEditable(true);
            tf1.setText("Enter Name");
            b3.setEnabled(true);
        }
        else
        {
            int flag = 0;
            try {
                    FileInputStream fin = new FileInputStream("Student.ser");
                    ObjectInputStream oim = new ObjectInputStream(fin);
                    while (true) {
                    Student ex = (Student) oim.readObject(); 
                    if((ex.roll).equals(tf1.getText()) || (ex.name).equalsIgnoreCase(tf1.getText()))
                    {
                        flag = 1;
                        tf2.setText("Name: "+ ex.name + "\nAge" + ex.age + "\nSemester: "+ ex.sem + "\nRoll Number: "+ ex.roll + "\nCPI: "+ ex.cpi + "\n");
                    }
                }
            }
            catch (Exception eof) {
              if(flag==0)
                JOptionPane.showMessageDialog(this,"Student Record Not Found!"); 
              tf1.setEditable(false);
              b3.setEnabled(false);
              tf1.setText("Enter Details Here");
            }
        }
    }
}

class Project
{
    public static void main(String args[])
    {
        new Menu();
    }
}
