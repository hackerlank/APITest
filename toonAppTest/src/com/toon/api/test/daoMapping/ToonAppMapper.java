package com.toon.api.test.daoMapping;

import org.apache.ibatis.annotations.Param;

public interface ToonAppMapper {
	
	public String getUserId(@Param("mobile") String mobile);
	
}
