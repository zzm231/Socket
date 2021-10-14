import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理用户登录的信息
 */
public class UserManager {
    // 保存用户信息
    private Map<String, Socket> users = new HashMap<>();

    /**
     * 判断用户是否已经登录
     */
    public synchronized boolean isLogined(String name){
        // 遍历数组
        for (String key: users.keySet()){
            if (key.equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * 保存当前登录的用户信息
     */
    public synchronized void save(String name,Socket socket){
        users.put(name,socket);
    }

    /**
     * 通过用户名找到对应的Socket对象
     */
    public synchronized Socket socketByName(String name){
        return users.get(name);
    }

    /**
     * 通过socket对象找到对应的名称
     */
    public synchronized String nameBySocket(Socket socket){
        for (String key:users.keySet()){
            if (socket == users.get(key)){
                return key;
            }
        }
        return null;
    }

    /**
     * 获取所有人的socket对象
     */
    public synchronized Collection<Socket> allUsers(){
        return users.values();
    }
}
















