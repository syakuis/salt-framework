package org.saltframework.store.model;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 2.
 */
public final class Store<V> {
	private String name;
	private V value;
	private boolean empty;

	public Store() {
		this.empty = true;
	}

	public Store(String name, V value) {
		this.empty = false;
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public V getValue() {
		return value;
	}

	public boolean isEmpty() {
		return empty;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Store<?> store = (Store<?>) object;

		if (!name.equals(store.name)) {
			return false;
		}
		return value.equals(store.value);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Store{@" + this.hashCode() +
				"; name='" + name + '\'' +
				", value=" + value +
				", empty=" + empty +
				'}';
	}
}
