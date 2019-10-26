package com.beluga.mybatis.test;


import com.beluga.mybatis.Mapper.EmployeeMapper;
import com.beluga.mybatis.Mapper.EmployeeMapperPlus;
import com.beluga.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MybatisTest {


    // 1. 读取mybaits的配置文件,构建SqlSessionFactory
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



    @Test
    public void testResultMap(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            /*Employee employee = mapper.getEmpById(1);
            System.out.println(employee);*/
            Employee empAndDept = mapper.getEmpAndDept(1);
            System.out.println(empAndDept);
        }finally {
            openSession.close();
        }
    }





    @Test
    public void testSelect(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//            Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
//            System.out.println(empByIdReturnMap);

            Map<Integer,Employee> map = mapper.getEmpByLastNameLikeReturnMap("%m");
            System.out.println(map);

        }catch (Exception e){

        }finally {
            openSession.close();
        }
    }


    @Test
    public void testSelectParam(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //Employee employee = mapper.selectEmpByIDAndLastName(2, "Jerry");
            Map<String, Object> map = new HashMap<>();
            map.put("id",1);
            map.put("lastName","Tom");
            Employee employee = mapper.selectEmpByMap(map);
            System.out.println(employee);
        }catch (Exception e){

        }finally {
            openSession.close();
        }
    }





    @Test
    public void helloWorldTest() throws IOException {

        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            // 3. 使用接口编程的方式,获取到映射对象 Mapper
            // 这里使用了动态代理的思想
            // Mapper对象对应一个 mapper.xml 文件
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 4. 使用mapper调用其中的方法
            Employee employee = mapper.selectEmployee(1);
            System.out.println(employee);
        } finally {

            // 5. 关闭SqlSession
            sqlSession.close();
        }
    }
}
