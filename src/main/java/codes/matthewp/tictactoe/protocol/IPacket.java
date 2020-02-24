package codes.matthewp.tictactoe.protocol;

import org.json.JSONObject;

public interface IPacket {

    PacketType getType();

    void read(JSONObject obj);

    IPacket create();

    JSONObject toObject();
}
