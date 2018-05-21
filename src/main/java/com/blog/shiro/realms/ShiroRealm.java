package com.blog.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("ShiroRealm:"+token);
		//强制类型转换
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//获取username
		String username = upToken.getUsername();
		//从数据库中查询usename
		String dbUsername="zhang5850";
		
		//用户名不存在异常
		if (!upToken.getUsername().equals(dbUsername)) {
			throw new UnknownAccountException();
		}
		//或者其他异常
		if ("".equals(username)) {
			throw new LockedAccountException();
		}
		//根据用户的情况，构建AuthenticationInfo对象
		//以下信息是从数据库中获取的
		//principal:认证的实体信息，可以是username,也可以是数据表对应的用户的实体类信息
		Object principal=username;
		//credentials:密码
		Object credentials="d1ae97dfc35bafbf4395e1d6ba2b1fb8";
		//realmName:当前realm对象的name,调用父类的getName()方法即可
		String realmName=getName();
		//盐值
		ByteSource credentialsSalt=ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info =null;// new SimpleAuthenticationInfo(principal, credentials, realmName);
		info=new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("ShiroRealm doGetAuthorizationInfo");
		//从PrincipalCollection中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		//利用登录信息的信息来获取用户的角色或权限（可能需要查询数据库）
		System.out.println("principal 用户具有user权限");
		Set<String> roles=new HashSet<String>();
		roles.add("user");
		if (principal.equals("admin")) {
			roles.add("admin");
		}
		//创建SimpleAuthorizationInfo对角，并设置其roles属性
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		//返回SimpleAuthorizationInfo对象
		return simpleAuthorizationInfo;
	}
	//获取加密后的值
	public static void main(String[] args) {
		String hashAlgorithmName="MD5";
		Object credentials="123456";
		Object salt=ByteSource.Util.bytes("zhang5850");
		int hashIterations=1024;
		SimpleHash hash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(hash.toString());
	}
	
}
