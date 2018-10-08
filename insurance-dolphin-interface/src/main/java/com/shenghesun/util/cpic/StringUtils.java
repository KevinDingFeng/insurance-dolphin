 /* 文档密级：秘密 */
package com.shenghesun.util.cpic;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtils {
	
	private static final Logger log = Logger.getLogger(StringUtils.class);  
	
	/**
	 * The Chinese character length
	 */
	public static final int CHINESE_CHAR_BYTES = 3;

	/**
	 * The empty String <code>""</code>.
	 * 
	 */
	public static final String EMPTY = "";

	/**
	 * The space String <code> </code>
	 */
	public static final String SPACE = " ";

	/**
	 * <p>
	 * <code>StringUtils</code> instances should NOT be constructed in standard
	 * programming. Instead, the class should be used as
	 * <code>StringUtils.trim(" foo ");</code>.
	 * </p>
	 * 
	 * <p>
	 * This constructor is public to permit tools that require a JavaBean
	 * instance to operate.
	 * </p>
	 */
	public StringUtils() {
		super();
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty(&quot;&quot;)        = true
	 * StringUtils.isEmpty(&quot; &quot;)       = false
	 * StringUtils.isEmpty(&quot;bob&quot;)     = false
	 * StringUtils.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty(&quot;&quot;)        = false
	 * StringUtils.isNotEmpty(&quot; &quot;)       = true
	 * StringUtils.isNotEmpty(&quot;bob&quot;)     = true
	 * StringUtils.isNotEmpty(&quot;  bob  &quot;) = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank(&quot;&quot;)        = true
	 * StringUtils.isBlank(&quot; &quot;)       = true
	 * StringUtils.isBlank(&quot;bob&quot;)     = false
	 * StringUtils.isBlank(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank(&quot;&quot;)        = false
	 * StringUtils.isNotBlank(&quot; &quot;)       = false
	 * StringUtils.isNotBlank(&quot;bob&quot;)     = true
	 * StringUtils.isNotBlank(&quot;  bob  &quot;) = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	// Trim
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String,
	 * handling <code>null</code> by returning <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use {@link #strip(String)}.
	 * </p>
	 * 
	 * <p>
	 * To trim your choice of characters, use the {@link #strip(String, String)}
	 * methods.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim(&quot;&quot;)            = &quot;&quot;
	 * StringUtils.trim(&quot;     &quot;)       = &quot;&quot;
	 * StringUtils.trim(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtils.trim(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed string, <code>null</code> if null String input
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning <code>null</code> if the String is empty ("") after the trim or
	 * if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToNull(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull(&quot;&quot;)            = null
	 * StringUtils.trimToNull(&quot;     &quot;)       = null
	 * StringUtils.trimToNull(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtils.trimToNull(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, <code>null</code> if only chars &lt;= 32,
	 *         empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an empty String ("") if the String is empty ("") after the trim
	 * or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = &quot;&quot;
	 * StringUtils.trimToEmpty(&quot;&quot;)            = &quot;&quot;
	 * StringUtils.trimToEmpty(&quot;     &quot;)       = &quot;&quot;
	 * StringUtils.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtils.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if <code>null</code> input
	 * @since 2.0
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an space String (" ") if the String is empty ("") after the
	 * trim or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = &quot; &quot;
	 * StringUtils.trimToEmpty(&quot;&quot;)            = &quot; &quot;
	 * StringUtils.trimToEmpty(&quot;     &quot;)       = &quot; &quot;
	 * StringUtils.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtils.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, or an space String (" ") if the String is
	 *         null, empty or whitespace
	 * @since 2.0
	 */
	public static String trimToSpace(String str) {
		return isBlank(str) ? SPACE : str.trim();
	}

	// Equals
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code>
	 * references are considered to be equal. The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, &quot;abc&quot;)  = false
	 * StringUtils.equals(&quot;abc&quot;, null)  = false
	 * StringUtils.equals(&quot;abc&quot;, &quot;abc&quot;) = true
	 * StringUtils.equals(&quot;abc&quot;, &quot;ABC&quot;) = false
	 * </pre>
	 * 
	 * @see java.lang.String#equals(Object)
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case sensitive, or
	 *         both <code>null</code>
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal
	 * ignoring the case.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code>
	 * references are considered equal. Comparison is case insensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equalsIgnoreCase(null, null)   = true
	 * StringUtils.equalsIgnoreCase(null, &quot;abc&quot;)  = false
	 * StringUtils.equalsIgnoreCase(&quot;abc&quot;, null)  = false
	 * StringUtils.equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
	 * StringUtils.equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
	 * </pre>
	 * 
	 * @see java.lang.String#equalsIgnoreCase(String)
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case insensitive, or
	 *         both <code>null</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	// Substring
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start <code>n</code> characters
	 * from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>null</code>. An empty ("")
	 * String will return "".
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substring(null, *)   = null
	 * StringUtils.substring(&quot;&quot;, *)     = &quot;&quot;
	 * StringUtils.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
	 * StringUtils.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
	 * StringUtils.substring(&quot;abc&quot;, 4)  = &quot;&quot;
	 * StringUtils.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
	 * StringUtils.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the substring from, may be null
	 * @param start
	 *            the position to start from, negative means count back from the
	 *            end of the String by this many characters
	 * @return substring from start position, <code>null</code> if null String
	 *         input
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start/end <code>n</code>
	 * characters from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * The returned substring starts with the character in the
	 * <code>start</code> position and ends before the <code>end</code>
	 * position. All position counting is zero-based -- i.e., to start at the
	 * beginning of the string use <code>start = 0</code>. Negative start and
	 * end positions can be used to specify offsets relative to the end of the
	 * String.
	 * </p>
	 * 
	 * <p>
	 * If <code>start</code> is not strictly to the left of <code>end</code>, ""
	 * is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substring(null, *, *)    = null
	 * StringUtils.substring(&quot;&quot;, * ,  *)    = &quot;&quot;;
	 * StringUtils.substring(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
	 * StringUtils.substring(&quot;abc&quot;, 2, 0)   = &quot;&quot;
	 * StringUtils.substring(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
	 * StringUtils.substring(&quot;abc&quot;, 4, 6)   = &quot;&quot;
	 * StringUtils.substring(&quot;abc&quot;, 2, 2)   = &quot;&quot;
	 * StringUtils.substring(&quot;abc&quot;, -2, -1) = &quot;b&quot;
	 * StringUtils.substring(&quot;abc&quot;, -4, 2)  = &quot;ab&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the substring from, may be null
	 * @param start
	 *            the position to start from, negative means count back from the
	 *            end of the String by this many characters
	 * @param end
	 *            the position to end at (exclusive), negative means count back
	 *            from the end of the String by this many characters
	 * @return substring from start position to end positon, <code>null</code>
	 *         if null String input
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		// check length next
		if (end > str.length()) {
			end = str.length();
		}

		// if start is greater than end, return ""
		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No separator is added to the joined String.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array) {
        return join(array, null);
    }
    
    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        return join(array, separator, 0, array.length);
    }
	
	/**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }
    
    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }
    
    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                        + separator.length());

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }
	public static List<String> averageSplit(String src, int perCount) {
		if (src == null || src.equals("") || src.length() % perCount != 0) {
			return new ArrayList<String>();
		}

		int count = src.length() / perCount;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			int temp = i * perCount;
			result.add(src.substring(temp, temp + perCount));
		}
		return result;
	}

	public static boolean isCardID(String cardID) {
		if (isBlank(cardID)) {
			return false;
		}

		if (cardID.length() == 15) {
			return Pattern
					.matches(
							"^[1-9]\\d{5}(\\d{2}((((0[13578])|(1[02]))(([0-2][0-9])|(3[01])))|(((0[469])|(11))(([0-2][0-9])|(30)))|(02[0-2][0-9])))\\d{3}$",
							cardID);
		} else if (cardID.length() == 18) {
			return Pattern
					.matches(
							"^[1-9]\\d{5}(\\d{4}((((0[13578])|(1[02]))(([0-2][0-9])|(3[01])))|(((0[469])|(11))(([0-2][0-9])|(30)))|(02[0-2][0-9])))\\d{3}[\\dX]$",
							cardID);
		} else {
			return false;
		}
	}

	public static String getSex(String cardID) {
		if (!isCardID(cardID)) {
			return null;
		}
		if (cardID.length() == 15) {
			int i = Integer.parseInt(cardID.substring(14));
			return i % 2 == 0 ? "1" : "2";
		} else if (cardID.length() == 18) {
			int i = Integer.parseInt(cardID.substring(16, 17));
			return i % 2 == 0 ? "1" : "2";
		} else {
			return null;
		}
	}

	/**
	 * 取出用分隔符分隔的字符串中的每一段字符
	 * 
	 * @param pStrSrc
	 *            源字符串
	 * @param pStrReg
	 *            分隔符
	 * @return 存放每一段字符串的字符串数组
	 */
	public static String[] split(String pStrSrc, String pStrReg) {
		if (pStrSrc == null || pStrReg == null) {
			return new String[0];
		} else {
			return pStrSrc.split(pStrReg);
		}
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static String MD5(String src, String charsetName) {
		try {
			return MD5(src.getBytes(charsetName));
		} catch (Exception e) {

		}
		return null;
	}

	public static String MD5(byte[] src) {
		String result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(src);
			result = bytesToHexString(md5.digest());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * o == null return null, o != null return o.toString()
	 * 
	 * @param o
	 * @return
	 */
	public static String value(Object o) {
		return o == null ? null : o.toString();
	}

	/**
	 * o == null return "", o != null return o.toString()
	 * 
	 * @param o
	 * @return
	 */
	public static String valueEmpty(Object o) {
		return o == null ? EMPTY : o.toString();
	}
	
	public static int chineseLength(String value) throws IllegalArgumentException {
		if (isBlank(value)) {
			throw new IllegalArgumentException();
		}
		int length = 0;
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) < 256) {
				length++;
			} else {
				length += CHINESE_CHAR_BYTES;
			}
		}
		return length;
	}

	/**
	 *
	 * 是否有数字
	 * */
	public static boolean hasDigitss(String content){
		  boolean flag=false;
		  Pattern p=Pattern.compile(".*\\d+.*");
		  Matcher m=p.matcher(content);
		  if(m.matches()) {
		     flag=true;
		  }
		  return flag;
    }
	
	/**
	 * 是否有字母
	 * 
	 * */
	public static boolean haszimu(String content) {
		boolean falg = false;
	    try {
	    	 Integer.valueOf(content);
	    } catch(Exception e) {
	    	falg = true;
	    }
		return falg;
	}
	public static void main(String[] args) {
		log.info(MD5("字段".getBytes()));
		log.info(StringUtils.hasDigitss("qwe0rt"));
		log.info(StringUtils.haszimu("12345678o"));
	}
}
