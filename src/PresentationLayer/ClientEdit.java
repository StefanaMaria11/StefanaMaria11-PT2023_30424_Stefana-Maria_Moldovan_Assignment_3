package PresentationLayer;

import BusinessLogic.ClientBLL;
import Model.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * The ClientDelete class represents the user interface for deleting a client.
 */
public class ClientEdit {
    private JFrame editFrame = new JFrame("Edit Client");

    private JLabel editL = new JLabel("Choose the client that you want to edit");
    private JLabel nameE = new JLabel("Edit name");
    private JLabel emailE = new JLabel("Edit email");
    private JLabel phoneE = new JLabel("Edit phone number");
    private JLabel addressE = new JLabel("Edit address");

    private JTextField editT = new JTextField();
    private JTextField nameT = new JTextField();
    private JTextField emailT = new JTextField();
    private JTextField phoneT = new JTextField();
    private JTextField addressT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enter = new JButton("enter");

    private Color color = new Color(245,222,179);
    /**
     * Constructs a new instance of the ClientDelete class.
     */
    public ClientEdit(){
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

        emailE.setBounds(100, 140, 500, 30);
        emailE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(emailE);

        phoneE.setBounds(100, 180, 500, 30);
        phoneE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(phoneE);

        addressE.setBounds(100, 220, 500, 30);
        addressE.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        editFrame.add(addressE);

        editT.setBounds(600, 20, 200, 30);
        nameT.setBounds(500, 100, 300, 30);
        emailT.setBounds(500,140,300,30);
        phoneT.setBounds(500, 180, 300, 30);
        addressT.setBounds(500, 220, 300, 30);
        editFrame.add(editT);
        editFrame.add(nameT);
        editFrame.add(emailT);
        editFrame.add(phoneT);
        editFrame.add(addressT);

        enter.setBounds(500, 260, 300, 30);
        enter.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enter.setBackground(new Color(176,196,222));
        editFrame.add(enter);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String edit = editT.getText();
                String name = nameT.getText();
                String email = emailT.getText();
                String phone = phoneT.getText();
                String address = addressT.getText();

                ClientBLL clientBLL = new ClientBLL();
                clientBLL.findClientByName(edit);

                client client= clientBLL.findClientByName(edit);

                if (client != null) {

                    if (!name.isEmpty()) {
                        client.setName(name);
                    }
                    if (!email.isEmpty()) {
                        client.setEmail(email);
                    }
                    if (!phone.isEmpty()) {
                        client.setPhone_number(phone);
                    }
                    if (!address.isEmpty()) {
                        client.setAddress(address);
                    }

                    // Update the client using the ClientBLL
                    clientBLL.updateClientByName(client);
                }


                editT.setText("");
                nameT.setText("");
                emailT.setText("");
                phoneT.setText("");
                addressT.setText("");
            }

        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        editFrame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrame.setVisible(false);
                ClientView clientView = new ClientView();
            }
        });

        editFrame.revalidate();
        editFrame.repaint();
    }
}
