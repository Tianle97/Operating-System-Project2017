import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Person implements Serializable {
	String Name;
	String Address;
	String PPS_Number;
	String Age;
	String height;
	String weight;
	ArrayList<Record> r = new ArrayList<Record>();
	
	public Person() {
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPPS_Number() {
		return PPS_Number;
	}

	public void setPPS_Number(String pPS_Number) {
		PPS_Number = pPS_Number;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public ArrayList<Record> getR() {
		return r;
	}

	public void setR(ArrayList<Record> r) {
		this.r = r;
	}
}
