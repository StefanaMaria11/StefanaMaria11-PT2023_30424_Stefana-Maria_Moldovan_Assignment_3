package PresentationLayer;

import BusinessLogic.OrdersBLL;
import Model.orders;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrdersView {
    private JFrame OrdersFrame = new JFrame("Orders");
    private JFrame viewFrame = new JFrame("ViewAll");

    private JLabel orderL = new JLabel("Order");

    private JButton add = new JButton("Place new order");
    private JButton edit = new JButton("Edit order");
    private JButton delete = new JButton("Delete order");
    private JButton viewAll = new JButton("View all orders");
    private JButton back = new JButton("back");


    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private Color color = new Color(245,222,179);
    /**
     * Creates a new OrdersView instance and initializes the graphical user interface.
     */
    public OrdersView(){
        this.OrdersFrame.setVisible(true);
        this.OrdersFrame.setSize(1000,1000);

        OrdersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OrdersFrame.getContentPane().setLayout(new BorderLayout());
        OrdersFrame.getContentPane().setLayout(null);
        OrdersFrame.getContentPane().setBackground( color );

        orderL.setBounds(450,20,500,30);
        orderL.setFont(new Font ("Century Gothic", Font.BOLD,25));
        OrdersFrame.add(orderL);

        add.setBounds(270,100,500,30);
        add.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        add.setBackground(new Color(188,143,143));
        OrdersFrame.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setVisible(false);
                OrdersAdd ordersAdd = new OrdersAdd();
            }
        });

        edit.setBounds(270, 150, 500, 30);
        edit.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        edit.setBackground(new Color(188,143,143));
        OrdersFrame.add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setVisible(false);
                OrdersEdit ordersEdit = new OrdersEdit();
            }
        });

        delete.setBounds(270, 200, 500, 30);
        delete.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        delete.setBackground(new Color(188,143,143));
        OrdersFrame.add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setVisible(false);
                OrdersDelete ordrsDelete = new OrdersDelete();
            }
        });

        viewAll.setBounds(270, 250, 500, 30);
        viewAll.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        viewAll.setBackground(new Color(188,143,143));
        OrdersFrame.add(viewAll);
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setVisible(false);
                prepareViewFrame();
                populateTable();
                viewFrame.setVisible(true);
            }
        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        OrdersFrame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersFrame.setVisible(false);
                View view = new View();
            }
        });

        OrdersFrame.revalidate();
        OrdersFrame.repaint();
    }
    private void prepareViewFrame(){
        this.viewFrame.setVisible(false);
        this.viewFrame.setSize(1000,1000);

        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.getContentPane().setLayout(new BorderLayout());
        viewFrame.getContentPane().setLayout(null);
        viewFrame.getContentPane().setBackground( color );

        viewFrame.revalidate();
        viewFrame.repaint();
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Customer Id", "Item Id", "Quantity", "Total Price"};

        OrdersBLL ordersBLL = new OrdersBLL();
        List<orders> orders = ordersBLL.getAllOrders();

        Object[][] data = new Object[orders.size()][5];
        for (int i = 0; i < orders.size(); i++) {
            orders o = orders.get(i);
            data[i][0] = o.isId();
            data[i][1] = o.isCustomer_id();
            data[i][2] = o.isItem_id();
            data[i][3] = o.isQuantity();
            data[i][4] = o.isTotal_price();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        ordersTable = new JTable(tableModel);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.setBounds(100, 100, 800, 500);
        viewFrame.add(scrollPane);
    }

}
