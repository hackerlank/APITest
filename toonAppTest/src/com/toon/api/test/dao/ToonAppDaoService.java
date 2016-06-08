package com.toon.api.test.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;


import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

import com.toon.api.test.daoMapping.ToonAppMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ToonAppDaoService {
	
	private static final Log log = LogFactory.getLog(ToonAppDaoService.class);
	private static SqlSession sqlSession = null;
	private static SqlSessionFactory sessionFactory = null;
	private static ToonAppMapper toonAppMapper = null;

	static {
		String resource = "myBatis.xml";
		InputStream is = ToonAppDaoService.class.getClassLoader().getResourceAsStream(resource);
	    sessionFactory = new SqlSessionFactoryBuilder().build(is);
	}
	
	public static void openSqlSession() {
		log.info("openSqlSession");
		sqlSession = sessionFactory.openSession();
		toonAppMapper = sqlSession.getMapper(ToonAppMapper.class);
	}

	public static void commit() {
		log.info("commit");
		sqlSession.commit();
	}

	public static void closeSqlSession() {
		log.info("closeSqlSession");
		try {
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearSqlSession() {
		log.info("clearSqlSession");
		sqlSession.clearCache();
	}
	
	public static ToonAppMapper getToonAppDao() {
		return toonAppMapper;
	}
	public static void setToonAppDao(ToonAppMapper toonAppMapper) {
		ToonAppDaoService.toonAppMapper = toonAppMapper;
	}
	
	
	/*public static void main(String[] args) throws IOException {
		
	
	 //mybatis的配置文件
	    String resource = "myBatis.xml";
	    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
	    InputStream is = ToonAppDaoService.class.getClassLoader().getResourceAsStream(resource);
	    //构建sqlSession的工厂
	    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
	    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
	    //Reader reader = Resources.getResourceAsReader(resource); 
	    //构建sqlSession的工厂
	    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	    //创建能执行映射文件中sql的sqlSession
	    SqlSession session = sessionFactory.openSession();
//	    *//**
//	     * 映射sql的标识字符串，
//	     * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
//	     * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
//	     *//*
	    String statement = "com.toon.api.test.daoMapping.ToonAppMapper.getUserId";//映射sql的标识字符串
	    //执行查询返回一个唯一user对象的sql
	    
	    Long startTime = System.currentTimeMillis();	
	    
	    String token = session.selectOne(statement, "13581535754");
	    
   
	    Long endTime = System.currentTimeMillis();	
	    
	    Long useTime = endTime - startTime;	
		
		
		System.out.println("============================================\r\n该接口响应时间为：" + useTime + "毫秒\r\n============================================");
		
	    System.out.println(token);
	    
	   
	    Long startTime1 = System.currentTimeMillis();	
	    
	    String token1 = session.selectOne(statement, "13581535754");
	    
	    Long endTime1 = System.currentTimeMillis();	
	    
	    Long useTime1 = endTime1 - startTime1;	
		
		
		System.out.println("============================================\r\n该接口响应时间为：" + useTime1 + "毫秒\r\n============================================");
		
	    System.out.println(token1);
	}*/
}
