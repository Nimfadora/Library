package library.helper;

import library.dao.BookDAO;
import library.dao.ReportDAO;
import library.dao.UserDAO;
import library.dao.db_dao.DBBookDAO;
import library.dao.db_dao.DBReportDAO;
import library.dao.db_dao.DBUserDAO;
import library.dao.in_memory_dao.IMBookDAO;
import library.dao.in_memory_dao.IMReportDAO;
import library.dao.in_memory_dao.IMUserDAO;
import library.dao.storage.InMemoryStorage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {
    private static Boolean inMemory = null;
    public static String URL;
    public static String LOGIN;
    public static String PASSWORD;
    public static String DRIVER_CLASS;

    private static Boolean isInMemory(){
        if(inMemory == null){
            Properties properties = new Properties();
            URL resource = PropertiesReader.class.getClassLoader().getResource("properties.properties");
            if (resource != null) {
                try(InputStream input = new FileInputStream(Paths.get(resource.toURI()).toFile())){
                    properties.load(input);
                    String property = properties.getProperty("inMemory");
                    URL = properties.getProperty("URL");
                    LOGIN = properties.getProperty("LOGIN");
                    PASSWORD = properties.getProperty("PASSWORD");
                    DRIVER_CLASS = properties.getProperty("DRIVER_CLASS");
                    if(property != null) {
                        inMemory = Boolean.parseBoolean(property);
                    }
                    System.out.println(inMemory);
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        return (inMemory != null && inMemory);
    }
    public static void generateSources(){
        if(isInMemory())
            InMemoryStorage.load();
    }
    public static void saveChanges(){
        if(isInMemory())
            InMemoryStorage.save();
    }
    public static BookDAO getBookDao(){
        return isInMemory() ? IMBookDAO.getInstance() : DBBookDAO.getInstance();
    }
    public static ReportDAO getReportDao(){
        return isInMemory() ? IMReportDAO.getInstance() : DBReportDAO.getInstance();
    }
    public static UserDAO getUserDao(){
        return isInMemory() ? IMUserDAO.getInstance() : DBUserDAO.getInstance();
    }
}
