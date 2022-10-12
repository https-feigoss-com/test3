package com.example.shirodemo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String testJndi(String url){
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
}
