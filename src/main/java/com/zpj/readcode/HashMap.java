/*
 *  @(#)HashMap.java	1.73 07/03/13
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *//*


package com.zpj.readcode;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

*/
/**
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.
 *
 * <p>This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the initial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.
 *
 * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of buckets in the hash table, and the initial
 * capacity is simply the capacity at the time the hash table is created.  The
 * <i>load factor</i> is a measure of how full the hash table is allowed to
 * get before its capacity is automatically increased.  When the number of
 * entries in the hash table exceeds the product of the load factor and the
 * current capacity, the hash table is <i>rehashed</i> (that is, internal data
 * structures are rebuilt) so that the hash table has approximately twice the
 * number of buckets.
 *
 * <p>As a general rule, the default load factor (.75) offers a good tradeoff
 * between time and space costs.  Higher values decrease the space overhead
 * but increase the lookup cost (reflected in most of the operations of the
 * <tt>HashMap</tt> class, including <tt>get</tt> and <tt>put</tt>).  The
 * expected number of entries in the map and its load factor should be taken
 * into account when setting its initial capacity, so as to minimize the
 * number of rehash operations.  If the initial capacity is greater
 * than the maximum number of entries divided by the load factor, no
 * rehash operations will ever occur.
 *
 * <p>If many mappings are to be stored in a <tt>HashMap</tt> instance,
 * creating it with a sufficiently large capacity will allow the mappings to
 * be stored more efficiently than letting it perform automatic rehashing as
 * needed to grow the table.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash map concurrently, and at least one of
 * the threads modifies the map structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more mappings; merely changing the value
 * associated with a key that an instance already contains is not a
 * structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.
 *
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:<pre>
 *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
 *
 * <p>The iterators returned by all of this class's "collection view methods"
 * are <i>fail-fast</i>: if the map is structurally modified at any time after
 * the iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author  Doug Lea
 * @author  Josh Bloch
 * @author  Arthur van Hoff
 * @author  Neal Gafter
 * @version 1.73, 03/13/07
 * @see     Object#hashCode()
 * @see     Collection
 * @see	    Map
 * @see	    TreeMap
 * @see	    Hashtable
 * @since   1.2
 *//*


public class HashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
{

    */
/**
     * The default initial capacity - MUST be a power of two.
     *//*

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    */
/**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     *//*

    static final int MAXIMUM_CAPACITY = 1 << 30;

    */
/**
     * The load factor used when none specified in constructor.
     *//*

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    */
/**
     * The table, resized as necessary. Length MUST Always be a power of two.
     *//*

    transient Entry[] table;

    */
/**
     * The number of key-value mappings contained in this map.
     *//*

    transient int size;

    */
/**
     * The next size value at which to resize (capacity * load factor).
     * @serial
     *//*

    int threshold;

    */
/**
     * The load factor for the hash table.
     *
     * @serial
     *//*

    final float loadFactor;

    */
/**
     * 这个数字是用来记录HashMap中数据改变的次数，好像是为了迭代器使用
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  
     * This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     *//*

    transient volatile int modCount;

    */
/**
     * 用指定的容量和系数来创建HashMap
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     *//*

    public HashMap(int initialCapacity, float loadFactor) {
    	//容量小于0 抛出不合法参数异常
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +initialCapacity);
        //如果初始化容量大于1<<30，则按照最大容量进行初始化  MAXIMUM_CAPACITY == 1 << 30 == 2^30
        if (initialCapacity > MAXIMUM_CAPACITY) initialCapacity = MAXIMUM_CAPACITY;
        //如果阀值系数<=0或者Not-a-Number  抛出不合法参数异常
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        //初始化initialCapacity如果不是2^n，则取大于initialCapacity的最小2^n为初始化容量。为什么会这样？？？？见：附录01
        while (capacity < initialCapacity)
        	// <<=  还能这样玩，长见识了！！！
            capacity <<= 1;
        //这四行和HashMap()相同
        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
        table = new Entry[capacity];
        init();
    }

    */
