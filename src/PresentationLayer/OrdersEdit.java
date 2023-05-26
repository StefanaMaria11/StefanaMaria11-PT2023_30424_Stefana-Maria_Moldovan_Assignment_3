package PresentationLayer;

import BusinessLogic.OrdersBLL;
import BusinessLogic.ProductBLL;
import Model.orders;
import Model.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The OrdersEdit class represents the user interface for editing orders.
 */
public class OrdersEdit {
    private JFrame editFrame = new JFrame("Edit Order");

    private JLabel editL = new JLabel("Choose the order that you want to edit");
    private JLabel quantityE = new JLabel("Edit quantity");
    private JLabel priceE = new JLabel("Edit price");

    private JTextField editT = new JTextField();
    private JTextField quantityT = new JTextField();
    private JTextField priceT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enter = new JButton("enter");

    private Color color = new Color(245,222,179);
    /**
     * Constructs an OrdersEdit object and initializes the UI.
     */
    public OrdersEdit(){
        this.editFrame.setVisible(true);
        this.editFrame.setSize(1000,1000);

        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.getContentPane().setLayout(new BorderLayout());
        editFrame.getContentPane().setLayout(null);
        editFrame.getContentPane().setBackground( color );

        editL.setBounds(200,20,500,30);
        editL.setFont(new Font ("Century Gothic", Font.BOLD,20));
        editFrame.add(editL);

        quantityE.setBounds(100, 100, 500, 30);
        quantityE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(quantityE);

        priceE.setBounds(100, 140, 500, 30);
        priceE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(priceE);

        editT.setBounds(600, 20, 200, 30);
        quantityT.setBounds(500, 100, 300, 30);
        priceT.setBounds(500,140,300,30);
        editFrame.add(editT);
        editFrame.add(quantityT);
        editFrame.add(priceT);

        enter.setBounds(500, 300, 300, 30);
        enter.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enter.setBackground(new Color(176,196,222));
        editFrame.add(enter);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int edit = Integer.parseInt(editT.getText());
                int quantity = Integer.parseInt(quantityT.getText());
                String price = priceT.getText();

                OrdersBLL ordersBLL = new OrdersBLL();
                orders orders= ordersBLL.findOrderById(edit);
                orders o=new orders();
                o.setId(edit);
                o.setCustomer_id(orders.isCustomer_id());
                o.setItem_id(orders.isItem_id());
                o.setQuantity(quantity);
                o.setTotal_price(price);


                if (o != null) {

                    if (quantity != 0) {
                        orders.setQuantity(quantity);
                    }
                    if (!price.isEmpty()) {
                        orders.setTotal_price(price);
                    }

                    // Update the client using the ClientBLL
                    ordersBLL.updateOrdersByName(o);
                }


                editT.setText("");
                priceT.setText("");
                quantityT.setText("");
            }

        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        editFrame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrame.setVisible(false);
                OrdersView ordersView = new OrdersView();
            }
        });

        editFrame.revalidate();
        editFrame.repaint();
    }

}

