# 高校基本信息详情
import requests
import re
import xlwt
import json
import pymysql


def get_html(url):
    try:
        headers = {
            'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.34"
        }
        r = requests.get(url, headers=headers)
        r.encoding = r.apparent_encoding
        return r.text
    except:
        print("请求失败")


def insert_mysql(id):
    # 存高校基本信息
    basic_info = []
    # ******************************************#
    # ****************数据库配置******************#
    # ******************************************#
    # ******************************************#

    try:
        conn = pymysql.connect(
            # mysql本机连接 或者写127.0.0.1也可以连接远程数据库
            host="localhost",
            # 数据库用户名称
            user="root",
            # 密码
            passwd="zz123456",
            # 连接的数据库名字
            db="top_university",
            # 端口
            port=3306
        )
    except pymysql.Error as e:
        print("连接失败：%s" % e)

    cursor = conn.cursor()
    url = "https://static-data.eol.cn/www/2.0/school/" + str(id) + "/info.json"
    infoes = json.loads(get_html(url))
    name = infoes['data']['name']
    logo = "https://static-data.eol.cn/upload/logo/" + str(id) + ".jpg"
    basic_info.append(infoes['data']['school_id'])
    basic_info.append(infoes['data']['data_code'])
    basic_info.append(name)
    basic_info.append(logo)
    basic_info.append(infoes['data']['address'])
    basic_info.append(infoes['data']['belong'])
    basic_info.append(infoes['data']['create_date'])
    basic_info.append(infoes['data']['area'])
    basic_info.append(infoes['data']['num_library'])
    basic_info.append(infoes['data']['num_academician'])
    basic_info.append(infoes['data']['num_doctor'])
    basic_info.append(infoes['data']['num_master'])
    basic_info.append(infoes['data']['num_lab'])
    basic_info.append(infoes['data']['num_subject'])
    basic_info.append(infoes['data']['phone'])
    basic_info.append(infoes['data']['postcode'])
    basic_info.append(infoes['data']['email'])
    basic_info.append(infoes['data']['content'])
    basic_info.append(infoes['data']['province_name'])
    basic_info.append(infoes['data']['province_id'])
    basic_info.append(infoes['data']['city_name'])
    basic_info.append(infoes['data']['city_id'])
    basic_info.append(infoes['data']['town_name'])
    basic_info.append(infoes['data']['level_name'])
    basic_info.append(infoes['data']['level'])
    basic_info.append(infoes['data']['type_name'])
    basic_info.append(infoes['data']['type'])
    basic_info.append(infoes['data']['school_nature_name'])
    basic_info.append(infoes['data']['site'])
    basic_info.append(infoes['data']['school_site'])
    basic_info.append(infoes['data']['f211'])
    basic_info.append(infoes['data']['f985'])
    basic_info.append(infoes['data']['short'])
    print("***********正在写入数据库 %s 高校基本信息数据**********" % name)

    sql = "insert into top_university_school_detail_info(school_id,school_no,school_name,school_logo,school_address,school_belong,school_create_time,school_area,school_library_num,school_academician_num,school_doctor_num,school_master_num,school_lab_num,school_subject_num,school_phone,school_postcode,school_email,school_content,school_province_name,school_province_id,school_city_name,school_city_id,school_town_name,school_level_name,school_level_id,school_type_name,school_type,school_nature_name,school_zsb_site,school_site,school_211,school_985,school_short)values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    cursor.execute(sql, (basic_info))
    conn.commit()  # 数据库数据提交
    conn.close()  # 关闭数据库连接
    print(name + "数据已经保存完毕!")


def main():
    # for id in range(2999, 3000):
    # try:
    insert_mysql(3000)


# except:


# continue


if __name__ == '__main__':
    main()
