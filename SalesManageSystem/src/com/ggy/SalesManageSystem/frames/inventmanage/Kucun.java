package com.ggy.SalesManageSystem.frames.inventmanage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.commons.TableToExcel;
import com.ggy.SalesManageSystem.frames.vipmanage.Module;

public class Kucun extends JFrame {
	private static final long serialVersionUID = 1L;
	public static Kucun r(){
		if(frame!=null)
			return frame;
		else
			return new Kucun();
	}
	
	public Kucun() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1093, 819);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		lblTitle = new JLabel("库存管理");
		lblTitle.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(20, 5, 944, 36);
		contentPane.add(lblTitle);
		
		btnWine = new JButton("酒类");
		btnWine.setBounds(20, 100, 93, 42);
		contentPane.add(btnWine);
		
		btnCigar = new JButton("烟类");
		
		btnCigar.setBounds(20, 161, 93, 42);
		contentPane.add(btnCigar);
		
		btnDairly = new JButton("日用品");
		btnDairly.setBounds(20, 216, 93, 42);
		contentPane.add(btnDairly);
		
		btnShowAll = new JButton("显示全部");
		btnShowAll.setBounds(20, 271, 93, 42);
		contentPane.add(btnShowAll);
		
		panelShowMsg = new JPanel();
		panelShowMsg.setBounds(126, 100, 922, 659);
		contentPane.add(panelShowMsg);
		panelShowMsg.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("修改");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change((JPanel) tabbedPane.getSelectedComponent());
			}
		});
		btnNewButton_4.setBounds(822, 122, 86, 42);
		panelShowMsg.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("返回");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Module.r().setVisible(true);
			}
		});
		
		btnNewButton_5.setBounds(822, 200, 86, 42);
		panelShowMsg.add(btnNewButton_5);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(0, 0, 814, 659);
		panelShowMsg.add(tabbedPane);
		
		btnLoss = new JButton("亏损");
		btnLoss.setBounds(20, 323, 93, 42);
		contentPane.add(btnLoss);
		
		btnProfit = new JButton("盈余");
		btnProfit.setBounds(20, 380, 93, 42);
		contentPane.add(btnProfit);
		
		textInput = new JTextField();		
		textInput.setText("请输入进货单：");
		textInput.setBounds(126, 49, 176, 24);
		contentPane.add(textInput);
		textInput.setColumns(10);
		
		btnInvent = new JButton("入库");
		
		
		btnInvent.setBounds(327, 49, 113, 27);
		contentPane.add(btnInvent);
		
		panel_drink = new Panel_drink();
		tabbedPane.addTab("酒类", null, panel_drink, null);
		tabbedPane.setEnabledAt(0,false);
		panel_cigar = new Panel_cigar();
		tabbedPane.addTab("烟类", null, panel_cigar, null);
		tabbedPane.setEnabledAt(1,false);
		panel_daily = new Panel_daily();
		tabbedPane.addTab("日用品", null, panel_daily, null);
		tabbedPane.setEnabledAt(2,false);
		panel_all = new Panel_all();
		tabbedPane.addTab("全部商品", null, panel_all, null);
		tabbedPane.setEnabledAt(3,false);
		panel_lose = new Panel_lose();
		tabbedPane.addTab("亏损", null, panel_lose, null);
		tabbedPane.setEnabledAt(4,false);
		panel_profit = new Panel_profit();
		tabbedPane.addTab("盈余", null, panel_profit, null);
		tabbedPane.setEnabledAt(5,false);
		
		btnExport = new JButton("导出");
		btnExport.setBounds(822, 42, 86, 42);
		panelShowMsg.add(btnExport);
		
		timer = new Timer();
		timer.setSize(307, 38);
		timer.setLocation(468, 45);
		contentPane.add(timer);
		new Thread(timer).start();
