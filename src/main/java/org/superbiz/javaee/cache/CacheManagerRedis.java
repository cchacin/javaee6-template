package org.superbiz.javaee.cache;

import org.redisson.Redisson;
import org.redisson.core.RList;

import javax.inject.Inject;

public class CacheManagerRedis implements CacheManager {

	private Redisson redisson;

	@Inject
	public CacheManagerRedis(final Redisson redisson) {
		this.redisson = redisson;
	}

	@Override
	public final <T extends Cacheable> T get(final String key) {
		RList<T> list = this.getList(key);
		if (!exists(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public final <T extends Cacheable> RList<T> getList(final String key) {
		RList<T> list = this.redisson.getList(key);
		if (!exists(list)) {
			return null;
		}
		return list;
	}

	@Override
	public final <T extends Cacheable> boolean put(final T object) {
		final RList<T> list = this.getList(object.getElementsKey());
		if (!exists(list)) {
			return false;
		}
		list.add(object);
		return true;
	}

	@Override
	public final <T extends Cacheable> boolean remove(final T object) {
		final RList<T> list = this.getList(object.getElementsKey());
		if (!exists(list)) {
			return false;
		}
		list.remove(object);
		return true;
	}

	private <T extends Cacheable> boolean exists(final RList<T> list) {
		return list == null || list.isEmpty();
	}
}
