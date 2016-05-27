package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcQuey {

	//数据库连接地址
	private final static String URL = "jdbc:mysql://localhost:3306/mysql";
	//用户名
	public final static String USERNAME = "root";
	//密码
	public final static String PASSWORD = "10ASDasd";
	//加载的驱动程序类（这个类就在我们导入的jar包中）
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection conn;
	private static PreparedStatement pst;

	/**
	 * @方法描述 TODO 方法：  查询操作
	 *  
	 */
	/**
	 * @方法描述 TODO  插入和查询，事务管理
	 *  
	 */
	public static void insertAndQuery() {
		conn = null;
		pst = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			//1.设置为手动提交事务
			conn.setAutoCommit(false);
			String sql1 = "insert into user(name,pwd)values(?,?)";
			String sql2 = "update user set pwd=? where name=?";

			pst = conn.prepareStatement(sql1);
			pst.setString(1, "smyhvae");
			pst.setString(2, "007");
			pst.executeUpdate();

			pst = conn.prepareStatement(sql2);
			pst.setString(1, "008");
			pst.setString(2, "smyh");
			pst.executeUpdate();

			//2.如果所有sql语句成功，则提交事务
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("调用Class的forName方法时，找不到指定的类");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 关闭资源
			 * 
			 * @param 
			 */
			try {

				if (pst != null) {
					// 关闭Statement
					pst.close();

					pst = null;
				}
				if (conn != null) {
					// 关闭Connection
					conn.close();

					conn = null;
				}
				//3.只要有一个sql语句出现错误，则将事务回滚 
				conn.rollback();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertAndQuery();

	}
}