/**
     * 用指定的初始化容量（initialCapacity）和默认的系数0.75来创建一个HashMap
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     *//*

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    */
/**
     * 以容量16，系数为0.75构造一个空的HashMap
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     *//*

    	//DEFAULT_LOAD_FACTOR=0.75
    	//DEFAULT_INITIAL_CAPACITY= 16
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        // threshold：阀值，用于判断是否需要扩容，如果容量>threshold,则需要进行扩容操作
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        //table为一个Entry数组 ，实例化table，初始容量16。
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
        init();
    }

    */
/**
     * 
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     *//*

    public HashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                      DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
        putAllForCreate(m);
    }

    // internal utilities

    */
/**
     * 该方法是为子类留用的，在hashMap初始化之后调用，在子类中覆写该方法进行一写子类的特殊操作
     * Initialization hook for subclasses. This method is called
     * in all constructors and pseudo-constructors (clone, readObject)
     * after HashMap has been initialized but before any entries have
     * been inserted.  (In the absence of this method, readObject would
     * require explicit knowledge of subclasses.)
     *//*

    void init() {
    }

    */
/**
     * Applies a supplemental hash function to a given hashCode, which
     * defends against poor quality hash functions.  This is critical
     * because HashMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits. Note: Null keys always map to hash 0, thus index 0.
     *//*

    //神奇！勿动！
    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    */
/**
     * Returns index for hash code h.
     *//*

    static int indexFor(int h, int length) {
    	//为什么这样计算呢？ 标识：XX03
        return h & (length-1);
    }

    */
/**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     *//*

    public int size() {
        return size;
    }

    */
/**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     *//*

    public boolean isEmpty() {
        return size == 0;
    }

    */
/**
     * 返回指定key在map中的value值，当map中没有该key时返回null值
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * @see #put(Object, Object)
     *//*

    //找到key的value，讲过，不讲
    public V get(Object key) {
        if (key == null)
        	//见上面的讲解
            return getForNullKey();
        int hash = hash(key.hashCode());
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }

    */
/**
     * 老版本中通过get（）方法寻找key为null的值，key为null的值放在tableb[0]的位置
     * 
     * Offloaded version of get() to look up null keys.  Null keys map
     * to index 0.  
     * 英语能力有限，无法断句，求大神翻译！！！
     * This null case is split out into separate methods
     * for the sake of performance in the two most commonly used
     * operations (get and put), but incorporated with conditionals in
     * others.
     *//*

    //找到key= null的value
    private V getForNullKey() {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null)
                return e.value;
        }
        return null;
    }

    */
/**
     * 如果map中有指定的key则返回true
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     *//*

    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    */
/**
     * 返回指定key在hashMap中关联的entry，如果没有包含该key则返回null
     * Returns the entry associated with the specified key in the
     * HashMap.  Returns null if the HashMap contains no mapping
     * for the key.
     *//*

    //循环 判断 返回 不讲
    final Entry<K,V> getEntry(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        //注意此for循环的使用
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }


    */
/**
     * 把给定的K-V键值对放入map，如果map已经有关于K的映射，则把value覆盖以前的旧值
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     *//*

    public V put(K key, V value) {
    	//如果key为null，调用putForNullKey（）进行存储，由此可见 HashMap的key允许为null
        if (key == null)
        	//TODO  
            return putForNullKey(value);
        //根据hash算法计算hashCode
        int hash = hash(key.hashCode());
        //根据hashCode和table.length找到key所在table数组中的下标
        int i = indexFor(hash, table.length);
        //取出table[i]的链表头节点，循环链表判断是否有重复数据
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            //此处判重为什么不直接判断key值，而要同时判断hash是否相同？既然已经找到table[i],只需要在链表中判断key是否相同即可？？ 标识：XX04
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            	//如果有重复则覆盖原来数据
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        //没有重复则新添加数据
        modCount++;
        addEntry(hash, key, value, i);
        return null;
    }

    */
