package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.dao.LoginFrame;
import com.ggy.SalesManageSystem.entity.Merchant;
import com.ggy.SalesManageSystem.entity.User;

public class LoginScreen extends JFrame implements LoginFrame {
	public static LoginScreen r() {
		if (frame != null)
			return frame;
		else
			return new LoginScreen();
	}

	public LoginScreen() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 270, 610, 364);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("销售管理系统");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", label.getFont().getStyle() | Font.BOLD | Font.ITALIC, 24));
		label.setBounds(25, 25, 201, 75);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(120, 113, 67, 38);
		contentPane.add(label_1);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 17));
		textField.setBounds(201, 113, 201, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
			}			
			@Override
			public void keyReleased(KeyEvent e) {
			}			
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					if(check()){
						hide();
						Cashier.r().setVisible(true);
					}
			}
		});;

		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setBounds(120, 160, 67, 37);
		contentPane.add(label_2);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordField.setBounds(201, 164, 201, 38);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		passwordField.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
			}			
			@Override
			public void keyReleased(KeyEvent e) {
			}			
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					if(check()){
						hide();
						Cashier.r().setVisible(true);
					}
			}
		});;

		JButton button = new JButton("忘记密码？");
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "发送姓名+联系方式+5元红包，到17605050454会告诉你密码！");
			}
		});
		button.setForeground(Color.BLACK);
		button.setBounds(434, 163, 120, 37);
		contentPane.add(button);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(424, 49, -55, -28);
		contentPane.add(layeredPane);

		JPanel panel = new JPanel();
		panel.setBounds(220, 260, 160, 37);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ChooeseRegist.r().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(78, 0, 82, 37);
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("登陆");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(check()){
					hide();
					Cashier.r().setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(0, 0, 82, 37);
		panel.add(btnNewButton);

		button_1 = new JButton("联系客服");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "请拨打400-882-3823");
			}
		});
		button_1.setBounds(434, 260, 120, 37);
		contentPane.add(button_1);
		
		rdStaff = new JRadioButton("员工");
		rdStaff.setActionCommand("staff");
		rdStaff.setBounds(224, 215, 75, 23);
		contentPane.add(rdStaff);
		
		rdMerchant = new JRadioButton("商户");
		rdMerchant.setActionCommand("merchant");
		rdMerchant.setBounds(301, 215, 75, 23);
		contentPane.add(rdMerchant);
		
		btg = new ButtonGroup();
		btg.add(rdStaff);
		btg.add(rdMerchant);

		setResizable(false);
	}
	

	
	private boolean check() {
		
		User user;
		Merchant merchant;
		
		String e_no = textField.getText();;
		String password = new String(passwordField.getPassword());
		
		ButtonModel bm = btg.getSelection();
		String command = "";
		if(bm!=null){
			command = bm.getActionCommand();
		}		
		
		if("staff".equals(command)){
			if (!"".equals(e_no) && !"".equals(password)){
				user = new User();
				user.setE_no(e_no);
				user.setPassword(password);
				if(checkLogin(user)){
					JOptionPane.showMessageDialog(null, "员工登录成功");
					textField.setText("");
					passwordField.setText("");
					queryUsername(user);
					return true;
				}else{
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					return false;
				}
			}else
				return false;
		}else if("merchant".equals(command)){
			if (!"".equals(e_no) && !"".equals(password)){
				merchant = new Merchant();
				merchant.setE_no(e_no);
				merchant.setPassword(password);
				if(checkLogin(merchant)){
					JOptionPane.showMessageDialog(null, "商户登录成功");
					textField.setText("");
					passwordField.setText("");
					queryMerchant(merchant);
					return true;
				}else{
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					return false;
				}
			}
			return false;
		}else{
			JOptionPane.showMessageDialog(null, "请选择用户类型！");
		}
		return false;
	}

	@Override
	public boolean checkLogin(Object obj) {	
		if(obj instanceof User)//员工登录就查user表
			return checkLoginMapper((User)obj);
		else if(obj instanceof Merchant)//商户登录就查merchant表
			return checkLoginMapper((Merchant)obj);
		else
			return false;
	}
	
	
	/**
	 * 检查员工是否登录成功
	 * @param user
	 * @return
	 */
	private boolean checkLoginMapper(User user){
		db = new DBUtils();
		ResultSet rs = db.query("select * from staff where e_no=? and pwd=?",
				user.getE_no(), user.getPassword());
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return false;
	}
	
	/**
	 * 检查商户是否登录成功
	 * @param merchant
	 * @return
	 */
	private boolean checkLoginMapper(Merchant merchant){
		db = new DBUtils();
		boolean bl = true;
		ResultSet rs = db.query("select * from merchant where e_no=? and pwd=?",
				merchant.getE_no(), merchant.getPassword());
		try {
			if (rs.next()) {
				
			}else
				bl = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return bl;
	}
	
	public String[] send(){			
		return ee;
	}
	
	private String[] ee = new String[2];
	/**
	 * 查询用户的登录密码
	 * @param user
	 */
	private void queryUsername(User user){
		db = new DBUtils();
		String sql = "select name,frommarket from staff where e_no=?";
		ResultSet rs = db.query(sql, user.getE_no());
		try {
			rs.next();
			ee[0] = rs.getString("name");
			ee[1] = rs.getString("frommarket");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 查询商户的登录密码
	 * @param merchant
	 */
	private void queryMerchant(Merchant merchant){
		db = new DBUtils();		
		ResultSet rs = db.query("select name from merchant where e_no=?", merchant.getE_no());
		String name = null;
		try {
			rs.next();
			name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ee[0] = null;
		ee[1] = name;
	} 
	
	
	private static final long serialVersionUID = 1L;
	/*本窗口*/
	private static LoginScreen frame;
	/*内容面板*/
	private JPanel contentPane;
	/*各种组件*/
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton button_1;
	/*数据库*/
	private DBUtils db;
	/*用户类型*/
	private ButtonGroup btg;
	private JRadioButton rdStaff,rdMerchant;
}
