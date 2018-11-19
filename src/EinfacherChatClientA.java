import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EinfacherChatClientA {

	JTextField ausgehend;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args) {
		EinfacherChatClientA client = new EinfacherChatClientA();
		client.los();
	}

	public void los() {
		JFrame frame = new JFrame("Chat-Client");
		JPanel hauptPanel = new JPanel();
		ausgehend = new JTextField(20);
		JButton sendenButton = new JButton("Senden");
		sendenButton.addActionListener(new SendenButtonListener());
		hauptPanel.add(ausgehend);
		hauptPanel.add(sendenButton);
		netzwerkEinrichten();
		frame.getContentPane().add(BorderLayout.CENTER, hauptPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	public void netzwerkEinrichten() {
try {
				sock = new Socket("127.0.0.1", 5000);
				writer = new PrintWriter(sock.getOutputStream());
				System.out.println("Netzwerkverbindung steht.");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
	}

	private class SendenButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				writer.println(ausgehend.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			ausgehend.setText("");
			ausgehend.requestFocus();

		}
	}
}
