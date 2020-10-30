package com.crazyandcoder.university.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String topUniversityName;

    private String topUniversityLevel;

    private String topUniversityType;

    private String topUniversityAddress;

    private String topUniversityAddmissionTel;

    private String topUniversityAddmissionWebsite;


}
