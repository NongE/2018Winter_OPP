package checkTableUI;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import chatClient.MultiChatController;
import model.*;
public class myListener implements ActionListener{
	tableButton btn;
	ArrayList<CorderVO> orderdatas;
	MenuVO menudata;
	PriceVO pricedata;
	MultiChatController myChat;
	
	public myListener(tableButton tbtn, MultiChatController myChat){
		btn = tbtn;
		this.myChat = myChat;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		System.out.println(btn.tnum);
		
		if(obj == btn) {
			myChat.setType(btn.tnum); // 테이블 버튼이 눌린경우 type을 테이블 번호로 설정
			myChat.getField().setText("> " + Integer.toString(btn.tnum)+ " : ");
			
		}
		
	}

}
