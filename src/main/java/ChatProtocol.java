/**
 * 定义规范
 */
public interface ChatProtocol {
    // 登录
    String LOGIN_FLAG = "u+";
    // 私聊
    String PRIVATE_FLAG = "p+";
    // 群聊
    String PUBLIC_FLAG = "a+";
    // 分隔符
    String SPLIT_FLAG = "♥";
    // 成功的状态
    String SUCCESS = "1";
    String FAILURE = "-1";
}
