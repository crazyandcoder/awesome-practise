package com.crazyandcoder.android.lib.base.utils;

import java.math.BigDecimal;

public class AccurateUtil {

	/*
	 * 
	 * 返回两位小数的float类型
	 */
	public static float getFloat(float f) {
		BigDecimal big = new BigDecimal(f);
		return big.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		// big.setScale(2, BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
	}

	/*
	 * 
	 * 返回两位小数的String_float类型
	 */
	public static String getFloat(String s) {
		BigDecimal big = BigDecimal.valueOf(Double.parseDouble(s));
		s = (float) big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
				+ "";
		return s;
	}

	/*
	 * 
	 * 返回两位小数的double类型
	 */
	public static double getDouble(Double d) {
		BigDecimal big = BigDecimal.valueOf(d);
		d = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}

	/*
	 * 
	 * 返回两位小数的String_double类型
	 */
	public static String getDouble(String s) {
		BigDecimal big = BigDecimal.valueOf(Double.parseDouble(s));
		s = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "";
		return s;
	}
	/*
	 * 
	 * 返回1位小数的String_double类型
	 */
	public static double getDouble1Bit(Double d) {
		BigDecimal big = BigDecimal.valueOf(d);
		d = big.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}

	/*
	 * 
	 * 除法保留两位小数
	 */
	public static float divide(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return (float) b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

}
