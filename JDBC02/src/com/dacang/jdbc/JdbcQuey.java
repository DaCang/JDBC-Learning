package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuey {

	//���ݿ����ӵ�ַ�������ַ���
	private final static String URL = "jdbc:mysql://localhost:3306/mysql";
	//�û���
	public final static String USERNAME = "root";
	//����
	public final static String PASSWORD = "10ASDasd";
	//���ص����������ࣨ�����������ǵ����jar���У�
	public final static String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * @�������� TODO    ִ�в����¼sql
	 * @param person 
	 *  
	 */
	public static void insert(Person person) {
		Connection conn = null;
		PreparedStatement pst = null;

		//�ʺſ������Ϊռλ�����м����ʺžʹ���Ҫ���뼸���У���������sql����ͱȽϼ�ࡣ
		String sql = "insert  into person(id,name,age,description) value(?,?,?,?)";

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pst = conn.prepareStatement(sql);// ���׳�SQLException
			//�ʺ���ֵ������1�����һ���ʺŵ�λ�ã��Դ����ơ�
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
		//������person�����ȥ������������ݿ��е�һ����¼��
		insert(p);
		query();

	}

	//��������ѯ����
	public static void query() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select id,name,age,description from person";
			Statement state = conn.createStatement();
			//ִ�в�ѯ�����ؽ����
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) { //ͨ��next���������ж��Ƿ�����һ����¼

				int id = rs.getInt(1); //������int java.sql.ResultSet.getInt(int columnIndex) throws SQLException

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
