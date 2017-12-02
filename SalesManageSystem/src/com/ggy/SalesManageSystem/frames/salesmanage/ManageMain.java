package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.frames.vipmanage.QueryEpanel;
import com.ggy.SalesManageSystem.frames.vipmanage.StaffAddPanel;

/**
 * @author LTW
 *
 */
public class ManageMain extends JFrame implements ActionListener{

	public ManageMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 874, 25);
		contentPane.add(panel);
		
		JLabel lblEset = new JLabel("设置");
		panel.add(lblEset);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 30, 91, 527);
		contentPane.add(panel_1);
		
		JButton btnEadd = new JButton("新增员工");
		//为按钮添加命令
		btnEadd.setActionCommand("adddata");
		panel_1.add(btnEadd);
		
		JButton btnEload = new JButton("查询编辑");
		//为按钮添加命令
		btnEload.setActionCommand("loaddata");
		panel_1.add(btnEload);
		
		JButton btnEqx = new JButton("权限设置");
		panel_1.add(btnEqx);
		
		panelEbutton = new JPanel();
		panelEbutton.setBounds(106, 40, 773, 517);
		contentPane.add(panelEbutton,BorderLayout.CENTER);
		panelEbutton.setLayout(new BorderLayout(0, 0));
		
		//注册监听
				btnEload.addActionListener(this);
				btnEadd.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//获取命令
				String command = e.getActionCommand();
				if(command.equals("loaddata")){
					 panelEbutton.removeAll();//移除所有组件
					 btnEquery = new QueryEpanel();
					 panelEbutton.add(btnEquery,BorderLayout.CENTER);
					 panelEbutton.updateUI();//刷新面板
				}else if(command.equals("adddata")){
					panelEbutton.removeAll();
					sap = new StaffAddPanel();
					panelEbutton.add(sap,BorderLayout.CENTER);
					panelEbutton.updateUI();
				}
		
	}
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelEbutton;
	private QueryEpanel btnEquery;
	private StaffAddPanel sap;
}
