package localhost.javaSailsRestDemo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Sample class that updates employee info in a json arraylist that is accessed
 * through sails
 * 
 * @author Stuart Filar
 * @since 2016-09-07
 * 
 */

public class UpdateEmployee {

	// URL of the API we want to connect to
	protected static String endpoint = "http://localhost:1337/employee/";

	// character set to use when encoding URL parameters
	protected static String charset = "UTF-8";

	// scanner for reading text input from the console
	protected static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// declaring variables
		String employeeID = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		String activeInput = null;
		String queryString = null;
		String active = null;
		String homePhone = null;
		String cellPhone = null;
		String password = null;

		try {

			// employee ID number
			System.out.println("What is the employee's ID number?");
			employeeID = sc.nextLine();

			// first name of the employee
			System.out.println("What is the employee's first name?");
			firstName = sc.nextLine();

			// last name of the employee
			System.out.println("What is the employee's last name?");
			lastName = sc.nextLine();

			// email of the employee
			System.out.println("What is the email of the employee?");
			email = sc.nextLine();

			// home phone of the employee
			System.out.println("What is the home phone number of the employee?");
			homePhone = sc.nextLine();

			// cell phone of the employee
			System.out.println("What is the cell phone number of the employee?");
			cellPhone = sc.nextLine();

			// password of employee
			System.out.println("What is the password of the employee?");
			password = sc.nextLine();

			// active status of the employee
			System.out.println("Is the employee's status currently active? (yes/no)");
			activeInput = sc.nextLine();

			// assigns 1 or 0 depending on employee's active status
			if (activeInput.equalsIgnoreCase("yes")) {
				active = "1";
			} else {
				active = "0";
			}

			// creates the url parameters as a string encoding them with the
			// define charset, based on conditions set by user input
			queryString = String.format(
					"%s?firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(employeeID, charset), URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset), URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset), URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset), URLEncoder.encode(active, charset));

			// creates a new URL out of the endpoint, returnType and queryString
			URL employeeList = new URL(endpoint + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeList.openConnection();
			connection.setRequestMethod("PUT");

			// if we did not get a 200(success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());

			} else {
				System.out.println("Employee's information has been added to the system.");
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
