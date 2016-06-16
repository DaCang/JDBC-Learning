package com.dacang.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @方法描述 TODO   通过id查询
	 * @param id  
	 * @return p      p对象
	 *  
	 */
	public static Person findById(int id) {
		Person p = null;
		//通过工具类获取数据库连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select name,age,description from person where id=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
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
			//通过工具类关闭数据库连接
			DBUtils.close(rs, ps, conn);
		}
		return p;

	}

	//	private  void listPrint(List<Person> list) {
	//		for (Object obj : list) {// 增强 的for循环
	//			System.out.println(obj);
	//		}
	//	}

	public static void main(String[] args) {
		Person person = new Person();
		person = findById(1);
		System.out.println("查找单条记录：" + person);
		List<Person> list = new ArrayList<Person>();
	        list = new Test().Quey();
		for (int i = 0; i < list.size(); i++) {
			Person person1 = list.get(i);
			System.out.println("|id:" + person1.getId() + "\t" + "| name:" + person1.getName() + "\t" + "| age:"
					+ person1.getAge() + "\t" + "| description:" + person1.getDescription());
		}

	}

	public static List<Person> Quey() {
		Person p = null;
		List<Person> PersonList = new ArrayList<Person>();
		//通过工具类获取数据库连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from person";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
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
			//通过工具类关闭数据库连接
			DBUtils.close(rs, ps, conn);
		}
		return PersonList;
	}

}
