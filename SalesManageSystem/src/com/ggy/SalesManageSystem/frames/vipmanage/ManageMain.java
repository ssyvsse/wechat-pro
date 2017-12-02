package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ggy.SalesManageSystem.commons.DBUtils;

/**
 * @author LTW
 *
 */
public class ManageMain extends JFrame implements ActionListener{	

	public static ManageMain r(){
		if(frame!=null)
			return frame;
		else
			return new  ManageMain();
	}

	public ManageMain() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 874, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEset = new JLabel("设置");
		lblEset.setFont(new Font("幼圆", Font.BOLD, 20));
		lblEset.setHorizontalAlignment(SwingConstants.CENTER);
		lblEset.setBounds(360, 5, 109, 40);
		panel.add(lblEset);
		
		panel_1 = new JPanel();
		panel_1.setBounds(5, 70, 141, 420);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEadd = new JButton("新增员工");
		btnEadd.setBounds(25, 20, 90, 40);
		//为按钮添加命令
		btnEadd.setActionCommand("adddata");
		panel_1.add(btnEadd);
		
		JButton btnEload = new JButton("查询编辑");
		btnEload.setBounds(25, 72, 90, 40);
		//为按钮添加命令
		btnEload.setActionCommand("loaddata");
		panel_1.add(btnEload);
		
		btnEqx = new JButton("权限设置");
		btnEqx.setBounds(25, 122, 90, 40);
		//为按钮添加命令
		btnEqx.setActionCommand("addp");
		panel_1.add(btnEqx);
		
		btnAbout = new JButton("关于");
		btnAbout.setBounds(25, 172, 90, 40);
		//为按钮添加命令
		btnAbout.setActionCommand("addabout");
		panel_1.add(btnAbout);
		
