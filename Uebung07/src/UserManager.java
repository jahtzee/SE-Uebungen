/**
 * @author Jan Zimmer last modified 26.12.2022
 * 
 *         Using a singleton pattern given example (a). The pattern ensures
 *         there can only ever be one instance of UserManager.
 */

class UserManager {
	private static UserManager instance;

	private UserManager() {
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public boolean isUsernameTaken(String username) {
		// TODO: Check whether given username is already taken
		return false;
	}
}
