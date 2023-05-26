package PresentationLayer;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.client;
import Model.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The ProductEdit class represents a graphical user interface for editing product information.
 * It allows the user to modify the name, description, price, year of manufacture, and quantity of a product.
 */
public class ProductEdit {
    private JFrame editFrame = new JFrame("Edit Product");

    private JLabel editL = new JLabel("Choose the product that you want to edit");
    private JLabel nameE = new JLabel("Edit name");
    private JLabel descriptionE = new JLabel("Edit description");
    private JLabel priceE = new JLabel("Edit price");
    private JLabel yearE = new JLabel("Edit year");
    private JLabel quantityE = new JLabel("Edit quantity");

    private JTextField editT = new JTextField();
    private JTextField nameT = new JTextField();
    private JTextField descriptionT = new JTextField();
    private JTextField priceT = new JTextField();
    private JTextField yearT = new JTextField();
    private JTextField quantityT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enter = new JButton("enter");

    private Color color = new Color(245,222,179);

    public ProductEdit(){
        this.editFrame.setVisible(true);
        this.editFrame.setSize(1000,1000);

        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.getContentPane().setLayout(new BorderLayout());
        editFrame.getContentPane().setLayout(null);
        editFrame.getContentPane().setBackground( color );

        editL.setBounds(200,20,500,30);
        editL.setFont(new Font ("Century Gothic", Font.BOLD,20));
        editFrame.add(editL);

        nameE.setBounds(100, 100, 500, 30);
        nameE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(nameE);

        descriptionE.setBounds(100, 140, 500, 30);
        descriptionE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(descriptionE);

        priceE.setBounds(100, 180, 500, 30);
        priceE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(priceE);

        yearE.setBounds(100, 220, 500, 30);
        yearE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(yearE);

        quantityE.setBounds(100, 260, 500,30);
        quantityE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(quantityE);

        editT.setBounds(600, 20, 200, 30);
        nameT.setBounds(500, 100, 300, 30);
        descriptionT.setBounds(500,140,300,30);
        priceT.setBounds(500, 180, 300, 30);
        yearT.setBounds(500, 220, 300, 30);
        quantityT.setBounds(500, 260, 300, 30);
        editFrame.add(editT);
        editFrame.add(nameT);
        editFrame.add(descriptionT);
        editFrame.add(priceT);
        editFrame.add(yearT);
        editFrame.add(quantityT);

        enter.setBounds(500, 300, 300, 30);
        enter.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enter.setBackground(new Color(176,196,222));
        editFrame.add(enter);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String edit = editT.getText();
                String name = nameT.getText();
                String description = descriptionT.getText();
                String price = priceT.getText();
                int year = Integer.parseInt(yearT.getText());
                int quantity = Integer.parseInt(quantityT.getText());

                ProductBLL productBLL = new ProductBLL();
                productBLL.findProductByName(edit);

                product product= productBLL.findProductByName(edit);

                if (product != null) {

                    if (!name.isEmpty()) {
                        product.setName(name);
                    }
                    if (!description.isEmpty()) {
                        product.setDescription(description);
                    }
                    if (!price.isEmpty()) {
                        product.setPrice(price);
                    }
                    if (year != 0) {
                        product.setYear_of_manufacture(year);
                    }
                    if (quantity != 0) {
                        product.setQuantity(quantity);
                    }
                    // Update the client using the ClientBLL
                    productBLL.updateProductByName(product);
                }


                editT.setText("");
                nameT.setText("");
                descriptionT.setText("");
                priceT.setText("");
                yearT.setText("");
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
                ProductView productView = new ProductView();
            }
        });

        editFrame.revalidate();
        editFrame.repaint();
    }
}
