package mybatisjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main3 {
	private final static String NS = "student2.";
	private static SqlSessionFactory sqlMap;
	static { //static 초기화 블럭. 메인보다 먼저 실행
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
		System.out.println("학생 전체 레코드 조회하기");
		List<Student> list=session.selectList(NS+"select1");
		for(Student s:list)
			System.out.println(s);
		Map<String, Object> map=new HashMap<>();
		System.out.println("1학년 학생 레코드 조회하기");
		map.put("grade", 1);
		list=session.selectList(NS+"select1",map);
		for(Student s: list)
			System.out.println(s);
		System.out.println();
		System.out.println("학번이 981213학생 레코드 조회하기");
		map.clear();
		map.put("studno", 981213);
		Student s1=session.selectOne(NS+"select1",map);
		System.out.println(s1);
		System.out.println();
		
		System.out.println("학생중 키가 180이상인 학생 레코드 조회하기");
		map.clear();
		map.put("height", 180);
		list=session.selectList(NS+"select1",map);
		for(Student s: list)
			System.out.println(s);
		//select1: 조건이 한개인 경우만 가능
		System.out.println("1학년 학생중 키가 180 이상인 학생 레코드 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list=session.selectList(NS+"select2",map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("1학년 학생 레코드 조회하기");
		map.clear();
		map.put("grade", 1);
		list=session.selectList(NS+"select2",map);
		for(Student s:list)
			System.out.println(s);
		System.out.println();
		System.out.println("1학년 학생중 키가 180 이상인 학생 레코드 조회하기======");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list=session.selectList(NS+"select3",map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("1학년 학생중 키가 180 이상이고, 80키로 이상인 학생 레코드 조회하기======");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		map.put("weight", 80);
		list=session.selectList(NS+"select3",map);
		for(Student s:list)
			System.out.println(s);
		System.out.println();
		System.out.println("101,201,301학과에 속한 학생의 레코드 조회하기=====");
		List<Integer> mlist=Arrays.asList(101,201,301);
		map.clear();
		map.put("column", "major1");
		map.put("datas", mlist);
		list=session.selectList(NS+"select4",map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("몸무게가 75, 80, 95인 학생의 레코드 조회하기======");
		list=session.selectList(NS+"select4",map);
		List<Integer> wlist=Arrays.asList(75,80,95);
		map.clear();
		map.put("column", "weight");
		map.put("datas", wlist);
		list=session.selectList(NS+"select4",map);
		for(Student s:list)
			System.out.println(s);
		
	}

}
