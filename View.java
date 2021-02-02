import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class View{
	private Object[][] productsDataSet = {};
	private String[][] userDataSet = {};
	private Object[][] prodDataSet = {};
	private int progressBarValue = 0;

	public JFrame frm_splash;
	public JButton btn_abortStart;
	private JProgressBar pBar_currentStatus;
	private JLabel lbl_currentStatus;

	public JFrame frm_login;
	private JTextField txtf_username;
	private JPasswordField txtf_password;
	public JButton btn_Login;
	
	public JFrame frm_dash;
	public JButton btn_LogoutUser;
	public JButton btn_AddProduct;
	public JButton btn_Cancel;
	public JButton btn_Checkout;
	private JTextField txtf_ProductCode;
	private JTextField txtf_Quantity;
	private JTable tbl_ProductsTable;
	private JLabel lbl_Username;
	
	public JFrame frm_admin;
	public JButton btn_addUser;
	public JButton btn_remUser;
	public JButton btn_addProds;
	public JButton btn_remProds;
	public JButton btn_clearProds;
	public JButton btn_LogoutAdmin;
	private JTable tbl_prodTbl;
	private JTable tbl_userTbl;
	private JLabel lbl_Adminname;

    View() {
		init();
    }

	public void init() {
		loginFrame();
		dashboardFrame();
		adminFrame();
		splashFrame();

		frm_splash.setVisible(true);
	}

    public void startProgram(int value) {
		progressBarValue +=  value;
		pBar_currentStatus.setValue(progressBarValue);

		if(progressBarValue == 0) {
			lbl_currentStatus.setText("Checking for Internet...");
		} else if(progressBarValue == 30) {
			lbl_currentStatus.setText("Connecting to Server...");
		} else if(progressBarValue == 60) {
			lbl_currentStatus.setText("Connecting to Database...");
		} else if(progressBarValue == 100) {
			frm_splash.dispose();
			frm_login.setVisible(true);
		}
	}
	
	private void splashFrame() {
		frm_splash = new JFrame();
		frm_splash.getContentPane().setBackground(Color.DARK_GRAY);
		frm_splash.setResizable(false);
		frm_splash.setBounds(100, 100, 450, 255);
		frm_splash.setUndecorated(true);
		frm_splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_splash.getContentPane().setLayout(null);
		
		JLabel lbl_splashTitle = new JLabel("Lola Clara's");
		lbl_splashTitle.setForeground(Color.WHITE);
		lbl_splashTitle.setFont(new Font("Tahoma", Font.BOLD, 50));
		lbl_splashTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_splashTitle.setBounds(10, 79, 430, 51);
		frm_splash.getContentPane().add(lbl_splashTitle);
		
		JLabel lbl_splashSubtitle = new JLabel("Store");
		lbl_splashSubtitle.setForeground(Color.WHITE);
		lbl_splashSubtitle.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
		lbl_splashSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_splashSubtitle.setBounds(10, 118, 430, 54);
		frm_splash.getContentPane().add(lbl_splashSubtitle);
		
		lbl_currentStatus = new JLabel("Pinging google.com...");
		lbl_currentStatus.setForeground(Color.WHITE);
		lbl_currentStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_currentStatus.setBounds(10, 215, 424, 23);
		frm_splash.getContentPane().add(lbl_currentStatus);
		
		pBar_currentStatus = new JProgressBar();
		pBar_currentStatus.setValue(0);
		pBar_currentStatus.setBounds(10, 239, 424, 4);
		frm_splash.getContentPane().add(pBar_currentStatus);
		
		btn_abortStart = new JButton("");
		btn_abortStart.setSelectedIcon(null);
		btn_abortStart.setIcon(new ImageIcon(View.class.getResource("/img/icons8_delete_sign_20px.png")));
		btn_abortStart.setBackground(Color.DARK_GRAY);
		btn_abortStart.setBounds(410, 11, 30, 30);
		btn_abortStart.setBorderPainted(false);
		btn_abortStart.setFocusPainted(false);
		frm_splash.getContentPane().add(btn_abortStart);
	}

	private void loginFrame() {
		frm_login = new JFrame();
		frm_login.setTitle("Cashier Login");
		frm_login.setBackground(Color.WHITE);
		frm_login.getContentPane().setBackground(Color.DARK_GRAY);
		frm_login.getContentPane().setForeground(Color.WHITE);
		frm_login.setResizable(false);
		frm_login.setBounds(100, 100, 372, 373);
		frm_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_login.getContentPane().setLayout(null);
		
		JLabel lbl_title = new JLabel("LOLA CLARA'S");
		lbl_title.setForeground(Color.WHITE);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 38));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(10, 11, 346, 59);
		frm_login.getContentPane().add(lbl_title);
		
		JLabel lbl_subtitle = new JLabel("Store");
		lbl_subtitle.setForeground(Color.WHITE);
		lbl_subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subtitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 39));
		lbl_subtitle.setBounds(10, 39, 346, 59);
		frm_login.getContentPane().add(lbl_subtitle);
		
		JLabel lbl_ActivityTitle = new JLabel("CASHIER LOGIN");
		lbl_ActivityTitle.setForeground(Color.WHITE);
		lbl_ActivityTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ActivityTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_ActivityTitle.setBounds(10, 91, 346, 59);
		frm_login.getContentPane().add(lbl_ActivityTitle);
		
		txtf_username = new JTextField();
		txtf_username.setForeground(Color.BLACK);
		txtf_username.setBackground(Color.LIGHT_GRAY);
		txtf_username.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtf_username.setBounds(36, 176, 285, 29);
		frm_login.getContentPane().add(txtf_username);
		txtf_username.setColumns(10);
		
		JLabel lbl_username = new JLabel("Username");
		lbl_username.setForeground(Color.WHITE);
		lbl_username.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbl_username.setBounds(36, 132, 135, 59);
		frm_login.getContentPane().add(lbl_username);
		
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbl_password.setBounds(36, 201, 135, 59);
		frm_login.getContentPane().add(lbl_password);
		
		btn_Login = new JButton("Login");
		btn_Login.setBackground(Color.LIGHT_GRAY);
		btn_Login.setForeground(Color.BLACK);
		btn_Login.setFont(new Font("Tahoma", Font.BOLD, 19));
		btn_Login.setBounds(219, 285, 102, 31);
		frm_login.getContentPane().add(btn_Login);
		
		txtf_password = new JPasswordField();
		txtf_password.setForeground(Color.BLACK);
		txtf_password.setBackground(Color.LIGHT_GRAY);
		txtf_password.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtf_password.setBounds(36, 245, 285, 29);
		frm_login.getContentPane().add(txtf_password);
	}

	private void dashboardFrame() {
		frm_dash = new JFrame();
		frm_dash.getContentPane().setBackground(Color.DARK_GRAY);
		frm_dash.setResizable(false);
		frm_dash.setBounds(100, 100, 1000, 599);
		frm_dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_dash.getContentPane().setLayout(null);
		
		JLabel lbl_StoreName = new JLabel("LOLA CLARA'S");
		lbl_StoreName.setForeground(Color.WHITE);
		lbl_StoreName.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl_StoreName.setBounds(10, 11, 774, 56);
		frm_dash.getContentPane().add(lbl_StoreName);
		
		JLabel lbl_StoreLabel = new JLabel("Store");
		lbl_StoreLabel.setForeground(Color.WHITE);
		lbl_StoreLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
		lbl_StoreLabel.setBounds(10, 45, 774, 56);
		frm_dash.getContentPane().add(lbl_StoreLabel);
		
		JPanel pan_ActivityPanel = new JPanel();
		pan_ActivityPanel.setBackground(Color.LIGHT_GRAY);
		pan_ActivityPanel.setBounds(10, 99, 961, 449);
		frm_dash.getContentPane().add(pan_ActivityPanel);
		pan_ActivityPanel.setLayout(null);
		
		txtf_ProductCode = new JTextField();
		txtf_ProductCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtf_ProductCode.setBounds(10, 398, 576, 31);
		pan_ActivityPanel.add(txtf_ProductCode);
		txtf_ProductCode.setColumns(10);
		
		JLabel lbl_ProductCode = new JLabel("Enter product code");
		lbl_ProductCode.setForeground(Color.BLACK);
		lbl_ProductCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_ProductCode.setBounds(10, 375, 130, 23);
		pan_ActivityPanel.add(lbl_ProductCode);
		
		txtf_Quantity = new JTextField();
		txtf_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtf_Quantity.setColumns(10);
		txtf_Quantity.setBounds(596, 398, 199, 31);
		pan_ActivityPanel.add(txtf_Quantity);
		
		JLabel lbl_Quantity = new JLabel("Quantity");
		lbl_Quantity.setForeground(Color.BLACK);
		lbl_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Quantity.setBounds(596, 375, 130, 23);
		pan_ActivityPanel.add(lbl_Quantity);
		
		btn_Checkout = new JButton("CHECKOUT");
		btn_Checkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Checkout.setBackground(Color.GREEN);
		btn_Checkout.setBounds(804, 11, 147, 33);
		pan_ActivityPanel.add(btn_Checkout);
		
		btn_Cancel = new JButton("CANCEL");
		btn_Cancel.setForeground(Color.WHITE);
		btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Cancel.setBackground(Color.RED);
		btn_Cancel.setBounds(648, 11, 147, 33);
		pan_ActivityPanel.add(btn_Cancel);
		
		btn_AddProduct = new JButton("ADD PRODUCT");
		btn_AddProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_AddProduct.setBackground(new Color(0, 255, 0));
		btn_AddProduct.setBounds(804, 398, 147, 32);
		pan_ActivityPanel.add(btn_AddProduct);

		JLabel lbl_LoginLabel = new JLabel("Logged in as:");
		lbl_LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_LoginLabel.setForeground(Color.WHITE);
		lbl_LoginLabel.setBounds(332, 21, 88, 23);
		frm_dash.getContentPane().add(lbl_LoginLabel);
		
		lbl_Username = new JLabel("Luie John Malimit");
		lbl_Username.setForeground(Color.WHITE);
		lbl_Username.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Username.setBounds(417, 21, 131, 23);
		frm_dash.getContentPane().add(lbl_Username);
		
		btn_LogoutUser = new JButton("LOGOUT");
		btn_LogoutUser.setForeground(Color.WHITE);
		btn_LogoutUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_LogoutUser.setBackground(Color.RED);
		btn_LogoutUser.setBounds(824, 16, 147, 33);
		frm_dash.getContentPane().add(btn_LogoutUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 941, 317);
		pan_ActivityPanel.add(scrollPane);

		tbl_ProductsTable = new JTable();
		tbl_ProductsTable.setModel(new DefaultTableModel(
			productsDataSet,
			new String[] {
				"Product Code", "Product Name", "Qty", "Price", "Total"
			}
		));
		tbl_ProductsTable.getColumnModel().getColumn(0).setPreferredWidth(110);
		tbl_ProductsTable.getColumnModel().getColumn(0).setMinWidth(110);
		tbl_ProductsTable.getColumnModel().getColumn(0).setMaxWidth(110);
		tbl_ProductsTable.getColumnModel().getColumn(1).setPreferredWidth(264);
		tbl_ProductsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		tbl_ProductsTable.getColumnModel().getColumn(2).setMinWidth(60);
		tbl_ProductsTable.getColumnModel().getColumn(3).setPreferredWidth(71);
		tbl_ProductsTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		scrollPane.setViewportView(tbl_ProductsTable);
	}

	public void adminFrame() {
		frm_admin = new JFrame();
		frm_admin.setTitle("Administrator");
		frm_admin.setResizable(false);
		frm_admin.getContentPane().setBackground(Color.DARK_GRAY);
		frm_admin.setBounds(100, 100, 1000, 560);
		frm_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_admin.getContentPane().setLayout(null);
		
		JLabel lbl_StoreName = new JLabel("LOLA CLARA'S");
		lbl_StoreName.setForeground(Color.WHITE);
		lbl_StoreName.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl_StoreName.setBounds(10, 0, 774, 56);
		frm_admin.getContentPane().add(lbl_StoreName);
		
		JLabel lbl_StoreLabel = new JLabel("Store");
		lbl_StoreLabel.setForeground(Color.WHITE);
		lbl_StoreLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
		lbl_StoreLabel.setBounds(10, 34, 774, 56);
		frm_admin.getContentPane().add(lbl_StoreLabel);
		
		btn_LogoutAdmin = new JButton("LOGOUT");
		btn_LogoutAdmin.setForeground(Color.WHITE);
		btn_LogoutAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_LogoutAdmin.setBackground(Color.RED);
		btn_LogoutAdmin.setBounds(825, 11, 147, 33);
		frm_admin.getContentPane().add(btn_LogoutAdmin);
		
		JPanel pan_userMan = new JPanel();
		pan_userMan.setBounds(10, 87, 461, 420);
		frm_admin.getContentPane().add(pan_userMan);
		pan_userMan.setLayout(null);
		
		JLabel lbl_userTitle = new JLabel("USER MANAGEMENT");
		lbl_userTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_userTitle.setForeground(Color.BLACK);
		lbl_userTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_userTitle.setBounds(10, 0, 441, 44);
		pan_userMan.add(lbl_userTitle);
		
		JScrollPane scp_userTable = new JScrollPane();
		scp_userTable.setBounds(10, 55, 441, 310);
		pan_userMan.add(scp_userTable);
		
		tbl_userTbl = new JTable();
		tbl_userTbl.setModel(new DefaultTableModel(
			userDataSet,
			new String[] {
				"Role", "Username", "Full Name"
			}
		));
		scp_userTable.setViewportView(tbl_userTbl);
		
		btn_addUser = new JButton("ADD");
		btn_addUser.setForeground(Color.BLACK);
		btn_addUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addUser.setBackground(Color.GREEN);
		btn_addUser.setBounds(368, 376, 83, 33);
		pan_userMan.add(btn_addUser);
		
		btn_remUser = new JButton("REMOVE");
		btn_remUser.setForeground(Color.WHITE);
		btn_remUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_remUser.setBackground(Color.RED);
		btn_remUser.setBounds(263, 376, 95, 33);
		pan_userMan.add(btn_remUser);
		
		JPanel pan_prodMan = new JPanel();
		pan_prodMan.setLayout(null);
		pan_prodMan.setBounds(481, 87, 491, 420);
		frm_admin.getContentPane().add(pan_prodMan);
		
		JLabel lbl_prodTitle = new JLabel("PRODUCT MANAGEMENT");
		lbl_prodTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_prodTitle.setForeground(Color.BLACK);
		lbl_prodTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_prodTitle.setBounds(10, 0, 471, 44);
		pan_prodMan.add(lbl_prodTitle);
		
		JScrollPane scp_prodTable = new JScrollPane();
		scp_prodTable.setBounds(10, 55, 471, 310);
		pan_prodMan.add(scp_prodTable);
		
		tbl_prodTbl = new JTable();
		tbl_prodTbl.setModel(new DefaultTableModel(
			prodDataSet,
			new String[] {
				"Product Code", "Product Name", "Price"
			}
		));
		tbl_prodTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
		scp_prodTable.setViewportView(tbl_prodTbl);
		
		btn_addProds = new JButton("ADD");
		btn_addProds.setForeground(Color.BLACK);
		btn_addProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addProds.setBackground(Color.GREEN);
		btn_addProds.setBounds(398, 376, 83, 33);
		pan_prodMan.add(btn_addProds);
		
		btn_remProds = new JButton("REMOVE");
		btn_remProds.setForeground(Color.WHITE);
		btn_remProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_remProds.setBackground(Color.RED);
		btn_remProds.setBounds(293, 376, 95, 33);
		pan_prodMan.add(btn_remProds);
		
		btn_clearProds = new JButton("CLEAR ALL");
		btn_clearProds.setForeground(Color.WHITE);
		btn_clearProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_clearProds.setBackground(Color.RED);
		btn_clearProds.setBounds(173, 376, 110, 33);
		pan_prodMan.add(btn_clearProds);
		
		JLabel lbl_LoginLabel = new JLabel("Logged in as:");
		lbl_LoginLabel.setForeground(Color.WHITE);
		lbl_LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_LoginLabel.setBounds(322, 11, 88, 23);
		frm_admin.getContentPane().add(lbl_LoginLabel);
		
		lbl_Adminname = new JLabel("Luie John Malimit");
		lbl_Adminname.setForeground(Color.WHITE);
		lbl_Adminname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Adminname.setBounds(407, 11, 131, 23);
		frm_admin.getContentPane().add(lbl_Adminname);
	}

	public String getUsername() {
		return txtf_username.getText();
	}

	public String getPassword() {
		return String.valueOf(txtf_password.getPassword());
	}

	public void setFullName(String fullName) {
		lbl_Adminname.setText(fullName);
		lbl_Username.setText(fullName);
	}

	public String getProdID() {
		return txtf_ProductCode.getText();
	}

	public int getQty() {
		return Integer.parseInt(txtf_Quantity.getText());
	}

	public void addProductToTable(Object[] data) {
		if (data.equals(null)) {
			JOptionPane.showMessageDialog(
				frm_dash,
				"Such product does not exist!",
				null,
				JOptionPane.ERROR_MESSAGE,
				null
				);
		} else {
			DefaultTableModel tblMdl = (DefaultTableModel) tbl_ProductsTable.getModel();

			tblMdl.addRow(data);

			txtf_ProductCode.setText("");
			txtf_Quantity.setText("");
		}
	}

	public void loadUserTable(Object[][] data) {
		if (data.equals(null)) {
			JOptionPane.showMessageDialog(
				frm_admin,
				"No user data to load!",
				null,
				JOptionPane.ERROR_MESSAGE,
				null
				);
		} else {
			DefaultTableModel tblMdl = (DefaultTableModel) tbl_userTbl.getModel();

			for(int i = 0; i < data.length; i++) {
				tblMdl.addRow(data[i]);
			}
		}
	}

	public void loadProdTable(Object[][] data) {
		if (data.equals(null)) {
			JOptionPane.showMessageDialog(
				frm_admin,
				"No product data to load!",
				null,
				JOptionPane.ERROR_MESSAGE,
				null
				);
		} else {
			DefaultTableModel tblMdl = (DefaultTableModel) tbl_prodTbl.getModel();

			for(int i = 0; i < data.length; i++) {
				tblMdl.addRow(data[i]);
			}
		}
	}

	public void invalidCredentials() {
		JOptionPane.showMessageDialog(frm_login, "Invalid credentials");
	}
	
	void actionButtonListener(ActionListener listener) {
		btn_Login.addActionListener(listener);
		btn_LogoutAdmin.addActionListener(listener);
		btn_LogoutUser.addActionListener(listener);
		btn_AddProduct.addActionListener(listener);
		btn_Checkout.addActionListener(listener);
		btn_Cancel.addActionListener(listener);
		btn_addUser.addActionListener(listener);
		btn_remUser.addActionListener(listener);
		btn_remProds.addActionListener(listener);
		btn_addProds.addActionListener(listener);
		btn_clearProds.addActionListener(listener);
		btn_abortStart.addActionListener(listener);
	}

	void logoutUser(JFrame frame) {
		frame.dispose();
		frm_login.setVisible(true);
	}

	void loginUser(JFrame frame, String fullName) {
		frm_login.dispose();
		setFullName(fullName);
		frame.setVisible(true);
	}

	void clearTable() {
		DefaultTableModel tblMdl = (DefaultTableModel) tbl_ProductsTable.getModel();

		tblMdl.setRowCount(0);
	}

	void checkoutProducts(int total) {
		int toPay = Integer.parseInt(
			JOptionPane.showInputDialog(
					frm_dash,
					"Total Price: " + total + "\n"
					+ "Enter cash below"
				)
			);

		if(total > toPay) {
			JOptionPane.showMessageDialog(
				frm_dash,
				"Invalid Transaction",
				null,
				JOptionPane.ERROR_MESSAGE,
				null
			);
		} else {
			JOptionPane.showMessageDialog(
				frm_dash,
				"Transaction Processed! Change is " + (toPay - total),
				null,
				JOptionPane.INFORMATION_MESSAGE,
				null
			);
			clearTable();
		}
	}

	void cancelTransaction() {
		int random = ThreadLocalRandom.current().nextInt(1111, 9998 + 1);

		int input = Integer.parseInt(
			JOptionPane.showInputDialog(
					frm_dash,
					"Are you sure you want to cancel this transaction? \n"
					+ "Enter " + random + " to confirm \n"
				)
			);

		if(input == random) {
			clearTable();
		} else {
			JOptionPane.showMessageDialog(
				frm_dash,
				"Transaction not canceled! \n"
				+"Confirmation number did not match!",
				null,
				JOptionPane.INFORMATION_MESSAGE,
				null
			);
		}
	}
	
	public Object[] getUserRegData() {
		Object data[] = new Object[4];
		int index = 1;
		Object comboData[] = {"Manager", "Cashier"};

		JTextField username = new JTextField();
		JTextField password = new JTextField();
		JTextField fullname = new JTextField();
		@SuppressWarnings({"rawtypes", "unchecked"})
		JComboBox comboBox = new JComboBox(comboData);
		comboBox.setSelectedIndex(index);
		Object[] message = {
			"Username: ", username,
			"Password", password,
			"Role", comboBox,
			"Name", fullname
		};

		int choice = JOptionPane.showConfirmDialog(
			frm_admin,
			message,
			"Register New User",
			JOptionPane.OK_CANCEL_OPTION
		);

		if (comboBox.getSelectedItem().toString().equals("Manager")) {
			index = 2;
		}

		if(choice == JOptionPane.OK_OPTION) {
			data[0] = index;
			data[1] = username.getText();
			data[2] = password.getText();
			data[3] = fullname.getText();

			return data;
		}
		
		return null;
	}

	public void clearUserTable() {
		DefaultTableModel tblMdl = (DefaultTableModel) tbl_userTbl.getModel();

		tblMdl.setRowCount(0);
	}

	public String getRemUsername() {
		String username = JOptionPane.showInputDialog(
			frm_admin,
			"Enter username to remove"
		);

		return username;
	}

	public Object[] getProdRegData() {
		Object data[] = new Object[3];

		JTextField productCode = new JTextField();
		JTextField productName = new JTextField();
		JTextField price = new JTextField();
		Object[] message = {
			"Product Code: ", productCode,
			"Product Name", productName,
			"Price", price
		};

		int choice = JOptionPane.showConfirmDialog(
			frm_admin,
			message,
			"Register New Product",
			JOptionPane.OK_CANCEL_OPTION
		);

		if(choice == JOptionPane.OK_OPTION) {
			data[0] = productCode.getText();
			data[1] = productName.getText();
			data[2] = Integer.parseInt(price.getText());

			return data;
		}
		
		return null;
	}

	public void clearProdTable() {
		DefaultTableModel tblMdl = (DefaultTableModel) tbl_prodTbl.getModel();

		tblMdl.setRowCount(0);
	}

	public String getRemProductID() {
		String username = JOptionPane.showInputDialog(
			frm_admin,
			"Enter product to remove"
		);

		return username;
	}

	public void showError(JFrame parent, String message, String title) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			title,
			JOptionPane.ERROR_MESSAGE
		);
	}
}