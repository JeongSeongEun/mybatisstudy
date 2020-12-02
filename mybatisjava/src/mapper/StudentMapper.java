package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import mybatisjava.Student;
/*
interface ������� Mybatis����ϱ�
	���ӽ����̽�: �������̽��� �̸�. mapper.StudentMapper
	id�Ӽ���: sql������ �����ϱ����� ������ ��.
			�޼����� �̸����� sql������ ������.
			=>�޼��� �����ε��� �Ǹ� ����� ���� �߻�
	parameterType: �Ű�����.
		�ΰ��̻��� ���� �Ű������� ������ ��� map�� �̿�
		=> ������̼��� �̿��Ͽ� �ΰ��̻��� ���� �Ű������� ���� ����
	returnType: ����Ÿ������ ����.
		
*/
public interface StudentMapper {
	//@Select("select * from student")
	@Select({"<script>","select * from student", "<if test='grade !=null'> where grade = #{grade}</if>","</script>"})
	List<Student> select(Map map);

}
