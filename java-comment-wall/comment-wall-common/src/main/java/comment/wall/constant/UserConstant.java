package comment.wall.constant;

/**
 * USER_NOT_EXIST(10001, "用户不存在"),
 * 	USER_EXIST(10002, "用户已存在"),
 * 	USER_PASSWORD_ERROR(10003, "用户密码错误"),
 * 	USER_NAME_ERROR(10004, "用户名错误"),
 * 	USER_NOT_LOGIN(10005, "用户未登录"),
 * 	USER_NOT_EXIST_OR_PASSWORD_ERROR(10006, "用户不存在或密码错误"),
 */
public class UserConstant {
	public static final String USER_NOT_EXIST = "用户不存在";
	public static final String USER_EXIST = "用户已存在";
	public static final String USER_PASSWORD_ERROR = "用户密码错误";
	public static final String USER_NAME_ERROR = "用户名错误";
	public static final String USER_NOT_LOGIN = "用户未登录";
	public static final String USER_NOT_EXIST_OR_PASSWORD_ERROR = "用户不存在或密码错误";
	public static final String USER_NOT_EXIST_OR_PASSWORD_ERROR_OR_NOT_LOGIN = "用户不存在或密码错误或未登录";
	public static final String USER_NOT_EXIST_OR_PASSWORD_ERROR_OR_NOT_LOGIN_OR_NOT_VERIFY = "用户不存在或密码错误或未登录或未验证";
	public static final String USER_NOT_EXIST_OR_PASSWORD_ERROR_OR_NOT_LOGIN_OR_NOT_VERIFY_OR_NOT_ACTIVE = "用户不存在或密码错误或未登录或未验证或未激活";
	public static final String USER_NOT_EXIST_OR_PASSWORD_ERROR_OR_NOT_LOGIN_OR_NOT_VERIFY_OR_NOT_ACTIVE_OR_NOT_ENABLE = "用户不存在或密码错误或未登录或未验证或未激活或未启用";
}