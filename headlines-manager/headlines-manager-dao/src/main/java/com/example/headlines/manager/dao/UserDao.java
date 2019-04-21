package com.example.headlines.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.headlines.manager.bean.User;

public interface UserDao {

	@Select("select * from user")
	public List<User> query();
	
	@Insert("insert into user (name,account,password,vcode) values(#{name},#{account},#{password},#{vcode})")
	public void insert(User user);

	@Select("select count(*) from user where account=#{account}")
	public Integer findAccount(User user);
	
	@Select("select name from user where account=#{account} and password=#{password}")
	public String findName(User user);
	
	@Select("select count(*) from user")
	public Integer queryCount();

	@Select("<script>"
            +"select * from user"
            +"<if test='queryText != null'>"
            +"where account LIKE CONCAT('%',#{queryText},'%')"
            +"</if>"+"LIMIT #{start} , #{pagesize}"
            +"</script>")
	public List<User> queryPaging(@Param("queryText")String queryText, @Param("start") int start, @Param("pagesize") int pagesize);

	@Select("<script>"
            +"select count(*) from user"
            +"<if test='queryText != null'>"
            +"where account LIKE CONCAT('%',#{queryText},'%')"
            +"</if>"+"</script>")
	public Integer querySatisfyCount(@Param("queryText")String queryText);

	@Delete("delete from user where id=#{id}")
	public void deleteById(Integer id);

	@Delete("<script>"+ 
			"delete from user where id in" + 
			"<foreach collection='userids' open='(' item='userid' separator=',' close=')'> #{userid}</foreach>" + 
			"</script>")
	public void deleteUsers(Map<String, Integer[]> map);

}
