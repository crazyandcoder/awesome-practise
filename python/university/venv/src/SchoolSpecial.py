# 高校开设的专业
# https://static-data.eol.cn/www/2.0/school/2147/pc_special.json

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
    url = "https://static-data.eol.cn/www/2.0/school/" + str(id) + "/pc_special.json"
    infoes = json.loads(get_html(url, id))
    if infoes:
        special = infoes['data']['special']
        if special is False:
            basic_info.append("")
        else:
            basic_info.append(str(special))

        nation_feature = infoes['data']['nation_feature']
        if nation_feature is False:
            basic_info.append("")
        else:
            basic_info.append(str(nation_feature))
        print("***********正在写入高校基本信息数据 start>>>>>" + str(id))
        sql = "insert into top_university_school_special(school_id,special,nation_feature)values(%s,%s,%s)"
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
        # for id in range(30, 3001):
        #     insert_mysql(id)
        pool.map(insert_mysql, origin_num)
    except Exception as e:
        print("异常 id: " + origin_num)
    end = time.time()
    print(f'总耗时：{end - start}')


def test():
    for id in range(30, 3001):
        url = "https://static-data.eol.cn/www/2.0/school/" + str(id) + "/pc_special.json"
        infoes = json.loads(get_html(url, id))
        if infoes:
            print(id)
        else:
            print("空值： ")
            continue


if __name__ == '__main__':
    main()
    # test()
