package util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ShardingRedisClient {
	private static ShardedJedisPool shardedJedisPool;  
	public  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static ShardedJedis jedis;
	
    static {  
        // 读取相关的配置  
    	FileResourceUtil fr = new FileResourceUtil("config.properties"); 
    	int maxActive = Integer.parseInt(fr.getConfigValue("redis.pool.maxActive"));  
        int maxIdle = Integer.parseInt(fr.getConfigValue("redis.pool.maxIdle"));  
        int maxWait = Integer.parseInt(fr.getConfigValue("redis.pool.maxWait"));  
          
        String ip = fr.getConfigValue("redis.ip");  
        int port = Integer.parseInt(fr.getConfigValue("redis.port")); 
          
        //设置配置  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(maxActive);  
        config.setMaxIdle(maxIdle);  
        config.setMaxWaitMillis(maxWait);  
          
        //设置分片元素信息  
        JedisShardInfo shardInfo1 = new JedisShardInfo(ip,port);  
        JedisShardInfo shardInfo2 = new JedisShardInfo(ip,port);  
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();  
        list.add(shardInfo1);  
        list.add(shardInfo2);  
        shardedJedisPool = new ShardedJedisPool(config, list);  
        jedis = shardedJedisPool.getResource();
    }  
  
      
    /** 
     * 向缓存中设置字符串内容 
     * @param key key 
     * @param value value 
     * @return 
     * @throws Exception 
     */  
    public static boolean  set(String key,String value) throws Exception{  
//        ShardedJedis jedis = null;  
        try {  
//            jedis = shardedJedisPool.getResource();  
            jedis.set(key, value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }finally{  
            shardedJedisPool.returnResource(jedis);  
        }  
    }  
      
    /** 
     * 向缓存中设置对象 
     * @param key  
     * @param value 
     * @return 
     */  
    public static boolean  set(String key,Object value){  
//        ShardedJedis jedis = null;  
        try {  
            String objectJson = gson.toJson(value);
//            		JSON.toJSONString(value);  
//            jedis = shardedJedisPool.getResource();  
            jedis.set(key, objectJson);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }finally{  
            shardedJedisPool.returnResource(jedis);  
        }  
    }  
      
    /** 
     * 删除缓存中得对象，根据key 
     * @param key 
     * @return 
     */  
    public static boolean del(String key){  
//        ShardedJedis jedis = null;  
        try {  
//            jedis = shardedJedisPool.getResource();  
            jedis.del(key);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }finally{  
            shardedJedisPool.returnResource(jedis);  
        }  
    }  
      
    /** 
     * 根据key 获取内容 
     * @param key 
     * @return 
     */  
    public static Object get(String key){  
//        ShardedJedis jedis = null;  
        try {  
//            jedis = shardedJedisPool.getResource();  
            Object value = jedis.get(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }finally{  
            shardedJedisPool.returnResource(jedis);  
        }  
    }  
      
  
    /** 
     * 根据key 获取对象 
     * @param key 
     * @return 
     */  
    public static <T> T get(String key,Class<T> clazz){  
//        ShardedJedis jedis = null;  
        try {  
//            jedis = shardedJedisPool.getResource();  
            String value = jedis.get(key);  
//            return JSON.parseObject(value, clazz);  
            return gson.fromJson(value, clazz);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }finally{  
            shardedJedisPool.returnResource(jedis);  
        }  
    }  
}
