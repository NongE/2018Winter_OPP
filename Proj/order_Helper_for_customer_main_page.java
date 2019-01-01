import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class title extends JPanel{
	public JLabel la= new JLabel();

	public title() {
		la.setFont(new Font("",Font.PLAIN,24));
		add(la);
	}
	
	public void setString(String s) {
		la.setText(s);
	}

}


public class mainPage extends JFrame {

	title ti = new title();

	JButton order;
	JButton checkSeat;
	
	public mainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		
		ti.setSize(100,35);
		ti.setLocation(340, 30);
		ti.setString("술 집"); // 타이틀 제목 설정
		c.add(ti);
		
		
		order = new JButton("주문 하기");
		checkSeat= new JButton("좌석 확인");
		
		order.setSize(300,300);
		order.setLocation(80, 100);
		
		checkSeat.setSize(300,300);
		checkSeat.setLocation(400, 100);
		
		add(order);
		add(checkSeat);
		
		
		
		setSize(800, 500);
		setVisible(true);
		
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print(e.getX()+" ");
				System.out.println(e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public static void main(String args[]) {

		new mainPage();
	}
}
