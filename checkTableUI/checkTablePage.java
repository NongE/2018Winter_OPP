package checkTableUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import chatClient.*;
import checkClient.*;
import mainUI.backBtn;
import mainUI.titlePanel;

public class checkTablePage extends JFrame{
	titlePanel myTitle;
	infoPanel myTable;
	
	MultiChatController myChat;
	MultiChatUI myChatUI;
	
	public backBtn myBack;

	
	
	public checkTablePage(MultiChatController myChat){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		
		
		setSize(800, 500);
		
		myTitle = new titlePanel();
		myTable = new infoPanel();
		MultiChatData myChatData = new MultiChatData();
		this.myChat = myChat;
		this.myChatUI = myChat.getUI();
		
		
		myBack= new backBtn();
		myBack.setSize(100,35);
		myBack.setLocation(10,10);
		
		myTitle.setSize(300,35);
		myTitle.setLocation(this.getSize().width*5/16, this.getSize().height*3/50);
		myTitle.setString("좌석 확인하기"); // 타이틀 제목 설정
		
		myTable.setSize(300,300);
		myTable.setLocation(this.getSize().width*1/8, this.getSize().height*1/5);
		
		myChatUI.setSize(300,300);
		myChatUI.setLocation(this.getSize().width*1/2, this.getSize().height*1/5);
		
		for(int i = 0; i < 12; i++) {
			myTable.tbtn[i].addActionListener(new myListener(myTable.tbtn[i], myChat));
			if(myTable.tbtn[i].pnum == 0)
			{
				myTable.tbtn[i].setEnabled(false);
			}
		}
		
		
		
		c.add(myTitle);
		c.add(myTable);
		c.add(myBack);
		c.add(myChatUI);

		setVisible(true);
	}


}
