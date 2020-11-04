package gateway.inbound;

import gateway.filter.HttpRquestFilterHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class ChildChannelHandler extends ChannelInitializer {

    private String backServer;

    public ChildChannelHandler(String backServer){
        this.backServer = backServer;
    }

    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new HttpRquestFilterHandler());
        pipeline.addLast(new HttpInboundHandler(backServer));
    }
}
