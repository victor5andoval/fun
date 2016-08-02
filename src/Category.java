import java.util.HashSet;
import java.util.List;


public interface Category {
    String getName();
    Category getParent();
    HashSet<String> getCategories();
    String addCategory(String category);
}
