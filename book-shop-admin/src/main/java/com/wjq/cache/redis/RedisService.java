/*
 * Copyright 2016 qingfeng All right reserved. This software is the
 * confidential and proprietary information of qingfeng ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qingfeng .
 */
package com.wjq.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 类RedisService.java的实现描述：
 */
public interface RedisService {

    String addStringToJedis(String key, String value, int cacheSeconds);

    boolean addStringToJedisWithNoReply(String key, String value, int cacheSeconds);

    boolean addListToJedis(String key, List<String> list, int cacheSeconds, String methodName);

    boolean addToSetJedis(String key, String[] value, String methodName);

    boolean removeSetJedis(String key, String value, String methodName);

    boolean rpushDataToListJedis(String key, String data, int cacheSeconds);

    boolean lpushDataToListJedis(String key, String data, int cacheSeconds);

    String lpopDataFromJedis(String key);

    String rpopDataFromJedis(String key);

    boolean lpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, String methodName);

    boolean rpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, String methodName);

    boolean addHashMapToJedis(String key, Map<String, String> map, int cacheSeconds);

    boolean addHashMapToJedis(String key, String field, String value);

    boolean updateHashMapToJedis(String key, String incrementField, long incrementValue, String dateField,
                                 String dateValue);

    String getStringFromJedis(String key);

    List<String> getListFromJedis(String key, String methodName);

    Set<String> getSetFromJedis(String key, String methodName);

    Map<String, String> getHashMapFromJedis(String key, String methodName);

    String getHashMapValueFromJedis(String key, String field);

    Long getIdentifyId(String identifyName, String methodName);

    Long delKeyFromJedis(String key);

    long getLength(String key);

    /**
     * 判断指定key是否存在
     * @param key
     * @param methodName
     * @return
     */
    boolean existKey(String key, String methodName);

    /**
     * 删除指定key的Map中的指定fields
     * @param key
     * @param fields
     * @return
     */
    boolean hDelFromJedis(String key, String... fields);
    
    /**
     * 将value按score分数值添加到指定key的zset中
     * @param key
     * @param score
     * @param value
     * @param methodName
     * @return
     */
    boolean addToZSetJedis(String key, double score, String value, String methodName);

    /**
     * 将scoreMembers中的数据添加到指定key的zset中
     * @param key
     * @param scoreMembers
     * @param methodName
     * @return
     */
    boolean addToZSetJedis(String key, Map<String, Double> scoreMembers, String methodName);

    /**
     * 获取指定key的zset中score分数值在min~max间的member对象(包括min、max)
     * 通过正向排序获取指定位置的成员，即从低到高的顺序。
     * @param key
     * @param min 最小分数
     * @param max 最大分数
     * @param methodName
     * @return
     */
    Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, String methodName);
    
    /**
     * 获取指定key的zset中score分数值在min~max间的member对象(包括min、max)
     * 通过正向排序获取指定位置的成员，即从低到高的顺序。
     * @param key
     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param offset 偏移值
     * @param count 返回数量
     * @param methodName
     * @return
     */
    Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, int offset, int count, String methodName);
    
    /**
     * 获取指定key的zset中score分数值在min~max间的member对象(包括min、max)
     * 通过反向排序获取指定位置的成员，即从高到低的顺序。
     * @param key
     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param methodName
     * @return
     */
    Set<String> getZSetRevRangeByScoreFromJedis(String key, String min, String max, String methodName);
    
    /**
     * 获取指定key的zset中score分数值在min~max间的member对象(包括min、max)
     * 通过反向排序获取指定位置的成员，即从高到低的顺序。
     * @param key
     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param offset 偏移值
     * @param count 返回数量
     * @param methodName
     * @return
     */
    Set<String> getZSetRevRangeByScoreFromJedis(String key, String min, String max, int offset, int count, String methodName);

    /**
     * 删除指定key的ZSet中的value值
     * @param key
     * @param values
     * @return
     */
    boolean delZSet(String key, String[] values);
    /**
     * 删除指定key的ZSet中指定分数值在min~max间的member对象(包括min、max)
     * @param key
     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param methodName
     * @return
     */
    boolean delZSetByScore(String key, String min, String max, String methodName);
    
    /**
     * 获取指定key的ZSet中分数区间的成员数量
     * @param key
     * @param min 最小分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param max 最大分数 -inf和+inf分别表示Sorted-Sets中分数的最低值和最高值。
     * @param methodName
     * @return
     */
    long getZSetCount(String key, String min, String max, String methodName);
}
