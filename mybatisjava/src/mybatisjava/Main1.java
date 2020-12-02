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
		System.out.println("member ���̺� ���ڵ� ����: "+x);
		System.out.println("member ���̺��� ����========");
		//selectList : select ������ ����� ���ڵ� �������� ��� List ���·� ����
		List<Member> list=session.selectList("member.list");
		for(Member m:list)
			System.out.println(m);
		System.out.println("member ���̺��� admin���� =======");
		Member mem=session.selectOne("member.selectid","admin");
		System.out.println(mem);
		System.out.println("member ���̺��� �̸��� '��'�̸� ���� ���� =====");
		list = session.selectList("member.selectname","%��%");
		for(Member m:list)
			System.out.println(m);
		System.out.println("member ���̺��� �̸��� '��'�̸� ���� ���� =====");
		list = session.selectList("member.selectname2","��");
		for(Member m:list)
			System.out.println(m);
		//�ΰ��̻��� �Ķ���� ����
		System.out.println("�̸� ������ ������ ��ȸ�ϱ�");
		System.out.println("member ���̺��� �̸��� '��'�̸� ���� ��������====");
		Map<String, Object> map=new HashMap<>();
		map.put("name","%��%");
		map.put("gender",1);
		list=session.selectList("member.selectnamegender",map);
		for(Member m: list)
			System.out.println(m);
	}
}
