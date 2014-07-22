package org.superbiz.javaee.cache;

import org.redisson.core.RList;

public interface CacheManager {
	<T extends Cacheable> RList<T> getList(String key);
	<T extends Cacheable> T get(String key);
	<T extends Cacheable> boolean put(T object);
	<T extends Cacheable> boolean remove(T object);
}
