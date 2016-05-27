
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ��� try-catch �����㣺��ȫ �淶����
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
			System.out.println("----1.�������ݿ�����");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("----2.ʹ��DriverManager��ȡ���ݿ�����");
			System.out.println("���ݿ����ӳɹ�: " + conn);
			System.out.println("----3.ʹ��Connection������һ��Statement����");
			smt = conn.createStatement();// ���׳�SQLException
			System.out.println("----4.ʹ��Statement����ִ��SQL���");
			/*
			 * Statement ��������ִ�в��������ļ� SQL ��䣻
			 * Statement������ִ��sql���ķ����� 
			 * 1. execute ��ִ���κ�SQL���--����һ��booleanֵ ���ִ�к�ĵ�һ�������ResultSet���򷵻�true�����򷵻�false 
			 * 2. executeQuery ���ڲ����������������䣬���� SELECT ���--���ز�ѯ���Ľ���� 
			 * 3. executeUpdate ����ִ��DML����DDL���--����һ��������ִ��DML����SQL���Ӱ��ļ�¼������ִ��DDL��䷵��0
			 */
			rs = smt.executeQuery("select * from news limit " + 0 + "," + 4);// ���׳�SQLException

			System.out.println("----5.���������");
			/*
			 * ResultSet���������෽��������ѯ�����
			 * 1. next()����¼ָ������һ��,first(),last()��
			 * 2.getXxx(������|����)��ȡ�м�¼ָ��ָ���У��ض��е�ֵ
			 */

			while (rs.next()) {// ���׳�SQLException
				int a = rs.getInt(1);
				System.out.println(a + "\t" + rs.getString(2) + "\t" + rs.getString(3));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����Class��forName����ʱ���Ҳ���ָ������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * �ر���Դ
			 * 
			 * @param rs,smt,conn
			 */
			try {
				System.out.println("----6.�������ݿ���Դ,��򿪵��ȹ�");
				if (rs != null) {
					// �ر�ResultSet
					rs.close();// ���׳�SQLException
					System.out.println("ResultSet rs���ݿ���Դ���ر�(����)��");
					rs = null;
				}
				if (smt != null) {
					// �ر�Statement
					smt.close();
					System.out.println("Statement smt ���ݿ���Դ���ر�(����)��");
					smt = null;
				}
				if (conn != null) {
					// �ر�Connection
					conn.close();
					System.out.println("Connection conn ���ݿ���Դ���ر�(����)��");
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
