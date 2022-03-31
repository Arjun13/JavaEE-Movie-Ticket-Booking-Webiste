/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import entity.Zipcodes;
import entity.Description;

/**
 *
 * @author arjun
 */
@Entity
@Table(name = "SHOWINGS")
@NamedQueries(
{
	@NamedQuery(name = "Showings.findAll",				query = "SELECT s FROM Showings s"),
	@NamedQuery(name = "Showings.findByShowingid",		query = "SELECT s FROM Showings s WHERE s.showingid = :showingid"),
	@NamedQuery(name = "Showings.findByShowtime",		query = "SELECT s FROM Showings s WHERE s.showtime = :showtime"),
	@NamedQuery(name = "Showings.findAllShowTime",		query = "SELECT s FROM Showings s WHERE s.theaterid = :theaterid AND s.movieid = :movieid")
})
public class Showings implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHOWINGID")
	private Integer showingid;
	@Size(max = 10)
    @Column(name = "SHOWTIME")
	private String showtime;
	@JoinColumn(name = "MOVIEID", referencedColumnName = "MOVIEID")
    @ManyToOne
	private Description movieid;
	@JoinColumn(name = "THEATERID", referencedColumnName = "THEATERID")
    @ManyToOne
	private Zipcodes theaterid;

	public Showings()
	{
	}

	public Showings(Integer showingid)
	{
		this.showingid = showingid;
	}

	public Integer getShowingid()
	{
		return showingid;
	}

	public void setShowingid(Integer showingid)
	{
		this.showingid = showingid;
	}

	public String getShowtime()
	{
		return showtime;
	}

	public void setShowtime(String showtime)
	{
		this.showtime = showtime;
	}

	public Description getMovieid()
	{
		return movieid;
	}

	public void setMovieid(Description movieid)
	{
		this.movieid = movieid;
	}

	public Zipcodes getTheaterid()
	{
		return theaterid;
	}

	public void setTheaterid(Zipcodes theaterid)
	{
		this.theaterid = theaterid;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (showingid != null ? showingid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Showings))
		{
			return false;
		}
		Showings other = (Showings) object;
		if ((this.showingid == null && other.showingid != null) || (this.showingid != null && !this.showingid.equals(other.showingid)))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "entity.Showings[ showingid=" + showingid + " ]";
	}
	
}
