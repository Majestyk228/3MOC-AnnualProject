package objects;

public class Tag {

	// ATTRIBUTES
	private int idTag;
	private String name;

	// CONSTRUCTOR
	public Tag(int idTag, String name) {
		this.idTag = idTag;
		this.name = name;
	}

	public Tag() {
	}

	// GETTERS & SETTERS
	public int getIdTag() {
		return idTag;
	}

	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// TO STRING
	@Override
	public String toString() {
		return name;
	}
}
