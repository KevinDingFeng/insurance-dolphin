package com.shenghesun.util.cpic;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class XmlUtils {
	private static Logger log = Logger.getLogger(XmlUtils.class);

	private XmlUtils() {

	}
	
	
	/**
	 * Loads a document from a xml string.
	 * 
	 * @param xmlStr
	 *            the data source
	 * @throw a org.dom4j.DocumentExcepiton occurs on parsing failure.
	 */
	public static Document parseWithSAX(String xmlStr, String encoding) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		if (encoding != null) {
			xmlReader.setEncoding(encoding);
		}
		return xmlReader.read(new StringReader(xmlStr));
	}
	
	

	public static String toCompactString(Document doc, String encoding) {
		OutputFormat format = OutputFormat.createCompactFormat();
		// format.setTrimText(false);
		format.setEncoding(encoding);

		String xml = "";
		Writer wr = new StringWriter();
		XMLWriter xw = new XMLWriter(wr, format);
		try {
			xw.write(doc);
			xw.flush();
			xml = wr.toString();
		} catch (Exception e) {
			log.error("将XML DOC对象转换成字符串失败！", e);
		} finally {
			if (xw != null) {
				try {
					xw.close();
				} catch (IOException e) {

				}
			}
		}
		return xml;
	}

	public static String toPrettyString(Document doc, String encoding) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setTrimText(false);
		format.setEncoding(encoding);

		String xml = "";
		Writer wr = new StringWriter();
		XMLWriter xw = new XMLWriter(wr, format);
		try {
			xw.write(doc);
			xw.flush();
			xml = wr.toString();
		} catch (Exception e) {
			log.error("将XML DOC对象转换成字符串失败！", e);
		} finally {
			if (xw != null) {
				try {
					xw.close();
				} catch (IOException e) {

				}
			}
		}
		return xml;
	}

	public static String getElementTrimText(Element el) {
		return el == null ? "" : el.getTextTrim();
	}

	public static String getElementTrimText(Element el, String defValue) {
		String value = getElementTrimText(el);
		return value.equals("") ? defValue : value;
	}

	public static Date getElementTextAsDate(Element el, String format) {
		return DateUtils.stringToDate(getElementTrimText(el), format);
	}

	public static Double getElementTextAsDouble(Element el, int scale) {
		String text = getElementTrimText(el);
		if (!"".equals(text)) {
			return DecimalUtils.round(Double.valueOf(text), scale);
		}
		return null;
	}
	
	public static Double getElementTextAsDouble(Element el, int scale, Double defValue) {
		String text = getElementTrimText(el);
		if (!"".equals(text)) {
			return DecimalUtils.round(Double.valueOf(text), scale);
		} else {
			return defValue;
		}
	}

	public static BigDecimal getElementTextAsDecimal(Element el, int scale) {
		String text = getElementTrimText(el);
		if (!"".equals(text)) {
			return DecimalUtils.roundBigDecimal(new BigDecimal(text), scale);
		}
		return null;
	}
	
	public static BigDecimal getElementTextAsDecimal(Element el, int scale, String defValue) {
		String text = getElementTrimText(el);
		if (!"".equals(text)) {
			return DecimalUtils.roundBigDecimal(new BigDecimal(text), scale);
		} else {
			return new BigDecimal(defValue);
		}
	}

	public static Integer getElementTextAsInt(Element el) {
		String text = getElementTrimText(el);
		if (!"".equals(text)) {
			return Integer.valueOf(text);
		}
		return null;
	}

	public static String getAttributeAsTrimText(Element el, String attrName) {
		return StringUtils.trimToEmpty(el.attributeValue(attrName));
	}

	public static Integer getAttributeAsInt(Element el, String attrName) {
		String attrValue = getAttributeAsTrimText(el, attrName);
		if (!"".equals(attrValue)) {
			return Integer.valueOf(attrValue);
		}
		return null;
	}
}
