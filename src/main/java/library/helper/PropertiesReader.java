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

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    private static Boolean inMemory = null;

    private static Boolean isInMemory(){
        if(inMemory == null){
            Properties properties = new Properties();
            try(InputStream input = new FileInputStream("src/main/java/library/resources/properties.properties")){
                properties.load(input);
                String property = properties.getProperty("inMemory");
                if(property != null)
                    inMemory = Boolean.getBoolean(property);
                System.out.println(inMemory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (inMemory != null && inMemory);
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
