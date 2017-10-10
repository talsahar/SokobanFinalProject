package view.graphicsBuilder;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import model.data.XObj;

public class ImageMap {

	private Map<Class<? extends XObj>, Image> firstMap;
	private Map<Integer, Image> secondMap;

	public ImageMap() {
		firstMap = new HashMap<Class<? extends XObj>, Image>();
		secondMap = new HashMap<Integer, Image>();
	}

	public Image extractFirstMap(Class<? extends XObj> className) {
		return firstMap.get(className);

	}

	public Image extractSecondMap(Integer integer) {
		return secondMap.get(integer);

	}

	public Map<Class<? extends XObj>, Image> getFirstMap() {
		return firstMap;
	}

	public Map<Integer, Image> getSecondMap() {
		return secondMap;
	}

	public void insertFirstMap(Class<? extends XObj> className, Image image) {
		firstMap.put(className, image);

	}

	public void insertSecondMap(Integer integer, Image image) {
		secondMap.put(integer, image);

	}

	public void setFirstMap(Map<Class<? extends XObj>, Image> firstMap) {
		this.firstMap = firstMap;
	}

	public void setSecondMap(Map<Integer, Image> secondMap) {
		this.secondMap = secondMap;
	}
}
