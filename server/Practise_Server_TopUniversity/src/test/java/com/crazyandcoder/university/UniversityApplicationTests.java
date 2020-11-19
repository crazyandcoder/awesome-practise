package com.crazyandcoder.university;

import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.mapper.TopUniversityInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UniversityApplicationTests {

//    @Autowired
//    TopUniversityInfoMapper topUniversityInfoMapper;

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
//        TopUniversityListInfo topUniversityListInfo = topUniversityInfoMapper.selectById(1);
//        System.out.println("" + topUniversityListInfo.toString());
    }

}
