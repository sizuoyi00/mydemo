package cn.sy.demo.constant.role;

public class JwtConstant {
    /**
     * 5天(以秒m计)
     */
    public static final long EXPIRATION_TIME = 43200;

    /**
     * JWT redis失效时间
     */
    public static final long ACCESSTOKEN_TIMEOUT = 86400;

    /**
     * JWT密码
     */
    public static final String SECRET = "demoSecretS4i";

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 存放Token的Header Key
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * 获取token的uuid
     */
    public static final String uuid = "uuid";

}
