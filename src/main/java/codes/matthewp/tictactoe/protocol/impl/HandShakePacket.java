package codes.matthewp.tictactoe.protocol.impl;

import codes.matthewp.tictactoe.protocol.IPacket;
import codes.matthewp.tictactoe.protocol.PacketType;
import org.json.JSONObject;

public class HandShakePacket implements IPacket {

    public String version;
    public String response;

    @Override
    public PacketType getType() {
        return PacketType.HANDSHAKE;
    }

    @Override
    public void read(JSONObject obj) {
        if(obj.has("version")) {
            this.version = obj.getString("version");
        } else if(obj.has("response")) {
            this.response = obj.getString("response");
        }
    }

    public IPacket setResponse(String response) {
        this.response = response;
        return this;
    }

    public IPacket setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public IPacket create() {
        return this;
    }

    @Override
    public JSONObject toObject() {
        JSONObject object = new JSONObject();
        object.put("type", "handshake");
        if(response != null) {
            object.put("response", response);
        }
        if(version != null) {
            object.put("version", version);
        }
        return object;
    }
}
