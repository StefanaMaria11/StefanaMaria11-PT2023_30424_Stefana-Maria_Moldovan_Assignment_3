package PresentationLayer;

import BusinessLogic.ClientBLL;
import Model.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents the GUI form for adding a client.
 */
public class ClientAdd {
    private JFrame addFrame = new JFrame("Add");

    private JButton back = new JButton("back");
    private JButton enterAdd = new JButton("Enter");

    private JLabel cName = new JLabel("Insert the client's name:");
    private JLabel cEmail = new JLabel("Insert the client's email:");
    private JLabel cPhone = new JLabel("Insert the client's phone number:");
    private JLabel cAddress = new JLabel("Insert the client's address:");

    private JTextField tName = new JTextField();
    private JTextField tEmail = new JTextField();
    private JTextField tPhone = new JTextField();
    private JTextField tAddress = new JTextField();

    private Color color = new Color(245,222,179);
    /**
     * Constructs a new instance of the ClientAdd class.
     * Initializes the GUI components and sets up event listeners.
     */
    public ClientAdd(){
        this.addFrame.setVisible(true);
        this.addFrame.setSize(1000,1000);

        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.getContentPane().setLayout(new BorderLayout());
        addFrame.getContentPane().setLayout(null);
        addFrame.getContentPane().setBackground( color );

        cName.setBounds(100, 100, 500, 30);
        cName.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addFrame.add(cName);

        cEmail.setBounds(100, 140, 500, 30);
        cEmail.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addFrame.add(cEmail);

        cPhone.setBounds(100, 180, 500, 30);
        cPhone.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addFrame.add(cPhone);

        cAddress.setBounds(100, 220, 500, 30);
        cAddress.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        addFrame.add(cAddress);

        tName.setBounds(500, 100, 300, 30);
        tEmail.setBounds(500,140,300,30);
        tPhone.setBounds(500, 180, 300, 30);
        tAddress.setBounds(500, 220, 300, 30);
        addFrame.add(tName);
        addFrame.add(tEmail);
        addFrame.add(tPhone);
        addFrame.add(tAddress);

        enterAdd.setBounds(500, 260, 300, 30);
        enterAdd.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        enterAdd.setBackground(new Color(176,196,222));
        addFrame.add(enterAdd);
        enterAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tName.getText();
                String email = tEmail.getText();
                String phoneNumber = tPhone.getText();
                String address = tAddress.getText();

                client client = new client();
                client.setName(name);
                client.setEmail(email);
                client.setPhone_number(phoneNumber);
                client.setAddress(address);

                ClientBLL clientBLL = new ClientBLL();
                clientBLL.insertClient(client);

                tName.setText("");
                tEmail.setText("");
                tPhone.setText("");
                tAddress.setText("");
            }

        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        //clientFrame.add(back);
        addFrame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.setVisible(false);
                ClientView clientView = new ClientView();
            }
        });


        addFrame.revalidate();
        addFrame.repaint();
    }
}
