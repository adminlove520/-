package com.tony.ddns;

import java.util.List;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.tony.ddns.pojo.Aliyun;
import com.tony.ddns.util.AliDdnsUtils;
import com.tony.ddns.util.LocalPublicIpv4;
import com.tony.ddns.util.PropertiesUtil;

/**
 * 调用阿里api,更新DNS域名解析
 *
 * @author xiang
 *
 */
public class UpdateDomainRecord {
	/**
	 * 设置域名参数
	 * 
	 * @param request
	 */
	public void setParam(DescribeDomainRecordsRequest request) {
		String domainName = PropertiesUtil.getProperty("DomainName");
		request.putQueryParameter("DomainName", domainName);
	}

	/**
	 * 解析DNS信息
	 */
	public void analysisDns() {
		// 获取公网ip
		LocalPublicIpv4 ip = new LocalPublicIpv4();
		String ipV4 = ip.publicip();
		// 获取解析的数据
		String actionName = "DescribeDomainRecords";
		DescribeDomainRecordsResponse response;
		// 获取request
		DescribeDomainRecordsRequest request = AliDdnsUtils.getRequestQuery(actionName);
		// 设置request参数
		setParam(request);
		try {
			response = AliDdnsUtils.getClient().getAcsResponse(request);
			// 声明解析对象
			DemoListDomains demo = new DemoListDomains();
			// 获取阿里云的数据
			List<Record> list = response.getDomainRecords();
			if (list == null || list.isEmpty()) {
				return;
			}
			// 更新ip
			Record record = list.get(0);
			Aliyun yun = new Aliyun();
			// 进行判定记录是否需要更新
			if (record.getValue().equals(ipV4)) {
				// 不需要更新，继续下次循环
				System.out.println("当前域名解析地址为：" + ipV4 + "不需要更新！");
			} else {
				System.out.println("更新域名：" + record.getDomainName());
				// 进行替换关键数据
				yun.setIpV4(ipV4);
				yun.setRecordId(record.getRecordId());
				yun.setRr(record.getRR());
				yun.setTTL(record.getTTL());
				yun.setType(record.getType());
				System.out.println("域名更换ip开始");
				demo.analysisDns(yun);
				System.out.println("域名更换ip结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("域名更换异常");
		}
	}
}
