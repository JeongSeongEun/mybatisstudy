package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
1. 학생테이블의 등록된 레코드의 건수를 출력하기
2. 학생테이블에 등록된 레코드 모든 정보를 출력하기
3. 학생테이블의 등록된 레코드의 1학년 학생의 정보를 출력하기
4. 학생테이블의 등록된 레코드의 이름이 두자인 학생의 정보를 출력하기
5. 학생테이블의 등록된 레코드의 2학년 학생 중 주민번호 기준 여학생 정보를 출력하기
*/
public class Exam1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap=null;
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			sqlMap=new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x=0;
		SqlSession session=sqlMap.openSession();
		x=(Integer)session.selectOne("student.count");
		System.out.println("student 테이블 레코드 갯수: "+x);
		System.out.println("student 테이블의 정보========");
		//selectList : select 구문의 결과가 레코드 여러개인 경우 List 형태로 리턴
		List<Student> list=session.selectList("student.list");
		for(Student s:list)
			System.out.println(s);
		System.out.println();
		System.out.println("student테이블의 1학년 학생들 조회========");
		list=session.selectList("student.selectgrade",1);
		for(Student s:list)
			System.out.println(s);
		System.out.println();
		System.out.println("student테이블의 이름이 2자========");
		list=session.selectList("selectstudent.name","__");
		for(Student s:list)
			System.out.println(s);
		
		System.out.println();
		System.out.println("student테이블의 3학년 학생 중 주민번호 기준 여자========");
		Map<String , Object> map= new HashMap<>();
		map.put("grade",3);
		map.put("jumin",2);
		list=session.selectList("student.juminfm",map);
		for(Student s:list)
			System.out.println(s);
		
	}
}