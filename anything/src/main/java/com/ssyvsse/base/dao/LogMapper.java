package com.ssyvsse.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ssyvsse.base.entity.Log;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午5:05:02
 */
public interface LogMapper {

	/**
	 * 
	 * @param key
	 * @param begin
	 * @param endTime
	 * @param start
	 * @param end
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	List<Log> selectLogLikeKeyAndFromBeginToEnd(@Param("key") String key, @Param("beginTime") String begin,
			@Param("endTime") String endTime, @Param("start") int start, @Param("end") int end,
			@Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

	/**
	 * 计算数量
	 * 
	 * @param key
	 * @param begin
	 * @param endTime
	 * @param start
	 * @param end
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	Long count(@Param("key") String key, @Param("beginTime") String begin, @Param("endTime") String endTime,
			@Param("start") int start, @Param("end") int end, @Param("sortName") String sortName,
			@Param("sortOrder") String sortOrder);

	/**
	 * 插入
	 * 
	 * @param log
	 */
	void insert(Log log);

	Long countId();
}
