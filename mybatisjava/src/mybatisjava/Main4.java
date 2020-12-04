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
		System.out.println("¸ðµç ÇÐ»ý Á¤º¸ Á¶È¸ÇÏ±â");
		List<Student> list=session.getMapper(StudentMapper.class).select(null);
		for(Student s:list)
			System.out.println(s);
		System.out.println("1ÇÐ³â ÇÐ»ý Á¤º¸ Á¶È¸ÇÏ±â");
		Map<String, Object> map=new HashMap<>();
		map.put("grade", 1);
		list=session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("981213ÇÐ¹ø ÇÐ»ý Á¤º¸ Á¶È¸ÇÏ±â");
		map.clear();
		map.put("studno", 981213);
		list=session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);
		
		System.out.println();
		System.out.println("±è»ñ°« ÇÐ»ý Ãß°¡ÇÏ±â");
		Student st=new Student();
		st.setStudno(1002);
		st.setName("±è»ñ°«");
		st.setJumin("123458-178964");
		st.setId("kimsk");
		int result = session.getMapper(StudentMapper.class).insert(st);
		System.out.println("±è»ñ°« ÇÐ»ý Á¶È¸ÇÏ±â");
		map.clear();
		map.put("name", "±è»ñ°«");
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("±è»ñ°« ÇÐ»ýÀÇ ÇÐ³â1, ¸ö¹«°Ô 80 , Å° 175·Î º¯°æÇÏ±â");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		result=session.getMapper(StudentMapper.class).update(st);
		System.out.println(result+"°Ç ¼öÁ¤");
		System.out.println("±è»ñ°« ÇÐ»ý Á¶È¸ÇÏ±â");
		map.clear();
		map.put("name", "±è»ñ°«");
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);
		System.out.println("1002¹øÇÐ»ý Á¦°ÅÇÏ±â");
		result=session.getMapper(StudentMapper.class).delete(1002);
		System.out.println(result+"°Ç »èÁ¦");
		list = session.getMapper(StudentMapper.class).select(map);
		for(Student s:list)
			System.out.println(s);
		System.out.println();
		System.out.println("101¹ø ÇÐ°ú ÇÐ»ýÁß 1ÇÐ³â ÇÐ»ý Á¶È¸ÇÏ±â");
		list = session.getMapper(StudentMapper.class).select2(101,1);
		for(Student s:list)
			System.out.println(s);
	}
}
