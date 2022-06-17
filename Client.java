package ChattingApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;





//import com.sun.glass.events.MouseEvent;

public class Client implements ActionListener
{

	JTextField text;
	static JPanel a1 ;
	static Box vertical = Box.createVerticalBox();
	static DataOutputStream dout;
	static JFrame f = new JFrame();
	
	Client()
	{
		f.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7, 94, 84));
		p1.setBounds(0, 0, 400, 70);
		f.add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ChattingApplication/3.png"));
		Image i2 = i1.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel back = new JLabel(i3);
		
		back.setBounds(5, 20, 23, 23);
		p1.setLayout(null);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)	
			{
				//System.exit(0);
				f.setVisible(false);
			}
			});
		
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("ChattingApplication/2.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel profile = new JLabel(i6);
		
		profile.setBounds(40, 10, 50, 50);
		p1.setLayout(null);
		p1.add(profile);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("ChattingApplication/video.png"));
		Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel video = new JLabel(i9);
		
		video.setBounds(250, 20, 25, 25);
		p1.setLayout(null);
		p1.add(video);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("ChattingApplication/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel phone = new JLabel(i12);
		
		phone.setBounds(300, 20, 30, 25);
		p1.setLayout(null);
		p1.add(phone);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("ChattingApplication/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 24, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel morevert = new JLabel(i15);
		
		morevert.setBounds(350, 20, 10, 24);
		p1.setLayout(null);
		p1.add(morevert);
		
		JLabel name = new JLabel("Admin");
		name.setBounds(110, 25, 100, 10);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN SERIF", Font.BOLD, 13));
		p1.add(name);
		
		JLabel status = new JLabel("Online");
		status.setBounds(110, 43, 100, 10);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN SERIF", Font.BOLD, 11));
		p1.add(status);
		
		a1 = new JPanel();
		a1.setBounds(5,75, 390, 550);
		f.add(a1);
		
		text = new JTextField();
		text.setBounds(1, 630, 250, 21);
		text.setFont(new Font("SAN SERIF", Font.BOLD, 16));
		f.add(text);
		
		JButton send = new JButton("Send");
		send.setBounds(270, 630, 120, 20);
		send.setBackground(new Color(7, 94, 84));
		send.setForeground(Color.WHITE);
		send.addActionListener(this);
		send.setFont(new Font("SAN SERIF", Font.BOLD, 13));
		f.add(send);
		
		
		f.setSize(400, 650);
		f.setLocation(800, 50);
		f.setUndecorated(true);
		f.getContentPane().setBackground(Color.white);
		f.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
		String out = text.getText();
		a1.setLayout(new BorderLayout());
		
		
		
		JPanel p2 = formatLabel(out);
		
		
		JPanel right = new JPanel(new BorderLayout());
		right.add(p2, BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical, BorderLayout.PAGE_START);
		
		dout.writeUTF(out);
		text.setText("");
		
		f.repaint();
		f.invalidate();
		f.validate();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
}
	public static JPanel formatLabel(String out)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel("<html><p style=\"width: 150px\">"+ out + "</p></html>");
		output.setFont(new Font("Tahoma", Font.PLAIN, 16));
		output.setBackground(Color.green);
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		
		panel.add(time);
		
		
		
		return panel;
		
	}
	
	public static void main(String args[])
	{
		new Client();
		
		try {
			
			Socket s = new Socket("127.0.0.1", 6001);
			DataInputStream din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			
			while(true)
			{
				a1.setLayout(new BorderLayout());
				
				String msg = din.readUTF();
				JPanel panel = formatLabel(msg);
				
				JPanel left = new JPanel(new BorderLayout());
				left.add(panel, BorderLayout.LINE_START);
				vertical.add(left);
				vertical.add(Box.createVerticalStrut(15));
				a1.add(vertical, BorderLayout.PAGE_START);
				
				f.validate();
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
