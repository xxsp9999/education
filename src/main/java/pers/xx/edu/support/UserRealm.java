package pers.xx.edu.support;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import pers.xx.edu.entity.User;
import pers.xx.edu.service.UserService;



/**
 * 后台管理员Realm
 * @author lch
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	
	/**
	 * 授权处理
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		System.out.println("userRealm授权处理");
		if (principalcollection == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		// String name = (String)principals.fromRealm(getName()).iterator().next();
		//String name = (String) super.getAvailablePrincipal(principals);
//		List<String> roles = new ArrayList<String>();
	//	List<String> permissions = new ArrayList<String>();
/*		User user = userService.getByLoginName(name);
		if (user == null) {
			throw new AuthorizationException();
		}
		Department dept = user.getDepartment();
		if (user.getLoginName().equals(name)) {
			roles = systemService.getShiroRole(user, dept);
			permissions = systemService.getShiroPermission(user, dept);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);
		info.addStringPermissions(permissions);*/
		//return /*info*/null;
		
		
		
		
		 //授权流程
        //第一步 从PrincipalCollection里获取授权的主要身份信息
  //      String userName = (String) principalcollection.getPrimaryPrincipal();
        //第二步 从数据库获取当前用户所拥有的角色权限[此处演示为模拟数据]
        ArrayList<String> roleAuthorization = new ArrayList<String>();
        roleAuthorization.add("vip");
        //第三步 从数据库获取角色对应的资源权限[此处演示为模拟数据]
        ArrayList<String> resourceAuthorization = new ArrayList<String>();
        resourceAuthorization.add("vip:read");
        resourceAuthorization.add("svip:write");
        resourceAuthorization.add("svip:exec");
        //第四步 创建SimpleAuthorizationInfo对象
        SimpleAuthorizationInfo sa = new SimpleAuthorizationInfo();
        //第五步将从数据库里查询到的角色权限与资源权限 信息填充到SimpleAuthorizationInfo对象中
        sa.addRoles(roleAuthorization);
        sa.addStringPermissions(resourceAuthorization);
        //第六步 返回
        return sa;
	}

	/**
	 * 认证处理
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		    System.out.println("userRealm认证处理");
		    User user = null;
	        // 1. 把AuthenticationToken转换为CustomizedToken
	        CustomizedToken customizedToken = (CustomizedToken) token;
	        // 2. 从CustomizedToken中获取username
	        String username = customizedToken.getUsername();
	        // 3. 若用户不存在，抛出UnknownAccountException异常
	        user = userService.getByLoginName(username);
	        if (user == null)
	            throw new UnknownAccountException("用户不存在！");
	        // 4.
	        // 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为SimpleAuthenticationInfo
	        // 以下信息从数据库中获取
	        // （1）principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象
	        Object principal = username;
	        // （2）credentials：密码
	        Object credentials = user.getPassword();
	        // （3）realmName：当前realm对象的name，调用父类的getName()方法即可
	        String realmName = getName();
	        // （4）盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
	       // ByteSource credentialsSalt = ByteSource.Util.bytes(username);
	        /*SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt,
	                realmName);*/
	        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,realmName);
	        return info;
		
	}
	
	// 清除用户授权缓存信息，用于实现修改权限后无需重启的更新subject
	public void clearAuthzCache() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject()
				.getPrincipals());
	}

}
