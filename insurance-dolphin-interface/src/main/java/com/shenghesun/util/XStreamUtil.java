package com.shenghesun.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 * @Description XStream实现xml和对象互相转换的工具
 * @ClassName XStreamUtil
 * @author yewenting2013@foxmail.com
 * @date 2017年8月24日 下午2:19:19
 *
 */
public class XStreamUtil {
   private static String XML_TAG = "<?xml version='1.0' encoding='UTF-8'?>";
 
   /**
    * Description: 私有化构造
    */
   private XStreamUtil() {
      super();
   }
   /**
    * @Description 为每次调用生成一个XStream
    * @Title getInstance
    * @return
    */
   private static XStream getInstance() {
      XStream xStream = new XStream(new DomDriver("UTF-8")) {
         /**
          * 忽略xml中多余字段
          */
         @Override
         protected MapperWrapper wrapMapper(MapperWrapper next) {
            return new MapperWrapper(next) {
               @SuppressWarnings("rawtypes")
               @Override
               public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                  if (definedIn == Object.class) {
                     return false;
                  }
                  return super.shouldSerializeMember(definedIn, fieldName);
               }
            };
         }
      };
 
      // 设置默认的安全校验
      XStream.setupDefaultSecurity(xStream);
      // 使用本地的类加载器
      xStream.setClassLoader(XStreamUtil.class.getClassLoader());
      // 允许所有的类进行转换
      xStream.addPermission(AnyTypePermission.ANY);
      return xStream;
   }
 
   /**
    * @Description 将xml字符串转化为java对象
    * @Title xmlToBean
    * @param xml
    * @param clazz
    * @return
    */
   public static <T> T xmlToBean(String xml, Class<T> clazz) {
      XStream xStream = getInstance();
      xStream.processAnnotations(clazz);
      Object object = xStream.fromXML(xml);
      T cast = clazz.cast(object);
      return cast;
   }
 
   /**
    * @Description 将java对象转化为xml字符串
    * @Title beanToXml
    * @param object
    * @return
    */
   public static String beanToXml(Object object) {
      XStream xStream = getInstance();
      xStream.processAnnotations(object.getClass());
      // 剔除所有tab、制表符、换行符
      String xml = xStream.toXML(object).replaceAll("\\s+", " ");
      return xml;
   }
    
   /**
    * @Description 将java对象转化为xml字符串（包含xml头部信息）
    * @Title beanToXml
    * @param object
    * @return
    */
   public static String beanToXmlWithTag(Object object) {
      String xml = XML_TAG + beanToXml(object);
      return xml;
   }
 
 
   /**
    * 测试main方法
    */
   public static void main(String[] args) {
 
    }
 
}