		butReturn = new JButton("返回");
		butReturn.setBounds(25, 225, 90, 40);
		butReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Module().setVisible(true);
			}
		});
		butReturn.setActionCommand("addabout");
		panel_1.add(butReturn);
		
		panelEbutton = new JPanel();
		
		img = new JPanel(){	
			private static final long serialVersionUID = 1L;
			
			Image image=null;
		    public void paint(Graphics g){
		        try {
		            image=ImageIO.read(new File("E:/Users/SalesManageSystem/QQ图片20170628171207.jpg"));
		            g.drawImage(image, 0, 0, 315, 420, null);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		};
		img.setLocation(100, 10);
		img.setSize(400, 420);
		panelEbutton.add(img);
		panelEbutton.setBounds(156, 70, 723, 420);
		contentPane.add(panelEbutton,BorderLayout.CENTER);
		panelEbutton.setLayout(new BorderLayout(0, 0));
		
		btnDel = new JButton("删除");
		btnDel.setActionCommand("edel");
		btnDel.addActionListener(this);
		
		btnUpdate = new JButton("修改");
		btnUpdate.setActionCommand("update");
		btnUpdate.addActionListener(this);
		
		//注册监听
				btnEload.addActionListener(this);
				btnEadd.addActionListener(this);
				btnAbout.addActionListener(this);
				btnEqx.addActionListener(this);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//获取命令
				String command = e.getActionCommand();
				if(command.equals("loaddata")){		
					 panelEbutton.remove(img);
					 panel_1.remove(btnEqx);
					 panel_1.remove(btnAbout);
					 panel_1.add(btnUpdate);
					 panel_1.add(btnDel);
					 panel_1.updateUI();
					 panelEbutton.removeAll();//移除所有组件
					 btnEquery = new QueryEpanel();
					 panelEbutton.add(btnEquery,BorderLayout.CENTER);
					 panel_1.add(btnEqx);
					 panel_1.add(btnAbout);
					 panelEbutton.updateUI();//刷新面板
				}else if(command.equals("adddata")){
					
					panel_1.add(btnEqx);
					panel_1.add(btnAbout);
					panel_1.remove(btnUpdate);
					panel_1.remove(btnDel);
					panel_1.updateUI();
					panelEbutton.removeAll();
					sap = new StaffAddPanel();
					panelEbutton.add(sap,BorderLayout.CENTER);
					panelEbutton.updateUI();
				}else if(command.equals("addabout")){
					panelEbutton.removeAll();//移除所有组件
					panel_1.remove(btnUpdate);
					panel_1.remove(btnDel);
					panel_1.updateUI();
					ap = new AboutPanel();
					panelEbutton.add(ap,BorderLayout.CENTER);
					panelEbutton.updateUI();//刷新面板
				}else if(command.equals("addp")){
					panelEbutton.removeAll();//移除所有组件
					panel_1.remove(btnUpdate);
					panel_1.remove(btnDel);
					panel_1.updateUI();
					pp = new PermissionsPanel();
					panelEbutton.add(pp,BorderLayout.CENTER);
					panelEbutton.updateUI();//刷新面板
				}else if(command.equals("edel")){
					int row = btnEquery.jta().getSelectedRow();
					if(row < 0){
						JOptionPane.showMessageDialog(null, "删除失败，请点击要删除的行");
					}else
					
					edelete();
					btnEquery.loadDate();
				}else if(command.equals("update")){
					int row = btnEquery.jta().getSelectedRow();
					if(row < 0){
						JOptionPane.showMessageDialog(null, "修改失败，请编辑要修改的行");
					}else
					update();
					btnEquery.loadDate();
				}
	}
	private void edelete() {
		int row = btnEquery.jta().getSelectedRow();
		model = btnEquery.jdt();
		String value0 = model.getValueAt(row, 0).toString();
		String value1 = model.getValueAt(row, 1).toString();
		String value2 = model.getValueAt(row, 2).toString();
		String value3 = model.getValueAt(row, 3).toString();
		String value4 = model.getValueAt(row, 4).toString();
		String value5 = model.getValueAt(row, 5).toString();
		String value6 = model.getValueAt(row, 6).toString();
		String value7 = model.getValueAt(row, 7).toString();
		String value8 = model.getValueAt(row, 8).toString();
		String value9 = model.getValueAt(row, 9).toString();
		DBUtils db = new DBUtils();
		if(db.update("delete from Staff where E_NO=? and Name=? and Pwd=? and Gender=? and Birth=? and Address=? and Phone=? and ID_card=? and Em_type=? and FromMarket=?", value0,value1,value2,value3,value4,value5,value6,value7,value8,value9)>=1)
			JOptionPane.showMessageDialog(null, "删除成功");
		else
			JOptionPane.showMessageDialog(null, "删除失败");
	}
	
	private void update(){
		int row = btnEquery.jta().getSelectedRow();
		model = btnEquery.jdt();
		String value0 = model.getValueAt(row, 0).toString();
		String value1 = model.getValueAt(row, 1).toString();
		String value2 = model.getValueAt(row, 2).toString();
		String value3 = model.getValueAt(row, 3).toString();
		String value4 = model.getValueAt(row, 4).toString();
		String value5 = model.getValueAt(row, 5).toString();
		String value6 = model.getValueAt(row, 6).toString();
		String value7 = model.getValueAt(row, 7).toString();
		String value8 = model.getValueAt(row, 8).toString();
		String value9 = model.getValueAt(row, 9).toString();
		DBUtils db = new DBUtils();
		if(db.update("update Staff set Name=?,Pwd=?Gender=?,Birth=?,Address=?,Phone=?,ID_card=?,Em_type=?,FromMarket=? where E_NO=?", value1,value2,value3,value4,value5,value6,value7,value8,value9,value0)>=1)
			JOptionPane.showMessageDialog(null, "修改成功");
		else
			JOptionPane.showMessageDialog(null, "修改失败");	
	}
	
	
	private static final long serialVersionUID = 1L;
	private static ManageMain frame;
	private JPanel contentPane,panelEbutton,panel_1,img;
	
	private QueryEpanel btnEquery;
	private StaffAddPanel sap;
	private AboutPanel ap;
	private PermissionsPanel pp;
	
	private DefaultTableModel model;
	private JButton btnEqx,btnAbout,btnUpdate,butReturn,btnDel;
}
