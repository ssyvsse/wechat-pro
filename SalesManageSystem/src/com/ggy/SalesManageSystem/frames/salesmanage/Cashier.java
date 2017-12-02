package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import com.ggy.SalesManageSystem.frames.inventmanage.Timer;
import com.ggy.SalesManageSystem.frames.vipmanage.Module;
import com.ggy.SalesManageSystem.frames.vipmanage.VipMain;

public class Cashier extends JFrame {
	
	public Cashier() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setLabel();
			}
		});
		frame = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1061, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("销售管理系统");
		label.setFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", label.getFont().getStyle() | Font.BOLD | Font.ITALIC, 25));
		label.setBounds(10, 18, 188, 64);
		contentPane.add(label);

		JButton btnExchange = new JButton("交接班");
		btnExchange.setFont(new Font("宋体", Font.PLAIN, 17));
		btnExchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePerson.r().setVisible(true);
			}
		});
		btnExchange.setBounds(212, 18, 133, 64);
		contentPane.add(btnExchange);

		JButton btnNewVip = new JButton("新会员");
		btnNewVip.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewVip.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				hide();
				VipMain.r().setVisible(true);
			}
		});
		btnNewVip.setBounds(370, 18, 133, 64);
		contentPane.add(btnNewVip);

		JButton btnGuaDan = new JButton("挂单");
		btnGuaDan.setFont(new Font("宋体", Font.PLAIN, 17));
		btnGuaDan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "待开发");
				putsUp();
			}
		});
		btnGuaDan.setBounds(517, 18, 133, 64);
		contentPane.add(btnGuaDan);

		JButton btnAllFun = new JButton("所有功能");
		btnAllFun.setFont(new Font("宋体", Font.PLAIN, 17));
		btnAllFun.setBounds(682, 18, 133, 64);
		btnAllFun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Module.r().setVisible(true);
			}
		});
		contentPane.add(btnAllFun);

		lblMarketName = new JLabel("超市名称:");
		lblMarketName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblMarketName.setBounds(853, 18, 188, 26);
		contentPane.add(lblMarketName);

		lblE_No = new JLabel("\u5DE5\u53F7\uFF1A");
		lblE_No.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblE_No.setBounds(853, 45, 188, 26);
		contentPane.add(lblE_No);

		JScrollPane showMsg = new JScrollPane();
		showMsg.setBounds(10, 106, 1031, 358);
		contentPane.add(showMsg);

		table = new JTable();			
		table.setRowHeight(30);

		cell = new String[] { "商品条码", "商品名称", "价格",
				"数量", "小计" };
		// 使用內部类设置不可编辑
		model = new DefaultTableModel(cell, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
//				if (column == 3)
//					return true;
//				else
					return false;
			}

		};
		table.setModel(model);
		table.setEnabled(true);
		showMsg.setViewportView(table);

		lblShowGoodsCount = new JLabel("总共0件商品");
		lblShowGoodsCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowGoodsCount.setFont(new Font("宋体", Font.PLAIN, 19));
		lblShowGoodsCount.setBounds(14, 474, 184, 45);
		contentPane.add(lblShowGoodsCount);

		txtInputBarcode = new JTextField();
		txtInputBarcode.setFont(new Font("宋体", Font.PLAIN, 20));
		txtInputBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtInputBarcode.getText())){
						insertIntoMiddleTable();
						calsumCount();
					}
					else
						JOptionPane.showMessageDialog(null, "请输入条形码 ！");
					// 读取并显示每一条商品信息之后清空输入区域的 文字
					txtInputBarcode.setText("");
				}
			}
		});
		txtInputBarcode.setHorizontalAlignment(SwingConstants.CENTER);
		txtInputBarcode.setText("手动输入条形码");
		txtInputBarcode.setBounds(10, 540, 188, 64);
		contentPane.add(txtInputBarcode);
		txtInputBarcode.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				txtInputBarcode.setText("手动输入条形码");
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtInputBarcode.setText("");
			}
		});

		txtInputBarcode.setColumns(10);

		txtInput = new JTextField();
		txtInput.setFont(new Font("宋体", Font.PLAIN, 20));
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtInput.getText()))
						queryVip();
					else
						JOptionPane.showMessageDialog(null, "请输入会员姓名或手机号 ！");
					txtInput.setText("");
				}
			}
		});
		txtInput.setBounds(478, 502, 172, 40);
		contentPane.add(txtInput);

		txtInput.setColumns(10);

		JLabel lblPhoneOrVip_id = new JLabel("手机号/会员号：");
		lblPhoneOrVip_id.setFont(new Font("宋体", Font.PLAIN, 20));
		lblPhoneOrVip_id.setBounds(325, 494, 152, 52);
		contentPane.add(lblPhoneOrVip_id);

		JButton btnReturn = new JButton("退货");
		btnReturn.setFont(new Font("宋体", Font.PLAIN, 20));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnGoods.r().setVisible(true);
			}
		});
		btnReturn.setBounds(911, 494, 120, 44);
		contentPane.add(btnReturn);

		JButton btnDelete = new JButton("删除");
		btnDelete.setFont(new Font("宋体", Font.PLAIN, 20));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afterDelUpdate();
			}
		});
		btnDelete.setBounds(773, 494, 120, 44);
		contentPane.add(btnDelete);

		btnCalAll = new JButton("结算¥：");
		btnCalAll.setFont(new Font("宋体", Font.PLAIN, 20));
		btnCalAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cash = 0;
				try {
					cash = Integer.parseInt(JOptionPane.showInputDialog("收取:"));
					if (cash > 0) {
						if(cash>=sum){
							JOptionPane.showMessageDialog(null, "结算成功，找零：" + (cash - sum));
							savaRecord();
							deleteKuncun();
							model.getDataVector().clear();
							model = new DefaultTableModel(cell, 0) {
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row, int column) {
//									if (column == 3)
//										return true;
//									else
										return false;
								}

							};
							table.setModel(model);
							table.updateUI();
							btnCalAll.setText("结算¥：");
							count=1;
							rowCount = 0;
							sumCount=0;
							goods.clear();
							mount.clear();
							lblShowGoodsCount.setText("总共0件商品");
						}else{
							JOptionPane.showMessageDialog(null, "现金不够，结算失败！");
						}
					} else {
						JOptionPane.showMessageDialog(null, "结算失败，请重新结算！：");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "输入错误，请重新输入！");
				}
			
			}
		});
		btnCalAll.setHorizontalAlignment(SwingConstants.LEFT);
		btnCalAll.setBounds(773, 545, 258, 59);
		contentPane.add(btnCalAll);

		txtShowPhone = new JTextField();
		txtShowPhone.setFont(new Font("宋体", Font.PLAIN, 20));
		txtShowPhone.setBackground(Color.WHITE);
		txtShowPhone.setEditable(false);
		txtShowPhone.setColumns(10);
		txtShowPhone.setBounds(325, 559, 152, 40);
		contentPane.add(txtShowPhone);

		txtShowName = new JTextField();
		txtShowName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtShowName.setBackground(Color.WHITE);
		txtShowName.setEditable(false);
		txtShowName.setColumns(10);
		txtShowName.setBounds(478, 559, 172, 40);
		contentPane.add(txtShowName);

		timer = new Timer();
		new Thread(timer).start();
		timer.setSize(188, 33);
		timer.setLocation(853, 69);
		contentPane.add(timer);
