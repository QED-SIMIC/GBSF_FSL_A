package bo;

/**
 * User -- Business Object representing the User
 *
 */

public class User {

	private String userName;
	private String password;

	// ----------------------------------------------------------
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;

	}

	// ----------------------------------------------------------
	public String getUserName() {
		return this.userName;
	}

	// ----------------------------------------------------------
	public String getPassword() {
		return this.password;
	}

	// ----------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("(");
		sb.append(userName);
		sb.append(", ");
		sb.append(password);
		sb.append(")");

		return sb.toString();
	}
}

