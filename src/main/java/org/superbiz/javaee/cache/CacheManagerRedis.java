package org.superbiz.javaee.cache;

import org.redisson.Redisson;
import org.redisson.core.RList;
import org.redisson.core.RMap;
import org.superbiz.javaee.entities.dtos.UserDTO;

import javax.inject.Inject;

public class CacheManagerRedis<T extends Cacheable> implements CacheManager<T> {

	private Redisson redisson;

	@Inject
	public CacheManagerRedis(final Redisson redisson) {
		this.redisson = redisson;
	}

	@Override
	public final T get(final String key) {
		RList<T> list = this.getList(key);
		if (!exists(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public final RList<T> getList(final String key) {
		return this.redisson.getList(key);
	}

	@Override
	public final boolean put(final T object) {
		final RList<T> list = this.getList(object.getElementsKey());
		if (!exists(list)) {
			return false;
		}
		list.add(object);
		return true;
	}

	@Override
	public final boolean remove(final T object) {
		final RList<T> list = this.getList(object.getElementsKey());
		if (!exists(list)) {
			return false;
		}
		list.remove(object);
		return true;
	}

	private boolean exists(final RList<T> list) {
		return list == null || list.isEmpty();
	}
}
