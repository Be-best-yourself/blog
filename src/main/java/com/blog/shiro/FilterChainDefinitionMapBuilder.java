package com.blog.shiro;


import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String,String> builderChainDefinitionMap(){
		LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/user/*", "anon");
		filterChainDefinitionMap.put("/editor/*", "authc");
		filterChainDefinitionMap.put("/classify/*", "authc");
		filterChainDefinitionMap.put("/drafts/*", "authc");
		filterChainDefinitionMap.put("/recycle/*", "authc");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "anon");
		return filterChainDefinitionMap;
	}
}
