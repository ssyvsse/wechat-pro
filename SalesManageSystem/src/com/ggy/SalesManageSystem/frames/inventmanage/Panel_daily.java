package com.ggy.SalesManageSystem.frames.inventmanage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Panel_daily extends JPanel {
	private static final long serialVersionUID = 1L;
	public Panel_daily() {
		setSize(814, 659);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 814, 659);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
	}
	public JTable jtable(){
		if(table!=null)
			return table;
		else
			return new JTable();
	}
	private JTable table;
}
