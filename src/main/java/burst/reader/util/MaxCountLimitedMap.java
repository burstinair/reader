package burst.reader.util;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MaxCountLimitedMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 952309954406829445L;

	private int maxCount;

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
	public MaxCountLimitedMap() { }
	public MaxCountLimitedMap(int maxCount) {
		this.maxCount = maxCount;
	}
	
	private ConcurrentLinkedQueue<K> q = new ConcurrentLinkedQueue<K>();
	
	private void ensureCountInMap() {
		while(q.size() > maxCount) {
			super.remove(q.poll());
		}
	}
	
	@Override
	public V put(K key, V value) {
		V res = super.put(key, value);
		ensureCountInMap();
		q.add(key);
		return res;
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		super.putAll(m);
		for (K key : m.keySet()) {
			q.add(key);
		}
		ensureCountInMap();
	}
	
	@Override
	public V putIfAbsent(K key, V value) {
		V res = super.putIfAbsent(key, value);
		q.add(key);
		ensureCountInMap();
		return res;
	}
	
	@Override
	public V remove(Object key) {
		q.remove(key);
		return super.remove(key);
	}
	
	@Override
	public boolean remove(Object key, Object value) {
		q.remove(key);
		return super.remove(key, value);
	}
	
	@Override
	public void clear() {
		q.clear();
		super.clear();
	}
	
}
