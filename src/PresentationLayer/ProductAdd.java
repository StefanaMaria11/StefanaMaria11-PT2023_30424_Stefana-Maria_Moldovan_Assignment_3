package PresentationLayer;

import BusinessLogic.ProductBLL;
import Model.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAdd {
    private JFrame addF = new JFrame("Add product");

    private JLabel nameL = new JLabel("Insert the name of the product:");
    private JLabel descriptionL = new JLabel("Insert a short description of the product: ");
    private JLabel priceL = new JLabel("Insert the price of the product:");
    private JLabel yearL = new JLabel("Insert the year of manufacture of the project:");
    private JLabel quantityL = new JLabel("Insert the quantity of the product:");

    private JTextField nameT = new JTextField();
    private JTextField descriptionT = new JTextField();
    private JTextField priceT = new JTextField();
    private JTextField yearT = new JTextField();
    private JTextField quantityT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enterAdd = new JButton("Enter");

    private Color color = new Color(245,222,179);
    /**
     * Constructs a ProductAdd object and initializes the user interface.
     */
    public ProductAdd(){
        this.addF.setVisible(true);
        this.addF.setSize(1400,1000);

        addF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addF.getContentPane().setLayout(new BorderLayout());
        addF.getContentPane().setLayout(null);
        addF.getContentPane().setBackground( color );

        nameL.setBounds(50, 100, 500,30);
        nameL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(nameL);

        descriptionL.setBounds(50, 140, 500,30);
        descriptionL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(descriptionL);

        priceL.setBounds(50, 180, 500,30);
        priceL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(priceL);

        yearL.setBounds(50, 220, 500,30);
        yearL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(yearL);

        quantityL.setBounds(50, 260, 500,30);
        quantityL.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addF.add(quantityL);

        nameT.setBounds(500,100, 500,30);
        descriptionT.setBounds(500,140, 500,30);
        priceT.setBounds(500,180, 500,30);
        yearT.setBounds(500,220, 500,30);
        quantityT.setBounds(500,260, 500,30);
        addF.add(nameT);
        addF.add(descriptionT);
        addF.add(priceT);
        addF.add(yearT);
        addF.add(quantityT);

        enterAdd.setBounds(500, 300, 300, 30);
        enterAdd.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enterAdd.setBackground(new Color(176,196,222));
        addF.add(enterAdd);
        enterAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameT.getText();
                String description = descriptionT.getText();
                String price = priceT.getText();
                int year = Integer.parseInt(yearT.getText());
                int quantity = Integer.parseInt(quantityT.getText());

                product product = new product();
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setYear_of_manufacture(year);
                product.setQuantity(quantity);

                ProductBLL productBLL = new ProductBLL();
                productBLL.insertProduct(product);

                nameT.setText("");
                descriptionT.setText("");
                priceT.setText("");
                yearT.setText("");
                quantityT.setText("");
            }

        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        addF.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addF.setVisible(false);
                ProductView productView = new ProductView();
            }
        });


        addF.revalidate();
        addF.repaint();
    }
}
