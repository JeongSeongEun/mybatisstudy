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

public class Main1 {
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
		x=(Integer)session.selectOne("member.count");
		System.out.println("member 테이블 레코드 갯수: "+x);
		System.out.println("member 테이블의 정보========");
		//selectList : select 구문의 결과가 레코드 여러개인 경우 List 형태로 리턴
		List<Member> list=session.selectList("member.list");
		for(Member m:list)
			System.out.println(m);
		System.out.println("member 테이블의 admin정보 =======");
		Member mem=session.selectOne("member.selectid","admin");
		System.out.println(mem);
		System.out.println("member 테이블에서 이름이 '빵'이름 가진 정보 =====");
		list = session.selectList("member.selectname","%빵%");
		for(Member m:list)
			System.out.println(m);
		System.out.println("member 테이블에서 이름이 '정'이름 가진 정보 =====");
		list = session.selectList("member.selectname2","정");
		for(Member m:list)
			System.out.println(m);
		//두개이상의 파라미터 전달
		System.out.println("이름 정보와 성별로 조회하기");
		System.out.println("member 테이블에서 이름이 '성'이름 가진 남자정보====");
		Map<String, Object> map=new HashMap<>();
		map.put("name","%성%");
		map.put("gender",1);
		list=session.selectList("member.selectnamegender",map);
		for(Member m: list)
			System.out.println(m);
	}
}
