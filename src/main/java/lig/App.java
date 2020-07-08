package lig;

import com.github.pagehelper.PageHelper;
import lig.DAO.Query参数;
import lig.DAO.UserDAO;
import lig.DAO.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    static  String conf= "mybatisConf.xml";
    static   SqlSession sqlSession;
    static
    {
        InputStream in= null;
        try {
            in = Resources.getResourceAsStream(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        sqlSession=factory.openSession();
    }

    public static void main( String[] args ) throws IOException {
        //根据接口，自动生成一个实现类，这真是牛逼的功能。自动根据接口去找映射文件里的语句id。
        getAllUsersButPageHelper();

    }


    static void getAllUsersButPageHelper()
    {
        //找到对应的MPPER空间，执行sql语句并返回结果，这个命名空间是一定要存在的。
        //lig.DAO.UserDAO相当于mapper的ID，后面的getAllUsers相当于select语句的id
        PageHelper.startPage(3,2);
        String sqlid="lig.DAO.UserDAO.getAllUsers";
        List<UserEntity> list=sqlSession.selectList(sqlid);
        for (UserEntity u:list
        ) {
            System.out.println(u.toString());
        }
        sqlSession.close();

    }
    static void 测试$占位符的查询语句 ()
    {
        UserDAO ud=sqlSession.getMapper(lig.DAO.UserDAO.class);
        UserEntity user= ud.getOneUserByName("王五");
        System.out.println(user);
        sqlSession.close();
    }
    static  void 测试传多个参数()
    {
        UserDAO ud= sqlSession.getMapper(lig.DAO.UserDAO.class);

        List<UserEntity> users=ud.getUsersByIds(100,110);
        Query参数 query参数=new Query参数();
        query参数.setName1("小明");
        query参数.setName2("小张");
        List<UserEntity> users2=ud.getUsersByNames(query参数);
        for(UserEntity u :users2)
        {
            System.out.println(u);
        }

    }
    static void getAllUsersByLike()
    {

        String sqlid="lig.DAO.UserDAO.getAllUsersAsLike";
        UserDAO ud=sqlSession.getMapper(UserDAO.class);
        List<UserEntity> list=ud.getUserAsLike("%小明%" );
        sqlSession.close();
    }
    static void getAllUsersByResultMap()
    {

        String sqlid="lig.DAO.UserDAO.getAllUsersByResultMap";
        UserDAO ud=sqlSession.getMapper(UserDAO.class);
        List<UserEntity> list=ud.getAllUsersByResultMap();
        sqlSession.close();
    }
    static void getAllUsersByMap()
    {
        //找到对应的MPPER空间，执行sql语句并返回结果，这个命名空间是一定要存在的。
        //lig.DAO.UserDAO相当于mapper的ID，后面的getAllUsers相当于select语句的id
        String sqlid="lig.DAO.UserDAO.getAllUsers";
        UserDAO ud=sqlSession.getMapper(UserDAO.class);
        Map<Object,Object> map=ud.getAllUsersByMap();
        sqlSession.close();
    }

    static void getAllUsers()
    {
       //找到对应的MPPER空间，执行sql语句并返回结果，这个命名空间是一定要存在的。
        //lig.DAO.UserDAO相当于mapper的ID，后面的getAllUsers相当于select语句的id
        String sqlid="lig.DAO.UserDAO.getAllUsers";
        List<UserEntity> list=sqlSession.selectList(sqlid);
        for (UserEntity u:list
             ) {
            System.out.println(u.toString());
        }
        sqlSession.close();

    }
    static void insertUser()
    {
        String sqlid="lig.DAO.UserDAO.insertUser";
        UserEntity user=new UserEntity();
        user.setName("王五");
        user.setAddress("王五大道");
        user.setAge("28");
        user.setBeizhu("第一条备注");
        System.out.println("添加了:"+ sqlSession.insert(sqlid,user)+"条数据");
        sqlSession.commit();
        sqlSession.close();
    }
    static void getOneUser()
    {
        String sqlid="lig.DAO.UserDAO.getOneUser";
        UserEntity user=null;
        user=(UserEntity)sqlSession.selectOne(sqlid,4);
        System.out.println(user.toString());
    }

    static  void deleteOneUser(int id)
    {
        String sqlid="lig.DAO.UserDAO.deleteUser";
        int num=sqlSession.delete(sqlid,id);
        sqlSession.commit();
        System.out.println("删除了"+num+"记录!");
    }
}
