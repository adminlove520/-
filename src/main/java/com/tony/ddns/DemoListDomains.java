package com.tony.ddns;

import java.util.List;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse.Domain;
import com.tony.ddns.pojo.Aliyun;
import com.tony.ddns.util.AliDdnsUtils;

/**
 * 阿里sdk
 * 
 * @author xiang
 *
 */
public class DemoListDomains {

	/**
	 * 设置参数
	 * 
	 * @param request
	 */
	public void setParam(DescribeDomainsRequest request, Aliyun yun) {
		// 设置参数
		request.putQueryParameter("RecordId", yun.getRecordId());
		request.putQueryParameter("RR", yun.getRr());
		request.putQueryParameter("Type", yun.getType());
		request.putQueryParameter("Value", yun.getIpV4());
		request.putQueryParameter("TTL", yun.getTTL());
	}

	/**
	 * 解析DNS信息
	 */
	public void analysisDns(Aliyun yun) {
		String actionName = "UpdateDomainRecord";
		DescribeDomainsRequest request = AliDdnsUtils.getRequest(actionName);
		DescribeDomainsResponse response;
		setParam(request, yun);

		try {
			response = AliDdnsUtils.getClient().getAcsResponse(request);
			List<Domain> list = response.getDomains();
			for (Domain domain : list) {
				System.out.println(domain.getDomainName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}