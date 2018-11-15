package heartBeat;



import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
    }

    /** 定期发送心跳包数据
    *@author Lu Baikan g
    *@Date  2018/11/14
    *@Param [ctx]
    *@return void
    */
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx){
        ctx.executor().schedule(() -> {
            if(ctx.channel().isActive()){
                //TODO 心跳包数据 考虑用proto buf传输
                ctx.channel().writeAndFlush("heartBeat");
                //模拟客户端超时发送心跳包
//                try {
//                    Thread.sleep(15*1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}