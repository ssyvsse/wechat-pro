package com.ggy.SalesManageSystem.frames.reportmanage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.StoreOrder;
import com.ggy.SalesManageSystem.dao.impl.StoreOrderImpl;
import com.ggy.SalesManageSystem.entity.Store;

public class StoreList extends JFrame {

	public static StoreList r(){
		if(frame!=null){
			return frame;
		}else{
			return new StoreList();
		}
	}
	
	public StoreList() {
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 850, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("订货商品列表");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 800, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g();
			}
		});
		btnNewButton.setBounds(443, 71, 85, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int line=table.getSelectedRow();//选中的行getSelectedRow count列；
				if(line==-1){
					JOptionPane.showMessageDialog(null,"请选择要删除的行！");
				}else{
					String str = (String)model.getValueAt(line, 0);
					int str1 = (Integer)model.getValueAt(line, 1);
					double str2 = (double)model.getValueAt(line, 2);
					String str3 = (String)model.getValueAt(line, 3);
					String str4 = (String)model.getValueAt(line, 4);
					
					db=new DBUtils();
					String sql="delete from order1 where Goods_Name=? and quantity_purchased=? and price=? and List=? and Duration=?";
					if(db.update(sql, str,str1,str2,str3,str4)>=1){
						model.removeRow(line);
						table.updateUI();
					}else{
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		btnNewButton_1.setBounds(632, 71, 85, 28);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 117, 789, 354);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		model =new DefaultTableModel(new String[]{"商品名称","商品数量","商品价格","规格","订货时间"},0){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int col){
				if(col==0){
					return false;
				}else{
					return true;
				}		
			}
			
		};
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setModel(model);
		scrollPane.setViewportView(table);
		de=new StoreOrderImpl();
		loaddate();
		
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StoreFrame.r().setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(725, 71, 85, 28);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查找");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model =new DefaultTableModel(new Object[][]{},new String[]{"商品名称","商品数量","商品价格","规格","订货时间"});
				table.setModel(model);
				scrollPane.setViewportView(table);
				de=new StoreOrderImpl();
				c();
//				loadinset();
			}
		});
		btnNewButton_3.setBounds(223, 71, 89, 28);
		contentPane.add(btnNewButton_3);
		
		JLabel lbll = new JLabel("商品名称：");
		lbll.setFont(new Font("宋体", Font.PLAIN, 14));
		lbll.setBounds(54, 78, 79, 15);
		contentPane.add(lbll);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					model.getDataVector().clear();
					c();
				}
			}
		});
		textField.setBounds(127, 75, 85, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnFresh = new JButton("刷新");
		btnFresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loaddate();
				table.updateUI();
			}
		});
		btnFresh.setBounds(536, 72, 85, 28);
		contentPane.add(btnFresh);
		
	}
	
	//显示全部
	public void loaddate(){
		model.getDataVector().clear();
		List<Store> list=de.queryAll();
		for (Store store : list) {
			model.addRow(new Object[]{store.getGoods_Name(),store.getQuantity_Purchased(),store.getPrice(),store.getList(),store.getDuration()});
		}
	}
	@SuppressWarnings("unused")
	private void loadinset(){
		model.getDataVector().clear();		
	}
	//修改方法
	@SuppressWarnings("deprecation")
	private void g(){		
		int row=table.getSelectedRow();
		if(row!=-1){
			db=new DBUtils();
			String value=model.getValueAt(row, 0).toString();
			int value1=Integer.parseInt(model.getValueAt(row, 1).toString());
			Double value2=Double.valueOf(model.getValueAt(row, 2).toString());
			String value3=model.getValueAt(row, 3).toString();
			String value4=new Date().toLocaleString();
			if(db.update("update order1 set Quantity_Purchased=?,Price=?,List=?,Duration=? where Goods_Name=?",value1,value2,value3,value4,value)>=1){
				JOptionPane.showMessageDialog(null, "修改成功");
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}	
		}else{
			JOptionPane.showMessageDialog(null, "请选择要修改的行！");
		}
	}
    //查询的方法
	private void c(){
		db=new DBUtils();
		
		String va=textField.getText();
		String sql="select * from order1 where goods_name =?";
		ResultSet rs=db.query(sql, va);
		System.out.println(rs);
		try {
			while(rs.next()){
				Object[] obj = {rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5)};
				model.addRow(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static StoreList frame;
	private JTable table;
	private StoreOrder de;
	private DefaultTableModel model;
	private JTextField textField;
	private JScrollPane scrollPane;
	private DBUtils db;
}
