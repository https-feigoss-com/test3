package com.example.shirodemo;

//import org.apache.hadoop.util.Shell.ShellCommandExecutor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//conf import org.apache.hadoop.mapred.JobConf;
//conf import org.apache.hadoop.mapred.JobHistory.JobInfo;
//import javax.naming.*;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.naming.Reference;
/*import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServlet;*/
import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.*;
//import javax.xml.xpath.XPathFactory;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathExpression;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpressionException;
//import java.beans.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
import java.sql.*;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;*/
//import java.util.Properties;

import com.jd.jss.Credential;
import com.jd.jss.JingdongStorageService;
import com.jd.jss.client.ClientConfig;
/*import com.jd.sec_api.*;
import com.jd.sec_api.extra.codecs.*;*/

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/*import javax.servlet.ServletRequest;
import org.xml.sax.InputSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//conf import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

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
//conf import javax.xml.xquery.*;
import java.io.InputStream;
import java.io.Reader;
import java.util.TimeZone;*/
@RestController
public class DemoController {

    @RequestMapping(path = "/permit/{value}")
    public String permit(@PathVariable final String value) {
        // String safe = SecApi.encoder().encodeForSQL(MySQLCodec.getInstance(), value);
        // String safe = SecApi.validator().isValidSafeSqlArg(value)?"true":"false";
        System.out.println("success!");
        return "ok";
    }

    // Another Bypass
    // @RequestMapping(path = "/permit/*")
    public String permit() {
        System.out.println("success!");
        return "success";
    }

    /*@RequestMapping(path = "/testJndi")
    public String testJndi(final String url) throws NamingException, RemoteException {
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://127.0.0.1:1099");
        final InitialContext ctx = new InitialContext(env);
        LocateRegistry.createRegistry(1099);
        final Reference reference = new Reference("Evil", "Evil", url);
        new ReferenceWrapper(reference);
        final ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        ctx.bind("evil", referenceWrapper);
        return "success";
    }*/
    /*
    @RequestMapping(path = "/testcmd")
    public void testJcmd(final String dir) throws IOException, SQLException {
        System.out.println(dir);
        final String[] cmdList = new String[] { "cmd", "/c", "dir " + dir };
        // String[] cmdList = new String[]{"/bin/sh", "-c", "ls",";pwd"};
        // String[] cmdList = new String[]{"/bin/sh", "-c", "\"ls;pwd\""};
        final Process p = Runtime.getRuntime().exec(cmdList);
    }

    @RequestMapping(value = "/XPathTest_S_12_1_0001", method = RequestMethod.GET)
    public NodeList XPathTest_S_12_1_0001(final Document document, final String string) {
        final XPathFactory xPathfactory = XPathFactory.newInstance();
        final XPath xpath = xPathfactory.newXPath();
        try {
            final XPathExpression xp = xpath.compile("//*[@begin]" + string);
            final NodeList timedNodes = (NodeList) xp.evaluate(document, XPathConstants.NODESET);
            return timedNodes;
        } catch (final XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/DeserializationTest_S_26_2_0002", method = RequestMethod.GET)
    public void handle(final HttpServletRequest request) throws Exception {
        final String name = request.getParameter("name");
        final ClientConfig config = new ClientConfig();
        final JingdongStorageService jss = new JingdongStorageService(new Credential("accessKey", "secretKey"), config);

        final String md5 = jss.bucket("dengliang.org").object("index.html").entity(new File("/export/test.html"))
                .contentType(name).put();

    }

    @RequestMapping(value = "/testXXE", method = RequestMethod.GET)
    public void testXXE(final HttpServletRequest request) throws Exception {
        final String code = request.getParameter("code");
        final String body = request.getParameter("body");
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 禁用 DOCTYPE
        dbf.setExpandEntityReferences(false);
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setXIncludeAware(false);
        final DocumentBuilder db = dbf.newDocumentBuilder();

        final Document doc = db.parse(new ByteArrayInputStream(body.getBytes(code)));
        }
    */
}
