package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangePerson extends JDialog {
	
	public ChangePerson() {
		dialog = this;
		setTitle("交接班表");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		dialog = this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textMsg = new JTextArea();
			textMsg.setLineWrap(true);
			textMsg.setWrapStyleWord(true);
			textMsg.setEditable(false);
			contentPanel.add(textMsg, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				textInput = new JTextField();
				textInput.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						
					}
				});
				textInput.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textInput.setText("");
					}
				});
				
				textInput.setText("请输入名字：");
				buttonPane.add(textInput);
				textInput.setColumns(10);
			}
			{
				JButton btnOnTime = new JButton("上班");
				btnOnTime.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						textMsg.append(textInput.getText()+"打卡成功，上班时间为："+(new Date().toLocaleString())+"\r\n");
					}
				});
				btnOnTime.setActionCommand("OK");
				buttonPane.add(btnOnTime);
				getRootPane().setDefaultButton(btnOnTime);
			}
			{
				JButton btnOverTime = new JButton("下班");
				btnOverTime.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						textMsg.append(textInput.getText()+"打卡成功，下班时间为："+(new Date().toLocaleString())+"\r\n");
					}
				});
				btnOverTime.setActionCommand("Cancel");
				buttonPane.add(btnOverTime);
			}
		}
	}
	
	public static ChangePerson r(){
		if(dialog!=null)
			return dialog;
		else
			return new ChangePerson();
	}
	
	private static final long serialVersionUID = 1L;
	/*本对话框*/
	private static ChangePerson dialog;
	private final JPanel contentPanel = new JPanel();
	private JTextField textInput;
	private JTextArea textMsg;

}
