package dong.socket;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author dong
 * @Title: Client
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/7/24下午 4:15
 */
public class Client {

    public static void main(String[] args){
        try {
            while (true){
                Socket socket = new Socket("localhost",8070);
                BufferedReader consolrIn = new BufferedReader(new InputStreamReader(System.in));
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter printWriter = new PrintWriter(writer);
                System.out.println("请输入要传递的信息");
                printWriter.print(consolrIn.readLine());
                printWriter.flush();
                socket.shutdownOutput();

                InputStreamReader read = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(read);
                String readStr;
                System.out.println("接收到的信息");
                while ((readStr=reader.readLine())!=null){
                    System.out.println(readStr);
                }
                socket.shutdownInput();
                reader.close();
                printWriter.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
