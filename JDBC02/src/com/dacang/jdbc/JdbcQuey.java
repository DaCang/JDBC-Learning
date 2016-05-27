package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuey {

	//数据库连接地址，连接字符串
	private final static String URL = "jdbc:mysql://localhost:3306/mysql";
	//用户名
	public final static String USERNAME = "root";
	//密码
	public final static String PASSWORD = "10ASDasd";
	//加载的驱动程序类（这个类就在我们导入的jar包中）
	public final static String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * @方法描述 TODO    执行插入记录sql
	 * @param person 
	 *  
	 */
	public static void insert(Person person) {
		Connection conn = null;
		PreparedStatement pst = null;

		//问号可以理解为占位符，有几个问号就代表要插入几个列，这样看来sql代码就比较简洁。
		String sql = "insert  into person(id,name,age,description) value(?,?,?,?)";

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pst = conn.prepareStatement(sql);// 会抛出SQLException
			//问号设值，参数1代表第一个问号的位置，以此类推。
			pst.setObject(1, person.getpId());
			pst.setObject(2, person.getpName());
			pst.setObject(3, person.getpAge());
			pst.setObject(4, person.getpDescription());
			pst.executeUpdate();
			pst.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person(13, "dddd", 23, "hhhhh");
		//将整个person对象进去，代表的是数据库中的一条记录。
		insert(p);
		query();

	}

	//方法：查询操作
	public static void query() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select id,name,age,description from person";
			Statement state = conn.createStatement();
			//执行查询并返回结果集
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) { //通过next来索引：判断是否有下一个记录

				int id = rs.getInt(1); //方法：int java.sql.ResultSet.getInt(int columnIndex) throws SQLException

				String name = rs.getString(2);
				int age = rs.getInt(3);
				String description = rs.getString(4);
				System.out.println("id=" + id + ",name=" + name + ",age=" + age + ",description=" + description);
			}
			rs.close();
			state.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
