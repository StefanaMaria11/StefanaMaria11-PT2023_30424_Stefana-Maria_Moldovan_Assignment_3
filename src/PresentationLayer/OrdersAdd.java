package PresentationLayer;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrdersBLL;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.client;
import Model.orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents the GUI for adding orders.
 */
public class OrdersAdd {
    private JFrame addF = new JFrame("Add product");

    private JLabel clientL = new JLabel("Insert the name of the client:");
    private JLabel productL = new JLabel("Insert the name of the product:");
    private JLabel quantityL = new JLabel("Insert the quantity of the product:");
    private JLabel priceL = new JLabel("Insert the total price of the order:");

    private JTextField clientT = new JTextField();
    private JTextField productT = new JTextField();
    private JTextField quantityT = new JTextField();
    private JTextField priceT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enterAdd = new JButton("Enter");

    private Color color = new Color(245,222,179);
    /**
     * Initializes and sets up the GUI for adding orders.
     */
    public OrdersAdd(){
        this.addF.setVisible(true);
        this.addF.setSize(1400,1000);

        addF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addF.getContentPane().setLayout(new BorderLayout());
        addF.getContentPane().setLayout(null);
        addF.getContentPane().setBackground( color );

        clientL.setBounds(50, 100, 500,30);
        clientL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(clientL);

        productL.setBounds(50, 140, 500,30);
        productL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(productL);

        quantityL.setBounds(50, 180, 500,30);
        quantityL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(quantityL);

        priceL.setBounds(50, 220, 500,30);
        priceL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(priceL);

        clientT.setBounds(500,100, 500,30);
        productT.setBounds(500,140, 500,30);
        quantityT.setBounds(500,180, 500,30);
        priceT.setBounds(500,220, 500,30);
        addF.add(clientT);
        addF.add(productT);
        addF.add(quantityT);
        addF.add(priceT);


        enterAdd.setBounds(500, 300, 300, 30);
        enterAdd.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enterAdd.setBackground(new Color(176,196,222));
        addF.add(enterAdd);
        enterAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String client = clientT.getText();
                String products = productT.getText();
                int quantity = Integer.parseInt(quantityT.getText());
                String price = priceT.getText();

                ClientDAO c=new ClientDAO();
                ProductDAO p=new ProductDAO();
                orders orders = new orders();

                orders.setCustomer_id(c.getCustomerIdByName(client));
                orders.setItem_id(p.getProductIdByName(products));

                orders.setQuantity(quantity);
                orders.setTotal_price(price);

                OrdersBLL ordersBLL = new OrdersBLL();
                ordersBLL.insertOrders(orders);

                clientT.setText("");
                productT.setText("");
                quantityT.setText("");
                priceT.setText("");
            }
        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        addF.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addF.setVisible(false);
                OrdersView ordersView = new OrdersView();
            }
        });

        addF.revalidate();
        addF.repaint();
    }
}
