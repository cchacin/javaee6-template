package org.superbiz.javaee.cache;

import org.redisson.core.RList;

public interface CacheManager<T> {
	RList<T> getList(String key);
	T get(String key);
	boolean put(T object);
	boolean remove(T object);
}
