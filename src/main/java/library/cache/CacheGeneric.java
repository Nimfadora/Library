package library.cache;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Владелец on 24.11.2015.
 */
public abstract class CacheGeneric <K, V> implements Cache<K,V> {
    private static Map cache = new HashMap();

    public V get(K key) {
        return (key == null)? null:(V)cache.get(key);
    }

    public Map getAll(Set set) {
        Map res = new HashMap();
        if(set != null)
            set.forEach((key)->{
                if(key!=null)
                    res.put(key, cache.get(key));
            });
        return res;
    }

    public boolean containsKey(Object key) {
        return (key != null) && cache.containsKey(key);
    }

    public void put(Object key, Object value) {
        if(key!=null)
            cache.put(key, value);
    }

    public void putAll(Map map) {
        if(map != null)
            cache.putAll(map);
        else
            throw new NullPointerException();
    }

    public boolean remove(Object key) {
        if(key != null)
            cache.remove(key);
        return (key != null) && cache.containsKey(key);
    }

    public V getAndRemove(K key) {
        return (key == null)? null:(V)cache.remove(key);
    }

    public boolean replace(Object key, Object value) {
        return (key != null) && (cache.replace(key, value) != null);
    }

    public void removeAll(Set set) {
        set.forEach((key)->{
            if(key!=null)
                cache.remove(key);
        });
    }

    public void removeAll() {
        cache.clear();
    }

    public String getName() {
        return null;
    }

    public Iterator<Entry<K, V>> iterator() {
        return cache.entrySet().iterator();
    }

    public void loadAll(Set set, boolean b, CompletionListener completionListener) {
        throw new NotImplementedException();
    }

    public V getAndPut(K key, V value) {
        throw new NotImplementedException();
    }

    public void close() {
        throw new NotImplementedException();
    }

    public boolean putIfAbsent(Object o, Object o2) {
        throw new NotImplementedException();
    }

    public boolean remove(Object o, Object o2) {
        throw new NotImplementedException();
    }

    public boolean replace(Object o, Object o2, Object v1) {
        throw new NotImplementedException();
    }

    public V getAndReplace(K key, V value) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public <C extends javax.cache.configuration.Configuration<K, V>> C getConfiguration(Class<C> clazz) {
        throw new NotImplementedException();
    }

    public CacheManager getCacheManager() {
        throw new NotImplementedException();
    }

    public boolean isClosed() {
        throw new NotImplementedException();
    }

    public void registerCacheEntryListener(CacheEntryListenerConfiguration cacheEntryListenerConfiguration) {
        throw new NotImplementedException();
    }

    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration cacheEntryListenerConfiguration) {
        throw new NotImplementedException();
    }

    public K unwrap(Class aClass) {
        throw new NotImplementedException();
    }

    public Map invokeAll(Set set, EntryProcessor entryProcessor, Object... objects) {
        throw new NotImplementedException();
    }

    public K invoke(Object o, EntryProcessor entryProcessor, Object... objects) throws EntryProcessorException {
        throw new NotImplementedException();
    }
}
