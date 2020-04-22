/**
 * Java2. Homework 6
 * Created by СПБ on 22.04.2020.
 *
 * @author Fedorov Konstantin
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServer extends JFrame {
    private JTextField msgInputFieldServer;
    private static JTextArea chatAreaServer;

    private static DataInputStream inServer;
    private static DataOutputStream outServer;
    private Socket socket;

    public EchoServer() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EchoServer();
            }
        });


        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            chatAreaServer.append("(" + getTime() + ") " + "Клиент подключился" + "\n");

            System.out.println("Клиент подключился");
            inServer = new DataInputStream(socket.getInputStream());
            outServer = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String str = inServer.readUTF();
                chatAreaServer.append("(" + getTime() + ") " + "Клиент: " + str);
                chatAreaServer.append("\n");
                if (str.equals("/end")) {
                    break;
                }
                outServer.writeUTF("Клиент: " + str);
                System.out.println(str);  //печатает в консоли str
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (!msgInputFieldServer.getText().trim().isEmpty()) {
            try {
                outServer.writeUTF("Сервер: " + msgInputFieldServer.getText());
                chatAreaServer.append("(" + getTime() + ")" + " Сервер: " + msgInputFieldServer.getText() + "\n");
                msgInputFieldServer.setText("");
                msgInputFieldServer.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public void closeConnection() {
        try {
            inServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareGUI() {
        // Параметры окна
        setBounds(400, 300, 500, 500);
        setTitle("Сервер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Текстовое поле для вывода сообщений
        chatAreaServer = new JTextArea();
        chatAreaServer.setEditable(false);
        chatAreaServer.setLineWrap(true);
        add(new JScrollPane(chatAreaServer), BorderLayout.CENTER);

        // Нижняя панель с полем для ввода сообщений и кнопкой отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputFieldServer = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputFieldServer, BorderLayout.CENTER);
        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        msgInputFieldServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    public static String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}