package Mohamed.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
    private JList<String> resolvedTicketsList;
    private JButton saveAndQuitButton;
    private JButton resolveSelectedTicketButton;
    private JLabel resolvedTicketLists;

    private DefaultListModel<Ticket> ticketlistModel;


    Ticketmgr(){
        setTitle("Ticketmgr Program ");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ticketlistModel = new DefaultListModel<>();
        openTicketlist1.setModel(ticketlistModel);

        openTicketlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addNewTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = descriptiontextField1.getText();
                String rep = reportedtextField2.getText();
                if(desc.length() == 0){
                    JOptionPane.showMessageDialog(Ticketmgr.this, "Please enter problem Descrioption:  ");
                    return;
                }
                if(rep.length()== 0){
                    JOptionPane.showMessageDialog(Ticketmgr.this, "Please enter a reporter  ");
                    return;
                }
                int priority;
                try{
                    priority = Integer.parseInt(severitytextField3.getText());
                    if(priority < 0){
                        JOptionPane.showMessageDialog(Ticketmgr.this, "Please enter apositive number ");
                        return;
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(Ticketmgr.this, "Enter a number for severity ");
                    return;
                }

                Date date = new Date();
                Ticket tm = new Ticket( desc, priority, rep, date);
                ticketlistModel.addElement(tm);


            }
        });
    }

}
