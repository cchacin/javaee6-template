/**
 * Copyright (C) 2014 Carlos Chacin (cchacin@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.javaee.cache;

import org.redisson.Redisson;
import org.redisson.core.RList;

import javax.inject.Inject;

public class CacheManagerRedis<T extends Cacheable> implements CacheManager<T> {

	private final Redisson redisson;

	@Inject
	public CacheManagerRedis(final Redisson redisson) {
		this.redisson = redisson;
	}

	@Override
	public final T get(final String key) {
		RList<T> list = this.getList(key);
		if (notExists(list)) {
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
		if (notExists(list)) {
			return false;
		}
		list.add(object);
		return true;
	}

	@Override
	public final boolean remove(final T object) {
		final RList<T> list = this.getList(object.getElementsKey());
		if (notExists(list)) {
			return false;
		}
		list.remove(object);
		return true;
	}

	private boolean notExists(final RList<T> list) {
		return list == null || list.isEmpty();
	}
}
