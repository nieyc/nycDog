package dog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author:nieyc
 * @company:panchan
 * @Date:created in 14:08 2018/6/28
 * @Description
 **/
public class GogServer {

    private  static  int serverPort=8088;
    public  static int count=0;
    public static void main(String[] args) {
        GogServer dog=new GogServer();
        dog.sart();
    }

    public  void sart(){
        try {
            ServerSocket ss=new ServerSocket(serverPort);
            Socket socket= ss.accept();
            while (socket!=null){
                System.out.println("接受一个来自客户端的http 请求");
                DogServerHandler dog=new DogServerHandler(socket);
                new Thread(dog).start();
                socket= ss.accept();
                count++;
                System.out.println("count:"+count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
