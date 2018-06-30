package com.blog.shiro.realms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entity.user.Permission;
import com.blog.entity.user.Role;
import com.blog.entity.user.User;
import com.blog.entity.user.UserRole;
import com.blog.service.user.IPermissionService;
import com.blog.service.user.IRoleService;
import com.blog.service.user.IUserRoleService;
import com.blog.service.user.IUserService;

public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private IUserService iUserService;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Autowired
	private IRoleService iRoleService;

	@Autowired
	private IPermissionService iPermissionService;

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 强制类型转换
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 获取username
		String userName = upToken.getUsername();
		String phonePattern = "^1[3|4|5|7|8][0-9]\\d{4,8}$";

		// 从数据库中查询usename
		User user = null;
		// 盐值
		ByteSource credentialsSalt=null;
		// 手机号登录
		if (Pattern.matches(phonePattern, userName)) {
			user = iUserService.getUserByPhoneNum(userName);
			credentialsSalt = ByteSource.Util.bytes(user.getUserName());
		} else {
			//用户名登录
			user = iUserService.getUserForUserName(userName);
			credentialsSalt = ByteSource.Util.bytes(userName);
		}

		// 用户名不存在异常
		if (user == null) {
			throw new UnknownAccountException();
		}
		// 或者其他异常
		if (user != null && user.getUserStatus() != 0) {
			throw new LockedAccountException();
		}
		// 根据用户的情况，构建AuthenticationInfo对象
		// 以下信息是从数据库中获取的
		// principal:认证的实体信息，可以是username,也可以是数据表对应的用户的实体类信息
		Object principal = user;
		// credentials:密码
		Object credentials = user.getUserPasswordSalt();
		// realmName:当前realm对象的name,调用父类的getName()方法即可
		String realmName = getName();

		SimpleAuthenticationInfo info = null;// new
												// SimpleAuthenticationInfo(principal,
												// credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 从PrincipalCollection中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		// 利用登录信息的信息来获取用户的角色或权限（可能需要查询数据库）
		UserRole userRole = new UserRole();
		userRole.setUserId(((User) principal).getId());
		List<UserRole> userRoles = iUserRoleService.getAlls(userRole);
		Set<String> roles = new HashSet<String>();
		Set<String> stringPermissions = new HashSet<String>();

		for (UserRole uR : userRoles) {
			// 设置角色
			Role role = iRoleService.getById(uR.getRoleId());
			roles.add(role.getRoleCode());
			// 设置权限
			String[] PermissionIds = role.getRolePermissionIds().split(",");
			for (String permissionId : PermissionIds) {
				Permission permission = iPermissionService.getById(Integer.valueOf(permissionId));
				stringPermissions.add(permission.getpCode());
			}
		}

		// 创建SimpleAuthorizationInfo对角，并设置其roles,permissions属性
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		simpleAuthorizationInfo.setStringPermissions(stringPermissions);
		// 返回SimpleAuthorizationInfo对象
		return simpleAuthorizationInfo;
	}

	// 获取加密后的值
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("zhang5850");
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(hash.toString());
	}

}