/**
     * Offloaded version :没有翻译出什么意思
     * 把key为null的值放入map
     * Offloaded version of put for null keys
     *//*

    private V putForNullKey(V value) {
    	//HashMap是用数组+链表进行数据存储的，在这里，程序把所有key为null的value放在table[0]的链表中。
    	//但不等同于table[0]的链表中不会存储key！= null的数据，因为有些key值通过hashCode（）计算出的数组下标有可能为0
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        	//找到key== null的节点，跟新value值
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                //这个方法没看出来是干嘛的  标识：XX01
                e.recordAccess(this);
                return oldValue;
            }
        }
        //如果HashMap中没找打key为null的值，则进行插入操作
        //修改次数+1  标识：XX02
        modCount++;
        //把数据添加到table[0]位置上链表的第一个元素
        addEntry(0, null, value, 0);
        return null;
    }

    */
/**
     * 该方法将会被构造器调用而不是put()。不需要对容器进行大小判断和扩容操作
     * 这是创建entry而不是添加entry
     * This method is used instead of put by constructors and
     * pseudoconstructors (clone, readObject).  It does not resize the table,
     * check for comodification, etc.  It calls createEntry rather than
     * addEntry.
     *//*

    // 不解释 重复
    private void putForCreate(K key, V value) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);

        */
/**
         * 通过key寻找以前存在的entry,在克隆和反序列化的时候不会发生，
         * 当map是一个有序map且
         * Look for preexisting entry for key.  This will never happen for
         * clone or deserialize.  It will only happen for construction if the
         * input Map is a sorted map whose ordering is inconsistent w/ equals.
         *//*

        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k)))) {
                e.value = value;
                return;
            }
        }
        //创建一个新的entry
        createEntry(hash, key, value, i);
    }

    //不再解释
    private void putAllForCreate(Map<? extends K, ? extends V> m) {
        for (Iterator<? extends Map.Entry<? extends K, ? extends V>> i = m.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<? extends K, ? extends V> e = i.next();
            putForCreate(e.getKey(), e.getValue());
        }
    }

    */
/**
     * 把map中的内容改写到一个容量更大的map中去，当插入时key的数量达到阀值的时候回自动调用本方法
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     *//*

    void resize(int newCapacity) {
    	//先保存原数据
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        //如果table容量已经达到最大值，此时无法再继续扩容，只能提高阀值，把threshold设置为Integer.MAX_VALUE。
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        //新创建一个table size=newCapacity
        Entry[] newTable = new Entry[newCapacity];
        //把原数据复制进新table中
        transfer(newTable);
        //更新table
        table = newTable;
        //更新threshold
        threshold = (int)(newCapacity * loadFactor);
    }

    */
/**
     * 把entrys从当前的table转移到newTable
     * Transfers all entries from current table to newTable.
     *//*

    void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        //循环复制table
        for (int j = 0; j < src.length; j++) {
            Entry<K,V> e = src[j];
            if (e != null) {
            	//取消指向链表的引用，交由gc进行处理
                src[j] = null;
                //循环复制链表
                do {
                    Entry<K,V> next = e.next;
                    //根据hashCode计算该该节点 在table中所处的位置
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];//注意此处返回的是链表的头节点，不是一个整链表，不要理解错误（弄明白什么是链表，什么事节点以及他们的关系）
                    newTable[i] = e;//把加入链表的节点加在table[i]中。
                    e = next;
                } while (e != null);
            }
        }
    }

    */
/**
     * 把给定的map复制进this.map，如果给定的map中有与this.map重复的key，则this.map中的数值将会被覆盖掉
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     *//*

    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        //如果m为空map则返回
        if (numKeysToBeAdded == 0)
            return;

        */
