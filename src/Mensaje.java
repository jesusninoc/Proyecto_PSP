import java.io.Serializable;

public class Mensaje implements Serializable {

    private String nick, texto, ip;

    public Mensaje(String nick, String texto, String ip) {
        this.nick = nick;
        this.texto = texto;
        this.ip = ip;
    }

    public Mensaje() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String mensaje) {
        this.texto = mensaje;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
