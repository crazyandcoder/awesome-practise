# 高校详情信息，更加细分
# https://static-data.eol.cn/www/school/{school_id}/detail/{item_id}.json
# school_id【30，3000】
# item_id 【69000，69010】
# 69000: 学校概况
# 69001: 院系设置
# 69002: 师资力量
# 69003: 体检标准
# 69004: 收费标准
# 69005: 就业情况
# 69006: 录取规则
# 69007: 学校领导
# 69008: 重点学科
# 69009: 招生政策
# 690010:双一流学科


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
        print("请求失败--->"+str(id))


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
    for item_id in range(69000, 69011):
        url = "https://static-data.eol.cn/www/school/" + str(id) + "/detail/" + str(item_id) + ".json"
        infoes = json.loads(get_html(url, id))
        content = jsonpath_content(infoes)
        if content is False:
            basic_info.append("")
        else:
            basic_info.append(infoes['content'])

    print("***********正在写入高校基本信息数据 >>>>>" + str(id))
    sql = "insert into top_university_school_detail_info_more(school_id,about_xuexiaogaikuang,about_yuanxishezhi,about_shizililiang,about_tijianbiaozhun,about_shoufeibiaozhun,about_jiuyeqingkuang,about_luquguize,about_xuexiaolingdao,about_zhongdianxueke,about_zhaoshengzhengce,about_yiliuxueke)values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    cursor.execute(sql, (basic_info))
    conn.commit()  # 数据库数据提交
    conn.close()  # 关闭数据库连接


#
def jsonpath_content(text):
    title_list = jsonpath.jsonpath(text, expr='$..content')
    return title_list


def main():
    start = time.time()
    pool = Pool(5)
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
