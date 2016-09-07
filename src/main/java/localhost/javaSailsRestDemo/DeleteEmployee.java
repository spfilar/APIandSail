package localhost.javaSailsRestDemo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Sample class that asks a user for an employee to be deleted that is stored in
 * a json arraylist that is accessed through sails
 * 
 * @author Stuart Filar
 * @since 2016-09-06
 * 
 */

public class DeleteEmployee {

	// URL of the API we want to connect to
	protected static String endpoint = "http://localhost:1337/employee/";

	// character set to use when encoding URL parameters
	protected static String charset = "UTF-8";

	// scanner for reading text input from the console
	protected static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// declaring variables
		String employeeID = null;
		String queryString = null;

		try {

			// id of the employee
			System.out.println("What is the employee's id#?");
			employeeID = sc.nextLine();

			// creates the url parameters as a string encoding them with the
			// define charset, based on conditions set by user input
			queryString = String.format("id=%s", URLEncoder.encode(employeeID, charset));

			// creates a new URL out of the endpoint, returnType and queryString
			URL employeeList = new URL(endpoint + "?" + queryString);

			HttpURLConnection connection = (HttpURLConnection) employeeList.openConnection();
			connection.setRequestMethod("DELETE");

			// if we did not get a 201(success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());

			} else {
				System.out.println("employee #" + employeeID + " was deleted from the record.");
			}

			// close connection to API
			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
