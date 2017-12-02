package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.frames.salesmanage.Cashier;

/**
 * @author LTW
 *
 */
public class VipMain extends JFrame implements ActionListener{
	
	public static VipMain r(){
		if(frame!=null)
			return frame;
		else
			return new VipMain();
	}

	public VipMain() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 874, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("会员管理模块");
		label.setBounds(344, 5, 178, 54);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		panel.add(label);
		
		panel_1 = new JPanel();
		panel_1.setBounds(5, 70, 130, 480);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnVMadd = new JButton("新增会员");
		btnVMadd.setFont(new Font("宋体", Font.BOLD, 12));
		btnVMadd.setBounds(18, 22, 92, 47);
		//为按钮添加命令
		btnVMadd.setActionCommand("adddata");
		panel_1.add(btnVMadd);
		
		btnUpdate = new JButton("修改");
		btnUpdate.setActionCommand("update");
		btnUpdate.addActionListener(this);
		
		JButton btnVMload = new JButton("查询编辑");
		btnVMload.setFont(new Font("宋体", Font.BOLD, 12));
		btnVMload.setBounds(18, 90, 92, 47);
		//为按钮添加命令
		btnVMload.setActionCommand("loaddata");
		panel_1.add(btnVMload);
		
		JButton btnReturn = new JButton("返回收银");
		btnReturn.setFont(new Font("宋体", Font.BOLD, 12));
		btnReturn.setBounds(18, 158, 92, 47);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cashier.r().setVisible(true);
			}
		});
		btnReturn.setActionCommand("loaddata");
		panel_1.add(btnReturn);
		
		/*JButton btnNewButton = new JButton("\u5220\u9664");
		panel_1.add(btnNewButton);*/
		
		btnNewButton = new JButton("删除");
		btnNewButton.setActionCommand("delete");
		btnNewButton.addActionListener(this);
		
		panelButton = new JPanel();
		panelButton.setBounds(144, 91, 725, 457);
		contentPane.add(panelButton,BorderLayout.CENTER);
		panelButton.setLayout(new BorderLayout(0, 0));
		
		//注册监听
		btnVMload.addActionListener(this);
		btnVMadd.addActionListener(this);
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		//获取命令
		String command = e.getActionCommand();
		if(command.equals("loaddata")){
			 panel_1.add(btnUpdate);
			 panel_1.add(btnNewButton);
			 panel_1.updateUI();
			 panelButton.removeAll();//移除所有组件
			 query = new QueryPanel();
			 panelButton.add(query,BorderLayout.CENTER);
			 panelButton.updateUI();//刷新面板
		}else if(command.equals("adddata")){
			panel_1.remove(btnUpdate);
			panel_1.remove(btnNewButton);
			panel_1.updateUI();
			panelButton.removeAll();
			vadd = new VipAddPanel();
			panelButton.add(vadd,BorderLayout.CENTER);
			panelButton.updateUI();
		}else if(command.equals("delete")){
			int row = query.jta().getSelectedRow();
			if(row < 0){
				JOptionPane.showMessageDialog(null, "删除失败，请点击要删除的行");
			}else
			
			delete();
			query.loadDate();
			
		}else if(command.equals("update")){
			int row = query.jta().getSelectedRow();
			if(row < 0){
				JOptionPane.showMessageDialog(null, "修改失败，请编辑要修改的行");
			}else
			update();
			query.loadDate();
		}
		//alert
		//JOptionPane.showMessageDialog(null, command);
		
	}
	
	
	
	private void delete(){
		int row = query.jta().getSelectedRow();
		model = query.jdt();
		String value1 = model.getValueAt(row, 1).toString();
		String value2 = model.getValueAt(row, 2).toString();
		String value3 = model.getValueAt(row, 3).toString();
		DBUtils db = new DBUtils();
		if(db.update("delete from vip_manage where name=? and tel=? and gender=?", value1,value2,value3)>=1){
			JOptionPane.showMessageDialog(null, "删除成功");
		}else{
			JOptionPane.showMessageDialog(null, "删除失败");
		}
	}
	
	private void update(){
		int row = query.jta().getSelectedRow();
		model = query.jdt();
		String value0 = model.getValueAt(row, 0).toString();
		String value1 = model.getValueAt(row, 1).toString();
		String value2 = model.getValueAt(row, 2).toString();
		String value3 = model.getValueAt(row, 3).toString();
		DBUtils db = new DBUtils();
		if(db.update("update vip_manage set Name=?,Tel=?,Gender=? where VIP_ID=?", value1,value2,value3,value0)>=1){
			JOptionPane.showMessageDialog(null, "修改成功");
		}else{
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private static VipMain frame;
	private JPanel contentPane;
	private JPanel panelButton;
	private QueryPanel query;
	private VipAddPanel vadd;
	private JButton btnNewButton;
	private JPanel panel_1;
	private DefaultTableModel model;
	private JButton btnUpdate;
}
