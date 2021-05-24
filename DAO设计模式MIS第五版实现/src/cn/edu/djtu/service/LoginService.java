package cn.edu.djtu.service;

import cn.edu.djtu.vo.Login;

public interface LoginService {
   public abstract String logon(Login login);
   public abstract String addLogin(Login login);
   
}
