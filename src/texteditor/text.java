package texteditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

class AppDelStack{
    public int opType;
    public String val;
}

class editor {
    
  
}

public class text extends JFrame implements ActionListener{
	
	ButtonGroup G=new ButtonGroup();	
	JRadioButton r1,r2,r3,r4;
    JLabel l1,l2;
    JButton b;
    
    text(){
    	
    	setFont(new Font("System", Font.BOLD, 22));
    	setTitle("Text Editor");
    	
    	l1 = new JLabel("Welcome to the Text Editor");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        l2 = new JLabel("Select the operation");
        l2.setFont(new Font("System", Font.BOLD, 35));
        
        r1 = new JRadioButton("Append Text");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        
        r2 = new JRadioButton("Delete Text");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        
        r3 = new JRadioButton("Print Text");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);
        
        r4 = new JRadioButton("Undo Operation");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);
        
        b = new JButton("Perform");
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        G.add(r1);
        G.add(r2);
        G.add(r3);
        G.add(r4);
        
        setLayout(null);
        
        l1.setBounds(140,20,600,30);
        add(l1);
        
        l2.setBounds(250,220,600,30);
        add(l2);
        
        r1.setBounds(260,290,200,30);
        add(r1);
        
        r2.setBounds(260,340,200,30);
        add(r2);
        
        r3.setBounds(260,390,200,30);
        add(r3);
        
        r4.setBounds(260,440,200,30);
        add(r4);
        
        b.setBounds(260,600,100,30);
        add(b);
        
        b.addActionListener(this); 
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(700,700);
        setLocation(500,90);
        setVisible(true);
    }
    Stack<AppDelStack> oprationSt = new Stack<AppDelStack>();
    Stack<Character> st = new Stack<Character>(); 
   public void undoOPD(){
       AppDelStack apdel = oprationSt.pop();
       if(apdel.opType == 1){
           for(int i=0;i<Integer.valueOf(apdel.val);i++){
               st.pop();
           }
       }else {
            for(int i=0;i<apdel.val.length();i++){
               st.push(apdel.val.charAt(i));
           }
       }
   }
   public void printOPD(int k){
       Stack<Character> tempst = new Stack<Character>();
       for(int i=st.size();i>k;i--){
           tempst.push(st.pop());
       }
       JOptionPane.showMessageDialog(null,st.peek());
       while(!tempst.isEmpty()){
           st.push(tempst.pop());
       }
      
     
   }
   public void appendOPD(String s){
       for(int i=0;i<s.length();i++){
        st.push(s.charAt(i));  
       }
       AppDelStack apdel = new AppDelStack();
       apdel.opType = 1;
       apdel.val = String.valueOf(s.length());
       oprationSt.push(apdel);
   }
  
   public void deleteOPD(int k){
       StringBuilder sb = new StringBuilder();
       for(int i= 0;i<k;i++){
           sb.append(st.pop());
       }
        AppDelStack apdel = new AppDelStack();
       apdel.opType = 2;
      
       apdel.val = sb.reverse().toString();
       oprationSt.push(apdel);
   }
    public void actionPerformed(ActionEvent ae){
    	String a="null";
    	String b="null";
    	if(r1.isSelected()){ 
            a="1";
            b=JOptionPane.showInputDialog(null,"Enter string to be appended"); 
            appendOPD(b);
        }
        else if(r2.isSelected()){ 
            a="2";
            b=JOptionPane.showInputDialog(null,"Enter the character index upto which to be deleted");
            deleteOPD(Integer.parseInt(b));
        }else if(r3.isSelected()){ 
            a="3";
            b=JOptionPane.showInputDialog(null,"Enter the character index upto which to be printed");
            printOPD(Integer.parseInt(b));
        }else {
        	a="4";
        	undoOPD();
        }
    }
    public static void main(String[] args){
        new text().setVisible(true);
    }
}
