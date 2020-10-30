package com.crazyandcoder.university;

import com.crazyandcoder.university.entity.TopUniversityInfo;
import com.crazyandcoder.university.mapper.TopUniversityInfoMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UniversityApplicationTests {

    @Autowired
    TopUniversityInfoMapper topUniversityInfoMapper;

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        TopUniversityInfo topUniversityInfo = topUniversityInfoMapper.selectById(1);
        System.out.println("" + topUniversityInfo.toString());
    }

}
