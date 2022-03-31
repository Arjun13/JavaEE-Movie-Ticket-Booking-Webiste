/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmdb_api;

/**
 *
 * @author arjun
 */

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;


public class Tmdb
{	
	// will be used to query the result for specific movie ID. Saves the work of not using multiple
	// split() function to get the name of the movie from the DB
	private static final String[] movieArray = {"Space+Jam+A+New+Legacy", "Black+Widow", "F9+The+Fast+Saga", 
												"The+Forever+Purge", "The+Hitman’s+Wife’s+Bodyguard", "Cruella", 
												"Snake+Eyes", "Roadrunner+A+Film+About+Anthony+Bourdain"};
	
	private static final String APIKEY = "api_key=e33f505907708fb34543b4878c417ba0";
	private static HttpURLConnection connection;
	private static String ratings;
	private static ArrayList<String> actorsName = new ArrayList<String>();  
	private static String[] stringArray = new String[2];

	// this function retrieves the rating for the given movie id
	public static String[] getRatingsAndCasts(int movieId)
	{
		try
		{
			movieId-=1;
			String line;
			StringBuilder responseContent = new StringBuilder();
			BufferedReader reader;

			String link = "http://api.themoviedb.org/3/search/movie?";
			String query = "&query=";
			
			URL url = new URL(link + APIKEY + query + movieArray[movieId]);
			connection = (HttpURLConnection) url.openConnection();
			
			// Request Setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			 int status = connection.getResponseCode();
			// System.out.println("Status: " + status);
			
			if (status>299)
			{
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
				}
				reader.close();
			}
			else
			{
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
				}
				reader.close();
			}
			
			JSONObject obj = new JSONObject(responseContent.toString());
			JSONArray arr = obj.getJSONArray("results");
			
			// gets the ratings for the movie and stores it in index 1
			double vote_average = arr.getJSONObject(0).getDouble("vote_average");
			stringArray[0] = String.format("%.01f", vote_average);
			
			// gets the movie ID to retrieve casts later and stores it in index 2
			int id = arr.getJSONObject(0).getInt("id");
			stringArray[1] = String.valueOf(id);
			
			System.out.println("The ratings for " + movieArray[movieId] + " is: " + ratings);
			System.out.println("The ID for " + movieArray[movieId] + " is: " + id);
			
			return stringArray;
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.disconnect();
		}
		return null;
	}

	public static String getActor(int TMDBid)
	{
		try
		{
			String line;
			StringBuilder responseContent = new StringBuilder();
			BufferedReader reader;

			String link = "http://api.themoviedb.org/3/movie/";
			URL url = new URL(link + TMDBid + "/credits?" + APIKEY);
			connection = (HttpURLConnection) url.openConnection();
			
			// Request Setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			 int status = connection.getResponseCode();
			// System.out.println("Status: " + status);
			
			if (status>299)
			{
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
				}
				reader.close();
			}
			else
			{
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
				}
				reader.close();
			}
			
			
			JSONObject obj = new JSONObject(responseContent.toString());
			JSONArray arr = obj.getJSONArray("cast");
			actorsName.clear();
			
			for (int i=0; i<7; i++)
			{
				actorsName.add(arr.getJSONObject(i).getString("name"));				
			}
			
			System.out.println("Cast: " + actorsName);
			String str = String.join(", ", actorsName);
			
			return str;
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.disconnect();
		}
		return null;
	}
}







