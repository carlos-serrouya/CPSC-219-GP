package application;

/**
 * Since an interface is an abstract class, and this interface uses an abstract
 * method this interfaces satisfies our abstract requirement
 * Determines if the given set of paths is path connected.
 * @param paths The set of paths to test for path connectivity.
 * @param listOfPoints The list of all points to be tested for connectivity.
 * @return true if the set of paths is path connected, if not return false.
 * Author Carlos
 */

public interface TestingConnect {
	
	/**
     * Determines if the given set of paths in a one-dimensional array is path connected.
     * @param paths The set of paths to test for path connectivity.
     * @param listOfPoints The list of all points to be tested for connectivity.
     * @return true if the set of paths is path connected, false otherwise.
     */
	
	boolean oneDArrayConnect(String[] paths);
	// abstract method found in Path Connect
}
