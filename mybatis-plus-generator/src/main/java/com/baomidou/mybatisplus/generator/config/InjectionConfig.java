/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.generator.config;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 抽象的对外接口
 *
 * @author hubin
 * @since 2016-12-07
 */
public class InjectionConfig {

    /**
     * 输出文件之前消费者
     */
    private BiConsumer<TableInfo, Map<String, Object>> beforeOutputFileBiConsumer;

    /**
     * 自定义配置 Map 对象
     */
    private Map<String, Object> map = new HashMap<>();

    @NotNull
    public void beforeOutputFile(TableInfo tableInfo, Map<String, Object> objectMap) {
        if (!map.isEmpty()) {
            objectMap.putAll(map);
        }
        if (null != beforeOutputFileBiConsumer) {
            beforeOutputFileBiConsumer.accept(tableInfo, objectMap);
        }
    }

    /**
     * 构建者
     */
    public static class Builder implements IConfigBuilder<InjectionConfig> {

        private final InjectionConfig injectionConfig;

        public Builder() {
            this.injectionConfig = new InjectionConfig();
        }

        /**
         * 输出文件之前消费者
         *
         * @param biConsumer 消费者
         * @return this
         */
        public Builder beforeOutputFile(@NotNull BiConsumer<TableInfo, Map<String, Object>> biConsumer) {
            this.injectionConfig.beforeOutputFileBiConsumer = biConsumer;
            return this;
        }

        /**
         * 自定义配置 Map 对象
         *
         * @param map Map 对象
         * @return this
         */
        public Builder map(@NotNull Map<String, Object> map) {
            this.injectionConfig.map = map;
            return this;
        }

        @Override
        public InjectionConfig build() {
            return this.injectionConfig;
        }
    }
}
