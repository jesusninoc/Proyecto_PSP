import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Cliente c = new Cliente();
                Servidor s = new Servidor();
            }
        });
    }
}
