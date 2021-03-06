package com.jintang.web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

import static com.jintang.web.MsgType.*;

public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        if(readableBytes<8)return;
        byteBuf.markReaderIndex();
        int i = byteBuf.readInt();
        MsgType msgType = MsgType.values()[i];
        int readInt = byteBuf.readInt();
        if(readableBytes<readInt){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes=new byte[readInt];
        byteBuf.readBytes(bytes);
        switch (msgType) {
            case TankJoin:
                TankJoinMsg tankJoinMsg = new TankJoinMsg();
                tankJoinMsg.parse(bytes);
                list.add(tankJoinMsg);
                break;
            case TankDirChanged:
                break;
            case TankStop:
                TankStopMsg tankstopMsg = new TankStopMsg();
                tankstopMsg.parse(bytes);
                list.add(tankstopMsg);
                break;
            case TankStartMoving:
                TankStartMovingMsg tankStartMovingMsg = new TankStartMovingMsg();
                tankStartMovingMsg.parse(bytes);
                list.add(tankStartMovingMsg);
                break;
            case BulletNew:
                TankBulletNewMsg tankBulletNewMsg = new TankBulletNewMsg();
                tankBulletNewMsg.parse(bytes);
                list.add(tankBulletNewMsg);
                break;
            case TankDie:
                TankDieMsg tankDieMsg = new TankDieMsg();
                tankDieMsg.parse(bytes);
                list.add(tankDieMsg);
                break;
        }
    }
}
