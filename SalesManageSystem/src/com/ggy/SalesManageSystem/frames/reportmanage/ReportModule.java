package com.ggy.SalesManageSystem.frames.reportmanage;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ggy.SalesManageSystem.commons.DBUtils;
import com.ggy.SalesManageSystem.commons.TableToExcel;
import com.ggy.SalesManageSystem.dao.QueryDifferentTable;
import com.ggy.SalesManageSystem.frames.vipmanage.Module;

public class ReportModule extends JFrame implements QueryDifferentTable {
	private static final long serialVersionUID = 1L;

	public ReportModule() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(480, 200, 967, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel label = new JLabel("销售系统数据");
		label.setFont(new Font("华文新魏", Font.PLAIN, 36));
		label.setForeground(SystemColor.textHighlight);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(8, 10, 758, 54);
		contentPane.add(label);

		JButton btnNewButton_1 = new JButton("导出");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int f = JOptionPane.showConfirmDialog(null, "是否导出");
				if (f == 0) {
					String d = JOptionPane.showInputDialog("请输入你要到出的名字");
					export(d);
				}
			}
		});
		btnNewButton_1.setBounds(673, 49, 108, 38);
		contentPane.add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBounds(8, 83, 144, 391);
		contentPane.add(panel);
		panel.setLayout(null);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("报表") {
			private static final long serialVersionUID = 1L;

			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("销售管理报表");
				node_1.add(new DefaultMutableTreeNode("销售记录表"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("进货管理报表");
				node_1.add(new DefaultMutableTreeNode("进货记录"));
				node_1.add(new DefaultMutableTreeNode("供货商"));
				node_1.add(new DefaultMutableTreeNode("货款汇总"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("库存管理报表");
				node_1.add(new DefaultMutableTreeNode("商品类型表"));
				node_1.add(new DefaultMutableTreeNode("退货明细表"));
				node_1.add(new DefaultMutableTreeNode("现金盘点表"));
				node_1.add(new DefaultMutableTreeNode("损耗清单"));
				node_1.add(new DefaultMutableTreeNode("利润表"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("商户管理");
				node_1.add(new DefaultMutableTreeNode("商户表"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("系统管理");
				node_1.add(new DefaultMutableTreeNode("员工表信息表"));
				node_1.add(new DefaultMutableTreeNode("会员信息表"));
				add(node_1);
			}
		}));
		tree.setBounds(10, 21, 124, 370);
		panel.add(tree);
		final JTree t = tree;
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) t.getLastSelectedPathComponent(); // 获得节点的内容
				Object object = node.getUserObject();
				if (!node.isRoot()) {
					if (((String) object).equals("销售记录表")) {
						model = new DefaultTableModel(new Object[][] {}, new String[] { "流水号", "详细信息", "总金额" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("sales_record");
					} else if (((String) object).equals("进货记录")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "进货商品名称", "进货商品数量", "进货价格", "类型", "订货时间" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("order1");
					} else if (((String) object).equals("供货商")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "供货商名称", "负责人", "电话", "地址", "法人", "备注" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("supplier");
					} else if (((String) object).equals("货款汇总")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "商品名称", "商品价格", "商品数量", "货款统计" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("loan_summary");
					} else if (((String) object).equals("商品类型表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] {  "条形码", "商品名称", "计量单位", "单价", "类型", "数量", "最后编辑时间" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("commodityinfo");
					} else if (((String) object).equals("退货明细表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "日期", "条形码", "商品名称", "类型", "退货数量", "操作人员" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("returnlist");
					} else if (((String) object).equals("现金盘点表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "票面额", "张数", "金额", "总金额", "盘点人", "监盘人" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("cash_list");
					} else if (((String) object).equals("损耗清单")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "条形码", "商品名称", "商品价格", "损耗", "日期", "损耗原因" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("depletion_list");
					} else if (((String) object).equals("利润表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "商品名称", "类型", "商品价格", "总金额", "商品成本", "净利润" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("profit");
					} else if (((String) object).equals("商户表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "商户账号", "名称", "密码", "邮箱", "联系方式", "地址" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("merchant");
					} else if (((String) object).equals("员工表信息表")) {
						model = new DefaultTableModel(new Object[][] {},
								new String[] { "员工号", "姓名", "密码", "邮箱", "性别", "出生日期", "家庭地址", "身份证号码", "角色", "所属商户" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("staff");
					} else if (((String) object).equals("会员信息表")) {
						model = new DefaultTableModel(new Object[][] {}, new String[] { "会员号", "姓名", "电话", "性别" });
						table.setModel(model);
						model.getDataVector().clear();
						queryAll("vip_manage");
					}

				}
			}
		});

		panelLoadDate = new JPanel();
		panelLoadDate.setBorder(UIManager.getBorder("Button.border"));
		getContentPane().add(panelLoadDate);
		panelLoadDate.setBounds(161, 102, 773, 372);
		panelLoadDate.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 757, 372);
		panelLoadDate.add(scrollPane);

		table = new JTable();
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "", "", "", "", "", "", "" });

		JButton btnNewButton_3 = new JButton("返回");
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Module.r().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(802, 49, 102, 38);
		contentPane.add(btnNewButton_3);

	}

	private DBUtils db;

	@Override
	public void queryAll(String tabname) {
		db = new DBUtils();
		String sql = "select * from " + tabname;
		ResultSet rs = db.query(sql);

		try {
			model.getDataVector().clear();
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			while (rs.next()) {
				Object[] obj = new Object[rsd.getColumnCount()];
				for (int i = 0; i < rsd.getColumnCount(); i++) {
					obj[i] = rs.getObject(i + 1);
				}
				model.addRow(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void queryByColname(String tabname, String value) {

	}

	public void export(String xlsName) {
		int columnCount = model.getColumnCount();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < columnCount; i++) {
			list.add(model.getColumnName(i));
		}
		@SuppressWarnings("rawtypes")
		Vector v = model.getDataVector();
		try {
			x = TableToExcel.xlsDto2Excel(list, v);
			File file = new File("C:/Users/Administrator/Desktop/" + xlsName + ".xlsx");
			if (!file.exists())
				file.createNewFile();
			OutputStream out = new FileOutputStream(file);
			x.write(out);
			JOptionPane.showMessageDialog(null, "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReportModule r(){
		if(frame!=null)
			return frame;
		else
			return new ReportModule();
	}
	
	/* various */
	private static ReportModule frame;
	private JPanel contentPane;
	private JPanel panelLoadDate;
	private DefaultTableModel model;
	private JTable table;
	private XSSFWorkbook x;
}
