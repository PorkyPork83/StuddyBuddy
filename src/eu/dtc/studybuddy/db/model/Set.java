package eu.dtc.studybuddy.db.model;

public class Set {
	
	int _id;
	String _name;
	String _created_at;
	
	// empty constructor
	public Set() {
	}
	
	// constructor with ID
	public Set(int id, String name, String created_at) {
		this._id = id;
		this._name = name;
		this._created_at = created_at;
	}
	
	// constructor without ID
	public Set(String name, String created_at) {
		this._name = name;
		this._created_at = created_at;
	}
	
	// get ID
	public int getId() {
		return this._id;
	}
	// set ID
	public void setId(int id) {
		this._id = id;
	}
	
	// get Name
	public String getName() {
		return this._name;
	}
	
	// set Name
	public void setName(String name) {
		this._name = name;
	}
	
	// get Created_at
	public String getCreatedAt() {
		return this._created_at;
	}
	
	// set Created_at
	public void setCreatedAt(String created_at) {
		this._created_at = created_at;
	}
}
