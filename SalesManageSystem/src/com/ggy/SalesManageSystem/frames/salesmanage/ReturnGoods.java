package com.ggy.SalesManageSystem.frames.salesmanage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ReturnGoods extends JDialog {
	


	public static ReturnGoods r(){
		if(frame!=null)
			return frame;
		else
			return new ReturnGoods();
	}

	/**
	 * Create the dialog.
	 */
	public ReturnGoods() {
		frame = this;
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(300, 250, 300, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 53, 141, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 110, 141, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 172, 141, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 238, 141, 21);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("请输入商品名称");
		lblNewLabel.setBounds(10, 73, 141, 35);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("请输入条形码");
		lblNewLabel_1.setBounds(10, 10, 141, 35);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("请输入退货原因");
		lblNewLabel_2.setBounds(10, 141, 141, 35);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("请输入退货时间");
		lblNewLabel_3.setBounds(10, 203, 141, 35);
		contentPanel.add(lblNewLabel_3);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("退货");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "退货成功");
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	private static ReturnGoods frame;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField,textField_1,textField_2,textField_3;
}
