import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.Component;

public class Controller{
    private View view;
    private Model model;

    private int userMode;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        startProgram();

        this.view.actionButtonListener(new ControllerButtonListener());
    }

    private void startProgram() {
        if(model.checkInternet()) {

            view.startProgram(30);
            if(model.checkConnectionToServer()) {

                view.startProgram(30);
                if(model.connectToDB()) {

                    view.startProgram(40);
                } else {
                    view.showError(
                        view.frm_splash,
                        "The application can't connect to the database. \n" +
                        "Please contact the developer at \n"+
                        "luiejohnmalimit@gmail.com or luiejohn@malimit.net",
                        "Unable to connect to the database"
                    );
                }
            } else {
                view.showError(
                    view.frm_splash,
                    "The application can't connect to the server. \n" +
                    "Please check if your machine can connect to the server "+
                    "by locating the address: freedb.tech in your browser",
                    "Unable to connect to the server"
                );
            }
        } else {
            view.showError(
                view.frm_splash,
                "The application can't connect to the internet. \n" +
                "Please check if your machine is connected to the internet \n"+
                " or if your firewall is blocking this program.",
                "Unable to connect to the internet"
            );
        }
    }

    class ControllerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Command");
            if(e.getSource().equals(view.btn_Login)) {
                verifyUser();
            } else if(e.getSource().equals(view.btn_LogoutUser)) {
                view.logoutUser(
                    getViewFrame(e)
                );
            } else if(e.getSource().equals(view.btn_LogoutAdmin)) {
                view.logoutUser(
                    getViewFrame(e)
                );
            } else if(e.getSource().equals(view.btn_AddProduct)) {
                try {
                    view.addProductToTable(model.getProduct(view.getProdID(), view.getQty()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if(e.getSource().equals(view.btn_Checkout)) {
                view.checkoutProducts(
                    model.getTotalPrice()
                );
            } else if(e.getSource().equals(view.btn_Cancel)) {
                view.cancelTransaction();
            } else if(e.getSource().equals(view.btn_addUser)) {
                addUser();
            } else if(e.getSource().equals(view.btn_remUser)) {
                removeUser();
            } else if(e.getSource().equals(view.btn_addProds)) {
                addProduct();
            } else if(e.getSource().equals(view.btn_remProds)) {
                removeProduct();
            } else if(e.getSource().equals(view.btn_clearProds)) {
                emptyProducts();
            } else if(e.getSource().equals(view.btn_abortStart)) {
                System.out.println("Hello");
                view.frm_splash.dispose();
            }
        }

        private void emptyProducts() {
            model.emptyProducts();
            loadProductsTable();
        }

        private void removeProduct() {
            String productCode = view.getRemProductID();

            if(model.removeProduct(productCode)) {
                JOptionPane.showMessageDialog(view.frm_admin, "Product Removed Successfully!");
                loadProductsTable();
            } else {
                JOptionPane.showMessageDialog(
                    view.frm_admin, 
                    "The product doesn't exist or invalid data",
                    null,
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        private void addProduct() {
            Object regData[] = view.getProdRegData();

            String productCode = regData[0].toString();
            String productName = regData[1].toString();
            int price = Integer.parseInt(regData[2].toString());
            

            if(model.addProduct(productCode, productName, price)) {
                JOptionPane.showMessageDialog(view.frm_admin, "Product Added Successfully!");
                loadProductsTable();
            } else {
                JOptionPane.showMessageDialog(
                    view.frm_admin, 
                    "The product has already been registered or invalid data",
                    null,
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        private void removeUser() {
            String username = view.getRemUsername();

            if(model.removeUser(username)) {
                JOptionPane.showMessageDialog(view.frm_admin, "User Removed Successfully!");
                loadUserTable();
            } else {
                JOptionPane.showMessageDialog(
                    view.frm_admin, 
                    "The user doesn't exist or invalid data",
                    null,
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        private void addUser() {
            Object regData[] = view.getUserRegData();

            int role = Integer.parseInt(regData[0].toString());
            String username = regData[1].toString();
            String password = regData[2].toString();
            String fullname = regData[3].toString();
            

            if(model.registerUser(role, username, password, fullname)) {
                JOptionPane.showMessageDialog(view.frm_admin, "User Added Successfully!");
                loadUserTable();
            } else {
                JOptionPane.showMessageDialog(
                    view.frm_admin, 
                    "The user has already been registered or invalid data",
                    null,
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        JFrame getViewFrame(ActionEvent e) {
            Component comp = (Component) e.getSource();
            JFrame frame = (JFrame) SwingUtilities.getRoot(comp);
            return frame;
        }

        private void verifyUser() {
            String username = view.getUsername();
            String password = view.getPassword();

            try {
                userMode = model.verifyUser(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            if(userMode == 0) {
                view.invalidCredentials();
            } else if(userMode == 1) {
                view.loginUser(
                    view.frm_dash,
                    model.getFullName()
                    );
            } else if(userMode == 2) {
                loadProductsTable();
                loadUserTable();

                view.loginUser(
                    view.frm_admin,
                    model.getFullName()
                    );
                
                
            }
            
        }
    }

    private void loadUserTable() {
        try {
            view.clearUserTable();
            view.loadUserTable(
                model.getUsers()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProductsTable() {
        try {
            view.clearProdTable();
            view.loadProdTable(
                model.getProducts()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}