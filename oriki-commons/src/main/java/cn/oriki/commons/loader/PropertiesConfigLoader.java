package cn.oriki.commons.loader;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Properties 映射加载
 * <p>
 * 可以进行多配置文件的加载，多配置加载下，如果出现同名 key ，后加载会覆盖前面加载的映射
 *
 * @author oriki.wang
 */
public class PropertiesConfigLoader {

    /**
     * 配置文件读取 Properties 对象
     */
    private Properties properties;

    /**
     * 键值对存储映射
     */
    private Map<String, String> parameters;

    public PropertiesConfigLoader(String... propertiesPaths) {
        // check
        {
            this.properties = new Properties();
            this.checkParameters();
        }
        Arrays.stream(propertiesPaths).forEach(this::load);
        // 加载完成后移除加载功能
        this.properties = null;
    }

    /**
     * 根据 key 获取 value ，如果不存在，返回 null
     *
     * @param key 键 key
     * @return 值 value ，如果不存在返回 null
     */
    public Object getProperty(String key) {
        return this.parameters.get(key);
    }

    /**
     * 根据 key 获取 value ，如果不存在，返回 defaultValue
     *
     * @param key          键 key
     * @param defaultValue 默认值
     * @return 如果可以通过 key 获取 value ，如果不存在，返回 defaultValue
     */
    public Object getProperty(String key, String defaultValue) {
        return this.parameters.getOrDefault(key, defaultValue);
    }

    /**
     * 根据 key 获取 value ，直接转换 boolean 类型。如果获取不到，返回 null
     *
     * @param key 键 key
     * @return 获取 key 转换为 boolean 类型，成功返回 boolean，失败或未获取到返回 null
     */
    public Boolean getBoolean(String key) {
        Boolean b = null;
        Object s = getProperty(key);
        if (Objects.nonNull(s)) {
            if (Boolean.TRUE.toString().equals(s)) {
                b = Boolean.TRUE;
            } else if (Boolean.FALSE.toString().equals(s)) {
                b = Boolean.FALSE;
            }
        }
        return b;
    }

    /**
     * 根据 key 获取 value ，转换成 boolean 类型。如果获取不到，返回 defaultBoolean
     *
     * @param key            键 key
     * @param defaultBoolean 默认 boolean 类型
     * @return 获取并转换成功，返回 boolean 类型数据，没有则返回 defaultBoolean
     */
    public boolean getBoolean(String key, boolean defaultBoolean) {
        Boolean b = this.getBoolean(key);
        return Objects.nonNull(b) ? b : defaultBoolean;
    }

    private void load(String propertiesPath) {
        // 使用 JDK 7 特性
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesPath)) {
            this.properties.load(inputStream);

            this.properties.forEach((key1, value1) -> {
                String key = (String) key1;
                String value = (String) value1;
                this.parameters.put(key, value);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.properties.clear();
        }
    }

    private void checkParameters() {
        if (Objects.isNull(this.parameters)) {
            this.parameters = Maps.newHashMap();
        }
    }

}
