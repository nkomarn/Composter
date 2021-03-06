package xyz.nkomarn.composter.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import xyz.nkomarn.composter.protocol.Packet;

import java.util.List;

public class PacketEncoder extends MessageToMessageEncoder<Packet<?>> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> out) throws Exception {
        out.add(Unpooled.wrappedBuffer(Unpooled.buffer(1).writeByte(packet.getId()), packet.encode()));
    }
}
