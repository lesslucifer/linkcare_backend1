package com.clinic.clinic.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Utils {
	public static Map<String, Object> mkMap(Object... objs) {
		if (objs.length == 0) {
			return Collections.emptyMap();
		}
		
		if (objs.length % 2 != 0) {
			throw new IllegalArgumentException("mkMap - objs size must be even");
		}
        
        for (int i = 0; i < objs.length; i += 2) {
            Object o = objs[i];
            
            if (o == null) {
    			throw new IllegalArgumentException("mkMap - key must not be null!");
            }
            else if (!(o instanceof String)) {
    			throw new IllegalArgumentException("mkMap - key must be String!");
            }
        }
        
        Map<String, Object> m = new HashMap<>();
        for (int i = 0; i < objs.length; i += 2) {
            String k = (String) objs[i];
            Object v = objs[i + 1];
            
            m.put(k, v);
        }
        
        return m;
    }
	
	public static LocalDateTime toDateTime(LocalDate date, int minutes) {
		return LocalDateTime.of(date, LocalTime.of(minutes / 60, minutes % 60));
	}
}
