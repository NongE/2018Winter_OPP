package chatClient;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class MultiChatData {
	JTextArea msgOut;
	public MultiChatData() {
		
	}
	
	void addObj(JComponent comp) {
		this.msgOut = (JTextArea)comp;
		
	}
	void refreshData(String msg) {
		msgOut.append(msg);
		
	}
	
	public static void main(String args[]) {
		
	}

}
