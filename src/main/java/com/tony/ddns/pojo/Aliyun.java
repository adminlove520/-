package com.tony.ddns.pojo;

import java.io.Serializable;

/**
 * 阿里云DNS 云解析对象
 * 
 * @author xiang
 *
 */
public class Aliyun implements Serializable {

	/** 序列号 */
	private static final long serialVersionUID = 1L;
	/** IPV4地址，当前电脑在公网的ip地址 */
	private String ipV4;
	/** 主机记录，如果要解析@.exmaple.com，主机记录要填写"@”，而不是空 */
	private String rr;
	/** 解析记录类型，参见解析记录类型格式 */
	private String type;
	/** 解析记录的ID，此参数在添加解析时会返回，在获取域名解析列表时会返回 */
	private String recordId;
	/** 生效时间，默认为600秒（10分钟），参见TTL定义说明。购买VIP可以升级为1秒生效。没必要1秒 */
	private Long ttl;

	public String getIpV4() {
		return ipV4;
	}

	public void setIpV4(String ipV4) {
		this.ipV4 = ipV4;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Long getTTL() {
		return ttl;
	}

	public void setTTL(Long ttl) {
		this.ttl = ttl;
	}
}
