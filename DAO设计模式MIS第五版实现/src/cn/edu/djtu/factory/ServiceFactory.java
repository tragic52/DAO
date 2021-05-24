package cn.edu.djtu.factory;

import cn.edu.djtu.service.impl.LoginServiceImpl;

public class ServiceFactory {
   public static LoginServiceImpl getLoginServiceImpl() {
	   return new LoginServiceImpl();
   }
}