/*
         * Expand the map if the map if the number of mappings to be added
         * is greater than or equal to threshold.  This is conservative（保守的）; the
         * obvious condition is (m.size() + size) >= threshold, but this
         * condition could result in a map with twice the appropriate capacity（合理的容量）,
         * if the keys to be added overlap with the keys already in this map.
         * By using the conservative calculation, we subject ourself
         * to at most one extra resize.
         *//*

        //如果addedMap的容量大于this.map的阀值则进行扩容
        if (numKeysToBeAdded > threshold) {
        	//因为 threshold = (int)(capacity * loadFactor);所以capacity= threshold/loadFactor，为什么后面需要+1？ 标识：XX05
            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
            //容量最大只能取MAXIMUM_CAPACITY
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;
            
            int newCapacity = table.length;
            //求出newCapacity
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;
            //进行扩容操作
            if (newCapacity > table.length)
                resize(newCapacity);
        }
        //通过迭代器进行table复制
        for (Iterator<? extends Map.Entry<? extends K, ? extends V>> i = m.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<? extends K, ? extends V> e = i.next();
            put(e.getKey(), e.getValue());
        }
    }

    */
/**
     * 如果存在则删除指定key的entry
     * Removes the mapping for the specified key from this map if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     *//*

    public V remove(Object key) {
        Entry<K,V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    */
/**
     * 删除并返回改HashMap中指定的key所关联的entry，如果hashMap不包含该key则返回null
     * Removes and returns the entry associated with the specified key
     * in the HashMap.  Returns null if the HashMap contains no mapping
     * for this key.
     *//*

    final Entry<K,V> removeEntryForKey(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());//如果key== null ,则返回0，因为key==0的数据在table[0]中存储。
        int i = indexFor(hash, table.length);
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;
        //循环
        while (e != null) {
            Entry<K,V> next = e.next;
            Object k;
            //判断
            if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k)))) {
                modCount++;//操作次数++
                size--;//HashMap.size--;
                //删除
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                e.recordRemoval(this);
                //返回
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    */
/**
     * 为EntrySet定制的方法
     * Special version of remove for EntrySet.
     *//*

    final Entry<K,V> removeMapping(Object o) {
    	//类型不对肯定不在map中就不用继续查找直接返回。
        if (!(o instanceof Map.Entry))
            return null;
        //重复、不解释
        Map.Entry<K,V> entry = (Map.Entry<K,V>) o;
        Object key = entry.getKey();
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;

        //循环链表，找到target进行删除
        while (e != null) {
            Entry<K,V> next = e.next;
            if (e.hash == hash && e.equals(entry)) {
                modCount++;
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    */
/**
     * 从map中移除所有的数据
     * 调用返回后map将被清空
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     *//*

    public void clear() {
    	//这里也++啊！！！到底干嘛用的？？？？？？
        modCount++;
        Entry[] tab = table;
        //清除引用
        for (int i = 0; i < tab.length; i++)
            tab[i] = null;
        //size置0
        size = 0;
    }

    */
/**
     * 如果map中包含一个或者多个value则返回true
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     *//*

    public boolean containsValue(Object value) {
	if (value == null)
            return containsNullValue();

	Entry[] tab = table;
	//循环table
        for (int i = 0; i < tab.length ; i++)
        	//循环链表，看来此方法挺耗时的！！
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
	return false;
    }

    */
/**
     * 特例   判断是够包括 value == null的 value
     * 为什么不重用 public boolean containsValue(Object value) 而不进行value== null判断呢？？？？？？
     * Special-case code for containsValue with null argument
     *//*

    //重复 不讲
    private boolean containsNullValue() {
	Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (e.value == null)
                    return true;
	return false;
    }

    */
/**
     *  shallow copy 克隆==浅拷贝？？？	标识：XX06
     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
     * values themselves are not cloned.
     *
     * @return a shallow copy of this map
     *//*

    public Object clone() {
        HashMap<K,V> result = null;
	try {
	    result = (HashMap<K,V>)super.clone();
	} catch (CloneNotSupportedException e) {
	    // assert false;
	}
        result.table = new Entry[table.length];
        result.entrySet = null;
        result.modCount = 0;
        result.size = 0;
        result.init();
        result.putAllForCreate(this);

        return result;
    }

    //静态内部类
    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        final int hash;

        */
