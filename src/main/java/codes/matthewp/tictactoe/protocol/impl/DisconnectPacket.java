package codes.matthewp.tictactoe.protocol.impl;

import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketType;
import org.json.JSONObject;

public class DisconnectPacket implements IPacket {

    @Override
    public PacketType getType() {
        return PacketType.DISCONNECT;
    }

    @Override
    public void read(JSONObject obj) {
    }

    @Override
    public IPacket create() {
        return this;
    }

    @Override
    public JSONObject toObject() {
        JSONObject object = new JSONObject();
        object.put("type", "disconnect");
        return object;
    }
}
