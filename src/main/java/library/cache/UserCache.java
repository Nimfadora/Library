package library.cache;

import library.model.entity.User;
import java.util.HashMap;
import java.util.Map;

public class UserCache extends CacheGeneric<Long, User> {
    private static Map cache = new HashMap();
    @Override
    public String getName(){
        return "userCache";
    }
}
