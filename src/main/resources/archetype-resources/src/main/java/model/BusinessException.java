package ${groupId}.model;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg){
        super(msg);
    }
}
