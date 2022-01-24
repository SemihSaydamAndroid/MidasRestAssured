package org.example.cache;

import groovy.lang.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Semih Saydam
 * @since 24.01.2022
 */
@Singleton
public class CacheHelper {

    private Map<String, Object> instanceMap;

    public Map<String, Object> globalVariable(){
        if (instanceMap == null){
            instanceMap = new HashMap<>();
        }
        return instanceMap;
    }

}
