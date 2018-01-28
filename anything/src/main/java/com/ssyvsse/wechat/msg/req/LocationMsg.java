package com.ssyvsse.wechat.msg.req;

/**
 * @author llb
 *
 * @Date 2017年12月10日 下午10:18:02
 */
public class LocationMsg extends ReqBaseMsg {

	/**
	 * 地理位置纬度
	 */
	private double latitude;

	/**
	 * 地理位置经度
	 */
	private double longtitude;
	/**
	 * 精度
	 */
	private double precision;

	public LocationMsg(String toUserName, String fromUserName, Integer createTime, String msgId, double latitude,
			double longtitude, double precision) {
		super(toUserName, fromUserName, createTime, "LOCATION", msgId);
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.precision = precision;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

}
