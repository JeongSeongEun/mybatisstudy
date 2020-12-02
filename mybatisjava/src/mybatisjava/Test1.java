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
		System.out.println("1. professor ���̺� ���ڵ� ����: "+x);
		System.out.println("2. professor ���̺��� ����========");
		//selectList : select ������ ����� ���ڵ� �������� ��� List ���·� ����
		List<Professor> list=session.selectList("professor.list");
		for(Professor p:list)
			System.out.println(p);
		System.out.println();
		System.out.println("3. professor���̺��� 101�а� ������ ��ȸ========");
		list=session.selectList("professor.selectdeptno",101);
		for(Professor p:list)
			System.out.println(p);
		System.out.println("4. professor���̺��� ���� ���� �达�� �ð����� ����ϱ�========");
		Map<String, Object> map =new HashMap<>();
		map.put("name","��%");
		map.put("position","�ð�����");
		list=session.selectList("professor.selectnameposition",map);
		for(Professor p:list)
			System.out.println(p);
	}

}
