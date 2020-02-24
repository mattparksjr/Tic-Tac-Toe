package codes.matthewp.tictactoe.protocol.impl;

import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketType;
import org.json.JSONObject;

public class ErrorPacket implements IPacket {

    private String message;

    @Override
    public PacketType getType() {
        return PacketType.ERROR;
    }

    @Override
    public void read(JSONObject obj) {
        message = obj.getString("text");
    }

    public IPacket setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public IPacket create() {
        return this;
    }

    @Override
    public JSONObject toObject() {
        JSONObject object = new JSONObject();
        object.put("type", "error");
        object.put("text", message);
        return object;
    }
}
