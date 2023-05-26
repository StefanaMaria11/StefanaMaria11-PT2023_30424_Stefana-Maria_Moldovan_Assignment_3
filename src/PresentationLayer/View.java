package PresentationLayer;

import BusinessLogic.ClientBLL;
import Model.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The main view class for the Orders Management application.
 * This class represents the graphical user interface of the application.
 */
public class View extends JFrame{
    private JFrame frame=new JFrame("Orders Management");
    private JLabel label1=new JLabel("Welcome!");
    private JLabel label2 = new JLabel("What do you want to do today?");
    private JButton clientB = new JButton("client");
    private JButton ordersB = new JButton("orders");
    private JButton productB = new JButton("products");
    //private JButton back = new JButton("back");
    private Color color = new Color(245,222,179);


    public View() {
        this.frame.setVisible(true);
        this.frame.setSize(700,500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground( color );

        label1.setBounds(260,20,500,30);
        label1.setFont(new Font ("Century Gothic", Font.BOLD,25));
        frame.add(label1);

        label2.setBounds(157, 80, 500,30);
        label2.setFont(new Font ("Century Gothic", Font.BOLD,25));
        frame.add(label2);

        clientB.setBounds(150, 150, 160, 30);
        clientB.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        clientB.setBackground(new Color(188,143,143));
        frame.add(clientB);
        clientB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientView clientView = new ClientView();
            }
        });

        ordersB.setBounds(360, 150, 160, 30);
        ordersB.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        ordersB.setBackground(new Color(188,143,143));
        frame.add(ordersB);
        ordersB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                OrdersView ordersView = new OrdersView();
            }
        });


        productB.setBounds(255, 200, 160, 30);
        productB.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        productB.setBackground(new Color(188,143,143));
        frame.add(productB);
        productB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ProductView productView = new ProductView();
            }
        });


        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}