/**
         * Creates new entry.
         *//*

        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }
        //setValue的时候把oldValue返回调用处
        public final V setValue(V newValue) {
	    V oldValue = value;
            value = newValue;
            return oldValue;
        }

        //判断entry是否相同，条件是key和value同时相同
        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return (key==null   ? 0 : key.hashCode()) ^
                   (value==null ? 0 : value.hashCode());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        */
/**
         * This method is invoked whenever the value in an entry is
         * overwritten by an invocation of put(k,v) for a key k that's already
         * in the HashMap.
         *//*

        void recordAccess(HashMap<K,V> m) {
        }

        */
/**
         * This method is invoked whenever the entry is
         * removed from the table.
         *//*

        void recordRemoval(HashMap<K,V> m) {
        }
    }

    */
/**
     * 把给定的key、value、和hashCode组成的entry放入容器，如果被appropriate则需要去修改table的size
     * Adds a new entry with the specified key, value and hash code to
     * the specified bucket.  It is the responsibility of this
     * method to resize the table if appropriate.
     *子类可以通过覆写这个方法去修改put方法。
     * Subclass overrides this to alter the behavior of put method.
     *//*

    void addEntry(int hash, K key, V value, int bucketIndex) {
    //取出table[bucketIndex]位置的链表节点e
	Entry<K,V> e = table[bucketIndex];
		//	创建新的entry放在table[bucketIndex]
        table[bucketIndex] = new Entry<K,V>(hash, key, value, e);
        //如果需要调整大小则进行resize()操作
        if (size++ >= threshold)
        	//按照两倍进行扩容，依旧符合容量为偶数的标准
            resize(2 * table.length);
    }

    */
/**
     * 该方法除了在一些构造器中创建新的entry之外和addEntry一样。这个版本不需要担心调整容量的问题
     * Like addEntry except that this version is used when creating entries
     * as part of Map construction or "pseudo-construction" (cloning,
     * deserialization).  This version needn't worry about resizing the table.
     * 
     *子类通过重写该方法去修改HashMap(Map)的构造方法、clone和readObject。
     *即：只有这三个方法才会调用该方法，而这三个方法是进行创建、克隆和反序列化，而不是添加新的entry，因此不必考虑容量的问题。
     * Subclass overrides this to alter the behavior of HashMap(Map),
     * clone, and readObject.
     *//*

    //操作代码不再讲解  Ctrl+alt+H 进行截图查看调用处
    void createEntry(int hash, K key, V value, int bucketIndex) {
	Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K,V>(hash, key, value, e);
        size++;
    }

    //HashMap 通过Iterator 迭代器进行迭代输出，通过此对象进行实现迭代输出
    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K,V> next;	// next entry to return  下一个将要输出的节点
        int expectedModCount;	// For fast-fail     用来表示map在进行迭代期间是否有其他线程对map进行操作
        int index;		// current slot                        当前对象（entry）在table中的位置
        Entry<K,V> current;	// current entry    当前进行输出的节点

        HashIterator() {
        	//初始化的时候设置此两值相同，在进行next的时候进行判断，如果相同则进行操作，不相同（说明同时有其他线程对map数据进行修改。不允许）则抛出异常。
            expectedModCount = modCount;
            if (size > 0) { // advance to first entry
                Entry[] t = table;
                //找到下一个不为null的链表的头结点
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        //从table中找到下一个entry链表的头节点，此方法只是更新current和next
        final Entry<K,V> nextEntry() {
        	//在迭代期间如果有其他线程对map进行数据操作操作则抛出异常。
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            Entry<K,V> e = next;
            if (e == null)
                throw new NoSuchElementException();

            //找到下一个不为null的节点
            if ((next = e.next) == null) {
                Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
	    current = e;
            return e;
        }

        public void remove() {
            if (current == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            Object k = current.key;
            current = null;//entry 置空
            //从Map中删除此节点
            HashMap.this.removeEntryForKey(k);
            //重新更新操作标识
            expectedModCount = modCount;
        }

    }

    private final class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().value;
        }
    }

    private final class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
        public Map.Entry<K,V> next() {
            return nextEntry();
        }
    }

    // Subclass overrides these to alter behavior of views' iterator() method
    Iterator<K> newKeyIterator()   {
        return new KeyIterator();
    }
    Iterator<V> newValueIterator()   {
        return new ValueIterator();
    }
    Iterator<Map.Entry<K,V>> newEntryIterator()   {
        return new EntryIterator();
    }


    // Views

    private transient Set<Map.Entry<K,V>> entrySet = null;

    */
