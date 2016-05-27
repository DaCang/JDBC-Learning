
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 多个 try-catch 出发点：安全 规范代码
 */

public class JdbcText1 {
	private static String url;
	private static String user;
	private static String passwd;

	public static void main(String[] args) {
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		url = "jdbc:mysql://localhost:3306/mysql";
		user = "root";
		passwd = "10ASDasd";
		try {
			System.out.println("----1.加载数据库驱动");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("----2.使用DriverManager获取数据库连接");
			System.out.println("数据库连接成功: " + conn);
			System.out.println("----3.使用Connection来创建一个Statement对象");
			smt = conn.createStatement();// 会抛出SQLException
			System.out.println("----4.使用Statement对象执行SQL语句");
			/*
			 * Statement 对象用于执行不带参数的简单 SQL 语句；
			 * Statement有三种执行sql语句的方法： 
			 * 1. execute 可执行任何SQL语句--返回一个boolean值 如果执行后的第一个结果是ResultSet，则返回true，否则返回false 
			 * 2. executeQuery 用于产生单个结果集的语句，例如 SELECT 语句--返回查询到的结果集 
			 * 3. executeUpdate 用于执行DML语句和DDL语句--返回一个整数，执行DML代表被SQL语句影响的记录条数；执行DDL语句返回0
			 */
			rs = smt.executeQuery("select * from news limit " + 0 + "," + 4);// 会抛出SQLException

			System.out.println("----5.操作结果集");
			/*
			 * ResultSet对象有两类方法操作查询结果集
			 * 1. next()将记录指针下移一行,first(),last()等
			 * 2.getXxx(列索引|列名)获取有记录指针指向行，特定列的值
			 */

			while (rs.next()) {// 会抛出SQLException
				int a = rs.getInt(1);
				System.out.println(a + "\t" + rs.getString(2) + "\t" + rs.getString(3));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("调用Class的forName方法时，找不到指定的类");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 关闭资源
			 * 
			 * @param rs,smt,conn
			 */
			try {
				System.out.println("----6.回收数据库资源,后打开的先关");
				if (rs != null) {
					// 关闭ResultSet
					rs.close();// 会抛出SQLException
					System.out.println("ResultSet rs数据库资源被关闭(回收)！");
					rs = null;
				}
				if (smt != null) {
					// 关闭Statement
					smt.close();
					System.out.println("Statement smt 数据库资源被关闭(回收)！");
					smt = null;
				}
				if (conn != null) {
					// 关闭Connection
					conn.close();
					System.out.println("Connection conn 数据库资源被关闭(回收)！");
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
