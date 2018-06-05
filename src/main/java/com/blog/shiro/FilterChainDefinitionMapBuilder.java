package com.blog.shiro;


import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String,String> builderChainDefinitionMap(){
		LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/user/*", "anon");
		filterChainDefinitionMap.put("/editor/*", "authc");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "anon");
		return filterChainDefinitionMap;
	}
}
