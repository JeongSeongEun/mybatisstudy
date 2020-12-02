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

import mapper.StudentMapper;

public class Main4 {
	private final static String NS="mapper.StudentMapper.";
	private static SqlSessionFactory sqlMap;
	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap=new SqlSessionFactoryBuilder().build(input);
	}
	public static void main(String[] args) {
		SqlSession session=sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		List<Student> list=session.getMapper(StudentMapper.class).select(null);
		for(Student s:list)
			System.out.println(s);
		System.out.println("1학년 학생 정보 조회하기");
		Map<String, Object> map=new HashMap<>();
		map.put("grade", 1);
		list=session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);

	}

}
