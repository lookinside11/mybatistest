package lig.DAO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    //查询所有数据
    public List<UserEntity> getAllUsers();
    //得到范围数据,这里主要是演示一下传多个参数给mapper，以便于写更加复杂的sql语句。
    public  List<UserEntity> getUsersByIds(@Param("ids") int ids,@Param("idm") int idm);

    //这是另一张传多参的方式，把所有的参数封装到一个类里，显然麻烦
    public  List<UserEntity> getUsersByNames(Query参数 query参数);
    //插入一条数据
    public int insertUser(UserEntity user);

    //
    public UserEntity getOneUserByName(@Param("name") String name);

    public UserEntity getOneUser(int id);

    public int deleteUser(int id);

    public int getUsersCount();

    public Map<Object,Object> getAllUsersByMap();

    public List<UserEntity> getAllUsersByResultMap();

    public List<UserEntity> getUserAsLike(@Param("name") String name);
}
