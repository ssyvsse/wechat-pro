package com.ggy.SalesManageSystem.frames.inventmanage;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

public class Timer extends JPanel implements Runnable{
	
	public Timer() {
		setLayout(null);
		setSize(307, 38);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, 0, 307, 38);
		add(lblNewLabel);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true){
			lblNewLabel.setText(new Date().toLocaleString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
}
