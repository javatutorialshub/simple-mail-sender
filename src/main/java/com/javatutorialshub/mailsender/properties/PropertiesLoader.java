package com.javatutorialshub.mailsender.properties;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesLoader {

    private static final Logger logger = Logger.getLogger(PropertiesLoader.class.getName());

    private static Properties properties;

    public static Properties getApplicationProperties() throws PropertiesLoaderException {
        try {
            if(properties == null){
                properties = new Properties();
                properties.load(PropertiesLoader.class.getClassLoader()
                        .getResourceAsStream("application.properties"));
            }
            return properties;
        } catch (IOException e) {
            String msg = "Unable to load application properties " +
                    "from application.properties resource";
            logger.log(Level.SEVERE, msg, e);
            throw new PropertiesLoaderException(msg, e);
        }
    }

}
