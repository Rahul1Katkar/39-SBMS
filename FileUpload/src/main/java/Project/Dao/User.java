package Project.Dao;


public class User {
	
	Integer Id;
	String Name;
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + "]";
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
	

}
