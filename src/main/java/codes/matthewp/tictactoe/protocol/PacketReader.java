package codes.matthewp.tictactoe.protocol;

import codes.matthewp.tictactoe.protocol.impl.DisconnectPacket;
import codes.matthewp.tictactoe.protocol.impl.ErrorPacket;
import codes.matthewp.tictactoe.protocol.impl.HandShakePacket;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PacketReader {

    private static Map<String, IPacket> packetType;

    static {
        packetType = new HashMap<>();
        packetType.put("error", new ErrorPacket());
        packetType.put("handshake", new HandShakePacket());
        packetType.put("disconnect", new DisconnectPacket());
    }

    /**
     * Read a string as a packet
     * @param jsonString JSON formatted string
     */
    public static IPacket readPacket(String jsonString) {
        return readPacket(new JSONObject(jsonString));
    }

    /**
     * Read a JSONObject as a packet
     * @param jsonObject JSONObject to read
     */
    public static IPacket readPacket(JSONObject jsonObject) {
        IPacket packet = packetType.get(jsonObject.getString("type"));
        packet.read(jsonObject);
        return packet;
    }
}
