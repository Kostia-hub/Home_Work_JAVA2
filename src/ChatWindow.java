import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatWindow extends JFrame {
    public ChatWindow() {
        setTitle("Чатик");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 200, 400, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp1 = new JPanel(new BorderLayout());

        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        jp1.add(jsp);
        add(jp1, BorderLayout.CENTER);

        JPanel jp2 = new JPanel(new GridLayout(3, 1));

        JLabel jlab = new JLabel("Введите сообщение:");
        jp2.add(jlab);

        JTextField field = new JTextField();
        jp2.add(field);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = getTime() + " Вы: " + field.getText() + "\n";
                System.out.print(str);
                jta.append(str);
                field.setText("");
            }
        });

        JButton button = new JButton("Отправить");
        jp2.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = getTime() + " Вы: " + field.getText() + "\n";
                System.out.print(str);
                jta.append(str);
                field.setText("");
            }
        });
        add(jp2, BorderLayout.SOUTH);

        setVisible(true);
    }
    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}