package mainUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class btnAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton temp = (JButton)e.getSource();
		if(temp.getText().equals("주문 하기")) {
			System.out.println("주문하기 버튼이 눌렸습니다.");
		}
		else if (temp.getText().equals("좌석 확인")) {
			System.out.println("좌석 확인 버튼이 눌렸습니다.");
		}
	}
	
}


public class mainPage extends JFrame {

	public JButton order;
	public JButton checkSeat;
	
	titlePanel ti = new titlePanel();
	btnAction bc = new btnAction();
	
	public mainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pub Manager");
		Container c = getContentPane();
		c.setLayout(null);
		
		ti.setSize(100,35);
		ti.setLocation(340, 30);
		ti.setString("세종 포차"); // 타이틀 제목 설정
		c.add(ti);
		
		
		order = new JButton("주문하기");
		checkSeat= new JButton("좌석확인");
		
		order.addActionListener(bc);
		checkSeat.addActionListener(bc);
		
		order.setSize(300,300);
		order.setLocation(80, 100);
		
		checkSeat.setSize(300,300);
		checkSeat.setLocation(400, 100);
		
		add(order);
		add(checkSeat);
		
		
		
		setSize(800, 500);
		setVisible(true);
	}

	public static void main(String args[]) {

		new mainPage();
	}
}
