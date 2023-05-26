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
 * The OrdersDelete class represents a graphical user interface for deleting orders.
 * It allows the user to enter the name of a product to be deleted.
 */
public class OrdersDelete {
    private JFrame deleteF = new JFrame("Delete Order");

    private JLabel deleteL = new JLabel("Enter the product's name that you want to delete:");

    private JTextField deleteT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enter = new JButton("Enter");

    private Color color = new Color(245,222,179);
    /**
     * Constructs an instance of the OrdersDelete class.
     * It initializes and sets up the graphical user interface for deleting orders.
     */
    public OrdersDelete(){
        this.deleteF.setVisible(true);
        this.deleteF.setSize(1000,1000);

        deleteF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteF.getContentPane().setLayout(new BorderLayout());
        deleteF.getContentPane().setLayout(null);
        deleteF.getContentPane().setBackground( color );

        deleteL.setBounds(110, 200, 500, 30);
        deleteL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        deleteF.add(deleteL);

        deleteT.setBounds(600, 200, 300, 30);
        deleteF.add(deleteT);

        enter.setBounds(400, 260, 300, 30);
        enter.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enter.setBackground(new Color(176,196,222));
        deleteF.add(enter);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(deleteT.getText());

                orders orders = new orders();
                OrdersBLL ordersBLL = new OrdersBLL();
                orders = ordersBLL.findOrderById(id);

                if(orders != null){

                    ordersBLL.deleteOrderByName(Integer.parseInt(deleteT.getText()));
                }

                deleteT.setText("");
            }
        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        deleteF.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteF.setVisible(false);
                OrdersView ordersView = new OrdersView();
            }
        });

        deleteF.revalidate();
        deleteF.repaint();
    }
}
