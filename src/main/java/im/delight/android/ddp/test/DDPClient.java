package im.delight.android.ddp.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import play.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by chengiant on 16-1-26.
 */
public class DDPClient implements MeteorCallback {
    private String server;
    private int port;
    private Meteor mMeteor;
    private String subscriptionId = null;
    public ConcurrentLinkedQueue<String> queue;
    private int maxQue = 10000;
    private boolean allowReconnect = true;
    private  Logger.ALogger logger = Logger.of(DDPClient.class);
    private static int countDisConnect = 0;//记录连接断开的次数
    public DDPClient() {
        queue = new ConcurrentLinkedQueue<String>();
    }
    public void setMaxQue(int m) {
        this.maxQue = m;
    }
    public void connect(String server,int port) {
        this.server = server;
        this.port = port;
        mMeteor = new Meteor("ws://"+server+":"+String.valueOf(port)+"/test/socket");
        mMeteor.setLoggingEnabled(true);
        mMeteor.setCallback(this);
    }
    public String fetch() {
        return queue.poll();
    }
    public void sendMessage(String message) {
        Object[] methodArgs = new Object[1];
        methodArgs[0] = "hhhhhhhhhh";
        mMeteor.call("sendWxMessage", methodArgs);

    }

    @Override
    public void onConnect(final boolean signedInAutomatically) {
        if (subscriptionId !=null) {
            mMeteor.unsubscribe(subscriptionId);
        }
        subscriptionId = mMeteor.subscribe("wxMessages");
        logger.info(this.server + ":" + this.port + "  connected!!");
    }

    @Override
    public void onDisconnect() {
        countDisConnect ++;
        logger.info("socket disconnected! countDisConnect:"+countDisConnect);
        if (allowReconnect) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Try to reconnect --->");
            mMeteor.reconnect();
        }
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        //System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: "+fieldsJson);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        logger.info(collectionName+"data changed :"+ updatedValuesJson);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        logger.info("data removed :"+ documentID);
    }

    @Override
    public void onException(Exception e) {
        logger.error(e.getMessage());
    }
    public void disconnect() {
        allowReconnect = false;
        mMeteor.disconnect();
        logger.info("close connection!!");
    }
}
