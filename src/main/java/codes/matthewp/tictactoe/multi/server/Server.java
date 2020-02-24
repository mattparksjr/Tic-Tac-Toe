package codes.matthewp.tictactoe.multi.server;

import codes.matthewp.tictactoe.TicTacToe;
import codes.matthewp.tictactoe.controller.CrlHostGame;
import codes.matthewp.tictactoe.multi.Side;
import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketReader;
import codes.matthewp.tictactoe.protocol.PacketType;
import codes.matthewp.tictactoe.protocol.impl.ErrorPacket;
import codes.matthewp.tictactoe.protocol.impl.HandShakePacket;
import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

public class Server extends Side {

    private CrlHostGame game;

    public Server(CrlHostGame game, Socket socket) {
        super(socket);
        this.game = game;
        TicTacToe.side = this;

        while (canCommunicate()) {
            String input;
            try {
                input = getIn().readUTF();
                handlePacket(PacketReader.readPacket(input));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        disconnect();
    }

    @Override
    public void handlePacket(IPacket packet) {
        super.handlePacket(packet);
        if (! handshakeDone()) {
            processHandshake(packet);
        }
    }

    private void processHandshake(IPacket packet) {
        if (packet.getType() != PacketType.HANDSHAKE) {
            setCanCommunicate(false);
            write(new ErrorPacket().
                    setMessage("You tried to send a non-handshake com before sending handshake").
                    create().toObject());
        } else {
            setHandshakeDone(true);
            HandShakePacket handShakePacket = (HandShakePacket) packet;
            if (! handShakePacket.version.equals(TicTacToe.COMM_PRO_VER)) {
                write(new HandShakePacket().setResponse("bad").toObject());
                setCanCommunicate(false);
            } else {
                write(new HandShakePacket().setResponse("good").toObject());
                System.out.println("Handshake passed, opening in server");
                Platform.runLater(() -> {
                    GuiUtil.openNewWindow(game.statusText.getScene().getWindow(),
                            "muti_player_sc");
                });
            }
        }
    }

    @Override
    public void disconnect() {
        setCanCommunicate(false);
        super.disconnect();
        Platform.runLater(() -> GuiUtil.openNewWindow(game.statusText.getScene().getWindow(),
                "main_menu"));
        // TODO: Add a popup stating you were disconnected
    }
}
