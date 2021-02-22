package hashtable;


/**
 * Simple parameterized hash table implementation. This class is *not* a part
 * of the Java Collections Framework. 
 * <p>Internally the values are stored in an array of slots where each slot 
 * contains either null or an instance of the embedded TableEntry class. The 
 * TableEntry class allows for single-list chaining, so collisions are handeled
 * by iterating the list of stored values.
 * <p>The table slot is calculated by a simple algorithm - modulo division of 
 * the key's hashcode (actually the absolute value of it) through table size.
 * <p>For sparsly populated tables are expected to provide a constant-time 
 * access whereas densly populated ones provide linear access time due to 
 * list iteration for each slot.
 * <p>The table's internal array size does *not* change once instantiated.  
 * <p>This implementation doesn't allow for null-keys nor null-values to be 
 * stored. The get method will for a non-null key use a null return value to 
 * signal that the requested key wasn't found in the table.
 * 
 * @param <K> Type of the key
 * @param <V> Type of the value
 */
public class SimpleHashtable<K, V> {

	private TableEntry<K, V>[] table;
	private int size;

	/**
	 * Creates a new SimpleHashtable with the desired capacity. Actual 
	 * size of the internal array will be the smallest power of two greater or
	 * equal to the requested capacity.
	 * 
	 * @param capacity The requested length for the internal table.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {		
		table = (TableEntry<K, V>[])new TableEntry[nextExpo(capacity)];
		size = 0;
	}

	/**
	 * Default constructor will instantiate a table of DEFAULT_CAPACITY size. 
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable() { 
		this.table = (TableEntry<K, V>[])new TableEntry[nextExpo(16)];
	}

	/**
	 * Stores a key-value pair in the table. See the class javadoc for 
	 * implementation details. 
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void put(K key, V value) { 
		if(key == null) { return; }
		if(value == null) { return; }
		int slot = slot(key);
		if(table[slot] == null ) {
			table[slot] = new TableEntry<K, V>(key, value);
			this.size++;
		}
		else {
			TableEntry<K,V> current = table[slot];
			while(current.key == key && current.next != null) {
				current = current.next;
			}
			if(current.key == key) {
				current.value = value;
			} 
			else {
				current.next = new TableEntry<K,V>(key,value);
				this.size++;
			}
		}
	}

	/**
	 * Retrieves a key-value pair from the table. See class javadoc for 
	 * details.
	 * 
	 * <p>null is returned if a null-key is given or if the requested key 
	 * wasn't found in the table.
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if(key == null) { return null; }
		int slot = slot(key);
		TableEntry<K, V> entry = table[slot];
		while(entry != null) { 
			if(entry.key.equals(key)) { 
				return entry.value;
			}
			entry = entry.next;			
		}
		return null;
	}

	/**
	 * Returns the size of the table (number of stored key-value pairs). The 
	 * method works in constant time.
	 * 
	 * @return
	 */
	public int size() { return size; }

	/**
	 * Returns true if the requested key was found in the table, false 
	 * otherwise.
	 * <p>The method iterates through all the key-value pairs of a slot so will
	 * work in constant time for sparsly-populated tables and in linear to 
	 * size time for densly populated ones.
	 * 
	 * @param key		the key to find in the table
	 * @return 			true if the requested key was found, false otherwise
	 */
	public boolean containsKey(K key) { 
		if(key == null) { return false; }
		int slot = slot(key);
		TableEntry<K, V> entry = table[slot];
		while(entry != null) { 
			if(entry.key.equals(key)) { 
				return true;
			}
			entry = entry.next;			
		}
		return false;

	}

	/**
	 * Returns true if the requested value was found among the stored key-value
	 * pairs. The method iterates through all the key-value pairs so is 
	 * expected to work in time linear to size of the table.
	 * 
	 * @param value		the value to find in the table
	 * @return	 		true if the requested value was found in the table, false 
	 * 					otherwise
	 */
	public boolean containsValue(V value) { 
		if(value == null) { return false; }
		for(TableEntry<K, V> entry : table) {
			while(entry != null) { 
				if(entry.value.equals(value)) { 
					return true;
				}
				entry = entry.next;			
			}
		}
		return false;
	}

	/**
	 * Removes a key-value pair from the table.
	 * 
	 * @param key 		the key of the pair to remove. 
	 */
	public void remove(K key) {
		if(key == null) { return; }
		int slot = slot(key);
		TableEntry<K, V> entry = table[slot];
		if(table[slot].key.equals(key)) { 
			table[slot] = table[slot].next;
			this.size--;
		}
		else {			
			while(entry.next!=null) { 
				if(entry.next.key.equals(key)) { 
					entry.next=entry.next.next;
					this.size--;
					break;
				}
			}
		}
	}

	/**
	 * Returns true if the table is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty() { return size == 0; }

	/**
	 * toString() is overridden to create a string of the form:"SimpleHashtable
	 * (0=>(k00=>v00)->(k01=>v01)->...);1=>(k10=>v10)->(k11=>v11)->...);)..."
	 * where "=>" depicts index-to-slot or key-to-value binding and "->" 
	 * depicts a linear linked list among the slots.
	 * <p>Used for simple debugging purposes.
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("SimpleHashtable(");
		for(int i = 0; i < table.length; i++) {
			TableEntry<K,V> entry = table[i];
			if(entry != null) { 
				sb.append(i).append("  ").append(entry.toString()).append(";");
			}
		}
		return sb.append(")").toString();

	}

	/*
	 * Implementation as requested per assignment
	 */
	private int slot(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	public static class TableEntry<K, V> {
		private final K key;
		private V value;
		private TableEntry<K, V> next = null;

		public TableEntry(K key, V value) { 
			this.key = key;
			this.value = value;
		}

		/*
		 * Helper method to create a readable toString() output for the 
		 * outer class.
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() { 
			StringBuilder sb = new StringBuilder("")
					.append(key.toString())
					.append("=")
					.append(value.toString());
			return next == null 
					? sb.toString() 
							: sb.append("->")
							.append(next.toString())
							.toString();
		}
	}

	/*
	 * Static helper methods
	 */
	
	/**
	 * Returns the next bigger or equal 2's exponent. e.g. <code>nextExpo(3) 
	 * = 4, nextExpo(4) = 4, nextExpo(5) = 8</code>. 
	 * The method will throw an IllegalArgumentException if called with a 
	 * number less than 1.  
	 * 
	 * @param req		integer greater than 0 to find the next exponent of 2 
	 * @return 			Smallest exponent of 2 greater than or equal to the 
	 * 					method argument 
	 */
	final static int nextExpo(int req) { 
		if(req <= 0) 
			throw new IllegalArgumentException("Can't find next exponent for numbers smaller than 1");
		int index = 1;
		while (true) {
			if (req <= index) break;
				index = index*2;
		}
		
		return index;
	}

	public static void main(String[] args) { 
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Anja", Integer.valueOf(4));
		examMarks.put("Ante", Integer.valueOf(3));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5)); // overwrites old grade for Ivana
		// query collection:
		Integer kristinaGrade = examMarks.get("Kristina");
		System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
		// What is collection's size? Must be four!
		System.out.println("Number of stored pairs: " + examMarks.size()); // writes: 4
		System.out.println("toString():" + examMarks.toString());

	}
}