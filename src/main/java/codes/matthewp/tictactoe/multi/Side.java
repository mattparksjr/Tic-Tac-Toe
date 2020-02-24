package codes.matthewp.tictactoe.multi;

import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketType;
import codes.matthewp.tictactoe.protocol.impl.DisconnectPacket;
import com.sun.org.apache.regexp.internal.RE;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * Note that each side will be in a separate thread, you will need to use Platform.runLater()
 * to access UI elements.
 */
public abstract class Side {

    // Communication socket
    private Socket socket;

    // Have we completed the handshake?
    private boolean doneHandShake = false;

    // Should we still be communicating?
    private boolean doCommunication = true;

    // Data Streams
    private DataInputStream in;
    private DataOutputStream out;

    public Side(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        write(new DisconnectPacket().toObject());
        die();
    }

    public void write(JSONObject object) {
        try {
            out.writeUTF(object.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO: Needs to be handled other then printing
        }
    }

    public boolean canCommunicate() {
        return doCommunication;
    }

    public void setCanCommunicate(boolean bool) {
        this.doCommunication = bool;
    }

    public boolean handshakeDone() {
        return doneHandShake;
    }

    public void setHandshakeDone(boolean bool) {
        this.doneHandShake = bool;
    }


    public void die() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error when disconnecting...");
            ex.printStackTrace();
        }
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void handlePacket(IPacket packet) {
        if (packet.getType() == PacketType.DISCONNECT) {
            disconnect();
        }
    }
}