//		setUndecorated(true);
	}

	public static Cashier r() {
		if (frame != null)
			return frame;
		else
			return new Cashier();
	}

	private void setLabel() {
		String[] msg = LoginScreen.r().send();
		int len = msg.length;
		for (int i = 0; i < len; i++) {
			lblMarketName.setText("超市：" + msg[1]);
			lblE_No.setText("员工：" + msg[0]);
		}
	}

	// 输入会员姓名，或手机号，查询是否会员
	private void queryVip() {
		String vip = txtInput.getText();
		db = new DBUtils();
		ResultSet rs = db.query(
				"select * from vip_manage where tel=? or name=?", vip, vip);
		try {
			if(rs.next()){
				txtShowPhone.setText((String) (rs.getObject("tel") + ""));
				txtShowName.setText((String) rs.getObject("name"));
			}else{
				JOptionPane.showMessageDialog(null, "该会员号不存在！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 输入条形码，一条一条读取数据
	private boolean loaddate(String barcode) {
		db = new DBUtils();		
		boolean bl = true;
		ResultSet rs = db.query("select barcode,goods_name,unit_price from "
				+ "commodityinfo where barcode=?", barcode);		
		try {
			if (rs.next()) {
				model.addRow(new Object[] { rs.getObject("barcode"),
						rs.getObject("goods_name"), rs.getObject("unit_price") });	
				
				bl = true;
			}else{
				JOptionPane.showMessageDialog(null, "该商品不存在");
				bl = false;
			}
		} catch (SQLException e) {			
			bl = false;
		} finally {
			db.close();
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return bl;
	}
	
	/**
	 * 获取到信息之后进行判断，然后添加到中间的表格中
	 */
	@SuppressWarnings("unchecked")
	private void insertIntoMiddleTable() {
		String good = txtInputBarcode.getText().toLowerCase();
		bll = goods.containsKey(good);
		if (bll) {//如果再次输入的商品已存在，就进行count增加计算，
			count = (Integer) mount.get(good);//从mount中取出已经计算的数量
			count++;//数量+1
			mount.put(good, count);//再存入mount中
			//对某一商品的数量进行增加计算
			model.setValueAt(count, (Integer) goods.get(good), 3);
			;
			calSmallTotal();			
		} else {
			//读取条形码的信息
			if(loaddate(good)){
				// goods存几行
				goods.put(good, rowCount);
				//每一次，有不同商品进来都把商品的数量重置为1			
				mount.put(good, 1);
				//对某一商品的数量进行增加计算
				model.setValueAt(mount.get(good),  goods.size()-1, 3);
				//进行小计
				calSmallTotal();				
				
				//每次有不同的商品，行数都加1
				rowCount++;
				//每次有不同的商品，数量都重置为1
				count = 1;
			}else{
				
			}
		}
		calTotal();
	}

//	public static void main(String[] args) {
//		new Cashier().setVisible(true);
//	}
	
	/**
	 * 计算每件商品的小计
	 */
	private void calSmallTotal() {
		int rowCount = model.getRowCount();
		int total;
		for (int i = 0; i < rowCount; i++) {
			//小计 = 单价 * 数量
			total = Integer.parseInt(model.getValueAt(i, 2).toString())			
					* Integer.parseInt(model.getValueAt(i, 3).toString());		
			model.setValueAt(total, i, 4);
		}		
	}

	
	/**
	 * 计算结算金额
	 */
	private void calTotal() {
		sum = 0;
		int rowCount = model.getRowCount();
		for (int i = 0; i < rowCount; i++) {	//总金额 = 每行小计相加
			sum += Integer.parseInt(model.getValueAt(i, 4).toString());
		}
		btnCalAll.setText("结算¥：      " + sum);		
	}
	
	
	/**
	 * 删除某件商品
	 * @param str
	 */
	private boolean delete(String str){
		boolean bl = false;
		int rowCount = model.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			String s = model.getValueAt(i, 0).toString();
			if(s.equalsIgnoreCase(str)){
				model.removeRow(i);
				mount.remove(str);
				goods.remove(str);
				calsumCount();
				bl = true;
			}else
				bl = false;				
		}
		if(!bl)
			JOptionPane.showMessageDialog(null, "该商品没有在准备购买的商品中！");
		return bl;
	}
	
	
	/**
	 * 删除之后，如果数据为零，进行重置
	 */
	@SuppressWarnings("rawtypes")
	private void afterDelUpdate(){
		String str = JOptionPane.showInputDialog(null, "请输入要删除的商品的条形码！");
		if(delete(str)){
			calSmallTotal();
			calTotal();
			if(model.getRowCount()==0){
				model.getDataVector().clear();
				goods = new HashMap();
				mount = new HashMap();
				rowCount=0;
			}
		}else{
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	/**
	 * 结算之后保存记录到销售记录表中
	 */
	@SuppressWarnings("deprecation")
	private void savaRecord(){
		@SuppressWarnings("rawtypes")
		Vector v = model.getDataVector();
		StringBuffer str = new StringBuffer();
		str.append(new Date().toLocaleString()+"\t");
		for (int i = 0; i < v.size(); i++) {
			str.append(v.get(i)+"\r\n");
		}
		db = new DBUtils();
		if(db.update("insert into sales_record(detail,total_money) values(?,?)",str.toString(), sum)>=1){
		}else{
			JOptionPane.showMessageDialog(null, "保存失败");
		}
	}
	
	private boolean bl = true;
	@SuppressWarnings("rawtypes")
	private Vector v;
	/**
	 * 挂单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void putsUp(){
		if(bl){
			if(JOptionPane.showConfirmDialog(null, "是否挂单？")==0){
				v = (Vector)model.getDataVector().clone();
				model.getDataVector().clear();
				table.updateUI();
				lblShowGoodsCount.setText("总共0件商品");
				calSmallTotal();
				calTotal();
				bl = false;
				good2 = new HashMap(goods);
				mount2 = new HashMap(mount);
				if(model.getRowCount()==0){
					model.getDataVector().clear();
					goods.clear();
					mount.clear();
					lblShowGoodsCount.setText("总共0件商品");
					rowCount=0;
				}
			}
			
		}else{
			if(JOptionPane.showConfirmDialog(null,  "有一单挂单，将会删除目前的信息，是否显示")==0){
				model.getDataVector().clear();
				for (int i = 0; i < v.size(); i++) {
					model.addRow((Vector)v.get(i));
				}
				goods = new HashMap(good2);
				mount = new HashMap(mount2);
				calSmallTotal();
				calTotal();	
				calsumCount();
				v.clear();
				good2.clear();
				mount2.clear();
				bl = true;
				bll = true;
			}	
			
		}
	}
	

	/**
	 * 计算总的商品件数
	 */
	private void calsumCount(){		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List list = new ArrayList(mount.values());
		sumCount = 0;
		for (int i = 0; i<list.size(); i++) {
			sumCount += Integer.parseInt(list.get(i).toString());
		}
		lblShowGoodsCount.setText("总共"+sumCount+"件商品");
	}
	
	/**
	 * 结算之后删除库存里的东西
	 */
	private void deleteKuncun(){
		int rowCount = model.getRowCount();
		int[] mou = new int[rowCount];
		String[] bar = new String[rowCount];
		for (int i = 0; i < rowCount; i++) {
			mou[i] = Integer.parseInt(model.getValueAt(i, 3).toString());
			bar[i] = model.getValueAt(i, 0).toString();
			deleteKucunMapper(bar[i],mou[i]);
		}
	}
	
	/**
	 * 将表中
	 * @param barcode
	 * @param count
	 */
	private void deleteKucunMapper(String barcode,int count){
		db = new DBUtils();
		ResultSet rs = db.query("select count from commodityinfo where barcode=?", barcode);
		int div = 0;
		try {
			if(rs.next()){
				div = Integer.parseInt(rs.getObject(1).toString())-count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(db.update("update commodityinfo set count=? where barcode=?", div,barcode)>=1){
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	/* various* 进行增加数量的变量 */
	@SuppressWarnings("rawtypes")
	private Map goods = new HashMap();
	@SuppressWarnings("rawtypes")
	private Map mount = new HashMap();
	@SuppressWarnings("rawtypes")
	private Map good2;
	@SuppressWarnings("rawtypes")
	private Map mount2;
	private int count = 1, rowCount = 0,sumCount=0;
	private double sum = 0;
	/* 数据库操作的变量 */
	private DBUtils db;
	/* 显示 会员框 */
	private JTextField txtShowPhone, txtShowName;
	/* 返回本窗口的变量 */
	private static Cashier frame;
	/* 内容面板 */
	private JPanel contentPane;
	private JTable table;
	private JTextField txtInputBarcode, txtInput;
	private JLabel lblMarketName, lblE_No;
	private DefaultTableModel model;
	private JButton btnCalAll;
	
	private JLabel lblShowGoodsCount;
	
	private String[] cell;
	
	private boolean bll;
	
	private Timer timer;

}
