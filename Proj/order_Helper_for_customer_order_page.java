import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class title extends JPanel {
	public JLabel la = new JLabel();

	public title() {

		la.setFont(new Font("", Font.PLAIN, 24));
		add(la);

	}

	public void setString(String s) {
		la.setText(s);
	}

}


class menuPane extends JPanel {
// 주문 타이틀 3x1 메뉴
	Vector<Integer> v = new Vector<Integer>();
	public JComboBox jcb[] = new JComboBox[4];
	public JLabel[] la = new JLabel[4];

	public menuPane() {
		this.setLayout(new GridLayout(5, 1, 10, 10));
		for (int i = 0; i < 6; i++) {
			v.add(i);
		}

		for (int i = 0; i < 4; i++) {
			jcb[i] = new JComboBox(v);

		}

		for (int i = 0; i < 4; i++) {

			la[i] = new JLabel();

			add(la[i]);
			add(jcb[i]);
		}

	}
}

class orderInfo extends JPanel {
//주문 선택 테이블 1x3 메뉴 팬생성, 부착
	menuPane tang = new menuPane();
	menuPane fry = new menuPane();
	menuPane drink = new menuPane();

	public orderInfo(orderArea oa) {
		this.setLayout(new GridLayout(1, 3, 20, 50));

		tang.la[0].setText("<html><strong>그냥 어묵탕</strong><br>12000원<html>");
		tang.la[1].setText("<html><strong>시원한 홍합탕</strong><br>12000원<html>");
		tang.la[2].setText("<html><strong>얼큰한 짬뽕탕</strong><br>13000원<html>");
		tang.la[3].setText("<html><strong>해물 누룽지탕</strong><br>14000원<html>");

		for (int i = 0; i < 4; i++) {
			tang.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		fry.la[0].setText("<html><strong>감자튀김</strong><br>6000원<html>");
		fry.la[1].setText("<html><strong>왕 돈까스</strong><br>7000원<html>");
		fry.la[2].setText("<html><strong>후라이드 치킨</strong><br>7000원<html>");
		fry.la[3].setText("<html><strong>새우튀김</strong><br>8000원<html>");

		for (int i = 0; i < 4; i++) {
			fry.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		drink.la[0].setText("<html><strong>맥주</strong><br>4000원<html>");
		drink.la[1].setText("<html><strong>소주</strong><br>4000원<html>");
		drink.la[2].setText("<html><strong>막걸리</strong><br>5000원<html>");
		drink.la[3].setText("<html><strong>양주</strong><br>89000원<html>");

		for (int i = 0; i < 4; i++) {
			drink.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		add(tang);
		add(fry);
		add(drink);

	}
}


class orderArea extends JPanel {

	public JTextArea bills = new JTextArea();
	public JTextArea txt = new JTextArea();
	public JButton ob = new JButton("주문하기");
	Vector<String> orderItem = new Vector<String>(); // 손님이 주문한 내역을 저장
	Vector<Integer> orderAmount = new Vector<Integer>(); // 주문한 내역 중 갯수 저장
	int sum = 0; // 총 가격 저장

	public orderArea() {
		this.setLayout(new BorderLayout());
		bills.setFont(new Font("", Font.BOLD, 13));
		add(bills, BorderLayout.NORTH);
		add(txt, BorderLayout.CENTER);
		add(ob, BorderLayout.SOUTH);
		//주문하기 버튼 액션
		ob.addActionListener(new ActionListener() {
			//이하 코드는 주문내역을 확인하기 위해 넣은 코드, 삭제무방
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				for (int i = 0; i < orderItem.size(); i++) {
					System.out.print(orderItem.get(i)+" ");
					System.out.print(orderAmount.get(i));
					System.out.println();
				}
				System.out.println(sum);

				txt.setText("");
				bills.setText("주문이 완료되었습니다!");

			}

		});

	}

}

class update implements ActionListener {

	menuPane m1;
	menuPane m2;
	menuPane m3;
	orderArea tempOA;

	public update(menuPane m1, menuPane m2, menuPane m3, orderArea oa) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		tempOA = oa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String temp;

		// System.out.println("업데이트 실시!");
		tempOA.txt.setText("\n");
		tempOA.bills.setText("");
		tempOA.orderItem.clear();
		tempOA.orderAmount.clear();
		tempOA.sum = 0;

		for (int i = 0; i < 4; i++) {
			temp = m1.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("12000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());

			} else if (i == 1 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("12000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			} else if (i == 2 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("13000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 13000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			} else if (i == 3 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("14000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 14000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			}

		}

		for (int i = 0; i < 4; i++) {
			temp = m2.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("6000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 6000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 1 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 2 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 3 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("8000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 8000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			}

		}

		for (int i = 0; i < 4; i++) {
			temp = m3.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 1 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 2 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("5000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 5000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 3 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("89000원<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 89000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			}

		}
		tempOA.bills.append("현재 금액: " + tempOA.sum + "원입니다.");

		// System.out.println();
	}

}

public class orderPage extends JFrame {

	JLabel tangMenu = new JLabel("탕류");
	JLabel fryMenu = new JLabel("튀김");
	JLabel drinkMenu = new JLabel("주류");

	title ti = new title();
	orderArea oa = new orderArea();
	orderInfo table = new orderInfo(oa);

	public orderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("프로그램");
		Container c = getContentPane();
		c.setLayout(null);

		ti.setSize(100, 35);
		ti.setLocation(350, 20);
		ti.setString("주문 하기"); 
		c.add(ti);// 타이틀 제목 설정
		
		table.setSize(500, 400);
		table.setLocation(10, 100);
		c.add(table); // 주문 선택 테이블 위치 조정, 추가
		
		oa.setSize(250, 250);
		oa.setLocation(530, 140);
		c.add(oa); // 주문 확인 테이블 위치 조정, 추가

		tangMenu.setFont(new Font("", Font.BOLD, 15));
		tangMenu.setSize(40, 30);
		tangMenu.setLocation(60, 77);
		c.add(tangMenu); // 좌표로 찍어서 탕류 타이틀 설정

		fryMenu.setFont(new Font("", Font.BOLD, 15));
		fryMenu.setSize(40, 30);
		fryMenu.setLocation(240, 77);
		c.add(fryMenu);// 좌표로 찍어서 튀김류 타이틀 설정

		drinkMenu.setFont(new Font("", Font.BOLD, 15));
		drinkMenu.setSize(40, 30);
		drinkMenu.setLocation(400, 77);
		c.add(drinkMenu);// 좌표로 찍어서 주류 타이틀 설정

		
		
		

		setSize(800, 500);
		setVisible(true);

		// 이하 액션 이벤트는 프레임 내 좌표를 구하기 위해 넣은 코드임. 삭제 무방
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print(e.getX() + " ");
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

		new orderPage();
	}
}
