package com.tony.ddns;

/**
 * 运行时入口
 * 
 * @author sang
 *
 */
public class AppRun {

	public static void main(String[] args) {
		System.out.println("开始ddns检查");
		UpdateDomainRecord record = new UpdateDomainRecord();
		record.analysisDns();
		System.out.println("ddns运行结束");
	}

}
