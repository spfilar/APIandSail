package localhost.javaSailsRestDemo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;

/**
 * Sample class that lists employee info in a json arraylist that is accessed
 * through sails
 * 
 * @author Stuart Filar
 * @since 2016-09-07
 * 
 */

public class GetEmployeeList {

	// URL of the API we want to connect to
	protected static String endpoint = "http://localhost:1337/employee";

	// character set to use when encoding URL parameters
	protected static String charset = "UTF-8";

	// scanner for reading text input from the console
	protected static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			// creates a new URL out of the endpoint, returnType and queryString
			URL employeeList = new URL(endpoint);
			HttpURLConnection connection = (HttpURLConnection) employeeList.openConnection();
			connection.setRequestMethod("GET");

			// if we did not get a 201(success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
			}

			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// loop of buffer line by line until it returns null
			while (br.readLine() != null) {
				// print out each line to the screen
				System.out.println(br.readLine());
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
