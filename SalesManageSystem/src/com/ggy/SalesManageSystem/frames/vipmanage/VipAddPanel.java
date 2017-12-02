package com.ggy.SalesManageSystem.frames.vipmanage;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import com.ggy.SalesManageSystem.dao.VipManageDao;
import com.ggy.SalesManageSystem.dao.impl.VipManageDaoImpl;
import com.ggy.SalesManageSystem.entity.vip_manage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * @author LTW
 *
 */
public class VipAddPanel extends JPanel {
	
	/**
	 * 会员添加面板
	 * Create the panel.
	 */
	public VipAddPanel() {
		setLayout(null);
		
		JLabel lblVid = new JLabel("会员号：");
		lblVid.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVid.setBounds(149, 37, 80, 15);
		add(lblVid);
		
		txtVid = new JTextField();
		txtVid.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtVid.setBounds(239, 34, 134, 30);
		add(txtVid);
		txtVid.setColumns(10);
		
		txtVname = new JTextField();
		txtVname.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtVname.setColumns(10);
		txtVname.setBounds(239, 99, 134, 25);
		add(txtVname);
		
		JLabel lblVname = new JLabel("姓   名：");
		lblVname.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVname.setBounds(149, 102, 80, 15);
		add(lblVname);
		
		txtVtel = new JTextField();
		txtVtel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtVtel.setColumns(10);
		txtVtel.setBounds(239, 162, 134, 25);
		add(txtVtel);
		
		JLabel lblVtel = new JLabel("手机号：");
		lblVtel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVtel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVtel.setBounds(149, 165, 80, 15);
		add(lblVtel);
		
		JLabel lblVgender = new JLabel("性别：");
		lblVgender.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVgender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVgender.setBounds(150, 227, 80, 15);
		add(lblVgender);
		
		rbMan = new JRadioButton("男");
		rbMan.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		rbMan.setActionCommand("男");
		rbMan.setBounds(248, 223, 50, 23);
		add(rbMan);
		
		rbFemale = new JRadioButton("女");
		rbFemale.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		rbFemale.setActionCommand("女");
		rbFemale.setBounds(308, 222, 50, 23);
		add(rbFemale);
		
		//实现男女二选一
		btgr = new ButtonGroup();
		btgr.add(rbMan);
		btgr.add(rbFemale);
		
		vmDao = new VipManageDaoImpl();
		
		JButton btnVadd = new JButton("添加");
		btnVadd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnVadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int VIP_ID = Integer.parseInt(txtVid.getText());
				String Name = txtVname.getText();
				String Tel = txtVtel.getText();
				//System.out.println(btgr.getSelection().getActionCommand());
				//char VIP_Gender = btgr.getSelection().getActionCommand().charAt(0);
				Gender = btgr.getSelection().getActionCommand(); 
				
				vip_manage vm = new vip_manage();
				vm.setVIP_ID(VIP_ID);
				vm.setName(Name);
				vm.setTel(Tel);
				vm.setGender(Gender);
				
				if(vmDao.add(vm)){
					//System.out.println(1);
					JOptionPane.showMessageDialog(null, "添加成功");
					txtVid.setText("");
					txtVname.setText("");
					txtVtel.setText("");
					//btgr.setSelected(null, true);
				}else{
					JOptionPane.showMessageDialog(null, "添加失败");
				}
			}
		});
		btnVadd.setBounds(247, 279, 104, 34);
		add(btnVadd);

	}
	
	private static final long serialVersionUID = 1L;
	private JTextField txtVid;
	private JTextField txtVname;
	private JTextField txtVtel;
	private VipManageDao vmDao;
	private JRadioButton rbMan;
	private JRadioButton rbFemale;
	private ButtonGroup btgr;
	private  String Gender;
}
