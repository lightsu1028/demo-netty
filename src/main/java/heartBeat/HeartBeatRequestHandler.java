package heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Baikang Lu
 * @date 2018/11/14
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<String> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        ctx.writeAndFlush(s);
    }
}
