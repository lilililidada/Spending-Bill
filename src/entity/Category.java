package entity;

public class Category {

	private int id;
	private String name;
	private int number;
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setNumber(int number) {
		this.number=number;
	}
	public int getNumber() {
		return number;
	}
	public Category(int id ,String name) {
		setId(id);
		setName(name);
	}
	public Category() {
		
	}
	public String toString() {
		return name;
	}
}
