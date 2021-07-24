package com.example.demomp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demomp.entity.User;
import com.example.demomp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemompApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //mp复杂查询操作
    @Test
    public void testSelect(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ge gt le lt
        //queryWrapper.ge("age",21);

        //eq ne
        //queryWrapper.eq("name","Tom");

        //between、notBetween
        //queryWrapper.between("age", 24, 28);

        //like、notLike、likeLeft、likeRight
        //queryWrapper.likeLeft("name","张");

        //orderBy、orderByDesc、orderByAsc

        queryWrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    //根据id删除
    @Test
    public void testDeleteId(){
        int rows = userMapper.deleteById(1417153290842697729L);
        System.out.println(rows);
    }


    //分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page(1,3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页的所有数据
        long pages = userPage.getPages();//总页数
        long current = userPage.getCurrent(); //当前页
        List<User> records = userPage.getRecords();//查询数据集合
        long total = userPage.getTotal();//总记录数
        boolean hasNext = userPage.hasNext(); //是否有下一页
        boolean hasPrevious = userPage.hasPrevious(); //是否有上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);

    }

    //简单条件查询
    @Test
    public void testSelect2(){
        HashMap<String , Object> columnMap = new HashMap<>();
        columnMap.put("name","Jack");
        columnMap.put("age",20);
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    //多个id批量查询
    @Test
    public void testSelect1(){

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //根据id查询
        User user = userMapper.selectById(1416957863140835329L);
        //修改
        user.setName("张三");
        int i = userMapper.updateById(user);
    }

    //修改
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1416746367261659138L);
        user.setName("lucymary");

        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    //添加
    @Test
    public void testAdd(){
        User user = new User();
        user.setName("王五");
        user.setAge(20);
        user.setEmail("123@qq.com");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
