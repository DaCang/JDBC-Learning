package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcQuey {

	//���ݿ����ӵ�ַ
	private final static String URL = "jdbc:mysql://localhost:3306/mysql";
	//�û���
	public final static String USERNAME = "root";
	//����
	public final static String PASSWORD = "10ASDasd";
	//���ص����������ࣨ�����������ǵ����jar���У�
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection conn;
	private static PreparedStatement pst;

	/**
	 * @�������� TODO ������  ��ѯ����
	 *  
	 */
	/**
	 * @�������� TODO  ����Ͳ�ѯ���������
	 *  
	 */
	public static void insertAndQuery() {
		conn = null;
		pst = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			//1.����Ϊ�ֶ��ύ����
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

			//2.�������sql���ɹ������ύ����
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����Class��forName����ʱ���Ҳ���ָ������");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/**
			 * �ر���Դ
			 * 
			 * @param 
			 */
			try {

				if (pst != null) {
					// �ر�Statement
					pst.close();

					pst = null;
				}
				if (conn != null) {
					// �ر�Connection
					conn.close();

					conn = null;
				}
				//3.ֻҪ��һ��sql�����ִ���������ع� 
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
