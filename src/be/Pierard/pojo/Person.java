package be.Pierard.pojo;

import java.util.Objects;

public class Person {
	private String firstname;
	private String lastname;
	private String nationality;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Person() {}
	public Person(String firstname, String lastname, String nationality) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.nationality = nationality;
	}
	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", nationality=" + nationality + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname, nationality);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(nationality, other.nationality);
	}
}
