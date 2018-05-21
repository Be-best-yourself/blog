package com.blog.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("SecondRealm:"+token);
		//强制类型转换
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//获取username
		String username = upToken.getUsername();
		//从数据库中查询usename
		String dbUsername="admin";
		
		//用户名不存在异常
		if (!upToken.getUsername().equals(dbUsername)) {
			throw new UnknownAccountException("用户不存在");
		}
		//或者其他异常
		if ("".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		//根据用户的情况，构建AuthenticationInfo对象
		//以下信息是从数据库中获取的
		//principal:认证的实体信息，可以是username,也可以是数据表对应的用户的实体类信息
		Object principal=username;
		//credentials:密码
		Object credentials="ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		//realmName:当前realm对象的name,调用父类的getName()方法即可
		String realmName=getName();
		//盐值
		ByteSource credentialsSalt=ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info =null;// new SimpleAuthenticationInfo(principal, credentials, realmName);
		info=new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, getName());
		return info;
	}
	//获取加密后的值
		public static void main(String[] args) {
			String hashAlgorithmName="SHA1";
			Object credentials="123456";
			Object salt=ByteSource.Util.bytes("admin");
			int hashIterations=1024;
			SimpleHash hash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			System.out.println(hash);
		}
}
