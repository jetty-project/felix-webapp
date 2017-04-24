package org.eclipse.jetty.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

@WebListener
public class AppListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        initFelix();
    }
    
    private void initFelix()
    {
        Map<String,String> properties = new HashMap<>();
        properties.put(Constants.FRAMEWORK_STORAGE, "target/felix-cache");
        properties.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);
        properties.put(Constants.FRAMEWORK_BUNDLE_PARENT, Constants.FRAMEWORK_BUNDLE_PARENT_FRAMEWORK);
        properties.put(Constants.FRAMEWORK_BOOTDELEGATION, "*");
    
        Framework framework = ServiceLoader.load(FrameworkFactory.class).iterator().next().newFramework(properties);
    
        try
        {
            System.err.println("Initializing felix");
            framework.init();
            System.err.println("Starting felix");
            framework.start();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    public void contextDestroyed(ServletContextEvent sce)
    {
    
    }
}
