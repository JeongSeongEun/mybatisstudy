package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import mybatisjava.Student;
/*
interface 방식으로 Mybatis사용하기
	네임스페이스: 인터페이스의 이름. mapper.StudentMapper
	id속성값: sql구문을 저장하기위한 고유의 값.
			메서드의 이름으로 sql구문을 저장함.
			=>메서드 오버로딩이 되면 실행시 오류 발생
	parameterType: 매개변수.
		두개이상의 값을 매개변수로 지정할 경우 map을 이용
		=> 어노테이션을 이용하여 두개이상의 값을 매개변수로 지정 가능
	returnType: 리턴타입으로 지정.
		
*/
public interface StudentMapper {
	//@Select("select * from student")
	@Select({"<script>","select * from student", "<if test='grade !=null'> where grade = #{grade}</if>","</script>"})
	List<Student> select(Map map);

}
