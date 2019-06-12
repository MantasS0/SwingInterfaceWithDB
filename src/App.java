import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class App {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JButton saveChangesButton;
    private Object item;
    private User selectedUser;

    /* Kodas vykdomas kai mano forma parodoma */
    public App() {
        System.out.println("labas as konstruktorius");
        populateComboBox1();

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    setItem(event.getItem());
                    for (User user : User.users) {
                        if (user.getEmail().equals(getItem().toString())) {
                            setSelectedUser(user);
                            System.out.println("Selected the user " + user.getName());
                            nameTextField.setText(user.getName());
                            emailTextField.setText(user.getEmail());

                        }
                    }
                    nameTextField.setText(getSelectedUser().getName());
                    emailTextField.setText(getSelectedUser().getEmail());
                }

            }
        });


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedUser.setName(nameTextField.getText());
                selectedUser.setEmail(emailTextField.getText());
                selectedUser.saveData();
                populateComboBox1();
            }
        });
    }

    private void populateComboBox1() {
        if (comboBox1.getItemCount() > 0) {
            comboBox1.removeAllItems();
        }
        for (int i = 0; i < User.getUsersCount(); i++) {
            comboBox1.addItem(User.users[i].getEmail());
        }

    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public JPanel getPanel1() {
        return panel1;
    }


}
