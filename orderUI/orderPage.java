package orderUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import orderClient.*;
import model.*;
import mainUI.*;

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

		tang.la[0].setText("<html><strong>어묵탕</strong><br>12000원<html>");
		tang.la[1].setText("<html><strong>홍합탕</strong><br>12000원<html>");
		tang.la[2].setText("<html><strong>짬뽕탕</strong><br>13000원<html>");
		tang.la[3].setText("<html><strong>누룽지탕</strong><br>14000원<html>");

		for (int i = 0; i < 4; i++) {
			tang.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		fry.la[0].setText("<html><strong>감자튀김</strong><br>6000원<html>");
		fry.la[1].setText("<html><strong>돈까스</strong><br>7000원<html>");
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
	public ArrayList<CorderVO> orderItem = new ArrayList<CorderVO>(); 
	// 손님이 주문한 내역을 저장

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
				orderClient c =  new orderClient(orderItem);
				//System.out.println(orderItem.size()+"orderArea");
				//System.out.print(orderItem[1].g);
/*
				for (int i = 0; i < orderItem.size(); i++) {
					System.out.print(orderItem.get(i));
				//	System.out.print(orderAmount.get(i));
					System.out.println();
				}
				//System.out.println(sum);
*/
				txt.setText("");
				bills.setText("주문이 완료되었습니다!");

			}

		});

	}

}

class update implements ActionListener {
// 고객이 콤보박스를 업데이트 할때마다 textArea를 업데이트해주는 클래스
	menuPane m1;
	menuPane m2;
	menuPane m3;
	orderArea tempOA;
	int tn = 4;
	int sum = 0;
	
	public update(menuPane m1, menuPane m2, menuPane m3, orderArea oa) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		tempOA = oa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String temp;
		
		tempOA.txt.setText("\n");
		tempOA.bills.setText("");
		tempOA.orderItem.clear();
		sum = 0;
		

		for (int i = 0; i < 4; i++) {
			CorderVO o = new CorderVO();
			o.settNum(tn);
			temp = m1.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m1.jcb[i].getSelectedIndex() != 0) {
				
				temp = temp.replace("12000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				o.setAmount(m1.jcb[i].getSelectedIndex());
				//System.out.println(o.product);
				tempOA.orderItem.add(o);

			} else if (i == 1 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("12000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				o.setAmount(m1.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 2 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("13000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m1.jcb[i].getSelectedIndex()) * 13000;
				o.setAmount(m1.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 3 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("14000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m1.jcb[i].getSelectedIndex()) * 14000;
				o.setAmount(m1.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			}
			
		}

		for (int i = 0; i < 4; i++) {
			CorderVO o = new CorderVO();
			o.settNum(tn);
			temp = m2.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("6000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m2.jcb[i].getSelectedIndex()) * 6000;
				o.setAmount(m2.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 1 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				o.setAmount(m2.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 2 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				o.setAmount(m2.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 3 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("8000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m2.jcb[i].getSelectedIndex()) * 8000;
				o.setAmount(m2.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			}

		}

		for (int i = 0; i < 4; i++) {
			CorderVO o = new CorderVO();
			o.settNum(tn);
			temp = m3.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				o.setAmount(m3.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 1 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				o.setAmount(m3.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 2 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("5000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m3.jcb[i].getSelectedIndex()) * 5000;
				o.setAmount(m3.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			} else if (i == 3 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("89000원<html>", "");
				o.setProduct(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "개" + "\n");
				sum += (m3.jcb[i].getSelectedIndex()) * 89000;
				o.setAmount(m3.jcb[i].getSelectedIndex());
				tempOA.orderItem.add(o);
			}
		}
		tempOA.bills.append("현재 금액: " + sum + "원입니다.");
	}

}

public class orderPage extends JFrame {

	JLabel tangMenu = new JLabel("탕류");
	JLabel fryMenu = new JLabel("튀김");
	JLabel drinkMenu = new JLabel("주류");

	titlePanel ti = new titlePanel();
	orderArea oa = new orderArea();
	orderInfo table = new orderInfo(oa);
	public backBtn myBack;

	public orderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pub_Manager 주문하기");
		Container c = getContentPane();
		c.setLayout(null);
		
		myBack = new backBtn();
		
		myBack.setSize(100,35);
		myBack.setLocation(10,10);

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

		c.add(myBack);
		
		

		setSize(800, 500);
		setVisible(true);
	}

	public static void main(String args[]) {

		new orderPage();
	}
}
