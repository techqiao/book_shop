package com.wjq.cache.redis.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wjq.cache.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 类RedisServiceImpl.java的实现描述：redis操作类
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private JedisPool jedisPool;

    private Integer index;

    private Jedis getJedis() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (index != null)
                jedis.select(index);
        } catch (JedisException e) {
            logger.error("failed:jedisPool getResource.", e);
            release(jedis, true);
            throw e;
        }
        return jedis;
    }

    private void release(Jedis jedis, boolean isBroken) {
        if (jedis != null) {
            if (isBroken) {
                jedis.close();
            } else {
                jedis.close();
            }
        }
    }

    public String addStringToJedis(String key, String value, int cacheSeconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = this.getJedis();
            lastVal = jedis.getSet(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return lastVal;
    }

    public boolean addStringToJedisWithNoReply(String key, String value, int cacheSeconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            Long n = jedis.setnx(key, value);
            if (n.longValue() == 0) {
                return false;
            }
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }


    public boolean addListToJedis(String key, List<String> list, int cacheSeconds, String methodName) {
        if (list != null && list.size() > 0) {
            Jedis jedis = null;
            boolean isBroken = false;
            try {
                jedis = this.getJedis();
                if (jedis.exists(key)) {
                    jedis.del(key);
                }
                for (String aList : list) {
                    jedis.rpush(key, aList);
                }
                if (cacheSeconds != 0) {
                    jedis.expire(key, cacheSeconds);
                }
            } catch (JedisException e) {
                isBroken = true;
                logger.error("redis failed.", e);
            } catch (Exception e) {
                isBroken = true;
                logger.error("redis failed.", e);
            } finally {
                release(jedis, isBroken);
            }
            return !isBroken;
        }
        return true;
    }

    public boolean addToSetJedis(String key, String[] value, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.sadd(key, value);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean removeSetJedis(String key, String value, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.srem(key, value);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean lpushDataToListJedis(String key, String data, int cacheSeconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.lpush(key, data);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean rpushDataToListJedis(String key, String data, int cacheSeconds) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.rpush(key, data);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public String rpopDataFromJedis(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        String value = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                value = jedis.rpop(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return value;
    }

    public String lpopDataFromJedis(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        String value = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                value = jedis.lpop(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return value;
    }

    public boolean rpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.del(key);
            jedis.rpush(key, batchData.toArray(new String[batchData.size()]));
            if (cacheSeconds != 0)
                jedis.expire(key, cacheSeconds);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean lpushDataToListJedis(String key, List<String> batchData, int cacheSeconds, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.del(key);
            jedis.lpush(key, batchData.toArray(new String[batchData.size()]));
            if (cacheSeconds != 0)
                jedis.expire(key, cacheSeconds);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean addHashMapToJedis(String key, Map<String, String> map, int cacheSeconds) {
        boolean isBroken = false;
        Jedis jedis = null;
        if (map != null && map.size() > 0) {
            try {
                jedis = this.getJedis();
                jedis.hmset(key, map);
                if (cacheSeconds > 0)
                    jedis.expire(key, cacheSeconds);
            } catch (Exception e) {
                isBroken = true;
                logger.error("redis failed.", e);
            } finally {
                release(jedis, isBroken);
            }
        }
        return !isBroken;
    }

    public boolean addHashMapToJedis(String key, String field, String value) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public boolean updateHashMapToJedis(String key, String incrementField, long incrementValue, String dateField,
                                        String dateValue) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.hincrBy(key, incrementField, incrementValue);
            jedis.hset(key, dateField, dateValue);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    public String getStringFromJedis(String key) {
        String value = null;
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return value;
    }


    public List<String> getListFromJedis(String key, String methodName) {
        List<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.lrange(key, 0, -1);
            }
        } catch (JedisException e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    public Set<String> getSetFromJedis(String key, String methodName) {
        Set<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.smembers(key);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    public Map<String, String> getHashMapFromJedis(String key, String methodName) {
        Map<String, String> hashMap = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            hashMap = jedis.hgetAll(key);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return hashMap;
    }

    public String getHashMapValueFromJedis(String key, String field) {
        String value = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis != null) {
                if (jedis.exists(key)) {
                    value = jedis.hget(key, field);
                }
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return value;
    }

    public Long getIdentifyId(String identifyName, String methodName) {
        boolean isBroken = false;
        Jedis jedis = null;
        Long identify = null;
        try {
            jedis = this.getJedis();
            if (jedis != null) {
                identify = jedis.incr(identifyName);
                if (identify == 0) {
                    return jedis.incr(identifyName);
                } else {
                    return identify;
                }
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return null;
    }

    /**
     * 删除某db的某个key值
     *
     * @param key
     * @return
     */
    public Long delKeyFromJedis(String key) {
        boolean isBroken = false;
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = this.getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return result;
    }

    public long getLength(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        long result = 0;
        try {
            jedis = this.getJedis();
            return jedis.llen(key);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return result;
    }

    public boolean existKey(String key, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return false;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean addToZSetJedis(String key, double score, String value, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.zadd(key, score, value);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    @Override
    public boolean addToZSetJedis(String key, Map<String, Double> scoreMembers, String methodName) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    @Override
    public Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, String methodName) {
        Set<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.zrangeByScore(key, min, max);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    @Override
    public Set<String> getZSetRangeByScoreFromJedis(String key, String min, String max, int offset, int count,
                                                    String methodName) {
        Set<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.zrangeByScore(key, min, max, offset, count);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    @Override
    public Set<String> getZSetRevRangeByScoreFromJedis(String key, String min, String max, String methodName) {
        Set<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.zrevrangeByScore(key, min, max);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    @Override
    public Set<String> getZSetRevRangeByScoreFromJedis(String key, String min, String max, int offset, int count,
                                                       String methodName) {
        Set<String> list = null;
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (jedis.exists(key)) {
                list = jedis.zrevrangeByScore(key, min, max, offset, count);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return list;
    }

    @Override
    public boolean delZSetByScore(String key, String min, String max, String methodName) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.zremrangeByScore(key, min, max);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    @Override
    public boolean hDelFromJedis(String key, String... fields) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.hdel(key, fields);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    @Override
    public boolean delZSet(String key, String[] values) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.zrem(key, values);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return !isBroken;
    }

    @Override
    public long getZSetCount(String key, String min, String max, String methodName) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            return jedis.zcount(key, min, max);
        } catch (Exception e) {
            isBroken = true;
            logger.error("redis failed.", e);
        } finally {
            release(jedis, isBroken);
        }
        return 0;
    }

    public Set<Tuple> getDataFromZset(String key, double min, double max, int offset, int count, String order) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            if (order.equals("desc")) {// 降序
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } else if (order.equals("asc")) {// 正序
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        } catch (Exception e) {
            logger.error("getDataFromZset failed.", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

}
