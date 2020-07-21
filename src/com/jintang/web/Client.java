package com.jintang.web;

import com.jintang.GameModel;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Client {
    public static final Client INSTANCE = new Client();
    private SocketChannel channel;
    private Client() {
        System.err.println("init....");
    }
 public void connect(){
     NioEventLoopGroup g = new NioEventLoopGroup(2);
     Bootstrap bootstrap = new Bootstrap();
     try {
         ChannelFuture future = bootstrap.group(g).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
                 .connect("localhost", 8888).sync();
         future.channel().closeFuture().sync();
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }
    public void send(Msg msg) {
        if(channel!=null)
            channel.writeAndFlush(msg);

    }

    class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            channel=socketChannel;
            ChannelPipeline p = socketChannel.pipeline();
            p.addLast(new MsgEncoder());
            p.addLast(new MsgDecoder());
            p.addLast(new ClientHander());

        }

    }
    class ClientHander extends SimpleChannelInboundHandler<Msg>{

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
//            MsgType msgType = msg.getMsgType();
            msg.handle();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(new TankJoinMsg(GameModel.getInstance().getMyTank()));
        }


    }
}
