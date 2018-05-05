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
	String s;//�����������һ������ķ���
	String str1 = "";//ǰһ����
	String str2 = "";//��һ����
	
	double d1=0;//double���͵�ǰһ����
	double d2=0;//double���͵ĺ�һ����
	
	double douResult;//���ս��
	
	//���ֱ�ʾ��ʾ����ǰ������1����ǰһ������2�����һ����
	int k1 = 1;
	
	//�����Ƿ����������㣬����1��Ĭ�϶����㣬������1������������, k2���ڼ�¼�����ʹ�ô���
	int k2 = 0;
	
	
	
	Calculator(){
		this.setLayout(new BorderLayout(5,5));
		this.setTitle("������V1.0");
		this.setSize(300, 485);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�����ı����������
		jtf = new JTextField("");//��ʼֵ
		jtf.setPreferredSize(new Dimension(100,150));
		jtf.setFont(new Font("����", Font.PLAIN, 60));
		jtf.setBackground(null);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		//�в���������
		jp = new JPanel();
		jp.setLayout(new GridLayout(4,4,10,25));
		jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp.setBackground(null);
		

		this.add(jtf, BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		
		ListenerNums lNums = new ListenerNums();//�������ּ�������
		ListenerSign lSign = new ListenerSign();//�������ż�������
		
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
		jb[14] = new JButton("��");	jb[14].addActionListener(lSign);
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
				if(s=="��"){
					if(d2!=0)
					douResult = d1/d2;
				
				}
				
				jtf.setText(String.valueOf(douResult));//����������
				
				//��ԭ����
				k1=1;
				k2=0;
				result="";
				str1 = "";//ǰһ����
				str2 = "";//��һ����
				d1=0;//double���͵�ǰһ����
				d2=0;//double���͵ĺ�һ����
			}
		});
		
		for (int i = 0; i < jb.length; i++) {
			jb[i].setFont(new Font("����", Font.PLAIN, 20));
			jb[i].setContentAreaFilled(false);  //ֻ����ϴ˾�
			jp.add(jb[i]);
		}
		
		this.setLocationRelativeTo(null);//��this���ڿյ����롢����Ļ����
		this.setResizable(true);//���ڲ�������
		this.setVisible(true);
	}
	
	//���ּ�
	class ListenerNums implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String s = ((JButton)e.getSource()).getText();//�����������ļ���Ӧ������,���ַ�����ʽ����
			if(k1==1){
				str1+=s;//������һ�������ַ���ƴ�ӣ�ʵ�ֶ�λ��¼��
				result = str1;//���������ʾ
				System.out.println("str1��"+str1);
				d1 = Double.valueOf(str1);//����ת�ͣ���������
			
			}
			if(k1==2){
				jtf.setText("");//���·��ż���ʼ�����ڶ�����ʱ����ı���
				System.out.println("1111");
				str2+=s;//������һ�������ַ���ƴ�ӣ�ʵ�ֶ�λ��¼��
				System.out.println("str2��"+str2);
				d2 = Double.valueOf(str2);

				if(k2==1){//������
					result = str2;//���������ʾ
					System.out.println("������");
				}
				else{
					//result = String.valueOf(d1);
					result = str2;
					str2 = "";//ÿ������������
				}
				
			}
			
			jtf.setText(result);//�ı�������ʾֵ
		}
	}
	//���ż�
		class ListenerSign implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				str2="";//������ܵ���������
				k2++;//�����ʹ�� ��¼��1
				s = ((JButton)e.getSource()).getText();//�����������ļ���Ӧ�ķ���,���ַ�����ʽ����
				
				douResult = 0;
				if(s=="+"){
					douResult = d1+d2;
				}
				if(s=="-"){
					douResult = d1-d2;
				}
				if(s=="x"){
					if(k1==1)//Ŀ����Ϊ�˽�ִ��һ��
						d2=1;//��Ϊd2��ʼֵΪ0
						douResult = d1*d2;
						System.out.println("xxxxxx");			
				}
				if(s=="��"){
					if(k1==1)//Ŀ����Ϊ�˽�ִ��һ��
					d2=1;
					if(d2!=0)
					douResult = d1/d2;
					else JOptionPane.showMessageDialog(null, "��������Ϊ0");
				}
		
				d1 = douResult;
				jtf.setText(String.valueOf(d1));
				k1=2;//����������ż��������Թ������Ϊǰһ�����Ѿ�¼�����
			}
		}
		
	public static void main(String[] args) {
		
	   new Calculator();  
		
	}
}
