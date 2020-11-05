# 毕业去向表
from multiprocessing.dummy import Pool
import requests
import re
import xlwt
import json
import pymysql
import time
import random
import jsonpath


def get_html(url, id):
    try:
        headers = {
            'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.34"
        }
        r = requests.get(url, headers=headers)
        r.encoding = r.apparent_encoding
        return r.text
    except:
        print("请求失败>>>" + id)


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
    url = "https://static-data.eol.cn/www/school/" + str(id) + "/pc_jobdetail.json"
    infoes = json.loads(get_html(url, id))
    basic_info.append(str(id))
    jobrate = jsonpath.jsonpath(infoes, expr='$..jobrate')
    if jobrate is False:
        basic_info.append("")
    else:
        basic_info.append(str(jobrate))
    province = jsonpath.jsonpath(infoes, expr='$..province')
    if province is False:
        basic_info.append("")
    else:
        basic_info.append(str(province))
    attr = jsonpath.jsonpath(infoes, expr='$..attr')
    if attr is False:
        basic_info.append("")
    else:
        basic_info.append(str(attr))
    company = jsonpath.jsonpath(infoes, expr='$..company')
    if company is False:
        basic_info.append("")
    else:
        basic_info.append(str(company))
    gradute = jsonpath.jsonpath(infoes, expr='$..gradute')
    if gradute is False:
        basic_info.append("")
    else:
        basic_info.append(str(gradute))
    remark = jsonpath.jsonpath(infoes, expr='$..remark')
    if remark is False:
        basic_info.append("")
    else:
        basic_info.append(str(remark))
    print("***********正在写入高校基本信息数据 >>>>>" + str(id))
    sql = "insert into top_university_school_job(school_id,jobrate,province,attr,company,gradute,remark)values(%s,%s,%s,%s,%s,%s,%s)"
    cursor.execute(sql, (basic_info))
    conn.commit()  # 数据库数据提交
    conn.close()  # 关闭数据库连接


def main():
    start = time.time()
    pool = Pool(10)
    origin_num = [x for x in range(30, 3001)]
    try:
        pool.map(insert_mysql, origin_num)
    except Exception as e:
        print(e)
    end = time.time()
    print(f'总耗时：{end - start}')


def test():
    id = 30
    url = "https://static-data.eol.cn/www/school/" + str(id) + "/pc_jobdetail.json"
    print(type(get_html(url)))


if __name__ == '__main__':
    main()
    # test()
