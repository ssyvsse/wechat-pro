package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.frames.inventmanage.Kucun;
import com.ggy.SalesManageSystem.frames.reportmanage.ReportModule;
import com.ggy.SalesManageSystem.frames.reportmanage.StoreFrame;
import com.ggy.SalesManageSystem.frames.salesmanage.Cashier;
import com.ggy.SalesManageSystem.frames.salesmanage.LoginScreen;
import java.awt.SystemColor;
import java.awt.Color;

/**
 * @author LTW
 *
 */
public class Module extends JFrame {	
	
	public Module() {
		setBackground(SystemColor.window);
		m = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(630, 300, 620, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setForeground(SystemColor.textHighlight);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnJhgl = new JButton("进货管理");
		btnJhgl.setForeground(new Color(160, 82, 45));
		btnJhgl.setBackground(Color.WHITE);
		btnJhgl.setFont(new Font("黑体", Font.PLAIN, 20));
		btnJhgl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StoreFrame.r().setVisible(true);
			}
		});
		btnJhgl.setBounds(69, 50, 127, 67);
		panel.add(btnJhgl);

		JButton btnXsgl = new JButton("销售管理");
		btnXsgl.setForeground(new Color(160, 82, 45));
		btnXsgl.setBackground(Color.WHITE);
		btnXsgl.setFont(new Font("黑体", Font.PLAIN, 20));
		btnXsgl.setBounds(236, 50, 127, 67);
		btnXsgl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Cashier.r().setVisible(true);
			}
		});
		panel.add(btnXsgl);

		JButton btnKcgl = new JButton("库存管理");
		btnKcgl.setForeground(new Color(160, 82, 45));
		btnKcgl.setBackground(Color.WHITE);
		btnKcgl.setFont(new Font("黑体", Font.PLAIN, 20));
		btnKcgl.setBounds(407, 50, 127, 67);
		btnKcgl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Kucun.r().setVisible(true);
			}
		});
		panel.add(btnKcgl);

		JButton btnBbgl = new JButton("报表管理");
		btnBbgl.setForeground(new Color(160, 82, 45));
		btnBbgl.setBackground(Color.WHITE);
		btnBbgl.setFont(new Font("黑体", Font.PLAIN, 20));
		btnBbgl.setBounds(69, 138, 127, 67);
		btnBbgl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ReportModule.r().setVisible(true);
			}
		});
		panel.add(btnBbgl);

		JButton btnYhqx = new JButton("退出登录");
		btnYhqx.setForeground(new Color(160, 82, 45));
		btnYhqx.setBackground(Color.WHITE);
		btnYhqx.setFont(new Font("黑体", Font.PLAIN, 20));
		btnYhqx.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
//				Cashier.r().dispose();
//				ManageMain.r().dispose();
//				Kucun.r().dispose();				
				new LoginScreen().setVisible(true);
			}
		});
		btnYhqx.setBounds(407, 138, 127, 67);
		panel.add(btnYhqx);
		
		JButton btnStaffgl = new JButton("员工管理");
		btnStaffgl.setForeground(new Color(160, 82, 45));
		btnStaffgl.setBackground(Color.WHITE);
		btnStaffgl.setFont(new Font("黑体", Font.PLAIN, 20));
		btnStaffgl.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				hide();
				new ManageMain().setVisible(true);
			}
		});
		btnStaffgl.setBounds(236, 138, 127, 67);
		panel.add(btnStaffgl);

//		setUndecorated(true);
	}

	public static Module r() {
		if (m != null)
			return m;
		else
			return new Module();
	}
	
	private static final long serialVersionUID = 1L;
	private static Module m;
	private JPanel contentPane;

}
