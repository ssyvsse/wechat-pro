package com.ggy.SalesManageSystem.frames.vipmanage;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author LTW
 *
 */
public class AboutPanel extends JPanel {
	public AboutPanel() {
		setLayout(null);		
		
		JLabel lblAbout = new JLabel("关于");
		lblAbout.setBounds(96, 118, 54, 15);
		add(lblAbout);		
		
		JLabel lblNewLabel = new JLabel("<html>版本2.0.0.0322_beta<br>Sales Manage System<br>版权所有2017 Java1704一组 保留所有权利。");
		lblNewLabel.setBounds(149, 143, 249, 76);
		add(lblNewLabel);
	}
	private static final long serialVersionUID = 1L;
}
