package com.harper.interview.config;

import cn.hutool.core.lang.Snowflake;
import io.shardingsphere.core.keygen.KeyGenerator;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.config
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-12  15:43
 * @Description: TODO
 * @Version: 1.0
 */
public class GenSnowflakeKey implements KeyGenerator {
    private Snowflake snowflake;

    public GenSnowflakeKey(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Number generateKey() {
        return snowflake.nextId();
    }
}
