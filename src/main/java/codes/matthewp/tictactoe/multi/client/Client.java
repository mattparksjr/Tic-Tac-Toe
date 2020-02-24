package codes.matthewp.tictactoe.multi.client;

import codes.matthewp.tictactoe.TicTacToe;
import codes.matthewp.tictactoe.controller.CrlJoinGame;
import codes.matthewp.tictactoe.multi.Side;
import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketReader;
import codes.matthewp.tictactoe.protocol.PacketType;
import codes.matthewp.tictactoe.protocol.impl.HandShakePacket;
import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.application.Platform;

import java.io.IOException;
import java.net.Socket;

public class Client extends Side {

    private CrlJoinGame screen;

    public Client(CrlJoinGame screen, Socket socket) {
        super(socket);
        this.screen = screen;
        TicTacToe.side = this;
        System.out.println("Starting the client!");

        // WE MUST SEND A HANDSHAKE PACKET FIRST
        write(new HandShakePacket().setVersion(TicTacToe.COMM_PRO_VER).toObject());

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
            handleHandshake(packet);
        }
    }

    private void handleHandshake(IPacket packet) {
        if (packet.getType() != PacketType.HANDSHAKE) {
            screen.infoText.setText(TicTacToe.lang.getString("errorServerHandshake"));
            setCanCommunicate(false);
        } else {
            HandShakePacket handShakePacket = (HandShakePacket) packet;
            if (handShakePacket.response.equals("good")) {
                setHandshakeDone(true);
                System.out.println("Handshake passed, opening in client");
                Platform.runLater(() -> {
                    GuiUtil.openNewWindow(screen.infoText.getScene().getWindow(),
                            "muti_player_sc");
                });
            } else {
                screen.infoText.setText(TicTacToe.lang.getString("errorVersionMismatch"));
                setCanCommunicate(false);
            }
        }
    }

    @Override
    public void disconnect() {
        setCanCommunicate(false);
        super.disconnect();
        Platform.runLater(() -> GuiUtil.openNewWindow(screen.infoText.getScene().getWindow(),
                "main_menu"));
        // TODO: Add a popup stating you were disconnected
    }
}
