package cn.tool.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 用户操作本地配置的工具类
 * 比如获取 计算机配置,ip,mac地址等 比如进行登陆限制等等
 */
public class LocalConfUtil {
    private static String confDir = "/data/conf";

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalConfUtil.class);
    /**本机ip**/
    private static String localIp = null;
    /** 本机所属环境 **/
    private static String localEnv = "online";
    static {
        try {
            //先读取k8s_evn配置
            String env = FileUtil.getFileContent(new File("/data/html", "tq_k8s_env"));
            //再读取通用环境配置
            if(StringUtils.isBlank(env)) {
                env = readConfFile("env");
            }
            if(StringUtils.isNotBlank(env)) {
                localEnv = env.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String ip = readConfFile("ip");
            if(StringUtils.isNotBlank(ip)) {
                localIp = ip.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(localIp)) {
            localIp = getMachineIp();
        }
    }

    /**
     * 获取本地ip
     * @return
     */
    public static String getLocalIp() {
        return localIp;
    }

    /**
     * 获取本地环境 是否登陆
     * @return
     */
    public static String getLocalEnv() {
        return localEnv;
    }

    /**
     * 获取本机ip
     * @return
     */
    private static String getMachineIp() {
        if (localIp != null && localIp.trim().length() > 0) {
            return localIp;
        }

        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements(); ) {
                NetworkInterface e = n.nextElement();

                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements(); ) {
                    InetAddress addr = a.nextElement();
                    if(addr.isSiteLocalAddress()) {
                        localIp = addr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            localIp = "127.0.0.1";
            LOGGER.error("获取本机ip地址失败，默认为127.0.0.1", e);
        }

        if (localIp == null || localIp.trim().length() == 0) {
            localIp = "127.0.0.1";
        }

        return localIp;
    }

    /**
     * 读取并返回配置文件中的内容
     * @param confFile
     * @return
     * @throws IOException
     */
    public static String readConfFile(String confFile) throws IOException {
        File file = new File(confDir, confFile);
        return FileUtil.getFileContent(file);
    }

    public static void main(String[] args) {

        System.out.println(getMachineIp());
        System.out.println(getLocalEnv()); // 当前的登陆状态
        System.out.println(getLocalIp());
    }
}
