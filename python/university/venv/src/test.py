from multiprocessing.dummy import Pool
import time


def calc_power(num):
    return num * num


def test():
    pool = Pool(3)
    # origin_num = [x for x in range(10)]
    origin_num = [x for x in range(1, 149)]
    print(origin_num)
    result = pool.map(calc_power, origin_num)
    print(result)


if __name__ == '__main__':
    start = time.time()
    test()
    end = time.time()
    print(f'总耗时：{end - start}')
