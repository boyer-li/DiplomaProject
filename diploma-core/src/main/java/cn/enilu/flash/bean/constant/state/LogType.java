package cn.enilu.flash.bean.constant.state;

/**
 * 日志类型
 *
 * @author fengshuonan
 * @Date 2017年1月22日 下午12:14:59
 */
public enum LogType {

    LOGIN("Login Log"),
    LOGIN_FAIL("Login Failure Log"),
    EXIT("Exit Log"),
    EXCEPTION("Exception Log"),
    BUSSINESS("Business Log");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
