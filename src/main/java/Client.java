import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintStream ps = null;
        BufferedReader brServer = null;

        // 连接服务器
        try(Socket socket = new Socket("192.168.31.141",8989)) {
            // 登录
            // 接收终端的输入流
            br = new BufferedReader(new InputStreamReader(System.in));
            // 发给客户端的输出流
            ps = new PrintStream(socket.getOutputStream());
            // 接收服务器端的输入流
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                // 接收终端输入信息
                String line = JOptionPane.showInputDialog("请输入用户名");

                // 拼接登录格式
                String loginStr = ChatProtocol.LOGIN_FLAG+line+ChatProtocol.LOGIN_FLAG;
                // 发送给服务器端
                ps.println(loginStr);
                // 接收服务器端返回的结果
                String result = brServer.readLine();

                // 判断登录结果
                if (result.equals(ChatProtocol.SUCCESS)){
                    System.out.println("登录成功");
                    break;
                }else {
                    System.out.println("用户名已存在 请重新输入");
                }
            }
            // 登录成功
            // 开启子线程处理服务器端的输入
            new ClientThread(socket).start();

            // 接收终端输入 发送给服务器
            String line;
            while ((line = br.readLine()) != null){
                // 发送给服务器
                ps.println(line);
            }
        } catch (IOException e) {

        }
    }
}

class ClientThread extends Thread{
    private Socket socket;

    public ClientThread(Socket socket){this.socket = socket;}

    @Override
    public void run() {
        BufferedReader br = null;
        // 接收服务器端的输入 发送给客户端
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("网络出错");
        }finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Client1{
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintStream ps = null;
        BufferedReader brServer = null;

        // 连接服务器
        try(Socket socket = new Socket("192.168.31.141",8989)) {
            // 登录
            // 接收终端的输入流
            br = new BufferedReader(new InputStreamReader(System.in));
            // 发给客户端的输出流
            ps = new PrintStream(socket.getOutputStream());
            // 接收服务器端的输入流
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                // 接收终端输入信息
                String line = JOptionPane.showInputDialog("请输入用户名");

                // 拼接登录格式
                String loginStr = ChatProtocol.LOGIN_FLAG+line+ChatProtocol.LOGIN_FLAG;
                // 发送给服务器端
                ps.println(loginStr);
                // 接收服务器端返回的结果
                String result = brServer.readLine();

                // 判断登录结果
                if (result.equals(ChatProtocol.SUCCESS)){
                    System.out.println("登录成功");
                    break;
                }else {
                    System.out.println("用户名已存在 请重新输入");
                }
            }
            // 登录成功
            // 开启子线程处理服务器端的输入
            new ClientThread(socket).start();

            // 接收终端输入 发送给服务器
            String line;
            while ((line = br.readLine()) != null){
                // 发送给服务器
                ps.println(line);
            }
        } catch (IOException e) {

        }
    }
}

class Client2 {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintStream ps = null;
        BufferedReader brServer = null;

        // 连接服务器
        try(Socket socket = new Socket("192.168.31.141",8989)) {
            // 登录
            // 接收终端的输入流
            br = new BufferedReader(new InputStreamReader(System.in));
            // 发给客户端的输出流
            ps = new PrintStream(socket.getOutputStream());
            // 接收服务器端的输入流
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                // 接收终端输入信息
                String line = JOptionPane.showInputDialog("请输入用户名");

                // 拼接登录格式
                String loginStr = ChatProtocol.LOGIN_FLAG+line+ChatProtocol.LOGIN_FLAG;
                // 发送给服务器端
                ps.println(loginStr);
                // 接收服务器端返回的结果
                String result = brServer.readLine();

                // 判断登录结果
                if (result.equals(ChatProtocol.SUCCESS)){
                    System.out.println("登录成功");
                    break;
                }else {
                    System.out.println("用户名已存在 请重新输入");
                }
            }
            // 登录成功
            // 开启子线程处理服务器端的输入
            new ClientThread(socket).start();

            // 接收终端输入 发送给服务器
            String line;
            while ((line = br.readLine()) != null){
                // 发送给服务器
                ps.println(line);
            }
        } catch (IOException e) {

        }
    }
}
















