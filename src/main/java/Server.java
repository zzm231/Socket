import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class Server {
    // 用于保存每一个用户对应的姓名和socket
    public static UserManager manager = new UserManager();

    public static void main(String[] args) {
        // 创建ServerSocket
        try (ServerSocket ss = new ServerSocket(8989)){
            // 监听所有来连接的客户端
            Socket socket = ss.accept();

            //让子线程来处理这个socket
            new ServerThread(socket).start();
        }catch (IOException e){

        }
    }
}

/**
 * 子线程处理每个客户端的输入和输出
 */
class ServerThread extends Thread{
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;

    public ServerThread(Socket socket){this.socket = socket;}

    @Override
    public void run() {
        try {
            // 得到对应的输入流对象
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 得到对应的输出流对象
            ps = new PrintStream(socket.getOutputStream());

            String line;
            while ((line = br.readLine()) != null){
                // 登录 u+ .... u+
                if ((line.startsWith(ChatProtocol.LOGIN_FLAG) && line.endsWith(ChatProtocol.LOGIN_FLAG))){
                    // 获取名字
                    String name = line.substring(2,line.length() - 2);
                    System.out.println(name);
                    // 判断这个用户是否已经登录过了
                    if (Server.manager.isLogined(name)){
                        // 登录过了
                        // 发送给客户端
                        ps.println(ChatProtocol.FAILURE);
                    }else {
                        // 没有登录
                        // 保存当前登录的用户信息
                        Server.manager.save(name,socket);
                        // 发送给客户端
                        ps.println(ChatProtocol.SUCCESS);
                    }
                }
                // 判断是不是私聊
                else if (line.startsWith(ChatProtocol.PRIVATE_FLAG) && (line.startsWith(ChatProtocol.PRIVATE_FLAG))){
                    // p+jack♥hellop+
                    // 获取信息
                    String msg = line.substring(2,line.length()- 2);
                    // 分割 jack hello
                    String[] items = msg.split(ChatProtocol.SPLIT_FLAG);
                    // 用户名
                    String name = items[0];
                    // 聊天内容
                    String message = items[1];
                    // 通过用户找到对应的Socket
                    Socket desSocket = Server.manager.socketByName(name);
                    PrintStream desPs = new PrintStream(desSocket.getOutputStream());

                    // 获取当前用户名称
                    String currentName = Server.manager.nameBySocket(socket);

                    // 发送私聊信息
                    desPs.println(currentName+"向你发来私聊"+message);
                }else {
                    // 群聊
                    // 处理数据
                    String msg = line.substring(2,line.length() - 2);

                    // 获取当前用户名称
                    String currentName = Server.manager.nameBySocket(socket);

                    // 遍历所有用户信息
                    Collection<Socket> sockets = Server.manager.allUsers();
                    for (Socket s : sockets){
                        PrintStream tempPs = new PrintStream(socket.getOutputStream());
                        tempPs.println(currentName+"发来群聊"+msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



















