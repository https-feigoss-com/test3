package com.example.shirodemo;

import org.apache.hadoop.util.Shell.ShellCommandExecutor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocalDirAllocator;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobHistory.JobInfo;
import javax.naming.*;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.naming.Reference;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.*;
//import javax.xml.xpath.XPathFactory;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathExpression;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpressionException;
import java.beans.*;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.jd.sec_api.*;
import com.jd.sec_api.extra.codecs.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.servlet.ServletRequest;
import org.xml.sax.InputSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Node;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.xquery.*;
import java.io.InputStream;
import java.io.Reader;
import java.util.TimeZone;
@RestController
public class DemoController {

    @RequestMapping(path = "/permit/{value}")
    public String permit(@PathVariable String value) {
        //String safe = SecApi.encoder().encodeForSQL(MySQLCodec.getInstance(), value);
        String safe = SecApi.validator().isValidSafeSqlArg(value)?"true":"false";
        System.out.println("success!");
        return safe;
    }

    // Another Bypass
    // @RequestMapping(path = "/permit/*")
    public String permit() {
        System.out.println("success!");
        return "success";
    }

    @RequestMapping(path = "/testJndi")
    public String testJndi(String url) throws NamingException, RemoteException {
                Properties env = new Properties();
                env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
                env.put(Context.PROVIDER_URL,"rmi://127.0.0.1:1099");
                InitialContext ctx = new InitialContext(env);
                LocateRegistry.createRegistry(1099);
                Reference reference = new Reference("Evil", "Evil", url);
                new ReferenceWrapper(reference);
                ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
                ctx.bind("evil", referenceWrapper);
                return "success";
    }

    @RequestMapping(path = "/testcmd")
    public void testJcmd(String dir) throws IOException, SQLException {
            System.out.println(dir);
            String[] cmdList = new String[]{"cmd", "/c", "dir "+dir};
            //String[] cmdList = new String[]{"/bin/sh", "-c", "ls",";pwd"};
            //String[] cmdList = new String[]{"/bin/sh", "-c", "\"ls;pwd\""};
            Process p = Runtime.getRuntime().exec(cmdList);
        }

    @RequestMapping(value = "/XPathTest_S_12_1_0001", method = RequestMethod.GET)
    public NodeList XPathTest_S_12_1_0001(Document document, String string) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            XPathExpression xp = xpath.compile("//*[@begin]"+string);
            NodeList timedNodes = (NodeList) xp.evaluate(document, XPathConstants.NODESET);
            return timedNodes;
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/CmdITest_S_3_12_0001", method = RequestMethod.GET)
    public void CmdITest_S_3_12_0001(HttpServletRequest request) throws IOException {
        String dir = request.getParameter("dir");
        String[] cmdList = new String[]{"/bin/sh", "-c", "ls -lh " + dir};
        new Shell.ShellCommandExecutor(cmdList);
    }


        @RequestMapping(value = "/DeserializationTest_S_26_2_0002", method = RequestMethod.GET)
        public void handle(HttpServletRequest request) throws Exception {
            String name = request.getParameter("name");

            String data = String.format("<xml><name>%s</name></xml>", name);

            XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(data.getBytes("utf-8")));
            decoder.readObject();

        }
        @RequestMapping(value = "/testXXE", method = RequestMethod.GET)
        public void testXXE(HttpServletRequest request) throws Exception {
            String code = request.getParameter("code");
            String body = request.getParameter("body");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             // 禁用 DOCTYPE
             dbf.setExpandEntityReferences(false);
             dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
             dbf.setXIncludeAware(false);
            DocumentBuilder db = dbf.newDocumentBuilder();

           

            Document doc = db.parse(new ByteArrayInputStream(body.getBytes(code)));
        }
        @RequestMapping(value = "/testLocalDirAllocator", method = RequestMethod.GET)
        public void testLocalDirAllocator(ServletRequest request) throws Exception {
            String s = request.getParameter("s");
            InputSource inputSource = new InputSource();
            inputSource.evaluate();//找不到
        }

        @RequestMapping(value = "/NoSQLInjectionTest_S_2_5_0001", method = RequestMethod.GET)
        public void NoSQLInjectionTest_S_2_5_0001(HttpServletRequest request) throws Exception {
                String data = request.getParameter("data");
                ScanRequest.Builder builder =  ScanRequest.builder();
                Map<String, String> map = new HashMap<>();
                map.put("key", data);
                builder.expressionAttributeNames(map);
        }
    @RequestMapping(value = "/XQueryInjectionTest_S_11_2_0001", method = RequestMethod.GET)
    public void XQueryInjectionTest_S_11_2_0001(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        XQExpression expr = new XQExpression() {
            @Override
            public void cancel() throws XQException {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void close() throws XQException {

            }

            @Override
            public void executeCommand(String cmd) throws XQException {

            }

            @Override
            public void executeCommand(Reader cmd) throws XQException {

            }

            @Override
            public XQResultSequence executeQuery(String query) throws XQException {
                return null;
            }

            @Override
            public XQResultSequence executeQuery(Reader query) throws XQException {
                return null;
            }

            @Override
            public XQResultSequence executeQuery(InputStream query) throws XQException {
                return null;
            }

            @Override
            public XQStaticContext getStaticContext() throws XQException {
                return null;
            }

            @Override
            public TimeZone getImplicitTimeZone() throws XQException {
                return null;
            }

            @Override
            public void bindAtomicValue(QName varName, String value, XQItemType type) throws XQException {

            }

            @Override
            public void bindString(QName varName, String value, XQItemType type) throws XQException {

            }

            @Override
            public void bindDocument(QName varName, String value, String baseURI, XQItemType type) throws XQException {

            }

            @Override
            public void bindDocument(QName varName, Reader value, String baseURI, XQItemType type) throws XQException {

            }

            @Override
            public void bindDocument(QName varName, InputStream value, String baseURI, XQItemType type) throws XQException {

            }

            @Override
            public void bindDocument(QName varName, XMLStreamReader value, XQItemType type) throws XQException {

            }

            @Override
            public void bindDocument(QName varName, Source value, XQItemType type) throws XQException {

            }

            @Override
            public void setImplicitTimeZone(TimeZone implicitTimeZone) throws XQException {

            }

            @Override
            public void bindItem(QName varName, XQItem value) throws XQException {

            }

            @Override
            public void bindSequence(QName varName, XQSequence value) throws XQException {

            }

            @Override
            public void bindObject(QName varName, Object value, XQItemType type) throws XQException {

            }

            @Override
            public void bindBoolean(QName varName, boolean value, XQItemType type) throws XQException {

            }

            @Override
            public void bindByte(QName varName, byte value, XQItemType type) throws XQException {

            }

            @Override
            public void bindDouble(QName varName, double value, XQItemType type) throws XQException {

            }

            @Override
            public void bindFloat(QName varName, float value, XQItemType type) throws XQException {

            }

            @Override
            public void bindInt(QName varName, int value, XQItemType type) throws XQException {

            }

            @Override
            public void bindLong(QName varName, long value, XQItemType type) throws XQException {

            }

            @Override
            public void bindNode(QName varName, Node value, XQItemType type) throws XQException {

            }

            @Override
            public void bindShort(QName varName, short value, XQItemType type) throws XQException {

            }
        };
        expr.executeCommand(data);
    }

}
