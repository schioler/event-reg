package dk.schioler.event.web.init;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchResultStruct implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<String> times = new ArrayList<String>();

	private Map <String, List<String>> categories = new TreeMap<String, List<String>>();
	
	public SearchResultStruct() {
	}

	public void addTime(String time) {
		this.times.add(time);
	}
	
	public List<String> getCategories(){
		return new ArrayList<String>(categories.keySet());
	}
	
	public void addCategory(String category) {
		if (!categories.containsKey(category.trim())) {
			categories.put(category.trim(), new ArrayList<String>());
		} 
	}
	
	public List<String> getData(String category){
		return categories.get(category);
	}
	
	public void addData(String category, String data) {
		List<String> list = categories.get(category);
		list.add(data);
		
	}

	
	public List<String> getTimes() {
		return times;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchResultStruct [times=");
		builder.append(times);
		builder.append(", categories=");
		builder.append(categories);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
