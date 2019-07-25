package dong.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dong
 * @Title: Server
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/7/24下午 3:43
 */
public class Server {

    private ServerSocket serverSocket;

    public static ExecutorService threadPool =  Executors.newFixedThreadPool(2);

    public void init(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8070);
            BufferedReader consolrIn = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                Socket accept = serverSocket.accept();
                threadPool.execute(new AccetpThread(accept,consolrIn));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class AccetpThread implements Runnable{

        private Socket socket;
        private BufferedReader consolrIn;

        public AccetpThread(Socket socket,BufferedReader bufferedReader){
            this.socket = socket;
            this.consolrIn = bufferedReader;
        }

        @Override
        public void run() {
            PrintWriter printWriter = null;
            BufferedReader r = null;
            try{
                InputStreamReader read = new InputStreamReader(socket.getInputStream());
                r = new BufferedReader(read);
                String outInfo;
                while ((outInfo = r.readLine())!=null){
                    System.out.println("接收到的信息");
                    System.out.println(outInfo);
                }
                socket.shutdownInput();

                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                printWriter = new PrintWriter(writer);
                System.out.println("请输入要传递的信息");
                printWriter.print(consolrIn.readLine());
                printWriter.flush();
                socket.shutdownOutput();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    printWriter.close();
                    r.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
