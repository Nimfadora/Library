package library.cache;

import library.model.entity.Book;
import java.util.HashMap;
import java.util.Map;

public class BookCache extends CacheGeneric<Long, Book> {
    private static Map cache = new HashMap();
    @Override
    public String getName(){
        return "bookCache";
    }
}
