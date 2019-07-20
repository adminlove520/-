package com.tony.ddns.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云解析工具
 * 
 * @author xiang
 *
 */
public class AliDdnsUtils {

	private static IAcsClient client = null;

	static {
		String regionId = "cn-hangzhou"; // 必填固定值，必须为“cn-hanghou”
		String accessKeyId = PropertiesUtil.getProperty("LTAI15I8BQwCm0mG"); // your
		// accessKey
		String accessKeySecret = PropertiesUtil.getProperty("weGhzmI74nPDGan0mFOPuG526og3zD");// your
		// accessSecret
		IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		// 若报Can not find endpoint to access异常，请添加以下此行代码
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
		} catch (ClientException e) {
			e.printStackTrace();
		}
		client = new DefaultAcsClient(profile);
	}

	/**
	 * 获取一次inter连接，修改DNS
	 */
	public static DescribeDomainsRequest getRequest(String actionName) {
		DescribeDomainsRequest request = new DescribeDomainsRequest();
		request.setProtocol(ProtocolType.HTTPS); // 指定访问协议
		request.setAcceptFormat(FormatType.JSON); // 指定api返回格式
		request.setMethod(MethodType.POST); // 指定请求方法
		// 设置请求action
		request.setActionName(actionName);
		return request;
	}

	/**
	 * 获取一次inter连接,查询dns
	 */
	public static DescribeDomainRecordsRequest getRequestQuery(String actionName) {
		DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
		request.setProtocol(ProtocolType.HTTPS); // 指定访问协议
		request.setAcceptFormat(FormatType.JSON); // 指定api返回格式
		request.setMethod(MethodType.POST); // 指定请求方法
		// 设置请求action
		request.setActionName(actionName);
		return request;
	}

	/**
	 * 获取客户端数据
	 * 
	 * @return
	 */
	public static IAcsClient getClient() {
		return client;
	}
}
