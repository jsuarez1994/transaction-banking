package com.banking.transaction.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Replace value if empty by replace
	 * 
	 * @param value
	 * @param replace
	 * @return String
	 */
	public static String replaceIfEmpty(String value, String replace) {
		return (null == StringUtils.trimToNull(value)) ? replace : StringUtils.trimToNull(value);
	}

	/**
	 * Replace value if empty by replace
	 * 
	 * @param value
	 * @param replace
	 * @return String
	 */
	public static String generateReference() {
		String number = Integer.toString((int) (Math.random() * 10000));
		for (int i = number.length(); i < 5; i++) {
			number = "0".concat(number);
		}
		return number.concat(RandomStringUtils.randomAlphabetic(1).toUpperCase());
	}
	
	/**
	 * Replace value if empty by replace
	 * 
	 * @param value
	 * @param replace
	 * @return String
	 */
	public static String withCeros(int param) {
		return (param < 10) ? "0".concat(Integer.toString(param)) : Integer.toString(param);
	}

	/**
	 * Generate current date.
	 *
	 * @return the date
	 */
	public static String generateCurrentDate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}
	
	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String generateStringDate(Timestamp date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		return Integer.toString(cal.get(Calendar.YEAR)).concat("-").concat(withCeros(cal.get(Calendar.MONTH))).concat("-") .concat(withCeros(cal.get(Calendar.DAY_OF_MONTH)));
	}
	
	public static boolean isBefore(Timestamp current, Timestamp compare) {
		try {
			Date compareDate = sdfDate.parse(generateStringDate(compare));
			Date currentDate = sdfDate.parse(generateStringDate(current)); 
			return compareDate.before(currentDate);
		} catch (ParseException e) {
			return false;
		} 
	}
	
	public static boolean isEquals(Timestamp current, Timestamp compare) {
		try {
			Date compareDate = sdfDate.parse(generateStringDate(compare));
			Date currentDate = sdfDate.parse(generateStringDate(current)); 
			return compareDate.equals(currentDate);
		} catch (ParseException e) {
			return false;
		} 
	}
	
	public static boolean isAfter(Timestamp current, Timestamp compare) {
		try {
			Date compareDate = sdfDate.parse(generateStringDate(compare));
			Date currentDate = sdfDate.parse(generateStringDate(current)); 
			return compareDate.after(currentDate);
		} catch (ParseException e) {
			return false;
		} 
	}

}
