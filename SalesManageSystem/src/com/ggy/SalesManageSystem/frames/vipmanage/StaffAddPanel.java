package com.ggy.SalesManageSystem.frames.vipmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.StaffManageDao;
import com.ggy.SalesManageSystem.dao.impl.StaffManageDaoImpl;
import com.ggy.SalesManageSystem.entity.Staff;
import com.ggy.SalesManageSystem.frames.salesmanage.LoginScreen;
import com.ggy.SalesManageSystem.frames.salesmanage.StaffRegist;

/**
 * @author LTW
 *
 */
public class StaffAddPanel extends JPanel {
	
	
	/**
	 * 员工添加面板
	 */
	public StaffAddPanel() {
		setLayout(null);
		
		JLabel lblEname = new JLabel("姓名：");
		lblEname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEname.setBounds(125, 28, 66, 15);
		add(lblEname);
		
		txtEname = new JTextField();
		txtEname.setColumns(10);
		txtEname.setBounds(201, 25, 137, 21);
		add(txtEname);

		JLabel lblEpwd = new JLabel("密码：");
		lblEpwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEpwd.setBounds(125, 56, 66, 15);
		add(lblEpwd);

		txtEpwd = new JTextField();
		txtEpwd.setBounds(201, 53, 137, 21);
		add(txtEpwd);
		txtEpwd.setColumns(10);

		JLabel lblEgender = new JLabel("性别：");
		lblEgender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEgender.setBounds(125, 84, 66, 15);
	    add(lblEgender);
		
		rbEman = new JRadioButton("男");
		rbEman.setActionCommand("男");
		rbEman.setBounds(201, 80, 60, 23);
		add(rbEman);
		
		rbEfemale = new JRadioButton("女");
		rbEfemale.setActionCommand("女");
		rbEfemale.setBounds(263, 80, 60, 23);
		add(rbEfemale);
		
		//实现男女二选一
		btgr = new ButtonGroup();
		btgr.add(rbEman);
		btgr.add(rbEfemale);

		JLabel lblEbirth = new JLabel("生日：");
		lblEbirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEbirth.setBounds(125, 109, 66, 15);
		add(lblEbirth);

		txtEbirth = new JTextField();
		txtEbirth.setBounds(201, 106, 137, 21);
		add(txtEbirth);
		txtEbirth.setColumns(10);

		JLabel lblEaddr = new JLabel("地址：");
		lblEaddr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEaddr.setBounds(125, 137, 66, 15);
		add(lblEaddr);

		txtEaddr = new JTextField();
		txtEaddr.setBounds(201, 134, 137, 21);
		add(txtEaddr);
		txtEaddr.setColumns(10);

		JLabel lblEphone = new JLabel("联系方式：");
		lblEphone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEphone.setBounds(125, 165, 66, 15);
		add(lblEphone);

		txtEphone = new JTextField();
		txtEphone.setBounds(201, 162, 137, 21);
		add(txtEphone);
		txtEphone.setColumns(10);


		JLabel lblEidc = new JLabel("身份证：");
		lblEidc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEidc.setBounds(125, 193, 66, 15);
		add(lblEidc);

		txtEidc = new JTextField();
		txtEidc.setBounds(201, 190, 137, 21);
		add(txtEidc);
		txtEidc.setColumns(10);
		
		JLabel lblErole = new JLabel("员工类型：");
		lblErole.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErole.setBounds(125, 221, 66, 15);
		add(lblErole);
		
		txtErole = new JTextField();
		txtErole.setColumns(10);
		txtErole.setBounds(201, 218, 137, 21);
		add(txtErole);
		
		JLabel lblEmarket = new JLabel("超市：");
		lblEmarket.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmarket.setBounds(125, 248, 66, 15);
		add(lblEmarket);
		
		txtEmarket = new JTextField();
		txtEmarket.setColumns(10);
		txtEmarket.setBounds(201, 245, 137, 21);
		add(txtEmarket);
		
		smDao = new StaffManageDaoImpl();
		JButton btnEadd = new JButton("添加");
		btnEadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = txtEname.getText();
				String Pwd = txtEpwd.getText();	
				String Gender = btgr.getSelection().getActionCommand();			
				String Birth = txtEbirth.getText();
				String Address = txtEaddr.getText();
				String Phone = txtEphone.getText();
				String ID_card = txtEidc.getText();
				String Em_type = txtErole.getText();
				String FromMarket = txtEmarket.getText();
				
				Staff sm = new Staff();
				sm.setName(Name);
				sm.setPwd(Pwd);
				sm.setGender(Gender);
				sm.setBirth(Birth);
				sm.setAddress(Address);
				sm.setPhone(Phone);
				sm.setID_card(ID_card);
				sm.setEm_type(Em_type);
				sm.setFromMarket(FromMarket);
				
				
				if(smDao.add(sm)){
					//System.out.println(1);
					JOptionPane.showConfirmDialog(null, "添加成功");
					showId();
					txtEname.setText("");
					txtEpwd.setText("");
					txtEbirth.setText("");
					txtEaddr.setText("");
					txtEphone.setText("");
					txtEidc.setText("");
					txtErole.setText("");
					txtEmarket.setText("");
					//btgr.setSelected(null, true);
					//弹出窗口，显示分配的工号
					
				}else{
					JOptionPane.showMessageDialog(null, "添加失败");
				}
			}
		});
		btnEadd.setBounds(201, 286, 100, 35);
		add(btnEadd);
		
		JButton btnNewButton = new JButton("返回登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffRegist.r().setVisible(false);
				ManageMain.r().dispose();
				new LoginScreen().setVisible(true);;
			}
		});
		btnNewButton.setBounds(354, 306, 93, 23);
		add(btnNewButton);
		
	}
	private void showId(){
		String Name = txtEname.getText();
		String Pwd = txtEpwd.getText();	
		String Gender = btgr.getSelection().getActionCommand();			
		String Birth = txtEbirth.getText();
		String Address = txtEaddr.getText();
		String Phone = txtEphone.getText();
		String ID_card = txtEidc.getText();
		String Em_type = txtErole.getText();
		String FromMarket = txtEmarket.getText();
		db = new DBUtils();
		ResultSet rs = db.query("select e_no from staff where name=? and pwd=? and gender=? and birth=? and address=? and phone=? and id_card=? and  frommarket=?",Name,Pwd,Gender,Birth,Address,Phone,ID_card,Em_type,FromMarket );
		try {
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "你的工号是："+rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	private JTextField txtEpwd;
	private JTextField txtEbirth;
	private JTextField txtEphone;
	private JTextField txtEaddr;
	private JTextField txtEidc;
	private JTextField txtEname;
	private JTextField txtErole;
	private JTextField txtEmarket;
	private ButtonGroup btgr;
	private JRadioButton rbEman;
	private JRadioButton rbEfemale;
	private StaffManageDao smDao;
	private DBUtils db;
}
