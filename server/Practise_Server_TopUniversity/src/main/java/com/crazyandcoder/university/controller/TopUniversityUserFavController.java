package com.crazyandcoder.university.controller;

import com.crazyandcoder.university.entity.AppUpdate;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.model.req.TopUniversityUserFavReq;
import com.crazyandcoder.university.service.ICrazyandcoderUpdateService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/top-university-info/fav")
public class TopUniversityUserFavController {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityUserFavController.class);


    @Autowired
    ICrazyandcoderUpdateService updateService;


}
