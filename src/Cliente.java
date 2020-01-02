import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends JFrame implements ActionListener {

    Container container;
    JLabel label;
    JTextArea textArea;
    JTextField textField, nick, ip;
    JButton button;
    JPanel pSuperior, pCentro, pInferior;
    InetAddress inetAddress;

    {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Cliente() {
        initGUI();
    }

    private void initGUI() {
        instancias();
        configurarContainer();
        acciones();
        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo();
        this.setLocation(200, 100);
        this.setVisible(true);
        //this.pack();
    }

    private void acciones() {
        button.addActionListener(this);
    }

    private void configurarContainer() {
        container.setLayout(new BorderLayout());
        container.add(configurarSuperior(), BorderLayout.NORTH);
        container.add(configurarCentro(), BorderLayout.CENTER);
        container.add(configurarInferior(), BorderLayout.SOUTH);
    }

    private JPanel configurarSuperior() {
        pSuperior.add(nick);
        pSuperior.add(label);
        pSuperior.add(ip);

        return pSuperior;
    }

    private JPanel configurarInferior() {
        pInferior.add(button);

        return pInferior;
    }

    private JPanel configurarCentro() {
        pCentro.setLayout(new GridBagLayout());
        configurarGridBag(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 0.90,
                GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10),
                textArea);
        configurarGridBag(0, 2, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 0.10,
                GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10),
                textField);

        return pCentro;
    }

    private void instancias() {
        container = this.getContentPane();
        label = new JLabel("CLIENTE");
        textField = new JTextField();
        nick = new JTextField(10);
        ip = new JTextField(inetAddress.toString(), 10);
        textArea = new JTextArea();
        button = new JButton("Enviar datos");
        pCentro = new JPanel();
        pSuperior = new JPanel();
        pInferior = new JPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ServerSocket s = new ServerSocket(0);
                Socket socket = new Socket(inetAddress, 9999);

                Mensaje mensaje = new Mensaje(nick.getText(), textField.getText(), ip.getText());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(mensaje);
                objectOutputStream.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void configurarGridBag(int pX, int pY, int tX, int tY,
                                   double peX, double peY, int anc,
                                   int fill, Insets ins, JComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = pX;
        constraints.gridy = pY;
        constraints.gridwidth = tX;
        constraints.gridheight = tY;
        constraints.weightx = peX;
        constraints.weighty = peY;
        constraints.fill = fill;
        constraints.anchor = anc;
        constraints.insets = ins;
        pCentro.add(component, constraints);
    }
}
