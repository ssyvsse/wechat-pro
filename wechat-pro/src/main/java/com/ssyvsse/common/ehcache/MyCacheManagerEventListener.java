package com.ssyvsse.common.ehcache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: MyCacheManagerEventListener
 * @Description: CacheManage监听的实现操作
 * @author: LUCKY
 * @date:2015年11月28日 下午3:56:12
 */
public class MyCacheManagerEventListener implements CacheManagerEventListener {
	private Log logger = LogFactory.getLog(MyCacheManagerEventListener.class);
	private final CacheManager cacheManager;

	public MyCacheManagerEventListener(CacheManager cacheManager) {

		this.cacheManager = cacheManager;
	}

	@Override
	public void init() throws CacheException {
		if (logger.isInfoEnabled()) {
			logger.info("init Cache..............");
		}
	}

	@Override
	public Status getStatus() {
		if (logger.isInfoEnabled()) {
			logger.info("the current Cache status is " + cacheManager.getStatus());
		}
		return cacheManager.getStatus();
	}

	@Override
	public void dispose() throws CacheException {
		if (logger.isInfoEnabled()) {
			logger.info("despose Cache...........");
		}
	}

	@Override
	public void notifyCacheAdded(String cacheName) {
		if (logger.isInfoEnabled()) {
			logger.info("cacheAdded the cacheName is" + cacheName);
		}
	}

	@Override
	public void notifyCacheRemoved(String cacheName) {
		if (logger.isInfoEnabled()) {
			logger.info("removeAdded the cacheName is" + cacheName);
		}
	}
}
