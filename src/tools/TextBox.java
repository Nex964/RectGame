package tools;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tools.network.HttpCallBack;
import world.LevelDesigner;

public class TextBox
        extends JFrame {
    public TextBox(final LevelDesigner ld, int type) {
        super();
        String title = null;
        switch (type){
            case 0:
                title = "Add Command";
                break;
            case 1:
                title = "Level Name";
                break;
        }
        setTitle(title);
        JLabel label = new JLabel(title);
        final JTextField editText = new JTextField(10);
        JButton button = new JButton("Submit");
        setLayout(new FlowLayout(1));
        add(label);
        add(editText);
        add(button);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        if (type == 0) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    ld.event_cmd = editText.getText();
                    ld.addToEventBlockList();
                    System.out.println("Event block added");
                }
            });
        } else {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String level = FileHandler.read("World3.txt");
                    APICalls.insertLevel(new HttpCallBack() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailed() {

                        }

                        @Override
                        public void onError() {

                        }
                    }, editText.getText(), level);
                }
            });
        }
    }
}
