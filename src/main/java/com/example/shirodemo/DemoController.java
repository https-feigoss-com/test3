package com.example.shirodemo;

import org.apache.hadoop.util.Shell;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.xml.xpath.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.*;
import java.util.Properties;

@RestController
public class DemoController {
    @RequestMapping(path = "/permit/{value}")
    public String permit(@PathVariable String value) {
        System.out.println("success!");
        return "success";
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
}
