package com.lion.constant;

/**
 * @description:
 * @author: Mr.Liu
 * @create: 2020-02-17 20:02
 */
public class DubboConstant {

    /**
     * 失败自动切换 (当出现失败，重试其它服务器，通常用于读操作（推荐使用）。 重试会带来更长延迟。)
     */
    public static final String CLUSTER_FAILOVER = "failover";

    /**
     * 快速失败 （只发起一次调用，失败立即报错,通常用于非幂等性的写操作。 如果有机器正在重启，可能会出现调用失败 。）
     */
    public static final String CLUSTER_FAILFAST = "failfast";

    /**
     * 失败安全 （出现异常时，直接忽略，通常用于写入审计日志等操作。 调用信息丢失 可用于生产环境 Monitor。）
     */
    public static final String CLUSTER_FAILSAFE = "failsafe";

    /**
     * 失败自动恢复 （后台记录失败请求，定时重发。通常用于消息通知操作 不可靠，重启丢失。 可用于生产环境 Registry。）
     */
    public static final String CLUSTER_FAILBACK = "failback";

    /**
     * 并行调用多个服务器 （只要一个成功即返回，通常用于实时性要求较高的读操作。 需要浪费更多服务资源   。）
     */
    public static final String CLUSTER_FORKING = "forking";

    /**
     * 广播调用 （所有提供逐个调用，任意一台报错则报错。通常用于更新提供方本地状态 速度慢，任意一台报错则报错）
     */
    public static final String CLUSTER_BROADCAST = "broadcast";

    /**
     * 重试次数
     */
    public static final int RETRIES = 3;

    /**
     *
     */
    public static final String USERNAME = "username";
}