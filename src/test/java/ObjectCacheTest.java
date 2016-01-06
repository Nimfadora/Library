import org.junit.Assert;
import org.junit.Test;
import practicals.practice3.ObjectCache;

import java.util.*;

public class ObjectCacheTest {
    private static ObjectCache objectCache = new ObjectCache();
    @Test
    public void putTest1(){
        objectCache.removeAll();
        objectCache.put(3, 10);
        Integer expected = 10;
        Integer actual = (Integer)objectCache.get(3);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void putTest2(){
        objectCache.removeAll();
        objectCache.put(1, "10");
        String expected = "10";
        String actual = (String)objectCache.get(1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void putTest3(){
        objectCache.removeAll();
        objectCache.put(null, "10");
        String expected = null;
        String actual = (String)objectCache.get(1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getTest1(){
        objectCache.removeAll();
        Object expected = null;
        Object actual = objectCache.get(1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getAllTestAndPutAllTest(){
        objectCache.removeAll();
        Map map = new HashMap();
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        objectCache.putAll(map);
        Map actual = objectCache.getAll(set);
        Map expected = map;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getAllTest(){
        objectCache.removeAll();
        Map map = new HashMap();
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        map.put(1, null);
        map.put(2, null);
        map.put(3, null);
        Map actual = objectCache.getAll(set);
        Map expected = map;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void containsKeyTest(){
        objectCache.removeAll();
        boolean actual = objectCache.containsKey(1);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void containsKeyTest1(){
        objectCache.removeAll();
        objectCache.put(1, null);
        boolean actual = objectCache.containsKey(1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void removeTest(){
        objectCache.removeAll();
        boolean actual = objectCache.remove(1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    } //TODO: ask if it is right (if cache contains no such key it still returns true
    @Test
    public void removeTest1(){
        objectCache.removeAll();
        objectCache.put(1, null);
        boolean actual = objectCache.remove(1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
     public void getAndRemoveTest(){
        objectCache.removeAll();
        objectCache.put(1, 2);
        Integer actual = (Integer)objectCache.getAndRemove(1);
        Integer expected = 2;
        Assert.assertEquals(expected, actual);
        boolean checkActual = objectCache.containsKey(1);
        boolean checkExpected = false;
        Assert.assertEquals(checkExpected, checkActual);
    }
    @Test
    public void getAndRemoveTest1(){
        objectCache.removeAll();
        Integer actual = (Integer)objectCache.getAndRemove(1);
        Integer expected = null;
        Assert.assertEquals(expected, actual);
        boolean checkActual = objectCache.containsKey(1);
        boolean checkExpected = false;
        Assert.assertEquals(checkExpected, checkActual);
    }
    @Test
    public void replaceTest(){
        objectCache.removeAll();
        objectCache.put(1, 2);
        boolean checkActual = objectCache.replace(1,3);
        boolean checkExpected = true;
        Assert.assertEquals(checkExpected, checkActual);
        Integer actual = (Integer)objectCache.get(1);
        Integer expected = 3;
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void replaceTest1(){
        objectCache.removeAll();
        objectCache.put(1, null);
        boolean checkActual = objectCache.replace(1,3);
        boolean checkExpected = true;
        Assert.assertEquals(checkExpected, checkActual);
        Integer actual = (Integer)objectCache.get(1);
        Integer expected = 3;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void replaceTest2(){
        objectCache.removeAll();
        boolean checkActual = objectCache.replace(1,3);
        boolean checkExpected = false;
        Assert.assertEquals(checkExpected, checkActual);
        Integer actual = (Integer)objectCache.get(1);
        Integer expected = null;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void replaceTest3(){
        objectCache.removeAll();
        objectCache.put(1, 3);
        boolean checkActual = objectCache.replace(1, null);
        boolean checkExpected = true;
        Assert.assertEquals(checkExpected, checkActual);
        Integer actual = (Integer)objectCache.get(1);
        Integer expected = null;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void iteratorTest(){
        objectCache.removeAll();
        objectCache.put(1, 3);
        objectCache.put(2, 5);
        objectCache.put(4, 6);
        Map map = new HashMap<>();
        map.put(1, 3);
        map.put(2, 5);
        map.put(4, 6);
        Iterator actual = objectCache.iterator();
        Iterator expected = map.entrySet().iterator();

        while(expected.hasNext()||actual.hasNext()){
            if(!expected.hasNext()||!actual.hasNext())
                Assert.assertEquals("the number of element is not equal", true, false);
            Assert.assertEquals(expected.next(),actual.next());
        }
    }


}
