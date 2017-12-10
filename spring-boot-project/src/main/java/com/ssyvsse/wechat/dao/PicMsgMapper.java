package com.ssyvsse.wechat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ssyvsse.wechat.entity.PicMsg;

/**
 * @author llb
 *
 * @Date 2017年12月10日 下午3:59:17
 */
public interface PicMsgMapper {

	/**
	 * 添加发送的图片信息
	 * 
	 * @param picMsg
	 */
	@Insert("insert into upload_img(media_id,pic_url,create_time,create_by) values(#{mediaId},#{picUrl},#{createTime},#{createBy})")
	void addPicMsg(PicMsg picMsg);
	
	@Select("select id from upload_img where media_id=#{mediaId}")
	int findIdByMediaId(@Param("mediaId")String mediaId);
	
	@Select("select media_id from upload_img where id=#{id} and create_by=#{createBy}")
	String findMediaIdById(@Param("id")int id,@Param("createBy")String owner);
}
