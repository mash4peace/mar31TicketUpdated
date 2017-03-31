package Mohamed.company;

import sun.plugin2.message.JavaObjectOpMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;

import static java.lang.Integer.*;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by tq1499vg on 3/29/2017.
 */
public class Ticketmgr extends JFrame {
    private JPanel rootPanel;
    private JButton addNewTicketButton;
    private JTextField descriptiontextField1;
    private JTextField reportedtextField2;
    private JTextField severitytextField3;
    private JList<Ticket> openTicketlist1;
    private JList<Ticket> resolvedTicketsList;
    private JButton saveAndQuitButton;
    private JButton resolveSelectedTicketButton;
    private JLabel resolvedTicketLists;
    private JRadioButton reloadPreviousTicketRadioButton;
    private JButton reloadPreviousTicketButton;
    private JButton searchATicketButton;
    private JList allTickets;
    private JList tickets;

    private DefaultListModel<Ticket> ticketlistModel;
    private static  DefaultListModel<Ticket> resolvedListModel;

    static LinkedList<Ticket> ticketQue = new LinkedList<>();



    Ticketmgr() {
        setTitle("Ticket Manager Program ");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ticketlistModel = new DefaultListModel<>();
        openTicketlist1.setModel(ticketlistModel);
        resolvedListModel = new DefaultListModel<>();
        resolvedTicketsList.setModel(resolvedListModel);


        openTicketlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addNewTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = descriptiontextField1.getText();
                String rep = reportedtextField2.getText();
                if (desc.length() == 0) {
                    showMessageDialog(Ticketmgr.this, "Please enter problem Descrioption:  ");
                    return;
                }
                if (rep.length() == 0) {
                    showMessageDialog(Ticketmgr.this, "Please enter a reporter  ");
                    return;
                }
                int priority;
                try {
                    priority = parseInt(severitytextField3.getText());
                    if (priority < 0) {
                        showMessageDialog(Ticketmgr.this, "Please enter apositive number ");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    showMessageDialog(Ticketmgr.this, "Enter a number for severity ");
                    return;
                }

                Date date = new Date();
                Ticket tm = new Ticket(desc, priority, rep, date);
                ticketlistModel.addElement(tm);
                ticketQue.add(tm);

                descriptiontextField1.setText("");
                reportedtextField2.setText("");
                severitytextField3.setText("");


            }
        });

        resolveSelectedTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(openTicketlist1.isSelectionEmpty()){
                    showMessageDialog(Ticketmgr.this, "Lists are empty !!!");
                }
                else{
                    Ticket t = openTicketlist1.getSelectedValue();
                    String resolution = JOptionPane.showInputDialog(" Please enter a resolution : ");

                    Date date = new Date();
                    String problem = t.getDescription();

                    String rep = t.getReporter();
                    int p = t.getPriority();


                    ResolvedTicket rt = new ResolvedTicket(problem, p, rep, date, resolution);

                /*
                Before I remove the item , I want to add it resolve ticket lists
                 */
                    resolvedTicketsList.setModel(resolvedListModel);
                    resolvedListModel.addElement(rt);
                    ticketQue.add(rt);
                    ticketlistModel.removeElement(t);

                }

            }
        });
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ticket t : ticketQue) {

                    try {
                        BufferedWriter bf = new BufferedWriter(new FileWriter("close_tickets.txt", true));
                        bf.append(String.valueOf(t) + "\n");


                        bf.close();



// Prints Input and output error to help me tracking the error
                    } catch (IOException ee) {
                        System.out.println("There is an error in reading the file ");
                        ee.printStackTrace();
                    }


                }

            }
        });


        reloadPreviousTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel listModel = new DefaultListModel();

                try {

                    BufferedReader reader = new BufferedReader(new FileReader("close_tickets.txt"));

                    String line = reader.readLine();
                    while (line != null) {
                        //System.out.println(tickets);
                        allTickets.setModel(listModel);
                        listModel.addElement(line);

                        line = reader.readLine();


                    }
                    reader.close();
                } catch (IOException io) {
                    System.out.println("File reading input error!!");
                }

            }
        });
        searchATicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] number = new int[ticketQue.size()];
                int p;
                p = parseInt((JOptionPane.showInputDialog(
                        Ticketmgr.this, "Enter a number: ")));
                DefaultListModel listModel = new DefaultListModel();

                try {

                    BufferedReader reader = new BufferedReader(new FileReader("close_tickets.txt"));

                    String line = reader.readLine();
                    while (line != null) {
                        //Here is my problem. I wanted to know if my input equals to 6, a number in the file.
                        // If it's confusing please print out numbers_in_line to get numbers 6.
                        for(Ticket t : ticketQue){
                            if(t.ticketID == p) {
                                System.out.println((t.getTicketID()));
                            }
                        }


                       //System.out.println(number_In_line);

                       // allTickets.setModel(listModel);
                       // listModel.addElement(line);








                        line = reader.readLine();


                    }
                    reader.close();
                } catch (IOException io) {
                    System.out.println("File reading input error!!");
                }





                    }










        });

    }
}

