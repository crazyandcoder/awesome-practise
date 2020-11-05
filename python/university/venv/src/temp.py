# https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&page=1&province_id=&request_type=1&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists

# 毕业去向表
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
    url = "https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&page=" + str(
        id) + "&province_id=&request_type=1&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists"
    infoes = json.loads(get_html(url))
    length = len(infoes['data']['item'])

    for i in range(length):
        result = []
        try:
            name = infoes['data']['item'][i]['name']
            result.append(infoes['data']['item'][i]['address'])
            result.append(infoes['data']['item'][i]['belong'])
            result.append(infoes['data']['item'][i]['city_id'])
            result.append(infoes['data']['item'][i]['city_name'])
            result.append(infoes['data']['item'][i]['code_enroll'])
            result.append(infoes['data']['item'][i]['colleges_level'])
            result.append(infoes['data']['item'][i]['department'])
            result.append(infoes['data']['item'][i]['dual_class'])
            result.append(infoes['data']['item'][i]['dual_class_name'])
            result.append(infoes['data']['item'][i]['f211'])
            result.append(infoes['data']['item'][i]['f985'])
            result.append(infoes['data']['item'][i]['level'])
            result.append(infoes['data']['item'][i]['level_name'])
            result.append(infoes['data']['item'][i]['is_top'])
            result.append(infoes['data']['item'][i]['name'])
            result.append(infoes['data']['item'][i]['nature_name'])
            result.append(infoes['data']['item'][i]['nature'])
            result.append(infoes['data']['item'][i]['province_id'])
            result.append(infoes['data']['item'][i]['province_name'])
            result.append(infoes['data']['item'][i]['publish_id'])
            result.append(infoes['data']['item'][i]['rank'])
            result.append(infoes['data']['item'][i]['rank_type'])
            result.append(infoes['data']['item'][i]['school_id'])
            result.append(infoes['data']['item'][i]['school_type'])
            result.append(infoes['data']['item'][i]['type'])
            result.append(infoes['data']['item'][i]['type_name'])
            sql = "insert into top_university_school_list(" \
                  "address," \
                  "belong," \
                  "city_id," \
                  "city_name," \
                  "code_enroll," \
                  "colleges_level," \
                  "department," \
                  "dual_class," \
                  "dual_class_name," \
                  "f211," \
                  "f985," \
                  "level," \
                  "level_name," \
                  "is_top," \
                  "name," \
                  "nature_name," \
                  "nature," \
                  "province_id," \
                  "province_name," \
                  "publish_id," \
                  "rank," \
                  "rank_type," \
                  "school_id," \
                  "school_type," \
                  "type," \
                  "type_name" \
                  ")values(" \
                  "%s,%s,%s,%s,%s," \
                  "%s,%s,%s,%s,%s," \
                  "%s,%s,%s,%s,%s," \
                  "%s,%s,%s,%s,%s," \
                  "%s,%s,%s,%s,%s," \
                  "%s" \
                  ")"
            cursor.execute(sql, (result))
            print("***********正在写入" + name)
        except Exception as e:
            print(e)

    conn.commit()
    conn.close()


def main():
    start = time.time()
    pool = Pool(5)
    # origin_num = [x for x in range(1, 149)]
    origin_num = [x for x in range(1, 2)]
    try:
        pool.map(insert_mysql, origin_num)
    except Exception as e:
        print(e)
    end = time.time()
    print(f'总耗时：{end - start}')


def test():
    for id in range(1, 2):
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
