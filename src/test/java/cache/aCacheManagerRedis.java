package cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.redisson.Redisson;
import org.redisson.core.RSet;
import org.superbiz.javaee.cache.CacheManagerRedis;
import org.superbiz.javaee.cache.Cacheable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class aCacheManagerRedis {

	@Mock
	private Redisson redisson = Redisson.create();
	private CacheManagerRedis cut = new CacheManagerRedis(Redisson.create());

	private ACacheableObject objectToStore;

	@Mock
	private RSet<Object> rset;

	@Before
	public void init() {
		this.objectToStore = new ACacheableObject("aProperty");

		when(this.redisson.getSet(this.objectToStore.getElementsKey()))
				.thenReturn(this.rset);
	}

	@Test
	public void shouldStoreAKey() {
		this.cut.put(this.objectToStore);

		RSet<Object> result = this.redisson.getSet(this.objectToStore
				.getElementsKey());

		// verify(this.redisson,
		// times(1)).getList(this.objectToStore.getElementsKey()).add(1);
	}

	@Data
	@AllArgsConstructor
	static class ACacheableObject implements Cacheable {

		private String aProperty;

		@Override
		public String getElementsKey() {
			return "aCacheableObjectElementsKey";
		}

		@Override
		public String getIdsKey() {
			return "aCacheableObjectIdsKey";
		}
	}
}
