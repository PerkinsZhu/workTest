package im.delight.android.ddp.test;

/**
 * Created by chengiant on 16-1-26.
 */
public class Test {
    public static void main(String[] args)  {
        testDdpClient();
    }

    private static void testDdpClient() {
        DDPClient ddpClient = new DDPClient();
        ddpClient.connect("localhost",9000);
        int times = 0;
        while (true) {
            times++;
            System.out.print("【sendMessage】times:"+times );
            ddpClient.sendMessage("hello world !");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }


}
