/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.moviebookEJB;
import entity.Description;
import entity.Showings;
import entity.Zipcodes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import tmdb_api.Tmdb;

/**
 *
 * @author arjun
 */
@FacesConfig
@Named(value = "mainPageBean")
@SessionScoped
public class MainPageBean implements Serializable
{

	@EJB
	private moviebookEJB moviebookEJB;
	private Zipcodes zip = new Zipcodes();
	private Description desc = new Description();
	private String adultSeatNum = "";
	private String seniorSeatNum = "";
	private String studentSeatNum = "";
	private String militarySeatNum = "";
	private int creditCardNumber = 0; 
	private Tmdb tmdb = new Tmdb();
	private String[] ratingsAndMovieID;
	private String casts;

	
	
	/**
	 * Creates a new instance of MainPageBean
	 */
	public MainPageBean()
	{
	}

	public moviebookEJB getMoviebookEJB()
	{
		return moviebookEJB;
	}

	public void setMoviebookEJB(moviebookEJB moviebookEJB)
	{
		this.moviebookEJB = moviebookEJB;
	}

	public Zipcodes getZip()
	{
		return zip;
	}

	public void setZip(Zipcodes zip)
	{
		this.zip = zip;
	}

	public Description getDesc()
	{
		return desc;
	}

	public void setDesc(Description desc)
	{
		this.desc = desc;
	}

	public String getAdultSeatNum()
	{
		return adultSeatNum;
	}

	public void setAdultSeatNum(String adultSeatNum)
	{
		this.adultSeatNum = adultSeatNum;
	}

	public String getSeniorSeatNum()
	{
		return seniorSeatNum;
	}

	public void setSeniorSeatNum(String seniorSeatNum)
	{
		this.seniorSeatNum = seniorSeatNum;
	}

	public String getStudentSeatNum()
	{
		return studentSeatNum;
	}

	public void setStudentSeatNum(String studentSeatNum)
	{
		this.studentSeatNum = studentSeatNum;
	}

	public String getMilitarySeatNum()
	{
		return militarySeatNum;
	}

	public void setMilitarySeatNum(String militarySeatNum)
	{
		this.militarySeatNum = militarySeatNum;
	}

	public int getCreditCardNumber()
	{
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
	}

	public Tmdb getTmdb()
	{
		return tmdb;
	}

	public void setTmdb(Tmdb tmdb)
	{
		this.tmdb = tmdb;
	}

	public String[] getRatingsAndMovieID()
	{
		return ratingsAndMovieID;
	}

	public void setRatingsAndMovieID(String[] ratingsAndMovieID)
	{
		this.ratingsAndMovieID = ratingsAndMovieID;
	}

	public String getCasts()
	{
		return casts;
	}

	public void setCasts(String casts)
	{
		this.casts = casts;
	}

	
	
	// NOT USING THIS SO FAR
	// opens the ShowTimes.xhtml
	public String ShowTimesPage(Zipcodes theaterName)
	{
		this.zip = theaterName;
		// zip.setZipcode(Integer.parseInt(userZipcode));
		return "ShowTimes";
	}
	
	// validates the zipcode from the input text box
	public void validateZipcode(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		int zipcode = (int) value;						// stores the zipcode as a string
		String zipcodeStr = Integer.toString(zipcode);	// stores the zipcode as a string
		String regexPattern = "^((75)\\d\\d\\d)$";		// regex pattern to check for the user input
		int length = zipcodeStr.length();				// stores the zipcode length
		boolean matches = Pattern.matches(regexPattern, zipcodeStr);
		
		/* If the user enters anything but the four zipcodes, then throws and error.
		   Four Zipcodes: 75206, 75080, 75093, 75075
		*/
		if(!matches || length!=5 || zipcode!=75080 && zipcode!=75206 && zipcode!= 75093 && zipcode!=75075)
		{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: The zipcode is invalid!", null));
		}
	}
	
	// return the theater's list of user inputted zipcode from the custom created
	// query in Zipcodes.java
	public List<Zipcodes> getTheaterList()
	{
		return moviebookEJB.findTheatersName(zip.getZipcode());
	}
	
	// gets the list of movie
	public List<Description> getMovieNames()
	{
		return moviebookEJB.findMovieName();
	}
	
	// save the theater name selected and open the page that displays the show time of
	// all movie of that theater and zipcode
	public String showNextPage(Zipcodes theaterName)
	{
		this.zip = theaterName;
		return "ShowTimes";
	}
	
	// shows all the showtime for each movie given the theater name and zipcode
	public List<Showings> getAllShowTime(Description movie)
	{
		return moviebookEJB.findAllShowings(movie, zip);
	}
	
	// shows the description page
	public String showDescriptionPage(Description moviename)
	{
		this.desc = moviename;
		return "ShowDescription";
	}
	
	// retrieves the description and shows it
	public List<Description> getDescriptionOfMovie()
	{
		return moviebookEJB.findDescriptionOfMovie(desc.getMoviename());
	}
	
	// shows the movie ratings
	public String showMovieRatings()
	{
		System.out.println("\n\n--Printing the ratings--");
		ratingsAndMovieID = Tmdb.getRatingsAndCasts(desc.getMovieid());
		System.out.println("\n\nThe ratings is: " + ratingsAndMovieID[0]);
		return ratingsAndMovieID[0];
	}
	
	// shows the first 10 cast member for the selected movie
	public String showCast()
	{
		casts = Tmdb.getActor(Integer.parseInt(ratingsAndMovieID[1]));
		System.out.println("\n\n--Printing the Cast Names--\n" + casts);
		return casts;
	}
	
	// validates the credit card number
	public void validateCCN(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		int ccn = (int) value;						// stores the zipcode as a string
		String ccnStr = Integer.toString(creditCardNumber);	// stores the zipcode as a string
		String regexPattern = "^(\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d)$";		// regex pattern to check for the user input
		int length = ccnStr.length();				// stores the zipcode length
		boolean matches = Pattern.matches(regexPattern, ccnStr);
		
		
		if(!matches || length!=16 )
		{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Please enter a 16 digit number!", null));
		}
	}
}
