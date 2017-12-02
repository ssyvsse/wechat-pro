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
    	/*System.out.println("name="+getInstance().ehManager.getName());
    	String[] cacheNames = getInstance().ehManager.getCacheNames();
    	for (int i = 0; i < cacheNames.length; i++) {
			String string = cacheNames[i];
			System.out.println("string="+string);
		}
    	List list = getInstance().ehManager.getCache(default_strategy).getKeys();
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
			System.out.println("key="+object);
		}*/
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
    
    /**
     * @Description: 获取用户id和积分值
     * @param scoreKey 通配符，比如获取积分，则使用score
     * @return   key,用户id，value-积分值         
     */
    public static Map<Integer,Integer> getUsersScore(String scoreKey){
    	Map<Integer,Integer> map = new HashMap<>(); 
    	List<String> keys = getInstance().ehManager.getCache(default_strategy).getKeys();
    	for (int i = 0; i < keys.size(); i++) {
			String object = keys.get(i);
			System.out.println("keys="+object);
			try {
				if(object.contains(scoreKey)){
					map.put(Integer.parseInt(object.substring(scoreKey.length(), object.length())), (Integer) getCacheObject(object));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return map;
    }
    
    /**
     * @Description: 获取所有的帖子的点击率值
     * @param scoreKey 通配符，比如获取积分，则使用score
     * @return   map ,key-postid,value-clickrate        
     */
    public static Map<Integer,Integer> getAllPostClickrate(String scoreKey){
    	Map<Integer,Integer> map = new HashMap<>(); 
    	
    	List<String> keys = getInstance().ehManager.getCache(default_strategy).getKeys();
    	
    	for (int i = 0; i < keys.size(); i++) {
			String object = keys.get(i);
			System.out.println("keys="+object);
			try{
				if(object.contains(scoreKey)){
					Set<String> set = (Set<String>) getCacheObject(object);
					map.put(Integer.parseInt(object.substring(scoreKey.length(), object.length())), set.size());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
        return map;
    }
    
    
    public static void clear(String cacheName){
    	getInstance().ehManager.getCache(cacheName).removeAll();
    }
    
    public static void clearDefault(){
    	getInstance().ehManager.getCache(default_strategy).removeAll();
    }
    
}  
