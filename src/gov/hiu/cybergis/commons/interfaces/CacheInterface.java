package gov.hiu.cybergis.commons.interfaces;

public interface CacheInterface<K,O>
{
	public boolean resident(K key);
	public O get(K key);
	public O[] evict(K keys[]);
	public O evict(K key);
	public O cache(K key, O text);
}
