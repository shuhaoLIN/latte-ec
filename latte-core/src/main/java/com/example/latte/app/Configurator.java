package com.example.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

public class Configurator {
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();//回收非常及时
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false); // fasle表示配置开始了，但是还没有完成
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //使用静态内部类进行单例模式的初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //当初始化结束了，那么需要配置完成true
    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true); // 表示初始化完成
    }

    /**
     * 配置api_host
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        //已经被初始化了,里面已经有字体了
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            //第一个已经被初始化了，所以下面就是从第一个开始
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 加入自己的图标
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 这个函数使用于判断配置是否完成，如果没有完成就抛出异常
     * 尽量设置不可变量，避免错误更改；并且编译器会进行相应的优化
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, please call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
