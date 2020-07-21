package com.jintang.web;

import com.jintang.Dir;
import com.jintang.GameModel;
import com.jintang.Group;
import com.jintang.Tank;

import java.io.*;
import java.util.UUID;

public class TankStopMsg extends Msg {

	public int x, y;
	public Dir dir;
	public boolean moving;
	public Group group;
	public UUID id;



	public TankStopMsg(Tank t) {
		this.x = t.getX();
		this.y = t.getY();
		this.dir = t.getDir();
		this.group = t.getGroup();
		this.id = t.getId();
		this.moving = t.isMoving();
	}

	public TankStopMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.moving = moving;
		this.group = group;
		this.id = id;
	}

	public TankStopMsg() {
	}

	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			//TODO:先读TYPE信息，根据TYPE信息处理不同的消息
			//略过消息类型
			//dis.readInt();
			
			this.x = dis.readInt();
			this.y = dis.readInt();
			this.dir = Dir.values()[dis.readInt()];
			this.moving = dis.readBoolean();
			this.group = Group.values()[dis.readInt()];
			this.id = new UUID(dis.readLong(), dis.readLong());
			//this.name = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null; 
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			
			//dos.writeInt(TYPE.ordinal());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.writeBoolean(moving);
			dos.writeInt(group.ordinal());
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			//dos.writeUTF(name);
			dos.flush();
			bytes = baos.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bytes;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName())
			   .append("[")
			   .append("uuid=" + id + " | ")
			   //.append("name=" + name + " | ")
			   .append("x=" + x + " | ")
			   .append("y=" + y + " | ")
			   .append("moving=" + moving + " | ")
			   .append("dir=" + dir + " | ")
			   .append("group=" + group + " | ")
			   .append("]");
		return builder.toString();
	}
	
	@Override
	public void handle() {
//		System.err.println(this.id.equals(GameModel.getInstance().getMyTank().getId())+"====="+GameModel.getInstance().findTankByUUID(this.id));
		Tank myTank = GameModel.getInstance().getMyTank();
		if(myTank!=null&&this.id.equals(myTank.getId())) return;
//		System.out.println(this);
//		Tank t = new Tank(this);
//		GameModel.getInstance().addTank(t);
		Tank tankByUUID = GameModel.getInstance().findTankByUUID(this.id);
		tankByUUID.setMoving(false);
		//send a new TankJoinMsg to the new joined tank
//		Client.INSTANCE.send(new TankStopMsg(GameModel.getInstance().getMyTank()));
	}

	@Override
	public MsgType getMsgType() {
		// TODO Auto-generated method stub
		return MsgType.TankStop;
	}


}
