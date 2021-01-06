package WorkPaySystem;

public class WorkersDetails {
	
	private int id;
	private String name;
	private int age;
	private int type;
	private int totalrup;
	
	public WorkersDetails(int id,String name,int age,int type,int totalrup) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.type = type;
		this.totalrup = totalrup;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotalrup() {
		return totalrup;
	}

	public void setTotalrup(int totalrup) {
		this.totalrup = totalrup;
	}

}
