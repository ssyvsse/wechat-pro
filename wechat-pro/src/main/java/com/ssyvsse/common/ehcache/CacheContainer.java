package com.ssyvsse.common.ehcache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
  
/** 
 * @ClassName: CacheContainer 
 * @Description: 缓存容器的类 
 * @author: LUCKY 
 * @date:2015年11月28日 下午3:27:52 
 */  
public class CacheContainer {  
  
//    public static final String DEFAULT_STRATEGY_NAME = "PostScore";  
  
//	private static String SCOREPREKEY="score";
	
    private CacheManager ehManager;  
//    private static String default_strategy = String  
//            .valueOf(DEFAULT_STRATEGY_NAME);  
    
    private static String default_strategy = "PostScore";
  
    private static class SingletonHolder {  
        static CacheContainer instance = new CacheContainer();  
    }  
  
    public static CacheContainer getInstance() {  
        return SingletonHolder.instance;  
    }  
  
    private CacheContainer() {  
        ehManager = new CacheManager(Thread.currentThread()  
                .getContextClassLoader().getResourceAsStream("ehcache.xml"));  
  
    }  
  
    public static boolean containskey(String key) {  
        return containskey(default_strategy, key);  
    }  
  
    public static boolean containskey(String cacheStrategy, String key) {  
        return getInstance().ehManager.getCache(cacheStrategy)  
                .isKeyInCache(key);  
    }  
  
    public static void removeCache(String cacheName) {  
        getInstance().ehManager.removeCache(cacheName);  
    }  
  
    public static void removeCacheObject(String cacheName, String key) {  
        getInstance().ehManager.getCache(cacheName).remove(key);  
    }  
  
    public static void removeCacheObjectAll(String cacheName) {  
        getInstance().ehManager.getCache(cacheName).removeAll();  
    }  
  
    public static void addCache(String key, Object value) {  
    	 Cache cache = getInstance().ehManager.getCache(default_strategy);  
         if (cache != null) {  
             Element el = new Element(key, value);  
             cache.put(el);  
         }  
    }  
  
    public static void addCache(String cacheName, String key, Object value) {  
        Cache cache = getInstance().ehManager.getCache(cacheName); 
        if (cache != null) {  
            Element el = new Element(key, value);  
            cache.put(el);  
        }  
    }  
  
      
    public static Object getCacheObject(String cacheName,String key){
        Element element=getInstance().ehManager.getCache(cacheName).get(key);  
        if(element==null){  
            return null;  
        }  
        return element.getObjectValue();  
    }  
    
    public static Object getCacheObject(String key){
        Element element=getInstance().ehManager.getCache(default_strategy).get(key);  
        if(element==null){  
            return null;  
        }  
        return element.getObjectValue();  
    }  
    
    
    
    
    public static void clear(String cacheName){
    	getInstance().ehManager.getCache(cacheName).removeAll();
    }
    
    public static void clearDefault(){
    	getInstance().ehManager.getCache(default_strategy).removeAll();
    }
    
}  
