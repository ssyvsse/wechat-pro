package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssyvsse.crawl.entity.Novel;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午11:15:38 
 */
public interface NovelMapper {
	void addNovel(Novel novel);
	
	List<Novel> findAll(@Param("start")int start,@Param("limit")int limit);
}
