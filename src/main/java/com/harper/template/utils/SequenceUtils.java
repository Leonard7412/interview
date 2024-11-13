package com.harper.template.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.utils
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-04  15:43
 * @Description: TODO
 * @Version: 1.0
 */
public class SequenceUtils {
    // 18位随机码（唯一id）
    public static synchronized String generateUniqueId() {
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis();
        // 生成一个5位的随机数
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        // 格式化字符串
        return String.format("%013d%d", timestamp, randomNum);
    }
}
