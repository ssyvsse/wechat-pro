package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.frames.vipmanage.StaffAddPanel;

public class StaffRegist extends JFrame {
	
	
	public static StaffRegist r(){
		if(frame!=null)
			return frame;
		else
			return new StaffRegist();
	}
	public StaffRegist() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 520, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		staffAddPane = new StaffAddPanel();
		contentPane.add(staffAddPane);
		setResizable(false);
	}
	private StaffAddPanel staffAddPane;
	
	
	private static final long serialVersionUID = 1L;
	private static StaffRegist frame;
	private JPanel contentPane;
}