//		model = new DefaultTableModel(new String[]{"条形码","商品名称","计量单位","单价","商品类型","库存数量","最后更新时间"},0){};
		myEvent();
	}
	
	
	/**
	 * 监听事件
	 */
	private void myEvent(){
		btnWine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(0,true);
				panel_drink.requestFocus();
				loadData(panel_drink,"酒");
				panel_drink.jtable().updateUI();
			}
		});
		btnCigar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(1,true);
				loadData(panel_cigar,"烟");
				panel_cigar.jtable().updateUI();				
			}
		});
		btnDairly.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(2,true);
				loadData(panel_daily,"日用品");
				panel_daily.jtable().updateUI();
				
			}
		});
		btnShowAll.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(3,true);				
				loadData(panel_all,"");
				panel_all.jtable().updateUI();
			}
		});
		textInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textInput.setText("");
			}
		});
		btnLoss.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(4,true);
				queryLose();
				panel_lose.jtable().updateUI();				
			}
		});
		btnInvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//执行完 操作 
				textInput.setText("");
			}
		});
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "是否导出？")==0){
					JFileChooser jfc = new JFileChooser(); 
					jfc.setDialogTitle("保存");
					jfc.setDialogType(JFileChooser.SAVE_DIALOG);
					int option = jfc.showSaveDialog(null);  
				    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
				    	export(jfc.getSelectedFile().toString());
				    }  					
				}
				
			}
		});	
		btnProfit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(5,true);
				queryProfit();
				panel_profit.jtable().updateUI();	
			}
		});
	}
	
	/**
	 * 读取数据
	 * @param panel
	 * @param type
	 */
	private void loadData(JPanel panel,String type){
		db = new DBUtils();
		
		model = new DefaultTableModel(new String[]{"条形码","商品名称","计量单位","单价","商品类型","库存数量","最后更新时间"},0){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int col){
				if(col==0 || col==1 || col==2 || col==4 )
					return false;
				else
					return true;
			}
		};
		Panel_drink pd = null;
		if(panel instanceof Panel_drink){
			pd = (Panel_drink)panel;
			pd.jtable().setModel(model);
		}
		Panel_cigar pc = null;
		if(panel instanceof Panel_cigar){
			pc = (Panel_cigar)panel;
			pc.jtable().setModel(model);
		}
		Panel_daily pdl = null;
		if(panel instanceof Panel_daily){
			pdl = (Panel_daily)panel;
			pdl.jtable().setModel(model);
		}
		Panel_all pa =null;
		if(panel instanceof Panel_all){
			pa = (Panel_all)panel;
			pa.jtable().setModel(model);
		}
		String sql;
		if("".equals(type))
			sql = "select * from commodityinfo";
		else
			sql = "select * from commodityinfo where type like '%"+type+"%' ";
		ResultSet rs = db.query(sql);
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			while(rs.next()){
				Object[] obj = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					obj[i] = rs.getObject(i+1);
				}
				model.addRow(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
			
				try {
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	private void change(JPanel panel){
		int row = 0;
		Panel_drink pd = null;
		if(panel instanceof Panel_drink){
			pd = (Panel_drink)panel;			
			row = pd.jtable().getSelectedRow();
		}
		Panel_cigar pc = null;
		if(panel instanceof Panel_cigar){
			pc = (Panel_cigar)panel;
			row = pc.jtable().getSelectedRow();
		}
		Panel_daily pdl = null;
		if(panel instanceof Panel_daily){
			pdl = (Panel_daily)panel;
			row = pdl.jtable().getSelectedRow();
		}
		Panel_all pa =null;
		if(panel instanceof Panel_all){
			pa = (Panel_all)panel;
			row = pa.jtable().getSelectedRow();
		}
		String barcode = model.getValueAt(row, 0).toString();
		String goods_name = model.getValueAt(row, 1).toString();		
		
		Object obj1 = model.getValueAt(row, 3);
		String value1 = "";	
		if(obj1==null)
			value1 = "1";
		else
			value1= obj1.toString();
		
		Object obj2 = model.getValueAt(row, 5);
		String value2 = "";	
		if(obj2==null)
			value2 = "1";
		else
			value2= obj2.toString();
		
		String value3 = new java.sql.Date(new Date().getTime()).toLocaleString();
	
		
		db = new DBUtils();
		if(db.update("update commodityinfo set unit_price=?,count=?,lastmodified=? where barcode=? and goods_name=?", value1,value2,value3,barcode,goods_name)>=1)
			JOptionPane.showMessageDialog(null, "修改成功");
		else
			JOptionPane.showMessageDialog(null, "修改失败");
		
	}
	private XSSFWorkbook x = null;
	/**
	 * 根据路径创建新的excel
	 * 将表中数据导出到Excel
	 * @param xlsxName
	 * @return
	 */
	private boolean export(String xlsxName){
		int colCount = model.getColumnCount();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < colCount; i++) {
			list.add(model.getColumnName(i).toString());
		}
		@SuppressWarnings("rawtypes")
		Vector v = model.getDataVector();
		try {
			x = TableToExcel.xlsDto2Excel(list, v);
			File file = new File(
					xlsxName+".xlsx");
			if (!file.exists())
				file.createNewFile();
			OutputStream out = new FileOutputStream(file);
			x.write(out);
			JOptionPane.showMessageDialog(null, "导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查询depletion_list表
	 */
	private void queryLose(){
		db = new DBUtils();
		ResultSet rs = db.query("select * from depletion_list");
		try {
			ResultSetMetaData rsm = rs.getMetaData();
			int columnCount = rsm.getColumnCount();
			String[] cell = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				cell[i] = rsm.getColumnName(i+1);
			}				
			model = new DefaultTableModel(cell,0);
			panel_lose.jtable().setModel(model);
			Object[] obj = new Object[columnCount];
			while(rs.next()){
				for (int i = 0; i < obj.length; i++) {
					obj[i] = rs.getObject(i);
				}
				model.addRow(obj);
			}
			panel_lose.jtable().updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询利润表
	 */
	private void queryProfit(){
		db = new DBUtils();
		ResultSet rs = db.query("select * from profit");
		try {
			ResultSetMetaData rsm = rs.getMetaData();
			int columnCount = rsm.getColumnCount();
			String[] cell = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				cell[i] = rsm.getColumnName(i+1);
			}				
			model = new DefaultTableModel(cell,0);
			panel_profit.jtable().setModel(model);
			Object[] obj = new Object[columnCount];
			while(rs.next()){
				for (int i = 0; i < obj.length; i++) {
					obj[i] = rs.getObject(i);
				}
				model.addRow(obj);
			}
			panel_profit.jtable().updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	/*本窗口*/
	private static Kucun frame;
	/*标题*/
	private JLabel lblTitle;
	/*内容面板*/
	private JPanel contentPane,panelShowMsg;
	/*请输入进货单*/
	private JTextField textInput;
	/*various	各种按钮*/
	private JButton btnLoss,btnProfit,btnExport,btnInvent,btnWine,btnCigar,btnDairly,btnShowAll;
	/*标签页*/
	private JTabbedPane tabbedPane;
	/*表*/	
	private DefaultTableModel model;	
	/*数据库*/
	private DBUtils db;
	
	private Panel_drink panel_drink;
	private Panel_cigar panel_cigar;
	private Panel_daily panel_daily;
	private Panel_all panel_all;
	private Panel_lose panel_lose;
	private Panel_profit panel_profit;
	
	private Timer timer;
}
