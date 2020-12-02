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

public class Test1 {

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
		x=(Integer)session.selectOne("professor.count");
		System.out.println("1. professor 테이블 레코드 갯수: "+x);
		System.out.println("2. professor 테이블의 정보========");
		//selectList : select 구문의 결과가 레코드 여러개인 경우 List 형태로 리턴
		List<Professor> list=session.selectList("professor.list");
		for(Professor p:list)
			System.out.println(p);
		System.out.println();
		System.out.println("3. professor테이블의 101학과 교수들 조회========");
		list=session.selectList("professor.selectdeptno",101);
		for(Professor p:list)
			System.out.println(p);
		System.out.println("4. professor테이블의 교수 성이 김씨인 시간강사 출력하기========");
		Map<String, Object> map =new HashMap<>();
		map.put("name","김%");
		map.put("position","시간강사");
		list=session.selectList("professor.selectnameposition",map);
		for(Professor p:list)
			System.out.println(p);
	}

}
