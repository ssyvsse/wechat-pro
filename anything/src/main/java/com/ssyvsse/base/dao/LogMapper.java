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

	List<Log> findByOperatorAndOperateTime(@Param("searchText") String searchText, @Param("begin") String begin,
			@Param("end") String end);

	@Insert("insert into system_log(operational_context,operation_time,operator,ip) values(#{operationalContext},#{operationTime},#{operator},#{ip})")
	void insert(Log log);

	@Select(value="select * from system_log order by id desc")
	@ResultMap(value="logMap")
	List<Log> findAll();
}
