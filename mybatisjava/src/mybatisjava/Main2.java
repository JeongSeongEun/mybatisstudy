package mybatisjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main2 {
	private final static String NS = "student.";
	private static SqlSessionFactory sqlMap;
	static { //static �ʱ�ȭ ����. ���κ��� ���� ����
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	public static void main(String[] args) {
		SqlSession session=sqlMap.openSession();
		System.out.println("Student���̺��� ���ڵ� �߰��ϱ�");
		Student st=new Student();
		st.setStudno(1002);
		st.setName("ȫ�浿");
		st.setGrade(1);
		st.setId("hongkd3");
		st.setJumin("123455-456758");
		st.setMajor1(101);
		int cnt=session.insert(NS+"insert",st);
		System.out.println("student ���ڵ� �߰�"+cnt);
		Student dispt=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(dispt);
		/*
		1001�� �л��� �г��� 2�г����� ������ 80 Ű 175
		�����Լ� 1001�� �����ϱ�
		*/
		st.setGrade(2);
		st.setWeight(80);
		st.setHeight(175);
		st.setProfno(1001);
		cnt=session.update(NS+"update",st);
		System.out.println("student ���ڵ� ����"+cnt);
		dispt=session.selectOne(NS+"selectno",st.getStudno());
		System.out.println(dispt);
		System.out.println("1001�� �л� �����ϱ�");
		cnt=session.delete(NS+"delete",st.getStudno());
		dispt=session.selectOne(NS+"selectno",st.getStudno());
		//�г��� 2�г��� �л��� ���� ����ϱ�
		System.out.println("�г��� 2�г� �̻��� �л��� ���� ����ϱ�");
		Map<String, Object> map=new HashMap<>();
		map.put("col", "grade");
		map.put("value", 2);
		List<Student> list=session.selectList(NS+"select2",map);
		for(Student s : list)
			System.out.println(s);
		// Ű�� 180�̻��� �л��� ���� ����ϱ�
		System.out.println("Ű�� 180�̻��� �л��� ���� ����ϱ�");
		map.clear();
		map.put("col", "height");
		map.put("value", 180);
		list=session.selectList(NS+"select2",map);
		for(Student s: list)
			System.out.println(s);
		//session.commit();
		
	}
}