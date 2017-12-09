package com.ssyvsse.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ssyvsse.pojo.Cplatest;

/**
 * @author llb
 *
 * @Date 2017年12月8日
 */
public interface CplatestMapper {

    int insert(Cplatest record);

    int insertSelective(Cplatest record);


    /**
     * 
    * @Title: updateByExampleAndTypeid 
    * @Description: 根据有传入值更新
    * @param record
     */
    int updateByExampleAndTypeid(@Param("record")Cplatest record);
    
    /**
     * 
    * @Title: selectNoByCptypeid 
    * @Description: 根据彩票种类id从最新彩票数据表查询最新开奖期数
    * @param typeid 彩票种类id
    * @return String  最新开奖期数 
     */
    @Select("select no from cplatest where typeid=#{_parameter}")
    String selectNoByCptypeid(int typeid);
    
    Cplatest selectByTypeid(@Param("typeid")Integer typeid);

	void updateCplatest(Cplatest cplatest);
	
	/***
	 * 根据彩种的名称获取彩种的最新开奖信息
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM cplatest l INNER JOIN cptype t ON l.typeid = t.id WHERE t.`name` = #{name}")
	Cplatest selectByName(@Param("name") String name);
	
	@Select("SELECT * FROM cplatest")
	List<Cplatest> selectAll();

	@Select("")
	Date getLatestOpenDate(Integer typeid);
    
	@Select("SELECT * FROM cplatest WHERE typeid=#{typeid}")
	Cplatest getcplatestByTypeid(@Param("typeid") Integer typeid);
}