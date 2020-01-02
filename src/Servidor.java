import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame implements ActionListener, Runnable {

    Container container;
    JLabel label;
    JTextArea textArea;
    JPanel pSuperior, pCentro;

    public Servidor() {
        initGUI();
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initGUI() {
        instancias();
        configurarContainer();
        acciones();
        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        this.setLocation(1200, 100);
        this.setVisible(true);
        //this.pack();
    }

    private void acciones() {
    }

    private void configurarContainer() {
        container.setLayout(new BorderLayout());
        container.add(configurarSuperior(), BorderLayout.NORTH);
        container.add(configurarCentro(), BorderLayout.CENTER);
    }

    private JPanel configurarSuperior() {
        pSuperior.add(label);

        return pSuperior;
    }

    private JPanel configurarCentro() {
        pCentro.setLayout(new GridLayout(1, 1));
        pCentro.add(textArea);

        return pCentro;
    }

    private void instancias() {
        container = this.getContentPane();
        label = new JLabel("SERVIDOR");
        textArea = new JTextArea();
        pCentro = new JPanel();
        pSuperior = new JPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            String nick,texto, ip;
            Mensaje mensaje = new Mensaje();


            while (true) {
                Socket socket = serverSocket.accept();

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                mensaje = (Mensaje) objectInputStream.readObject();
                nick = mensaje.getNick();
                texto = mensaje.getTexto();
                ip = mensaje.getIp();

                textArea.append("\n "+nick+": "+texto+" para "+ip);

                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
