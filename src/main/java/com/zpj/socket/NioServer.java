package com.zpj.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/***
 @author  Perkins Zhu
 @date  2017年4月10日 下午4:04:23
 */
public class NioServer {
    private Selector selector;
    public NioServer init(int port) throws IOException{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        selector=Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }
    
    public void listen() throws IOException{
        System.out.println("服务器端启动成功");
        
        while(true){
            selector.select();
            System.out.println("-------------");
            Iterator<SelectionKey> ite=selector.selectedKeys().iterator();
            
            while(ite.hasNext()){
                System.out.println("=======");
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
                    channel.register(selector, SelectionKey.OP_READ);
                    
                    System.out.println("客户端请求连接事件");
                }else if(key.isReadable()){//有可读数据事件
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    int read = channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    
                    System.out.println("receive message from client, size:" + buffer.position() + " msg: " + message);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        new NioServer().init(12345).listen();
        System.out.println("end");
    }
}