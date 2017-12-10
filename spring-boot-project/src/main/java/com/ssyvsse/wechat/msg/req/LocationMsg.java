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
	private double longitude;

	/**
	 * 地理位置精度
	 */
	private double precision;

	public LocationMsg(String toUserName, String fromUserName, Integer createTime, String msgType, double latitude,
			double longitude, double precision) {
		super(toUserName, fromUserName, createTime, msgType);
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision = precision;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

}
