package PresentationLayer;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.client;
import Model.product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**

 The ProductView class represents the user interface for managing products.
 It provides functionality to add, edit, delete, and view products.
 */
public class ProductView {
    private JFrame productF = new JFrame("Product");
    private JFrame viewAllF = new JFrame("View All");

    private JLabel productL = new JLabel("Products");

    private JButton add = new JButton("Add new product");
    private JButton edit = new JButton("Edit product");
    private JButton delete = new JButton("Delete product");
    private JButton viewAll = new JButton("View all products");
    private JButton back = new JButton("back");

    private JTable productTable;
    private DefaultTableModel tableModel;
    private Color color = new Color(245,222,179);

    public ProductView(){
        this.productF.setVisible(true);
        this.productF.setSize(1000,1000);

        productF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productF.getContentPane().setLayout(new BorderLayout());
        productF.getContentPane().setLayout(null);
        productF.getContentPane().setBackground( color );

        productL.setBounds(450,20,500,30);
        productL.setFont(new Font ("Century Gothic", Font.BOLD,25));
        productF.add(productL);

        add.setBounds(270,100,500,30);
        add.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        add.setBackground(new Color(188,143,143));
        productF.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productF.setVisible(false);
                ProductAdd productAdd = new ProductAdd();
            }
        });

        edit.setBounds(270, 150, 500, 30);
        edit.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        edit.setBackground(new Color(188,143,143));
        productF.add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productF.setVisible(false);
                ProductEdit productEdit = new ProductEdit();
            }
        });

        delete.setBounds(270, 200, 500, 30);
        delete.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        delete.setBackground(new Color(188,143,143));
        productF.add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productF.setVisible(false);
                ProductDelete productDelete = new ProductDelete();
            }
        });

        viewAll.setBounds(270, 250, 500, 30);
        viewAll.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        viewAll.setBackground(new Color(188,143,143));
        productF.add(viewAll);
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productF.setVisible(false);
                prepareViewFrame();
                populateTable();
                viewAllF.setVisible(true);
            }
        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        productF.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productF.setVisible(false);
                View view = new View();
            }
        });

        productF.revalidate();
        productF.repaint();
    }

    private void prepareViewFrame(){
        this.viewAllF.setVisible(false);
        this.viewAllF.setSize(1000,1000);

        viewAllF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewAllF.getContentPane().setLayout(new BorderLayout());
        viewAllF.getContentPane().setLayout(null);
        viewAllF.getContentPane().setBackground( color );

        viewAllF.revalidate();
        viewAllF.repaint();
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Name","Description", "Price", "Year of Manufacture", "Quantity"};

        ProductBLL productBLL = new ProductBLL();
        List<product> products = productBLL.getAllProducts();

        Object[][] data = new Object[products.size()][6];
        for (int i = 0; i < products.size(); i++) {
            product p = products.get(i);
            data[i][0] = p.isId();
            data[i][1] = p.isItem_name();
            data[i][2] = p.isDescription();
            data[i][3] = p.isPrice();
            data[i][4] = p.isYear_of_manufacture();
            data[i][5] = p.isQuantity();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        productTable = new JTable(tableModel);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(100, 100, 800, 500);
        viewAllF.add(scrollPane);
    }
}
