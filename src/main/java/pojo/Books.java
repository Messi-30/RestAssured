package pojo;

public class Books {
	
	private Integer id;
	private String name;
	private String type;
	private Boolean available;
	
	public Books() {
		
	}
	
	public Books(Integer id, String name, String type, Boolean available) {
	
		this.id = id;
		this.name = name;
		this.type = type;
		this.available = available;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
