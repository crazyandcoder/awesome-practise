package com.crazyandcoder.android.lib.common.utils.log;
import android.util.Log;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/**
 * @ClassName: XmlLog
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 11:06 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 11:06 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class XmlLog {
    public static void printXml(String tag, String xml, String headString) {

        if (xml != null) {
            xml = XmlLog.formatXML(xml);
            xml = headString + "\n" + xml;
        } else {
            xml = headString + CrazyLog.NULL_TIPS;
        }

        CrazyLogUtil.printLine(tag, true);
        String[] lines = xml.split(CrazyLog.LINE_SEPARATOR);
        for (String line : lines) {
            if (!CrazyLogUtil.isEmpty(line)) {
                Log.d(tag, "║ " + line);
            }
        }
        CrazyLogUtil.printLine(tag, false);
    }

    private static String formatXML(String inputXML) {
        try {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            e.printStackTrace();
            return inputXML;
        }
    }
}
