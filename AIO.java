import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
import chatClient.*;
import model.*;
import orderUI.orderPage;
import checkTableUI.*;
import mainUI.mainPage;
import model.*;
import checkClient.*;
import orderClient.*;


public class AIO {
//all in one
	static orderPage op;
	static checkTablePage ct;
	static MultiChatController myChat;
	static MultiChatUI myChatUI;
	static MultiChatData myChatData;
	static int id = 4;
	
	public static void main(String args[]) {
		
		myChatData = new MultiChatData();
		myChatUI = new MultiChatUI();
		myChat = new MultiChatController(myChatData,myChatUI);
		myChat.appMain(id);
		// 채팅 서버를 실행
		
		mainPage mp = new mainPage();
		mp.order.addActionListener(new ActionListener() {
			// 주문하기 버튼이 눌렀을때 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.setVisible(false);
				op = new orderPage();
				op.setVisible(true);
				
				op.myBack.addActionListener(new ActionListener() {
					// 뒤로가기키 액션
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						op.setVisible(false);
						mp.setVisible(true);
					}
					
				});

			}

		});
		
		mp.checkSeat.addActionListener(new ActionListener() {
			// 좌석확인 버튼이 눌렸을때 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				mp.setVisible(false);
				ct = new checkTablePage(myChat);
				ct.setVisible(true);
				
				
				ct.myBack.addActionListener(new ActionListener() {
					// 뒤로가기 키 액션
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ct.setVisible(false);
						mp.setVisible(true);
					}
					
				});
			}

		});
		
		
	}
}
