package dog;

import java.io.*;
import java.net.Socket;

/**
 * @author:nieyc
 * @company:panchan
 * @Date:created in 14:13 2018/6/28
 * @Description
 **/
public class DogServerHandler implements Runnable {
    private  Socket socket=null;
    public DogServerHandler(Socket socket){
        this.socket=socket;

    }

    @Override
    public void run() {
        String path=this.getClass().getResource("/").getPath();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
         /*   String s=null;
               while ((s = br.readLine()) != null && !s.equals("")){
                System.out.println(s);
            }*/
            String line = br.readLine();
            System.out.println("line: " + line);
            String resource = line.substring(line.indexOf('/')+1,line.lastIndexOf('/') - 5);
            System.out.println("the resource you request is: "+ resource);
           // Response.fileHandle("E:\\pic\\"+resource+"",socket);
            Response.fileHandle(path+"/template/"+resource+"",socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
