package library.helper;

import library.dao.BookDAO;
import library.dao.ReportDAO;
import library.dao.UserDAO;
import library.dao.db_dao.impl.DBBookDAOImpl;
import library.dao.db_dao.impl.DBReportDAOImpl;
import library.dao.db_dao.impl.DBUserDAOImpl;
import library.dao.in_memory_dao.impl.IMBookDAOImpl;
import library.dao.in_memory_dao.impl.IMReportDAOImpl;
import library.dao.in_memory_dao.impl.IMUserDAOImpl;
import library.dao.storage.DBStorage;
import library.dao.storage.InMemoryStorage;
import library.dao.storage.Storage;
import library.model.entity.Book;

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
        return isInMemory() ? IMBookDAOImpl.getInstance() : DBBookDAOImpl.getInstance();
    }
    public static ReportDAO getReportDao(){
        return isInMemory() ? IMReportDAOImpl.getInstance() : DBReportDAOImpl.getInstance();
    }
    public static UserDAO getUserDao(){
        return isInMemory() ? IMUserDAOImpl.getInstance() : DBUserDAOImpl.getInstance();
    }
}
