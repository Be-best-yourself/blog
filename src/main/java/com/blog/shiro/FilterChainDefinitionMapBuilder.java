package com.blog.shiro;


import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String,String> builderChainDefinitionMap(){
		LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/user/*", "anon");
		filterChainDefinitionMap.put("/editor/*", "anon");
		filterChainDefinitionMap.put("/**", "anon");
		return filterChainDefinitionMap;
	}
}
