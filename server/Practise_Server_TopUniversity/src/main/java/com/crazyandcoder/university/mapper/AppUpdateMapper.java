package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crazyandcoder.university.entity.AppUpdate;
import org.apache.ibatis.annotations.Param;

public interface AppUpdateMapper extends BaseMapper<AppUpdate> {

    AppUpdate getUpdateInfo(@Param("pkg") String pkg, @Param("versionCode") int versionCode);
}
