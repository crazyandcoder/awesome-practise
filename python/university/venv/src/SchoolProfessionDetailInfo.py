# https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&page=1&province_id=&request_type=1&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists

# 专业详情表
from multiprocessing.dummy import Pool
import requests
import re
import xlwt
import json
import pymysql
import time
import random


# 高校基本信息

def get_html(url):
    try:
        headers = {
            'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.34"
        }
        r = requests.get(url, headers=headers)
        r.encoding = r.apparent_encoding
        return r.text
    except Exception as e:
        print(e)


def get_id_list_select_mysql():
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
    sql = "SELECT special_id,name FROM top_university_school_profession_list"
    cursor.execute(sql)
    data = cursor.fetchall()
    idList = []
    for id in data:
        idList.append(id[0])
    return idList


def insert_mysql(id):
    # 存高校基本信息
    basic_info = []
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
    url = "https://static-data.eol.cn/www/special/" + str(id) + "/pc_special_detail.json"
    infoes = json.loads(get_html(url))
    basic_info.append(infoes['code'])
    basic_info.append(infoes['content'])
    basic_info.append(infoes['degree'])
    basic_info.append(infoes['do_what'])
    basic_info.append(infoes['e_status'])
    basic_info.append(str(infoes['heat']))
    basic_info.append(infoes['id'])
    basic_info.append(str(infoes['impress']))
    basic_info.append(infoes['is_what'])
    basic_info.append(infoes['job'])
    basic_info.append(str(infoes['jobdetail']))
    basic_info.append(str(infoes['jobrate']))
    basic_info.append(infoes['learn_what'])
    basic_info.append(infoes['level1'])
    basic_info.append(infoes['level1_name'])
    basic_info.append(infoes['level2'])
    basic_info.append(infoes['level3'])
    basic_info.append(infoes['limit_year'])
    basic_info.append(infoes['name'])
    basic_info.append(infoes['rate'])
    basic_info.append(infoes['sel_adv'])
    basic_info.append(infoes['status'])
    basic_info.append(infoes['type'])
    basic_info.append(infoes['type_detail'])
    sql = "insert into top_university_school_profession_detail_info(code,content,degree,do_what," \
          "e_status,heat,profession_id,impress," \
          "is_what,job,jobdetail,jobrate," \
          "learn_what,level1,level1_name,level2," \
          "level3,limit_year,name,rate," \
          "sel_adv,status,type,type_detail )values(%s,%s,%s,%s," \
          "%s,%s,%s,%s," \
          "%s,%s,%s,%s," \
          "%s,%s,%s,%s," \
          "%s,%s,%s,%s," \
          "%s,%s,%s,%s)"
    cursor.execute(sql, (basic_info))
    print("***********正在写入专业详情信息>>>" + infoes['name'])

    conn.commit()
    conn.close()


def main():
    start = time.time()
    pool = Pool(5)
    origin_num = [x for x in get_id_list_select_mysql()]
    try:
        pool.map(insert_mysql, origin_num)
    except Exception as e:
        print(e)
    end = time.time()
    print(f'总耗时：{end - start}')


def test():
    for id in range(1, 74):
        try:
            url = "https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&page=" + str(
                id) + "&province_id=&request_type=1&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists"
            # print("index: " + str(id) + " " + url)
            infoes = json.loads(get_html(url))
            length = len(infoes['data']['item'])
            for i in range(length):
                print(infoes['data']['item'][i]['name'])
        except Exception as e:
            print(e)
            continue


if __name__ == '__main__':
    main()
# test()
