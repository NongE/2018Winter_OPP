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
// �ֹ� Ÿ��Ʋ 3x1 �޴�
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
//�ֹ� ���� ���̺� 1x3 �޴� �һ���, ����
	menuPane tang = new menuPane();
	menuPane fry = new menuPane();
	menuPane drink = new menuPane();

	public orderInfo(orderArea oa) {
		this.setLayout(new GridLayout(1, 3, 20, 50));

		tang.la[0].setText("<html><strong>�׳� ���</strong><br>12000��<html>");
		tang.la[1].setText("<html><strong>�ÿ��� ȫ����</strong><br>12000��<html>");
		tang.la[2].setText("<html><strong>��ū�� «����</strong><br>13000��<html>");
		tang.la[3].setText("<html><strong>�ع� ��������</strong><br>14000��<html>");

		for (int i = 0; i < 4; i++) {
			tang.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		fry.la[0].setText("<html><strong>����Ƣ��</strong><br>6000��<html>");
		fry.la[1].setText("<html><strong>�� ���</strong><br>7000��<html>");
		fry.la[2].setText("<html><strong>�Ķ��̵� ġŲ</strong><br>7000��<html>");
		fry.la[3].setText("<html><strong>����Ƣ��</strong><br>8000��<html>");

		for (int i = 0; i < 4; i++) {
			fry.jcb[i].addActionListener(new update(tang, fry, drink, oa));
		}

		drink.la[0].setText("<html><strong>����</strong><br>4000��<html>");
		drink.la[1].setText("<html><strong>����</strong><br>4000��<html>");
		drink.la[2].setText("<html><strong>���ɸ�</strong><br>5000��<html>");
		drink.la[3].setText("<html><strong>����</strong><br>89000��<html>");

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
	public JButton ob = new JButton("�ֹ��ϱ�");
	Vector<String> orderItem = new Vector<String>(); // �մ��� �ֹ��� ������ ����
	Vector<Integer> orderAmount = new Vector<Integer>(); // �ֹ��� ���� �� ���� ����
	int sum = 0; // �� ���� ����

	public orderArea() {
		this.setLayout(new BorderLayout());
		bills.setFont(new Font("", Font.BOLD, 13));
		add(bills, BorderLayout.NORTH);
		add(txt, BorderLayout.CENTER);
		add(ob, BorderLayout.SOUTH);
		//�ֹ��ϱ� ��ư �׼�
		ob.addActionListener(new ActionListener() {
			//���� �ڵ�� �ֹ������� Ȯ���ϱ� ���� ���� �ڵ�, ��������
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
				bills.setText("�ֹ��� �Ϸ�Ǿ����ϴ�!");

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

		// System.out.println("������Ʈ �ǽ�!");
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
				temp = temp.replace("12000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());

			} else if (i == 1 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("12000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 12000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			} else if (i == 2 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("13000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 13000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			} else if (i == 3 && m1.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("14000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m1.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m1.jcb[i].getSelectedIndex()) * 14000;
				tempOA.orderAmount.add(m1.jcb[i].getSelectedIndex());
			}

		}

		for (int i = 0; i < 4; i++) {
			temp = m2.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("6000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 6000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 1 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 2 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("7000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 7000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			} else if (i == 3 && m2.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("8000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m2.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m2.jcb[i].getSelectedIndex()) * 8000;
				tempOA.orderAmount.add(m2.jcb[i].getSelectedIndex());
			}

		}

		for (int i = 0; i < 4; i++) {
			temp = m3.la[i].getText();
			temp = temp.replace("<html><strong>", "");
			temp = temp.replace("</strong><br>", "");

			if (i == 0 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 1 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("4000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 4000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 2 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("5000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 5000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			} else if (i == 3 && m3.jcb[i].getSelectedIndex() != 0) {
				temp = temp.replace("89000��<html>", "");
				tempOA.orderItem.add(temp);
				tempOA.txt.append(temp + "\t\t" + m3.jcb[i].getSelectedIndex() + "��" + "\n");
				tempOA.sum += (m3.jcb[i].getSelectedIndex()) * 89000;
				tempOA.orderAmount.add(m3.jcb[i].getSelectedIndex());
			}

		}
		tempOA.bills.append("���� �ݾ�: " + tempOA.sum + "���Դϴ�.");

		// System.out.println();
	}

}

public class orderPage extends JFrame {

	JLabel tangMenu = new JLabel("����");
	JLabel fryMenu = new JLabel("Ƣ��");
	JLabel drinkMenu = new JLabel("�ַ�");

	title ti = new title();
	orderArea oa = new orderArea();
	orderInfo table = new orderInfo(oa);

	public orderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("���α׷�");
		Container c = getContentPane();
		c.setLayout(null);

		ti.setSize(100, 35);
		ti.setLocation(350, 20);
		ti.setString("�ֹ� �ϱ�"); 
		c.add(ti);// Ÿ��Ʋ ���� ����
		
		table.setSize(500, 400);
		table.setLocation(10, 100);
		c.add(table); // �ֹ� ���� ���̺� ��ġ ����, �߰�
		
		oa.setSize(250, 250);
		oa.setLocation(530, 140);
		c.add(oa); // �ֹ� Ȯ�� ���̺� ��ġ ����, �߰�

		tangMenu.setFont(new Font("", Font.BOLD, 15));
		tangMenu.setSize(40, 30);
		tangMenu.setLocation(60, 77);
		c.add(tangMenu); // ��ǥ�� �� ���� Ÿ��Ʋ ����

		fryMenu.setFont(new Font("", Font.BOLD, 15));
		fryMenu.setSize(40, 30);
		fryMenu.setLocation(240, 77);
		c.add(fryMenu);// ��ǥ�� �� Ƣ��� Ÿ��Ʋ ����

		drinkMenu.setFont(new Font("", Font.BOLD, 15));
		drinkMenu.setSize(40, 30);
		drinkMenu.setLocation(400, 77);
		c.add(drinkMenu);// ��ǥ�� �� �ַ� Ÿ��Ʋ ����

		
		
		

		setSize(800, 500);
		setVisible(true);

		// ���� �׼� �̺�Ʈ�� ������ �� ��ǥ�� ���ϱ� ���� ���� �ڵ���. ���� ����
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
