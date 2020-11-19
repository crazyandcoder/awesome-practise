CREATE TABLE `top_university_school_list` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
	`belong` varchar(255) DEFAULT NULL,
	`city_id` varchar(255) DEFAULT NULL,
	`city_name` varchar(255) DEFAULT NULL,
	`code_enroll`varchar(255) DEFAULT NULL,
	`colleges_level` varchar(255) DEFAULT NULL,
	`department` varchar(255) DEFAULT NULL,
	`dual_class` varchar(255) DEFAULT NULL,
	`dual_class_name` varchar(255) DEFAULT NULL,
	`f211` varchar(255) DEFAULT NULL,
	`f985` varchar(255) DEFAULT NULL,
	`level` varchar(255) DEFAULT NULL,
	`level_name` varchar(255) DEFAULT NULL,
	`is_top` varchar(255) DEFAULT NULL,
	`name` varchar(255) DEFAULT NULL,
	`nature_name` varchar(255) DEFAULT NULL,
	`nature` varchar(255) DEFAULT NULL,
	`province_id` varchar(255) DEFAULT NULL,
	`province_name` varchar(255) DEFAULT NULL,
	`publish_id` varchar(255) DEFAULT NULL,
	`school_id` varchar(255) DEFAULT NULL,
	`school_type` varchar(255) DEFAULT NULL,
	`type` varchar(255) DEFAULT NULL,
	`type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2511 DEFAULT CHARSET=utf8;


DESC top_university_school_list

SELECT DISTINCT school_type FROM top_university_school_list

https://api.eol.cn/gkcx/api/?access_token=&
    admissions=&
    central=&
    department=&
    dual_class=&
    f211=&
    f985=&
    is_dual_class=&
    keyword=&
    page=1&
    province_id=&
    request_type=1&
    school_type=&
    signsafe=&
    size=20&
    sort=view_total&
    type=&uri=apigkcx/api/school/hotlists
