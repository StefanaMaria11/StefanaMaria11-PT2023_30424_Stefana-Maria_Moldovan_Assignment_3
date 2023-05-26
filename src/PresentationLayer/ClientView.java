package PresentationLayer;

import BusinessLogic.ClientBLL;
import Model.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * The ClientView class represents the graphical user interface for managing clients.
 * It provides functionality for adding, editing, deleting, and viewing clients.
 */
public class ClientView {

    private JFrame clientFrame = new JFrame("Client");
    private JFrame viewFrame = new JFrame("ViewAll");

    private JLabel clientL = new JLabel("Client");

    private JButton add = new JButton("Add new client");
    private JButton edit = new JButton("Edit client");
    private JButton delete = new JButton("Delete client");
    private JButton viewAll = new JButton("View all clients");
    private JButton back = new JButton("back");


    private JTable clientTable;
    private DefaultTableModel tableModel;
    private Color color = new Color(245,222,179);
    /**
     * Constructs a new instance of the ClientView class.
     * Initializes the graphical user interface components and sets up event listeners.
     */
    public ClientView(){
        this.clientFrame.setVisible(true);
        this.clientFrame.setSize(1000,1000);

        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.getContentPane().setLayout(new BorderLayout());
        clientFrame.getContentPane().setLayout(null);
        clientFrame.getContentPane().setBackground( color );

        clientL.setBounds(450,20,500,30);
        clientL.setFont(new Font ("Century Gothic", Font.BOLD,25));
        clientFrame.add(clientL);

        add.setBounds(270,100,500,30);
        add.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        add.setBackground(new Color(188,143,143));
        clientFrame.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
                ClientAdd clientAdd = new ClientAdd();
            }
        });

        edit.setBounds(270, 150, 500, 30);
        edit.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        edit.setBackground(new Color(188,143,143));
        clientFrame.add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
                ClientEdit clientEdit = new ClientEdit();
            }
        });

        delete.setBounds(270, 200, 500, 30);
        delete.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        delete.setBackground(new Color(188,143,143));
        clientFrame.add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
               ClientDelete clientDelete = new ClientDelete();
            }
        });

        viewAll.setBounds(270, 250, 500, 30);
        viewAll.setFont(new Font ("Century Gothic", Font.PLAIN,20));
        viewAll.setBackground(new Color(188,143,143));
        clientFrame.add(viewAll);
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
                prepareViewFrame();
                populateTable();
                viewFrame.setVisible(true);
            }
        });

        back.setBounds(0,0, 80,30);
        back.setBackground(new Color(176,196,222));
        clientFrame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
                View view = new View();
            }
        });

        clientFrame.revalidate();
        clientFrame.repaint();
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
        String[] columnNames = {"ID", "Name", "Email", "Phone Number", "Address"};

        ClientBLL clientBLL = new ClientBLL();
        List<client> clients = clientBLL.getAllClients();

        Object[][] data = new Object[clients.size()][5];
        for (int i = 0; i < clients.size(); i++) {
            client c = clients.get(i);
            data[i][0] = c.isId();
            data[i][1] = c.isName();
            data[i][2] = c.isEmail();
            data[i][3] = c.isPhone_number();
            data[i][4] = c.isAddress();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        clientTable = new JTable(tableModel);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBounds(100, 100, 800, 500);
        viewFrame.add(scrollPane);
    }

}
