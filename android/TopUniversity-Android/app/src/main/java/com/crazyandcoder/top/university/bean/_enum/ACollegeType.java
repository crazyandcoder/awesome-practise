package com.crazyandcoder.top.university.bean._enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ICollegeType.bachelorDegree, ICollegeType.associateDegree})
@Retention(RetentionPolicy.SOURCE)
public @interface ACollegeType {
    /**
     * 高校类型，本科还是专科
     */
}
