package util;

import com.systoon.passport.inter.IUserManager;
import com.systoon.passport.parameter.UserInfo;
import com.systoon.passport.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboToonUtil{
	private static final Log log = LogFactory.getLog(DubboToonUtil.class);
	
		static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		
	 	public static Long getUserId (String mobile){
	 		context.start();
	 		IUserManager userManager = (IUserManager) context.getBean("userService");
	 		
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userid = userManager.getUserId(userInfo).getUser().getUserId();
	 		log.info("手机号：" + mobile + " 的userid=" + userid);
	 		return userid;
	 	} 	
	 	
	 	public static void main(String[] args) {
			Long userid = util.DubboToonUtil.getUserId("13581535754");
			System.out.println(userid);

		}
}
