package others;

import java.util.HashMap;
import java.util.Map;

public class MyMapper<V, W> {

	Map<V, W> map;

	public MyMapper() {
		map = new HashMap<>();
	}

	public MyMapper<V, W> add(V key, W value) {
		if (map == null)
			throw new NullPointerException();

		map.put(key, value);
		return this;
	}

	public W getByKey(V key) {
		return map.get(key);
	}

}
