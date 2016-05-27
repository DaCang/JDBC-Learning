package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @�������� TODO   ͨ��id��ѯ
	 * @param id  
	 * @return p      p����
	 *  
	 */
	public static Person findById(int id) {
		Person p = null;
		//ͨ���������ȡ���ݿ�����
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select name,age,description from person where id=?";
		try {
			ps = conn.prepareStatement(sql);
			//����ռλ����Ӧ��ֵ
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Person();
				p.setName(rs.getString(1));
				p.setAge(rs.getInt(2));
				p.setDescription(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ͨ��������ر����ݿ�����
			DBUtils.close(rs, ps, conn);
		}
		return p;

	}

	private static void listPrint(List<Person> list) {
		for (Object obj : list) {// ��ǿ ��forѭ��
			System.out.println(obj);
		}
	}

	public static void main(String[] args) {
		Person person = new Person();
		person = findById(1);
		System.out.println("���ҵ�����¼��" + person);
		List<Person> list = new ArrayList<Person>();
		list = Quey();
		listPrint(list);

	}

	public static List<Person> Quey() {
		Person p = null;
		List<Person> PersonList = new ArrayList<Person>();
		//ͨ���������ȡ���ݿ�����
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from person";
		try {
			ps = conn.prepareStatement(sql);
			//����ռλ����Ӧ��ֵ
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String description = rs.getString("description");
				p = new Person(id, name, age, description);
				PersonList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ͨ��������ر����ݿ�����
			DBUtils.close(rs, ps, conn);
		}
		return PersonList;
	}

}