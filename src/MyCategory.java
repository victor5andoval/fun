import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MyCategory {
	
	private HashSet<MyCategory> categories = new HashSet<MyCategory>();
	private MyCategory parent;
	private String name;

	public MyCategory(MyCategory parent, String name) {
		this.parent = parent;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public MyCategory getParent() {
		return parent;
	}

	public HashSet<MyCategory> getCategories() {
		return categories;
	}

	public MyCategory addCategory(String name) {
		MyCategory newCategory = new MyCategory(this, name);
		categories.add(newCategory);
		return newCategory;
	}

}
