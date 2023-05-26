package PresentationLayer;

import BusinessLogic.ClientBLL;
import Model.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The ClientDelete class represents the user interface for deleting a client.
 */
public class ClientDelete {
    private JFrame deleteF = new JFrame("Delete Client");

    private JLabel deleteL = new JLabel("Enter the client that you want to delete:");

    private JTextField deleteT = new JTextField();

    private JButton back = new JButton("back");
    private JButton enter = new JButton("Enter");

    private Color color = new Color(245,222,179);
    /**
     * Constructs a new instance of the ClientDelete class.
     */
    public ClientDelete(){
        this.deleteF.setVisible(true);
        this.deleteF.setSize(1000,1000);

        deleteF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteF.getContentPane().setLayout(new BorderLayout());
        deleteF.getContentPane().setLayout(null);
        deleteF.getContentPane().setBackground( color );

        deleteL.setBounds(200, 200, 500, 30);
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
                String delete = deleteT.getText();

                client client = new client();
                ClientBLL clientBLL = new ClientBLL();
                client = clientBLL.findClientByName(delete);

                if(client != null){

                    clientBLL.deleteClientByName(client);
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
                ClientView clientView = new ClientView();
            }
        });

        deleteF.revalidate();
        deleteF.repaint();
    }
}
