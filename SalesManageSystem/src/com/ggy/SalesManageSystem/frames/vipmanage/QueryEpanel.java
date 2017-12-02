package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ggy.SalesManageSystem.dao.StaffManageDao;
import com.ggy.SalesManageSystem.dao.impl.StaffManageDaoImpl;
import com.ggy.SalesManageSystem.entity.Staff;

/**
 * @author LTW
 *
 */
public class QueryEpanel extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public QueryEpanel() {
        setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		//初始化存储表格数据的对象
		//model = new DefaultTableModel(new Object[][]{},new String[]{"工号","姓名","密码","性别","生日","地址","手机号","身份证","员工类型","超市","操作"});
		model = new DefaultTableModel(new String[]{"工号","姓名","密码","性别","生日","地址","手机号","身份证","员工类型","超市","操作"},0){
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row,int col){
				if(col==0){
					return false;
				}else{
					return true;
				}
			}
		
		};
		
		//将数据绑定到table对象中
		table.setModel(model);
		table.setRowHeight(30);
		
		scrollPane.setViewportView(table);
		
		smDao = new StaffManageDaoImpl();
		loadDate();

	}
	
	/**
	 * 读取数据库当中的数据放到tableModel当中
	 */
	public void loadDate(){
		//清除旧的数据
		model.getDataVector().clear();
		//查询所有的用户信息
		List<Staff> list = smDao.queryAll();
		//遍历每一条数据，添加到model当中
		for (Staff sm : list) {
			model.addRow(new Object[]{sm.getE_NO(),sm.getName(),sm.getPwd(),sm.getGender(),sm.getBirth(),sm.getAddress(),sm.getPhone(),sm.getID_card(),sm.getEm_type(),sm.getFromMarket()});
			
		}

	}
	
	public JTable jta(){
		return table;
	}
	
	public DefaultTableModel jdt(){
		return model;
	}

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;//用于存储表格数据
	private StaffManageDao smDao;
}
