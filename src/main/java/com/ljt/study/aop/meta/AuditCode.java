package com.ljt.study.aop.meta;

/**
 * @author LiJingTang
 * @version 2015年9月16日下午3:34:32
 */
public enum AuditCode {

	PASS, REJECT;
	
	@Override
	public String toString() {
		switch (this) {
			case PASS :
				return "通过";
			case REJECT :
				return "拒绝";
		}
		
		return null;
	}
	
}
