package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChooeseRegist extends JDialog {
	
	
	public static JDialog r(){
		if(jdialog!=null)
			return jdialog;
		else
			return new ChooeseRegist();
	}


	public ChooeseRegist() {		
		jdialog = this;
		setBounds(680, 300, 400, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JButton btnNewButton = new JButton("员工注册");
			btnNewButton.setBounds(27, 73, 168, 71);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					StaffRegist.r().setVisible(true);
				}
			});
			contentPanel.setLayout(null);
			btnNewButton.setFont(new Font("宋体", Font.PLAIN, 26));
			contentPanel.add(btnNewButton);
		}
		
		JButton btnzhuce = new JButton("商户注册");
		btnzhuce.setBounds(209, 73, 159, 71);
		btnzhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MerchantRegist.r().setVisible(true);
			}
		});
		btnzhuce.setFont(new Font("宋体", Font.PLAIN, 26));
		contentPanel.add(btnzhuce);
		setResizable(false);
	}
	
	private static final long serialVersionUID = 1L;
	private static ChooeseRegist jdialog;
	private final JPanel contentPanel = new JPanel();
}
