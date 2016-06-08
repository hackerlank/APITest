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
		
	
	 //mybatis�������ļ�
	    String resource = "myBatis.xml";
	    //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
	    InputStream is = ToonAppDaoService.class.getClassLoader().getResourceAsStream(resource);
	    //����sqlSession�Ĺ���
	    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
	    //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
	    //Reader reader = Resources.getResourceAsReader(resource); 
	    //����sqlSession�Ĺ���
	    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	    //������ִ��ӳ���ļ���sql��sqlSession
	    SqlSession session = sessionFactory.openSession();
//	    *//**
//	     * ӳ��sql�ı�ʶ�ַ�����
//	     * me.gacl.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
//	     * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
//	     *//*
	    String statement = "com.toon.api.test.daoMapping.ToonAppMapper.getUserId";//ӳ��sql�ı�ʶ�ַ���
	    //ִ�в�ѯ����һ��Ψһuser�����sql
	    
	    Long startTime = System.currentTimeMillis();	
	    
	    String token = session.selectOne(statement, "13581535754");
	    
   
	    Long endTime = System.currentTimeMillis();	
	    
	    Long useTime = endTime - startTime;	
		
		
		System.out.println("============================================\r\n�ýӿ���Ӧʱ��Ϊ��" + useTime + "����\r\n============================================");
		
	    System.out.println(token);
	    
	   
	    Long startTime1 = System.currentTimeMillis();	
	    
	    String token1 = session.selectOne(statement, "13581535754");
	    
	    Long endTime1 = System.currentTimeMillis();	
	    
	    Long useTime1 = endTime1 - startTime1;	
		
		
		System.out.println("============================================\r\n�ýӿ���Ӧʱ��Ϊ��" + useTime1 + "����\r\n============================================");
		
	    System.out.println(token1);
	}*/
}
