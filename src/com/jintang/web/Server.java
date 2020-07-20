package com.jintang.web;

import com.jintang.GameModel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public Server() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup wokers = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            ChannelFuture future = serverBootstrap.group(boss, wokers).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer()).bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
//            channel=socketChannel;
            ChannelPipeline p = socketChannel.pipeline();
            p.addLast(new MsgEncoder());
            p.addLast(new MsgDecoder());
            p.addLast(new ServerHander());

        }

    }
    class ServerHander extends SimpleChannelInboundHandler<Msg> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
//            MsgType msgType = msg.getMsgType();
//            msg.handle();
//            channelHandlerContext.writeAndFlush(msg);
            System.err.println(msg);
            clients.writeAndFlush(msg);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            clients.add(ctx.channel());
        }


    }

    public static void main(String[] args) {
        new Server();
    }
}
