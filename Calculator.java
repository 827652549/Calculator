package homeworks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Calculator extends JFrame{

	JTextField jtf;
	JPanel jp;
	JButton[] jb = new JButton[16];
	
	String result="";
	String s;//用来保存最后一个输入的符号
	String str1 = "";//前一个数
	String str2 = "";//后一个数
	
	double d1=0;//double类型的前一个数
	double d2=0;//double类型的后一个数
	
	double douResult;//最终结果
	
	//区分表示表示操作前后数，1代表前一个数，2代表后一个数
	int k1 = 1;
	
	//区分是否是连续运算，若是1则默认短运算，若大于1则是连续运算, k2用于记录运算符使用次数
	int k2 = 0;
	
	
	
	Calculator(){
		this.setLayout(new BorderLayout(5,5));
		this.setTitle("计算器V1.0");
		this.setSize(300, 485);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//北部文本框参数设置
		jtf = new JTextField("");//初始值
		jtf.setPreferredSize(new Dimension(100,150));
		jtf.setFont(new Font("黑体", Font.PLAIN, 60));
		jtf.setBackground(null);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		//中部参数设置
		jp = new JPanel();
		jp.setLayout(new GridLayout(4,4,10,25));
		jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp.setBackground(null);
		

		this.add(jtf, BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		
		ListenerNums lNums = new ListenerNums();//创建数字监听对象
		ListenerSign lSign = new ListenerSign();//创建符号监听对象
		
		jb[0] = new JButton("7");	jb[0].addActionListener(lNums);
		jb[1] = new JButton("8");	jb[1].addActionListener(lNums);
		jb[2] = new JButton("9");	jb[2].addActionListener(lNums);
		jb[3] = new JButton("+");	jb[3].addActionListener(lSign);
		jb[4] = new JButton("4");	jb[4].addActionListener(lNums);
		jb[5] = new JButton("5");	jb[5].addActionListener(lNums);
		jb[6] = new JButton("6");	jb[6].addActionListener(lNums);
		jb[7] = new JButton("-");	jb[7].addActionListener(lSign);
		jb[8] = new JButton("1");	jb[8].addActionListener(lNums);
		jb[9] = new JButton("2");	jb[9].addActionListener(lNums);
		jb[10] = new JButton("3");	jb[10].addActionListener(lNums);
		jb[11] = new JButton("x");	jb[11].addActionListener(lSign);
		jb[12] = new JButton("0");	jb[12].addActionListener(lNums);
		jb[13] = new JButton(".");
		jb[14] = new JButton("÷");	jb[14].addActionListener(lSign);
		jb[15] = new JButton("=");	jb[15].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				douResult = 0;
				if(s=="+"){
					douResult = d1+d2;
				}
				if(s=="-"){
					douResult = d1-d2;
				}
				if(s=="x"){
					
					douResult = d1*d2;
					System.out.println(douResult+"xxxx");
				}
				if(s=="÷"){
					if(d2!=0)
					douResult = d1/d2;
				
				}
				
				jtf.setText(String.valueOf(douResult));//更新输出结果
				
				//还原操作
				k1=1;
				k2=0;
				result="";
				str1 = "";//前一个数
				str2 = "";//后一个数
				d1=0;//double类型的前一个数
				d2=0;//double类型的后一个数
			}
		});
		
		for (int i = 0; i < jb.length; i++) {
			jb[i].setFont(new Font("黑体", Font.PLAIN, 20));
			jb[i].setContentAreaFilled(false);  //只须加上此句
			jp.add(jb[i]);
		}
		
		this.setLocationRelativeTo(null);//将this置于空的中央、即屏幕中央
		this.setResizable(true);//窗口不可缩放
		this.setVisible(true);
	}
	
	//数字键
	class ListenerNums implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String s = ((JButton)e.getSource()).getText();//用来保存点击的键对应的数字,以字符串形式表现
			if(k1==1){
				str1+=s;//操作第一个数：字符串拼接，实现多位数录入
				result = str1;//方便框中显示
				System.out.println("str1是"+str1);
				d1 = Double.valueOf(str1);//数字转型，方便运算
			
			}
			if(k1==2){
				jtf.setText("");//按下符号键开始操作第二个数时清空文本框。
				System.out.println("1111");
				str2+=s;//操作第一个数：字符串拼接，实现多位数录入
				System.out.println("str2是"+str2);
				d2 = Double.valueOf(str2);

				if(k2==1){//短运算
					result = str2;//方便框中显示
					System.out.println("短运算");
				}
				else{
					//result = String.valueOf(d1);
					result = str2;
					str2 = "";//每次运算完清零
				}
				
			}
			
			jtf.setText(result);//文本框中显示值
		}
	}
	//符号键
		class ListenerSign implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				str2="";//方便可能的连续运算
				k2++;//运算符使用 记录加1
				s = ((JButton)e.getSource()).getText();//用来保存点击的键对应的符号,以字符串形式表现
				
				douResult = 0;
				if(s=="+"){
					douResult = d1+d2;
				}
				if(s=="-"){
					douResult = d1-d2;
				}
				if(s=="x"){
					if(k1==1)//目的是为了仅执行一次
						d2=1;//因为d2初始值为0
						douResult = d1*d2;
						System.out.println("xxxxxx");			
				}
				if(s=="÷"){
					if(k1==1)//目的是为了仅执行一次
					d2=1;
					if(d2!=0)
					douResult = d1/d2;
					else JOptionPane.showMessageDialog(null, "除数不能为0");
				}
		
				d1 = douResult;
				jtf.setText(String.valueOf(d1));
				k1=2;//如果触发符号监听，可以姑且理解为前一个数已经录入完毕
			}
		}
		
	public static void main(String[] args) {
		
	   new Calculator();  
		
	}
}
