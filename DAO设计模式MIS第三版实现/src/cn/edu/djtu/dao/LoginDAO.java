package cn.edu.djtu.dao;

import cn.edu.djtu.vo.Emp;

public interface LoginDAO {
      public abstract Emp findByName(String username) throws Exception;
      public abstract boolean save(Emp login) throws Exception;
      
}