/**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *//*

    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return newKeyIterator();
        }
        public int size() {
            return size;
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return HashMap.this.removeEntryForKey(o) != null;
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    */
/**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *//*

    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null ? vs : (values = new Values()));
    }

    private final class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return newValueIterator();
        }
        public int size() {
            return size;
        }
        public boolean contains(Object o) {
            return containsValue(o);
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    */
/**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     *//*

    public Set<Map.Entry<K,V>> entrySet() {
	return entrySet0();
    }

    private Set<Map.Entry<K,V>> entrySet0() {
        Set<Map.Entry<K,V>> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        public Iterator<Map.Entry<K,V>> iterator() {
            return newEntryIterator();
        }
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<K,V> e = (Map.Entry<K,V>) o;
            Entry<K,V> candidate = getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }
        public boolean remove(Object o) {
            return removeMapping(o) != null;
        }
        public int size() {
            return size;
        }
        public void clear() {
            HashMap.this.clear();
        }
    }

    */
/**
     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>capacity</i> of the HashMap (the length of the
     *		   bucket array) is emitted (int), followed by the
     *		   <i>size</i> (an int, the number of key-value
     *		   mappings), followed by the key (Object) and value (Object)
     *		   for each key-value mapping.  The key-value mappings are
     *		   emitted in no particular order.
     *//*

    private void writeObject(java.io.ObjectOutputStream s)
        throws IOException
    {
	Iterator<Map.Entry<K,V>> i =
	    (size > 0) ? entrySet0().iterator() : null;

	// Write out the threshold, loadfactor, and any hidden stuff
	s.defaultWriteObject();

	// Write out number of buckets
	s.writeInt(table.length);

	// Write out size (number of Mappings)
	s.writeInt(size);

        // Write out keys and values (alternating)
	if (i != null) {
	    while (i.hasNext()) {
		Map.Entry<K,V> e = i.next();
		s.writeObject(e.getKey());
		s.writeObject(e.getValue());
	    }
        }
    }

    private static final long serialVersionUID = 362498820763181265L;

    */
/**
     * Reconstitute the <tt>HashMap</tt> instance from a stream (i.e.,
     * deserialize it).
     *//*

    private void readObject(java.io.ObjectInputStream s)
         throws IOException, ClassNotFoundException
    {
	// Read in the threshold, loadfactor, and any hidden stuff
	s.defaultReadObject();

	// Read in number of buckets and allocate the bucket array;
	int numBuckets = s.readInt();
	table = new Entry[numBuckets];

        init();  // Give subclass a chance to do its thing.

	// Read in size (number of Mappings)
	int size = s.readInt();

	// Read the keys and values, and put the mappings in the HashMap
	for (int i=0; i<size; i++) {
	    K key = (K) s.readObject();
	    V value = (V) s.readObject();
	    putForCreate(key, value);
	}
    }

    // These methods are used when serializing HashSets
    int   capacity()     { return table.length; }
    float loadFactor()   { return loadFactor;   }
}
*/
