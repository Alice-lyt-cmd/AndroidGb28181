package com.alice.androidgb28181.other;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tencent.mmkv.MMKV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2/5/21.
 *
 * @author Des：
 * MMKV 是基于 mmap 内存映射的 key-value 组件，底层序列化/反序列化使用 protobuf 实现，性能高，稳定性强。
 * 从 2015 年中至今在微信上使用，其性能和稳定性经过了时间的验证。
 * 近期也已移植到 Android / macOS / Win32 / POSIX 平台，一并开源。
 * https://github.com/Tencent/MMKV/wiki/android_tutorial_cn
 */
public class MmkvHelper {
    private static boolean isInit = false;
    private static MMKV mmkv;

    private MmkvHelper() {
        if (isInit) {
            mmkv = MMKV.defaultMMKV();
        }
    }

    public static MmkvHelper getInstance() {
        return MmkvHolder.INSTANCE;
    }

    private static class MmkvHolder {
        private static final MmkvHelper INSTANCE = new MmkvHelper();
    }

    public static void initMmkv(Context context) {
        isInit = true;
        MMKV.initialize(context.getApplicationContext());
    }

    public MMKV getMmkv() {
        return mmkv;
    }

    /**
     * 存入map集合
     *
     * @param key 标识
     * @param map 数据集合
     */
    public void saveInfo(String key, Map<String, Object> map) {
        Gson gson = new Gson();
        JSONArray mJsonArray = new JSONArray();
        JSONObject object = null;
        try {
            object = new JSONObject(gson.toJson(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonArray.put(object);
        if (isReady()) {
            mmkv.encode(key, mJsonArray.toString());
        }
    }

    /**
     * 获取map集合
     *
     * @param key 标识
     */
    public Map<String, String> getInfo(String key) {
        Map<String, String> itemMap = new HashMap<>();
        if (!isReady()) {
            return itemMap;
        }
        String result = mmkv.decodeString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);

                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
            }
        } catch (JSONException e) {

        }
        return itemMap;
    }

    /**
     * 扩展MMKV类使其支持对象存储
     */

    public <T> T getObjectClass(String key, Class<T> t) {
        if (!isReady()) {
            return null;
        }
        String str = mmkv.decodeString(key, null);
        if (!TextUtils.isEmpty(str)) {
            return new GsonBuilder().create().fromJson(str, t);
        } else {
            return null;
        }
    }

    public void putObject(String key, Object object) {
        String jsonString = new GsonBuilder().create().toJson(object);
        if (isReady()) {
            mmkv.encode(key, jsonString);
        }
    }

    public Object getObject(@NonNull String key, Object defaultValue) {
        if (TextUtils.isEmpty(key) || !isReady()) {
            return defaultValue;
        }
        if (defaultValue instanceof String) {
            return mmkv.decodeString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return mmkv.decodeInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return mmkv.decodeBool(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return mmkv.decodeFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Double) {
            return mmkv.decodeDouble(key, (Double) defaultValue);
        } else if (defaultValue instanceof Long) {
            return mmkv.decodeLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Byte) {
            return mmkv.decodeBytes(key, (byte[]) defaultValue);
        }
        return defaultValue;
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return isReady() ? mmkv.decodeString(key, defaultValue) : defaultValue;
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return isReady() ? mmkv.decodeInt(key, defaultValue) : defaultValue;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return isReady() ? mmkv.decodeBool(key, defaultValue) : defaultValue;
    }

    public Float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public Float getFloat(String key, float defaultValue) {
        return isReady() ? mmkv.decodeFloat(key, defaultValue) : defaultValue;
    }

    public Double getDouble(String key) {
        return getDouble(key, 0);
    }

    public Double getDouble(String key, double defaultValue) {
        return isReady() ? mmkv.decodeDouble(key, defaultValue) : defaultValue;
    }

    public Long getLong(String key) {
        return getLong(key, 0);
    }

    public Long getLong(String key, long defaultValue) {
        return isReady() ? mmkv.decodeLong(key, defaultValue) : defaultValue;
    }

    public byte[] getByte(String key) {
        return getByte(key, null);
    }

    public byte[] getByte(String key, byte[] defaultValue) {
        return isReady() ? mmkv.decodeBytes(key, defaultValue) : defaultValue;
    }

    /**
     * 是否可以用
     */
    private boolean isReady() {
        if (isInit && mmkv != null) {
            return true;
        }

        return false;
    }
}
