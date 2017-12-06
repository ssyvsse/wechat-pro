package com.ssyvsse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssyvsse.pojo.CpHistory;

public interface CpHistoryMapper {

	@Update("update ${shortpy} set img=#{img} where no=#{no}")
	int updateHistoryImg(CpHistory history);

	/**
	 * @Title: selectAllHistoryTableName
	 * @Description: 查询所有的彩票历史表表名
	 */
	@Select("SELECT a.TABLE_NAME FROM Information_schema.TABLES a,cptype b WHERE table_Name LIKE '%_cp_history'"
			+ " and a.table_name=b.shortpy and b.`status`=1")
	List<String> selectAllHistoryTableName();

	@Select("select max(id) from ${shortpy} where no=#{no} ")
	int selectPrimaryKeyByNo(@Param("shortpy") String shortpy, @Param("no") String no);

	int deleteByPrimaryKey(Integer id);

	int insert(CpHistory record);

	int insertSelective(CpHistory record);

	CpHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CpHistory record);

	int updateByPrimaryKey(CpHistory record);

	/**
	 * 取某彩种的某平台的数据
	 * 
	 * @param platform
	 * @param typeid
	 * @return
	 */
	@Select("SELECT * FROM cp_history where typeid = #{typeid} AND platform=#{platform} ORDER BY id desc limit 1")
	CpHistory getLatestHistoryByPlatformAndType(@Param("platform") String platform, @Param("typeid") Integer typeid);

	/***
	 * 获取最新的历史开奖 通过typeid
	 * 
	 * @param typeid
	 * @return
	 */
	@Select("SELECT * FROM cp_history where typeid = #{typeid} ORDER BY id desc limit 1")
	CpHistory getLatestHistoryByType(@Param("typeid") Integer typeid);

	/***
	 * 查询走势图计算的历史信息
	 * 
	 * @param table
	 *            表的名称
	 * @param latestHistory
	 * @param limit
	 * @return
	 */
	List<CpHistory> getCalHistory(@Param("table") String table, @Param("latest") CpHistory latestHistory,
			@Param("limit") Integer limit);

	/***
	 * 查询走势图计算的历史信息
	 * 
	 * @param table
	 *            表的名称
	 * @param latestHistory
	 * @param limit
	 * @return
	 */
	List<CpHistory> getCalHistoryByPlatform(@Param("table") String table, @Param("latest") CpHistory latestHistory,
			@Param("limit") Integer limit, @Param("platform") String platform);

	/***
	 * 通过typeid 和 platform 获取当天的开奖数据
	 * 
	 * @param typeid
	 * @param platform
	 * @return
	 */
	@Select("SELECT * FROM cp_history WHERE typeid = #{typeid} AND platform = #{platform} AND DATE_FORMAT(createtime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
	List<CpHistory> getTodayByTypeAnsdPlatform(@Param("typeid") Integer typeid, @Param("platform") String platform);

	/***
	 * AND platform = #{platform}
	 * 
	 * @param typeid
	 *            彩种名称
	 * @param platform
	 *            平台 去掉开奖平台
	 * @param n
	 *            最新的多少期
	 * @return
	 */
	@Select("SELECT * FROM cp_history WHERE typeid = #{typeid}  ORDER BY createtime DESC LIMIT #{n}")
	List<CpHistory> getCpHistoryTopN(@Param("typeid") Integer typeid, @Param("platform") String platform,
			@Param("n") Integer n);

	//////////////////////////////////
	List<CpHistory> selectAllCpHistory();

	int editCpHistory(CpHistory cpHistory);

	CpHistory find(@Param("id") Integer id);

	int deleteCp(@Param("id") Integer id);

	List<CpHistory> findAllByLike(@Param("id") Integer id);

	int addCpHistory(CpHistory cpHistory);

	/***
	 * 查找彩票的历史
	 * 
	 * @param typeid
	 *            彩种ID
	 * @param year
	 *            年
	 * @param platform
	 *            平台
	 * @return
	 */
	@Select("SELECT * FROM cp_history WHERE typeid = #{typeid} AND DATE_FORMAT(opendate,'%Y') = #{year} AND platform = #{platform}")
	List<CpHistory> selectCpHistoryByYear(@Param("typeid") Integer typeid, @Param("year") String year,
			@Param("platform") String platform);

	/***
	 * 查找彩票的历史
	 * 
	 * @param typeid
	 *            彩种ID
	 * @param no
	 *            期号
	 * @return
	 */
	@Select("SELECT * FROM cp_history WHERE typeid = #{typeid} AND no=#{no}")
	List<CpHistory> selectCpHistoryByTypeidAndNo(@Param("typeid") Integer typeid, @Param("no") String no);

	@Select("SELECT * FROM cp_history WHERE typeid = #{typeid} AND no=#{no} AND platform = #{platform}")
	CpHistory getCpByTypeidAndNoAndPlatform(@Param("typeid") Integer typeid, @Param("no") String no,
			@Param("platform") String platform);

	// @Select("SELECT * FROM cp_history WHERE typeid = #{typeid} AND no=#{no}")
	CpHistory getCpNumsByTypeidAndNo(@Param("typeid") Integer typeid, @Param("no") String no);

	@Update("UPDATE cp_history SET before_nums=#{before_nums},before_specialnums=#{before_specialnums},opendate=#{opendate},money=#{money},status=#{status},userid=#{userid} where no=#{no}")
	void updateHisByNo(CpHistory his);

}