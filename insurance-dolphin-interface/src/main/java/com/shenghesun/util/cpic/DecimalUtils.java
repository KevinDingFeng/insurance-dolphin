 /* 文档密级：秘密 */
package com.shenghesun.util.cpic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DecimalUtils {
	public static int scale2 = 2;

	public static int scale6 = 6;
	
	private static Map formats = new HashMap();

	static {
		formats.put(Integer.valueOf("2"), new DecimalFormat("##0.00"));
		formats.put(Integer.valueOf("4"), new DecimalFormat("##0.0000"));
		formats.put(Integer.valueOf("6"), new DecimalFormat("##0.000000"));
	}

	private DecimalUtils() {

	}

	public static String format(double value, int scale) {
		if (value == 0) {
			return "0";
		}
		DecimalFormat fmt = (DecimalFormat) formats.get(Integer.valueOf(String
				.valueOf(scale)));
		return fmt.format(value);
	}

	public static String format(Double value, String format) {
		if (value == null) {
			return "";
		}
		DecimalFormat fmt = new DecimalFormat(format);
		return fmt.format(value);
	}
	
	public static Double stringToDouble(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return Double.valueOf(value);
	}
	
	public static BigDecimal stringToBigDecimal(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return BigDecimal.valueOf(Long.parseLong(value));
	}
	
	public static String doubleToString(Double value) {
		if(value == null) {
			return null;
		}
		return value.toString();
	}

	/**
	 * 减
	 * 
	 * @author zhouwei
	 */
	public static Double subtration(Double value1, Double value2, int scale) {
		if (value1 == null || value2 == null) {
			return null;
		}
		return new Double(subtration(value1.doubleValue(),
				value2.doubleValue(), scale));
	}

	/**
	 * 减
	 * 
	 * @author zhouwei
	 */
	public static double subtration(double value1, double value2, int scale) {
		BigDecimal d1 = new BigDecimal(Double.toString(value1));
		BigDecimal d2 = new BigDecimal(Double.toString(value2));
		return round(d1.subtract(d2), scale);
	}

	/**
	 * 乘
	 * 
	 * @author zhouwei
	 */
	public static Double multiply(Double value1, Double value2, int scale) {
		if (value1 == null || value2 == null) {
			return null;
		}
		return new Double(multiply(value1.doubleValue(), value2.doubleValue(),
				scale));
	}

	/**
	 * 乘
	 * 
	 * @author zhouwei
	 */
	public static double multiply(double value1, double value2, int scale) {
		BigDecimal d1 = new BigDecimal(Double.toString(value1));
		BigDecimal d2 = new BigDecimal(Double.toString(value2));
		return round(d1.multiply(d2), scale);
	}
	
	public static BigDecimal multiplyBigDecimal(BigDecimal value1, BigDecimal value2, int scale) {
		if (value1 == null || value2 == null) {
			return null;
		}
		return roundBigDecimal(multiply(value1.doubleValue(), value2.doubleValue(), scale), scale); 
	}
	/**
	 * 除
	 * 
	 * @author zhouwei
	 */
	public static Double division(Double value1, Double value2, int scale) {
		if (value1 == null || value2 == null) {
			return null;
		}
		return new Double(division(value1.doubleValue(), value2.doubleValue(),
				scale));
	}

	/**
	 * 除
	 * 
	 * @author zhouwei
	 */
	public static double division(double value1, double value2, int scale) {
		BigDecimal d1 = new BigDecimal(Double.toString(value1));
		BigDecimal d2 = new BigDecimal(Double.toString(value2));
		return d1.divide(d2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除
	 * 
	 * @author xb
	 */
	public static BigDecimal division(BigDecimal value1, BigDecimal value2, int scale) {
		return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 两double类型值相加，返回double类型值
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            精度
	 * @return
	 */
	public static double add(double v1, double v2, int scale) {
		BigDecimal d1 = new BigDecimal(String.valueOf(v1));
		BigDecimal d2 = new BigDecimal(String.valueOf(v2));
		return round(d1.add(d2), scale);
	}

	/**
	 * 两Double类型值相加，返回Double类型值
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            精度
	 * @return
	 */
	public static Double add(Double v1, Double v2, int scale) {
		if (v1 == null || v2 == null) {
			return null;
		}

		BigDecimal d1 = new BigDecimal(String.valueOf(v1));
		BigDecimal d2 = new BigDecimal(String.valueOf(v2));
		return round(d1.add(d2), scale);
	}

	/**
	 * 两BigDecimal类型值相加，返回Double类型值
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static Double add(BigDecimal v1, BigDecimal v2, int scale) {
		if (v1 == null || v2 == null) {
			return null;
		}
		return round(v1.add(v2), scale);
	}

	/**
	 * 两Double类型值相加，返回BigDecimal类型值
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            精度
	 * @return
	 */
	public static BigDecimal addBigDecimal(Double v1, Double v2, int scale) {
		if (v1 == null || v2 == null) {
			return null;
		}

		BigDecimal d1 = new BigDecimal(v1.toString());
		BigDecimal d2 = new BigDecimal(v2.toString());
		return roundBigDecimal(d1.add(d2), scale);
	}

	/**
	 * 两BigDecimal类型值相加，返回BigDecimal类型值
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            精度
	 * @return
	 */
	public static BigDecimal addBigDecimal(BigDecimal v1, BigDecimal v2,
			int scale) {
		if (v1 == null || v2 == null) {
			return null;
		}
		return roundBigDecimal(add(v1, v2, scale), scale);
	}

	/**
	 * 对double类型值四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		BigDecimal d1 = new BigDecimal(String.valueOf(v));
		return round(d1, scale);
	}

	/**
	 * 对Double类型值四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static Double round(Double v, int scale) {
		if (v == null) {
			return null;
		}
		BigDecimal d1 = new BigDecimal(v.toString());
		return round(d1, scale);
	}

	/**
	 * 对BigDecimal类型值四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static Double round(BigDecimal v, int scale) {
		if (v == null) {
			return null;
		}
		return roundBigDecimal(v, scale).doubleValue();
	}

	/**
	 * 对Double类型值四舍五入，返回BigDecimal类型值
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundBigDecimal(Double v, int scale) {
		if (v == null) {
			return null;
		}
		BigDecimal d1 = new BigDecimal(v.toString());
		return roundBigDecimal(d1, scale);
	}

	/**
	 * 对BigDecimal类型值四舍五入，返回BigDecimal类型值
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static BigDecimal roundBigDecimal(BigDecimal v, int scale) {
		if (v == null) {
			return null;
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 判断两个值是否相等
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean compareToBigDecimal(BigDecimal v1, BigDecimal v2){
		if (v1 == null && v2 == null) {
			return true;
		}else if (v1 == null || v2 == null) {
			return false;
		}else {
			return v1.compareTo(v2) == 0;
		}
	}
	
	/**
	 * 判断一个值是否为0
	 * @param v1
	 * @return
	 */
	public static boolean ifZero(BigDecimal v){
		if (v == null) {
			return false;
		}
		return v.compareTo(BigDecimal.ZERO) == 0;
	}
	 
}
