package cn.edu.djtu.dao;

import cn.edu.djtu.vo.Login;

public interface LoginDAO {
      public abstract Login findByName(String username) throws Exception;
      public abstract boolean save(Login login) throws Exception;
      
}
