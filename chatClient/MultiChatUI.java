package chatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class MultiChatUI extends JPanel {

	
	private JLabel inLabel;
	protected JLabel outLabel;
	protected JTextField idInput;

	protected JTextArea msgOut;

	private JPanel msgPanel;
	protected JTextField msgInput;
	protected String id;

	public MultiChatUI() {
		setLayout(new BorderLayout());



		msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		msgInput = new JTextField();
		msgPanel.add(msgInput, BorderLayout.CENTER);


		msgOut = new JTextArea("", 10, 30);
		msgOut.setEditable(false);

		JScrollPane jsp = new JScrollPane(msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(jsp, BorderLayout.CENTER);
		add(msgPanel, BorderLayout.SOUTH);
		
	}

	public void addButtonActionListener(ActionListener listener) {
		msgInput.addActionListener(listener);

	}

}
