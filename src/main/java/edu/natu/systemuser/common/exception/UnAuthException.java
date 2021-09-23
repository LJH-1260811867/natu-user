package edu.natu.systemuser.common.exception;

/**
 * @author Ljiahai
 * @des 授权异常
 * @date 2021-09-23 09:12:27
 */
public class UnAuthException extends RuntimeException{

    public UnAuthException() {
        this("您未登录或登录超时");
    }

    public UnAuthException(String message) {
        super(message);
    }
}
