package chatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.JTextField;

import com.google.gson.Gson;

public class MultiChatController extends Thread {

	private final MultiChatUI v;
	private final MultiChatData chatData;
	Message m;
	Gson gson = new Gson();
	boolean status = true;
	Logger logger;
	String ip = "172.16.10.229";
	Socket socket;
	BufferedReader inMsg = null;
	PrintWriter outMsg = null;
	Thread thread;
	int id;
	int guest;
	String type="msg";
	

	public MultiChatController(MultiChatData chatData, MultiChatUI v) {
		logger = Logger.getLogger(this.getClass().getName());

		this.chatData = chatData;
		this.v = v;

	}

	public void appMain(int id) {
		this.id = id;

		chatData.addObj(v.msgOut);
		connectServer();
		
		v.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Object obj = e.getSource();
				
				if (obj == v.msgInput) {
					outMsg.println(gson.toJson(new Message(Integer.toString(id),v.msgInput.getText(),type)));
					//type이 테이블 좌석(번호)인 경우 귓속말 / type이 "msg"인 경우 전체체팅
					v.msgInput.setText("");
					System.out.println(new Message(Integer.toString(id),v.msgInput.getText(),type).getType());
					type = "msg"; // 귓속말 보낸 후 다시 전체체팅으로 초기화
				}
			}

		});

		
	}
	
	public void setType(int n) {
		type = Integer.toString(n);
	}
	
	public MultiChatUI getUI() {
		return v;
	}

	public void connectServer() {
		try {
			
			socket = new Socket(ip, 8888);
			logger.info("[Client]Server 연결 성공");
			inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outMsg = new PrintWriter(socket.getOutputStream(), true);
			thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			logger.warning("[MultiChatUI]connectServer() Exception 발생!");
			e.printStackTrace();
		}
	}
	
	public void run() {
		String msg;
		Message m = new Message();
		Gson gson = new Gson();
		while(status) {
			try {
				msg = inMsg.readLine();
				m = gson.fromJson(msg,Message.class);
				chatData.refreshData(m.getId()+" Table"+">"+m.getMsg()+"\n");
				v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
				
			}catch(IOException e) {
				logger.warning("[MultiChatUI]메시지 스트림 종료!");
			}
		}
		logger.info("[MultiChatUI]"+thread.getName()+"메시지 수신 스레드 종료");
	}
	
	public JTextField getField() {
		return v.msgInput;
	}
}
