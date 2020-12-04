package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import mybatisjava.Professor;



public interface ProfessorMapper {
	@Select({"<script>select * from professor"
			+ "<trim prefix ='where' prefixOverrides='AND||OR'>"
			+ "<if test='deptno != null'> and deptno=#{deptno}</if>"
			+ "<if test='no != null'> and no=#{no}</if>"
			+ "<if test='position != null'> and position=#{position}</if>"
			+ "<if test='datas != null'> and ${column} in"
			+ "<foreach collection='datas' item='d' separator=',' open='(' close=')'>"
			+ "#{d}</foreach></if></trim></script>"})
	List<Professor> select(Map map);
	
	

}
