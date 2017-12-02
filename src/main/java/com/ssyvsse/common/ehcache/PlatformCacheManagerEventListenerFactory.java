package com.ssyvsse.common.ehcache;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;
  
/**   
 * @ClassName: PlatformCacheManagerEventListenerFactory    
 * @Description: CacheManager监听工厂 
 * @author: LUCKY   
 * @date:2015年11月28日 下午3:53:51      
 */  
public class PlatformCacheManagerEventListenerFactory  extends CacheManagerEventListenerFactory{  
	
   /* @Override  
    public CacheManagerEventListener createCacheManagerEventListener(  
            CacheManager cacheManager, Properties properties) {  
        return new MyCacheManagerEventListener(cacheManager);  
    }*/

	@Override
	public CacheManagerEventListener createCacheManagerEventListener(
			Properties properties) {
		return new MyCacheManagerEventListener(new CacheManager(Thread.currentThread()  
                .getContextClassLoader().getResourceAsStream("ehcache.xml")));  
	}  
  
}  
