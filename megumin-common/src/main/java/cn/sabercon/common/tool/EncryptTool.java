package cn.sabercon.common.tool;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.Scanner;

/**
 * 运行加密方法的工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
class EncryptTool {

    public static void main(String[] args) {
        encrypt();
    }

    /**
     * 利用 jasypt 加密配置
     */
    private static void encrypt() {
        var scanner = new Scanner(System.in);
        System.out.println("请输入要加密的字符串:");
        var text = scanner.nextLine().trim();
        System.out.println("请输入密钥:");
        var password = scanner.nextLine().trim();
        System.out.printf("Encrypted message: %s", stringEncryptor(password).encrypt(text));
    }

    private static StringEncryptor stringEncryptor(String password) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
