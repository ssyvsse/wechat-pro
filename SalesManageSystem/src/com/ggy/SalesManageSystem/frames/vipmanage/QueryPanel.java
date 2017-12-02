package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ggy.SalesManageSystem.dao.VipManageDao;
import com.ggy.SalesManageSystem.dao.impl.VipManageDaoImpl;
import com.ggy.SalesManageSystem.entity.vip_manage;

/**
 * @author LTW
 *
 */
public class QueryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;//用于存储表格数据
	private VipManageDao vmDao;
	

	/**
	 * Create the panel.
	 */
	public QueryPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		//初始化存储表格数据的对象
		model = new DefaultTableModel(new String[]{"会员号","姓名","手机号","性别","操作"},0){
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
		
		vmDao = new VipManageDaoImpl();
		loadDate();

	}
	
	/**
	 * 读取数据库当中的数据放到tableModel当中
	 */
	public void loadDate(){
		//清除旧的数据
		model.getDataVector().clear();
		//查询所有的用户信息
		List<vip_manage> list = vmDao.queryAll();
		//遍历每一条数据，添加到model当中
		for (vip_manage vm : list) {
			model.addRow(new Object[]{vm.getVIP_ID(),vm.getName(),vm.getTel(),vm.getGender()});
			
		}
	}
	
	public JTable jta(){
		return table;
	}
	
	public DefaultTableModel jdt(){
		return model;
	}

}
