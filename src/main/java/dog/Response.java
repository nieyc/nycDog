package dog;

import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * @author:nieyc
 * @company:panchan
 * @Date:created in 15:25 2018/6/29
 * @Description
 **/
public class Response {
    /**
     @Author:nieyc
     @Description: 读取本地文件返回客户端
     @Date:14:35 2018/6/29
     **/
    public static void fileHandle(String path,Socket socket) {
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            try {
                //根据Socket获取输出流对象，将访问的资源数据写入到输出流中
                PrintStream writer = new PrintStream(socket.getOutputStream());
                writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
                writer.println("Content-Type: text/html; charset=UTF-8\n\n");//返回文本格式
                //writer.println("Content-Length:" + file.length());// 返回内容字节数
                writer.println();// 根据 HTTP 协议, 空行将结束头信息

                FileInputStream fis = new FileInputStream(file);
                byte[] buf = new byte[fis.available()];
                fis.read(buf);
                writer.write(buf);
                writer.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     @Author:nieyc
     @Description:模拟一个简单的输出
     @Date:14:35 2018/6/29
     **/
    public static void response(Socket socket) throws IOException {
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("HTTP/1.1 200 OK\n");
        bw.write("Content-Type: text/html; charset=UTF-8\n\n");
        bw.write("<html>\n" +
                "<head>\n" +
                "    <title>first page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>404! page not found  </h1>\n" +
                "</body>\n" +
                "</html>\n");
        bw.flush();
        bw.close();
        socket.close();
    }
}
