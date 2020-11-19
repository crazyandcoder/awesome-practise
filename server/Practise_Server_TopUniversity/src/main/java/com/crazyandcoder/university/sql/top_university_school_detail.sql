CREATE TABLE `top_university_school_detail_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `school_id` varchar(255) DEFAULT NULL,
  `school_no` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `school_logo` varchar(255) DEFAULT NULL,
  `school_address` varchar(255) DEFAULT NULL,
  `school_belong` varchar(255) DEFAULT NULL,
  `school_create_time` varchar(255) DEFAULT NULL,
  `school_area` varchar(255) DEFAULT NULL,
  `school_library_num` varchar(255) DEFAULT NULL,
  `school_academician_num` varchar(255) DEFAULT NULL,
  `school_doctor_num` varchar(255) DEFAULT NULL,
  `school_master_num` varchar(255) DEFAULT NULL,
  `school_lab_num` varchar(255) DEFAULT NULL,
  `school_subject_num` varchar(255) DEFAULT NULL,
  `school_phone` varchar(255) DEFAULT NULL,
  `school_postcode` varchar(255) DEFAULT NULL,
  `school_email` varchar(255) DEFAULT NULL,
	`school_content` text DEFAULT NULL,
  `school_province_name` varchar(255) DEFAULT NULL,
  `school_province_id` varchar(255) DEFAULT NULL,
  `school_city_name` varchar(255) DEFAULT NULL,
  `school_city_id` varchar(255) DEFAULT NULL,
  `school_town_name` varchar(255) DEFAULT NULL,
  `school_level_name` varchar(255) DEFAULT NULL,
  `school_level_id` varchar(255) DEFAULT NULL,
  `school_type_name` varchar(255) DEFAULT NULL,
  `school_type` varchar(255) DEFAULT NULL,
  `school_nature_name` varchar(255) DEFAULT NULL,
  `school_zsb_site` varchar(255) DEFAULT NULL,
  `school_site` varchar(255) DEFAULT NULL,
  `school_211` varchar(255) DEFAULT NULL,
  `school_985` varchar(255) DEFAULT NULL,
  `school_short` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2511 DEFAULT CHARSET=utf8;

DESC top_university_school_detail_info

SELECT count(*) FROM top_university_school_detail_info;

SELECT school_id FROM top_university_school_detail_info
