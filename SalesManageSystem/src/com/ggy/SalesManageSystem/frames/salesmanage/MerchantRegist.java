package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.RegistDao;
import com.ggy.SalesManageSystem.entity.Merchant;

public class MerchantRegist extends JFrame implements RegistDao{

	public MerchantRegist() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 300, 624, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPart = new JLabel("销售管理系统");
		lblPart.setFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", lblPart
				.getFont().getStyle() | Font.BOLD | Font.ITALIC, 17));
		lblPart.setBounds(5, 5, 130, 30);
		contentPane.add(lblPart);

		JLabel lblTitle = new JLabel("商户注册");
		lblTitle.setForeground(new Color(30, 144, 255));
		lblTitle.setFont(lblTitle.getFont().deriveFont(
				lblTitle.getFont().getStyle() | Font.BOLD,
				lblTitle.getFont().getSize() + 6f));
		lblTitle.setBounds(280, 8, 100, 27);
		contentPane.add(lblTitle);

		JLabel lblAlert = new JLabel(
				"用户名不超过24个字，由数字、字母、下划线组成");
		lblAlert.setFont(new Font("宋体", Font.PLAIN, 11));
		lblAlert.setBounds(164, 89, 287, 15);
		contentPane.add(lblAlert);

		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(189, 121, 66, 15);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(275, 114, 160, 30);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		JLabel llbPwdCheck = new JLabel("确认密码：");
		llbPwdCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		llbPwdCheck.setBounds(189, 169, 66, 15);
		contentPane.add(llbPwdCheck);

		passwordChecked = new JPasswordField();
		passwordChecked.setBounds(275, 162, 160, 30);
		contentPane.add(passwordChecked);
		passwordChecked.setColumns(10);

		JLabel lblEmail = new JLabel("邮箱：");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(189, 375, 66, 15);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(275, 368, 160, 30);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPhone = new JLabel("联系方式：");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(189, 323, 66, 15);
		contentPane.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(275, 316, 160, 30);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblAddress = new JLabel("地址：");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(189, 270, 66, 15);
		contentPane.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(275, 263, 160, 30);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		JLabel lblMarketName = new JLabel("店名：");
		lblMarketName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarketName.setBounds(189, 220, 66, 15);
		contentPane.add(lblMarketName);
		
		txtMarkeName = new JTextField();
		txtMarkeName.setColumns(10);
		txtMarkeName.setBounds(275, 213, 160, 30);
		contentPane.add(txtMarkeName);

		JButton btnRegist = new JButton("注册");
		btnRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
//				LoginScreen.r().setVisible(true);
				checkRegist();
			}
		});
		btnRegist.setBounds(295, 420, 100, 35);
		contentPane.add(btnRegist);
		
		JLabel lblE_no = new JLabel("用户名：");
		lblE_no.setHorizontalAlignment(SwingConstants.RIGHT);
		lblE_no.setBounds(189, 50, 66, 15);
		contentPane.add(lblE_no);
		
				txtE_no = new JTextField();
				txtE_no.setBounds(275, 43, 160, 30);
				contentPane.add(txtE_no);
				txtE_no.setColumns(10);
				
				JButton btnNewButton = new JButton("返回登录");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						LoginScreen.r().setVisible(true);
					}
				});
				btnNewButton.setBounds(452, 420, 100, 35);
				contentPane.add(btnNewButton);
		
		setResizable(false);
	}

	public static MerchantRegist r() {
		if (frame != null)
			return frame;
		else
			return new MerchantRegist();
	}
	
	private DBUtils db;
	private JTextField txtMarkeName;
	@SuppressWarnings("deprecation")
	private void checkRegist(){
		Merchant merchant = new Merchant();
		String e_no = txtE_no.getText();
		merchant.setE_no(e_no);
		String password = new String(passwordField.getPassword());
		merchant.setPassword(password);
		String name = txtMarkeName.getText();
		merchant.setName(name);
		String address = txtAddress.getText();
		merchant.setAddress(address);
		String phone = txtPhone.getText();
		merchant.setPhone(phone);
		String email = txtEmail.getText();		
		merchant.setEmail(email);		
		db = new DBUtils();		
		if(regist(merchant)){
			ResultSet rs = db.query("select * from merchant where name=?", e_no);
			try {
				boolean bl = rs.next();
				if(bl){
					JOptionPane.showMessageDialog(null, "商户已存在，请重新注册！");
				}else{
					db.update("insert into merchant(e_no,name,pwd,email,phone,address) values(?,?,?,?,?,?)", e_no,name,password,email,phone,address);
					ResultSet rs1 = db.query("select e_no from merchant where e_no=? and pwd=?", e_no,password);
					rs1.next();
					Object[] obj = {rs1.getObject("e_no"),name,password,address,phone,email};
					if(db.update("insert into merchant(e_no,name,pwd,address,phone,email) values(?,?,?,?,?,?)", obj)>=1)
					{	
						JOptionPane.showMessageDialog(null, "注册成功");
						txtE_no.setText("");
						passwordField.setText("");
						passwordChecked.setText("");
						txtMarkeName.setText("");
						txtEmail.setText("");
						txtPhone.setText("");
						txtAddress.setText("");
						hide();
						LoginScreen.r().setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "注册失败");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "注册失败");
		}
	}	
	@Override
	public boolean regist(Merchant merchant) {
		
		return registMapper(merchant);
	}
	
	private boolean registMapper(Merchant merchant){	
		return checkE_No(merchant.getE_no())&&checkPassword(merchant.getPassword())
				&&checkPhone(merchant.getPhone())&&checkEmail(merchant.getEmail());
	}
	private boolean checkE_No(String e_no){
		if(!"".equals(e_no))
			return true;
		else
			return false;
	}
	private boolean checkPassword(String password){
		String pwd = new String(passwordChecked.getPassword());
		if(!"".equals(pwd)){
			if(pwd.equals(password))
				return true;
			else{
				JOptionPane.showMessageDialog(null, "2次输入的密码不一致！");
				return false;
			}
		}else{
			if(pwd.equals(password))
				return false;
			else{
				JOptionPane.showMessageDialog(null, "2次输入的密码不一致！");
				return false;
			}
		}
	}
	
	private boolean checkEmail(String email){
		if(!"".equals(email))
			return true;
		else
			return false;
	}	
	
	private boolean checkPhone(String phone){		
		if(!"".equals(phone))			
			return true;
		else		
			return false;
	}
	
	
	
	private static final long serialVersionUID = 1L;

	private static MerchantRegist frame;

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordChecked;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtE_no;
}
