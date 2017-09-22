package com.zpj.socket.pingpang.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.zpj.writeToFile.FileTool;

/***
 * @author Perkins Zhu
 * @date 2017年4月13日 上午8:30:53
 */
public class Server {

	public static void main(String[] args) {
		MessageCollection mc = new MessageCollection();
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		acceptor.setHandler(new ServerHandler(mc));

		acceptor.getSessionConfig().setReadBufferSize(1024);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			acceptor.bind(new InetSocketAddress(12345));
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(new ReadBuffer(mc));
	}
}
class ReadBuffer implements Runnable{
	MessageCollection mc ;
	StringBuffer buffer;
	FileTool fileTool;
	public ReadBuffer(MessageCollection mc) {
		this.mc = mc;
		 fileTool = new FileTool();
		 fileTool.createFile("receiveMessage.txt");
	}

	@Override
	public void run() {
		buffer = mc.getReadBuffer();
		System.out.println(buffer.toString());
		buffer.delete(0, buffer.length());
	}
	
}
class MessageCollection {
	private StringBuffer bufferOne = new StringBuffer();
	private StringBuffer bufferTwo = new StringBuffer();
	private boolean canWriteToOne = true;
	private boolean isWrittingToOne = true;
	private boolean isWrittingToTwo = true;

	public StringBuffer getWriteBuffer() {
		return canWriteToOne ? getBufferOneForWrite() : getBufferTwoForWrite();
	}

	private StringBuffer getBufferTwoForWrite() {
		synchronized (this) {
			isWrittingToTwo = true;
			isWrittingToOne = false;
		}
		return bufferTwo;
	}

	private StringBuffer getBufferOneForWrite() {
		synchronized (this) {
			isWrittingToOne = true;
			isWrittingToTwo = false;
		}
		return bufferOne;
	}

	public StringBuffer getReadBuffer() {
		canWriteToOne = !canWriteToOne;//写入哪个buffer由此处控制
		return canWriteToOne ? getBufferTwoForRead() : getBufferOneForRead();
	}

	private StringBuffer getBufferTwoForRead() {
		while(true){//等待此次写结束之后再进行读操作
			if(!isWrittingToTwo){
				return bufferTwo;
			}
		}
	}
	private StringBuffer getBufferOneForRead() {
		while(true){//等待此次写结束之后再进行读操作
			if(!isWrittingToOne){
				return bufferOne;
			}
		}
	}

}
