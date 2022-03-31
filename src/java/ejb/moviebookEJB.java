/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Description;
import entity.Showings;
import entity.Zipcodes;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.annotation.FacesConfig;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arjun
 */
@FacesConfig
@Stateless
public class moviebookEJB
{

	@PersistenceContext(unitName = "MovieTheaterPU")
	private EntityManager em;
	private Zipcodes zip = new Zipcodes();

	public void persist(Object object)
	{
		em.persist(object);
	}
	
	public List<Zipcodes> findTheatersName(int zipcode)
	{
		return em.createNamedQuery("Zipcodes.findTheaterName", Zipcodes.class)
				.setParameter("zipcode", zipcode)
				.getResultList();
	}
	
	// gets the list of the movie names
	public List<Description> findMovieName()
	{
		return em.createNamedQuery("Description.findAll", Description.class).getResultList();
	}
	
	// gets a list of all the showings for each movie given the theatername and zipcode
	public List<Showings> findAllShowings(Description movieid, Zipcodes theaterid)
	{
		return em.createNamedQuery("Showings.findAllShowTime", Showings.class)
				 .setParameter("theaterid", theaterid)
				 .setParameter("movieid", movieid)
				 .getResultList();
	}
	
	// shows the description of the given movie name
	public List<Description> findDescriptionOfMovie(String moviename)
	{
		return em.createNamedQuery("Description.findByMoviename", Description.class)
				 .setParameter("moviename", moviename)
				 .getResultList();
	}
	
	
	
}
