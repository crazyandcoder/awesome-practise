# 高校图集，更加细分
# https://static-data.eol.cn/www/2.0/school/459/img/list.json

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
        print("请求失败--->" + str(id))


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
    basic_info.append(str(id))
    basic_info.append("https://static-data.eol.cn/upload/logo/" + str(id) + ".jpg")
    url = "https://static-data.eol.cn/www/2.0/school/" + str(id) + "/img/list.json"
    infoes = json.loads(get_html(url, id))
    content = jsonpath_data(infoes)
    if content is False:
        basic_info.append("")
    else:
        basic_info.append(str(content))

    print("***********正在写入高校基本信息数据 >>>>>" + str(id))
    sql = "insert into top_university_school_image(school_id,logo,images)values(%s,%s,%s)"
    cursor.execute(sql, (basic_info))
    conn.commit()  # 数据库数据提交
    conn.close()  # 关闭数据库连接


#
def jsonpath_data(text):
    title_list = jsonpath.jsonpath(text, expr='$..data')
    return title_list


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
    for item_id in range(69000, 69011):
        print(item_id)


if __name__ == '__main__':
    main()
    # test()
