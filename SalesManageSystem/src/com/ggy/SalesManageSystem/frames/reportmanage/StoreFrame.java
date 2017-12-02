package com.ggy.SalesManageSystem.frames.reportmanage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.frames.vipmanage.Module;

public class StoreFrame extends JFrame {
	public static void main(String[] args) {
		new StoreFrame().setVisible(true);
	}
	public StoreFrame() {
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(580, 200, 717, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblTittle = new JLabel("门店订货表");
		lblTittle.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setBounds(10, 0, 687, 42);
		contentPane.add(lblTittle);
		
		JLabel lblGoods_Name = new JLabel("商品名称：");
		lblGoods_Name.setFont(new Font("黑体", Font.PLAIN, 16));
		lblGoods_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoods_Name.setBounds(75, 52, 87, 31);
		contentPane.add(lblGoods_Name);
		
		txtGoods_Name = new JTextField();
		txtGoods_Name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtCount.requestFocus();
				}
			}
		});
		
		txtGoods_Name.setBounds(206, 52, 304, 31);
		contentPane.add(txtGoods_Name);
		txtGoods_Name.setColumns(10);
		
		JLabel lblCount = new JLabel("商品数量:");
		lblCount.setFont(new Font("黑体", Font.PLAIN, 16));
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(75, 121, 87, 28);
		contentPane.add(lblCount);
		
		txtCount = new JTextField();
		txtCount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtPrice.requestFocus();
				}
			}
		});
		txtCount.setBounds(206, 118, 304, 31);
		contentPane.add(txtCount);
		txtCount.setColumns(10);
		
		JLabel lblPrice = new JLabel("商品价格:");
		lblPrice.setFont(new Font("黑体", Font.PLAIN, 16));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(75, 182, 87, 31);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtList.requestFocus();
				}
			}
		});
		txtPrice.setBounds(206, 179, 304, 31);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblList = new JLabel("商品规格：");
		lblList.setHorizontalAlignment(SwingConstants.CENTER);
		lblList.setFont(new Font("黑体", Font.PLAIN, 16));
		lblList.setBounds(75, 242, 87, 28);
		contentPane.add(lblList);
		
		txtList = new JTextField();
		txtList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtDuration.requestFocus();
				}
			}
		});
		txtList.setBounds(206, 239, 304, 31);
		contentPane.add(txtList);
		txtList.setColumns(10);
		
		JLabel lblDuration = new JLabel("订货时间：");
		lblDuration.setFont(new Font("黑体", Font.PLAIN, 16));
		lblDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuration.setBounds(75, 299, 87, 28);
		contentPane.add(lblDuration);
		
		txtDuration = new JTextField();
		txtDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					btnBack.requestFocus();
					regist();
				}
			}
		});
		txtDuration.setBounds(206, 296, 304, 31);
		contentPane.add(txtDuration);
		txtDuration.setColumns(10);
		
		JButton btnAdd = new JButton("添加");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regist();
			}
		});
		btnAdd.setBounds(154, 350, 100, 34);
		contentPane.add(btnAdd);
		
		btnBack = new JButton("返回");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Module.r().setVisible(true);
			}
		});
		btnBack.setBounds(297, 350, 100, 34);
		contentPane.add(btnBack);
		
		btnAlreadyOrder = new JButton("已订商品");
		btnAlreadyOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StoreList.r().setVisible(true);
			}
		});
		btnAlreadyOrder.setBounds(439, 350, 93, 33);
		contentPane.add(btnAlreadyOrder);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 687, 402);
		contentPane.add(panel);
		
	}
	
	
	@SuppressWarnings("deprecation")
	private void regist(){
		db=new DBUtils();
		String goods_name = txtGoods_Name.getText();
		Integer Quantity_Purchased=Integer.parseInt(txtCount.getText());
		Integer Price=Integer.parseInt(txtPrice.getText());
		String list=txtList.getText();
		String Duration=txtDuration.getText();
		if("".equals(Duration)){
			Duration = new java.sql.Date(new Date().getTime()).toLocaleString();	
		}
		String sql="insert into order1 values(?,?,?,?,?)";
		db.update(sql, goods_name,Quantity_Purchased,Price,list,Duration);
		txtGoods_Name.setText("");
		txtCount.setText("");
		txtPrice.setText("");
		txtList.setText("");
		txtDuration.setText("");
		
	}

	public static StoreFrame r() {
		if(frame!=null){
			return frame;
		}else{
			return new StoreFrame();
		}		
	}
	
	private static final long serialVersionUID = 1L;
	/*本窗口*/
	private static StoreFrame frame;
	/*各类组件*/
	private JPanel contentPane,panel;
	private JTextField txtGoods_Name,txtCount,txtPrice,txtList,txtDuration;
    private JButton btnBack,btnAlreadyOrder;
    /*数据库*/
	private DBUtils db=null;
	
}
