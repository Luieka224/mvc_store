import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
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
		frm_splash.setMinimumSize(new Dimension(450, 255));
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

		frm_splash.pack();
		frm_splash.setLocationRelativeTo(null);
	}

	private void loginFrame() {
		frm_login = new JFrame();
		frm_login.setTitle("Cashier Login");
		frm_login.setBackground(Color.WHITE);
		frm_login.getContentPane().setBackground(Color.DARK_GRAY);
		frm_login.getContentPane().setForeground(Color.WHITE);
		frm_login.setResizable(false);
		frm_login.setBounds(100, 100, 372, 373);
		frm_login.setMinimumSize(new Dimension(372, 373));
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

		frm_login.pack();
		frm_login.setLocationRelativeTo(null);
	}

	private void dashboardFrame() {
		frm_dash = new JFrame();
		frm_dash.getContentPane().setBackground(Color.DARK_GRAY);
		frm_dash.setBounds(100, 100, 1000, 599);
		frm_dash.setMinimumSize(new Dimension(1000, 599));
		frm_dash.setTitle("Lola Clara's Store | Cashier");
		frm_dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frm_dash.getContentPane().setLayout(springLayout);
		
		JLabel lbl_StoreName = new JLabel("LOLA CLARA'S");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_StoreName, 11, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_StoreName, 10, SpringLayout.WEST, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_StoreName, 67, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_StoreName, 784, SpringLayout.WEST, frm_dash.getContentPane());
		lbl_StoreName.setForeground(Color.WHITE);
		lbl_StoreName.setFont(new Font("Tahoma", Font.BOLD, 40));
		frm_dash.getContentPane().add(lbl_StoreName);
		
		JLabel lbl_StoreLabel = new JLabel("Store");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_StoreLabel, 45, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_StoreLabel, 10, SpringLayout.WEST, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_StoreLabel, 101, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_StoreLabel, 784, SpringLayout.WEST, frm_dash.getContentPane());
		lbl_StoreLabel.setForeground(Color.WHITE);
		lbl_StoreLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
		frm_dash.getContentPane().add(lbl_StoreLabel);
		
		JPanel pan_ActivityPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pan_ActivityPanel, 99, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pan_ActivityPanel, 10, SpringLayout.WEST, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pan_ActivityPanel, -10, SpringLayout.SOUTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pan_ActivityPanel, -10, SpringLayout.EAST, frm_dash.getContentPane());
		pan_ActivityPanel.setBackground(Color.LIGHT_GRAY);
		frm_dash.getContentPane().add(pan_ActivityPanel);
		SpringLayout sl_pan_ActivityPanel = new SpringLayout();
		pan_ActivityPanel.setLayout(sl_pan_ActivityPanel);
		
		txtf_Quantity = new JTextField();
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, txtf_Quantity, -30, SpringLayout.SOUTH, txtf_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, txtf_Quantity, -200, SpringLayout.EAST, txtf_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, txtf_Quantity, -168, SpringLayout.EAST, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, txtf_Quantity, -10, SpringLayout.SOUTH, pan_ActivityPanel);
		txtf_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtf_Quantity.setColumns(10);
		pan_ActivityPanel.add(txtf_Quantity);
		
		txtf_ProductCode = new JTextField();
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, txtf_ProductCode, -30, SpringLayout.SOUTH, txtf_ProductCode);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, txtf_ProductCode, 10, SpringLayout.WEST, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, txtf_ProductCode, -10, SpringLayout.WEST, txtf_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, txtf_ProductCode, -10, SpringLayout.SOUTH, pan_ActivityPanel);
		txtf_ProductCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pan_ActivityPanel.add(txtf_ProductCode);
		txtf_ProductCode.setColumns(10);
		
		JLabel lbl_ProductCode = new JLabel("Enter product code");
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, lbl_ProductCode, -20, SpringLayout.SOUTH, lbl_ProductCode);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, lbl_ProductCode, 10, SpringLayout.WEST, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, lbl_ProductCode, -10, SpringLayout.NORTH, txtf_ProductCode);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, lbl_ProductCode, 140, SpringLayout.WEST, pan_ActivityPanel);
		lbl_ProductCode.setForeground(Color.BLACK);
		lbl_ProductCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pan_ActivityPanel.add(lbl_ProductCode);
		
		JLabel lbl_Quantity = new JLabel("Quantity");
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, lbl_Quantity, -20, SpringLayout.SOUTH, lbl_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, lbl_Quantity, 0, SpringLayout.WEST, txtf_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, lbl_Quantity, -10, SpringLayout.NORTH, txtf_Quantity);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, lbl_Quantity, 200, SpringLayout.WEST, lbl_Quantity);
		lbl_Quantity.setForeground(Color.BLACK);
		lbl_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pan_ActivityPanel.add(lbl_Quantity);
		
		btn_Checkout = new JButton("CHECKOUT");
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, btn_Checkout, 11, SpringLayout.NORTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, btn_Checkout, -148, SpringLayout.EAST, btn_Checkout);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, btn_Checkout, 44, SpringLayout.NORTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, btn_Checkout, -10, SpringLayout.EAST, pan_ActivityPanel);
		btn_Checkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Checkout.setBackground(Color.GREEN);
		pan_ActivityPanel.add(btn_Checkout);
		
		btn_Cancel = new JButton("CANCEL");
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, btn_Cancel, 11, SpringLayout.NORTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, btn_Cancel, -148, SpringLayout.EAST, btn_Cancel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, btn_Cancel, 44, SpringLayout.NORTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, btn_Cancel, -10, SpringLayout.WEST, btn_Checkout);
		btn_Cancel.setForeground(Color.WHITE);
		btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Cancel.setBackground(Color.RED);
		pan_ActivityPanel.add(btn_Cancel);
		
		btn_AddProduct = new JButton("ADD PRODUCT");
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, btn_AddProduct, -33, SpringLayout.SOUTH, btn_AddProduct);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, btn_AddProduct, -148, SpringLayout.EAST, btn_AddProduct);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, btn_AddProduct, -10, SpringLayout.SOUTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, btn_AddProduct, -10, SpringLayout.EAST, pan_ActivityPanel);
		btn_AddProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_AddProduct.setBackground(new Color(0, 255, 0));
		pan_ActivityPanel.add(btn_AddProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_pan_ActivityPanel.putConstraint(SpringLayout.NORTH, scrollPane, 55, SpringLayout.NORTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -80, SpringLayout.SOUTH, pan_ActivityPanel);
		sl_pan_ActivityPanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, pan_ActivityPanel);
		pan_ActivityPanel.add(scrollPane);

		JLabel lbl_LoginLabel = new JLabel("Logged in as:");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_LoginLabel, 21, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_LoginLabel, 332, SpringLayout.WEST, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_LoginLabel, 44, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_LoginLabel, 420, SpringLayout.WEST, frm_dash.getContentPane());
		lbl_LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_LoginLabel.setForeground(Color.WHITE);
		frm_dash.getContentPane().add(lbl_LoginLabel);
		
		lbl_Username = new JLabel("Luie John Malimit");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Username, 21, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_Username, 417, SpringLayout.WEST, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_Username, 44, SpringLayout.NORTH, frm_dash.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_Username, 548, SpringLayout.WEST, frm_dash.getContentPane());
		lbl_Username.setForeground(Color.WHITE);
		lbl_Username.setFont(new Font("Tahoma", Font.BOLD, 14));
		frm_dash.getContentPane().add(lbl_Username);
		
		btn_LogoutUser = new JButton("LOGOUT");
		springLayout.putConstraint(SpringLayout.NORTH, btn_LogoutUser, 0, SpringLayout.NORTH, lbl_LoginLabel);
		springLayout.putConstraint(SpringLayout.WEST, btn_LogoutUser, -148, SpringLayout.EAST, btn_LogoutUser);
		springLayout.putConstraint(SpringLayout.SOUTH, btn_LogoutUser, -13, SpringLayout.SOUTH, lbl_StoreName);
		springLayout.putConstraint(SpringLayout.EAST, btn_LogoutUser, -10, SpringLayout.EAST, frm_dash.getContentPane());
		
		btn_LogoutUser.setForeground(Color.WHITE);
		btn_LogoutUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_LogoutUser.setBackground(Color.RED);
		frm_dash.getContentPane().add(btn_LogoutUser);

		tbl_ProductsTable = new JTable();
		tbl_ProductsTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbl_ProductsTable.setFillsViewportHeight(true);
		tbl_ProductsTable.setFont(new Font("Tahoma", Font.PLAIN, 38));
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
		tbl_ProductsTable.setFont(new Font("Serif", Font.BOLD, 14));
		tbl_ProductsTable.setRowHeight(20);
		scrollPane.setViewportView(tbl_ProductsTable);
		
		frm_dash.pack();
		frm_dash.setLocationRelativeTo(null);
	}

	public void adminFrame() {
		frm_admin = new JFrame();
		frm_admin.setTitle("Administrator");
		frm_admin.getContentPane().setBackground(Color.DARK_GRAY);
		frm_admin.setBounds(100, 100, 1000, 560);
		frm_admin.setMinimumSize(new Dimension(1000, 560));
		frm_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frm_admin.getContentPane().setLayout(springLayout);
		
		JLabel lbl_StoreName = new JLabel("LOLA CLARA'S");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_StoreName, 0, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_StoreName, 10, SpringLayout.WEST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_StoreName, 56, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_StoreName, 784, SpringLayout.WEST, frm_admin.getContentPane());
		lbl_StoreName.setForeground(Color.WHITE);
		lbl_StoreName.setFont(new Font("Tahoma", Font.BOLD, 40));
		frm_admin.getContentPane().add(lbl_StoreName);
		
		JLabel lbl_StoreLabel = new JLabel("Store");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_StoreLabel, 34, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_StoreLabel, 10, SpringLayout.WEST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_StoreLabel, 90, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_StoreLabel, 784, SpringLayout.WEST, frm_admin.getContentPane());
		lbl_StoreLabel.setForeground(Color.WHITE);
		lbl_StoreLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
		frm_admin.getContentPane().add(lbl_StoreLabel);
		
		btn_LogoutAdmin = new JButton("LOGOUT");
		springLayout.putConstraint(SpringLayout.NORTH, btn_LogoutAdmin, 11, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btn_LogoutAdmin, -147, SpringLayout.EAST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btn_LogoutAdmin, 44, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btn_LogoutAdmin, -10, SpringLayout.EAST, frm_admin.getContentPane());
		btn_LogoutAdmin.setForeground(Color.WHITE);
		btn_LogoutAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_LogoutAdmin.setBackground(Color.RED);
		frm_admin.getContentPane().add(btn_LogoutAdmin);
		
		JPanel pan_userMan = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pan_userMan, 87, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pan_userMan, 10, SpringLayout.WEST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pan_userMan, -10, SpringLayout.SOUTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pan_userMan, 485, SpringLayout.WEST, frm_admin.getContentPane());
		frm_admin.getContentPane().add(pan_userMan);
		SpringLayout sl_pan_userMan = new SpringLayout();
		pan_userMan.setLayout(sl_pan_userMan);
		
		JLabel lbl_userTitle = new JLabel("USER MANAGEMENT");
		sl_pan_userMan.putConstraint(SpringLayout.NORTH, lbl_userTitle, 0, SpringLayout.NORTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.WEST, lbl_userTitle, 10, SpringLayout.WEST, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.SOUTH, lbl_userTitle, 44, SpringLayout.NORTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.EAST, lbl_userTitle, 465, SpringLayout.WEST, pan_userMan);
		lbl_userTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_userTitle.setForeground(Color.BLACK);
		lbl_userTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pan_userMan.add(lbl_userTitle);
		
		btn_addUser = new JButton("ADD");
		sl_pan_userMan.putConstraint(SpringLayout.NORTH, btn_addUser, -33, SpringLayout.SOUTH, btn_addUser);
		sl_pan_userMan.putConstraint(SpringLayout.WEST, btn_addUser, -100, SpringLayout.EAST, btn_addUser);
		sl_pan_userMan.putConstraint(SpringLayout.SOUTH, btn_addUser, -10, SpringLayout.SOUTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.EAST, btn_addUser, -10, SpringLayout.EAST, pan_userMan);
		btn_addUser.setForeground(Color.BLACK);
		btn_addUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addUser.setBackground(Color.GREEN);
		pan_userMan.add(btn_addUser);
		
		btn_remUser = new JButton("REMOVE");
		sl_pan_userMan.putConstraint(SpringLayout.NORTH, btn_remUser, -33, SpringLayout.SOUTH, btn_remUser);
		sl_pan_userMan.putConstraint(SpringLayout.WEST, btn_remUser, -100, SpringLayout.EAST, btn_remUser);
		sl_pan_userMan.putConstraint(SpringLayout.SOUTH, btn_remUser, -10, SpringLayout.SOUTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.EAST, btn_remUser, -10, SpringLayout.WEST, btn_addUser);
		btn_remUser.setForeground(Color.WHITE);
		btn_remUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_remUser.setBackground(Color.RED);
		pan_userMan.add(btn_remUser);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_pan_userMan.putConstraint(SpringLayout.NORTH, scrollPane, 55, SpringLayout.NORTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.SOUTH, scrollPane, -60, SpringLayout.SOUTH, pan_userMan);
		sl_pan_userMan.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, pan_userMan);
		pan_userMan.add(scrollPane);

		JPanel pan_prodMan = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pan_prodMan, 87, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pan_prodMan, 10, SpringLayout.EAST, pan_userMan);
		springLayout.putConstraint(SpringLayout.SOUTH, pan_prodMan, -10, SpringLayout.SOUTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pan_prodMan, -10, SpringLayout.EAST, frm_admin.getContentPane());
		frm_admin.getContentPane().add(pan_prodMan);
		SpringLayout sl_pan_prodMan = new SpringLayout();
		pan_prodMan.setLayout(sl_pan_prodMan);
		
		JLabel lbl_prodTitle = new JLabel("PRODUCT MANAGEMENT");
		sl_pan_prodMan.putConstraint(SpringLayout.NORTH, lbl_prodTitle, 0, SpringLayout.NORTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.WEST, lbl_prodTitle, 10, SpringLayout.WEST, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.SOUTH, lbl_prodTitle, 44, SpringLayout.NORTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.EAST, lbl_prodTitle, -10, SpringLayout.EAST, pan_prodMan);
		lbl_prodTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_prodTitle.setForeground(Color.BLACK);
		lbl_prodTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pan_prodMan.add(lbl_prodTitle);
		
		btn_addProds = new JButton("ADD");
		sl_pan_prodMan.putConstraint(SpringLayout.NORTH, btn_addProds, -33, SpringLayout.SOUTH, btn_addProds);
		sl_pan_prodMan.putConstraint(SpringLayout.WEST, btn_addProds, -100, SpringLayout.EAST, btn_addProds);
		sl_pan_prodMan.putConstraint(SpringLayout.SOUTH, btn_addProds, -10, SpringLayout.SOUTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.EAST, btn_addProds, -10, SpringLayout.EAST, pan_prodMan);
		btn_addProds.setForeground(Color.BLACK);
		btn_addProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addProds.setBackground(Color.GREEN);
		pan_prodMan.add(btn_addProds);
		
		btn_remProds = new JButton("REMOVE");
		sl_pan_prodMan.putConstraint(SpringLayout.NORTH, btn_remProds, -33, SpringLayout.SOUTH, btn_remProds);
		sl_pan_prodMan.putConstraint(SpringLayout.WEST, btn_remProds, -100, SpringLayout.EAST, btn_remProds);
		sl_pan_prodMan.putConstraint(SpringLayout.SOUTH, btn_remProds, -10, SpringLayout.SOUTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.EAST, btn_remProds, -10, SpringLayout.WEST, btn_addProds);
		btn_remProds.setForeground(Color.WHITE);
		btn_remProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_remProds.setBackground(Color.RED);
		pan_prodMan.add(btn_remProds);
		
		btn_clearProds = new JButton("CLEAR ALL");
		sl_pan_prodMan.putConstraint(SpringLayout.NORTH, btn_clearProds, -33, SpringLayout.SOUTH, btn_clearProds);
		sl_pan_prodMan.putConstraint(SpringLayout.WEST, btn_clearProds, -120, SpringLayout.EAST, btn_clearProds);
		sl_pan_prodMan.putConstraint(SpringLayout.SOUTH, btn_clearProds, -10, SpringLayout.SOUTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.EAST, btn_clearProds, -10, SpringLayout.WEST, btn_remProds);
		btn_clearProds.setForeground(Color.WHITE);
		btn_clearProds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_clearProds.setBackground(Color.RED);
		pan_prodMan.add(btn_clearProds);

		JLabel lbl_LoginLabel = new JLabel("Logged in as:");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_LoginLabel, 21, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_LoginLabel, 332, SpringLayout.WEST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_LoginLabel, 44, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_LoginLabel, 420, SpringLayout.WEST, frm_admin.getContentPane());
		lbl_LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_LoginLabel.setForeground(Color.WHITE);
		frm_admin.getContentPane().add(lbl_LoginLabel);
		
		lbl_Adminname = new JLabel("Luie John Malimit");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Adminname, 21, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl_Adminname, 417, SpringLayout.WEST, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_Adminname, 44, SpringLayout.NORTH, frm_admin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lbl_Adminname, 548, SpringLayout.WEST, frm_admin.getContentPane());
		lbl_Adminname.setForeground(Color.WHITE);
		lbl_Adminname.setFont(new Font("Tahoma", Font.BOLD, 14));
		frm_admin.getContentPane().add(lbl_Adminname);

		JScrollPane scp_prodTable = new JScrollPane();
		sl_pan_prodMan.putConstraint(SpringLayout.NORTH, scp_prodTable, 55, SpringLayout.NORTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.WEST, scp_prodTable, 10, SpringLayout.WEST, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.SOUTH, scp_prodTable, -60, SpringLayout.SOUTH, pan_prodMan);
		sl_pan_prodMan.putConstraint(SpringLayout.EAST, scp_prodTable, -10, SpringLayout.EAST, pan_prodMan);
		pan_prodMan.add(scp_prodTable);

		tbl_prodTbl = new JTable();
		tbl_prodTbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_prodTbl.setModel(new DefaultTableModel(
			prodDataSet,
			new String[] {
				"Product Code", "Product Name", "Price"
			}
		));
		tbl_prodTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
		scp_prodTable.setViewportView(tbl_prodTbl);

		tbl_userTbl = new JTable();
		tbl_userTbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_userTbl.setModel(new DefaultTableModel(
			userDataSet,
			new String[] {
				"Role", "Username", "Full Name"
			}
		));
		scrollPane.setViewportView(tbl_userTbl);

		frm_admin.pack();
		frm_admin.setLocationRelativeTo(null);
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
		// @SuppressWarnings({"rawtypes", "unchecked"})
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