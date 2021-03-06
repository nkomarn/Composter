package xyz.nkomarn.composter.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.composter.protocol.Direction;
import xyz.nkomarn.composter.protocol.Protocol;
import xyz.nkomarn.composter.util.DataTypeUtils;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private final Protocol protocol;

    public PacketDecoder(@NotNull Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        var session = ctx.attr(Session.SESSION_KEY).get();
        int id = DataTypeUtils.readVarInt(buffer);
        var packet = session.getState().getPacketById(Direction.C2S, id);

        // TODO Bi packet support

        if (packet == null) {
            // System.out.println(" -> Null packet to handle.");
            return;
        }

        out.add(packet.decode(buffer));
    }
}
