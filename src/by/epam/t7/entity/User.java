package by.epam.t7.entity;

/**
 * The base class for all entities involved in business logic.
 */
public abstract class User {

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
