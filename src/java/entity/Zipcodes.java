/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arjun
 */
@Named
@Entity
@Table(name = "ZIPCODES")
@NamedQueries(
{
	@NamedQuery(name = "Zipcodes.findAll", query = "SELECT z FROM Zipcodes z"),
	@NamedQuery(name = "Zipcodes.findByZipcode", query = "SELECT z FROM Zipcodes z WHERE z.zipcode = :zipcode"),
	@NamedQuery(name = "Zipcodes.findByTheatername", query = "SELECT z FROM Zipcodes z WHERE z.theatername = :theatername"),
	@NamedQuery(name = "Zipcodes.findByTheaterid", query = "SELECT z FROM Zipcodes z WHERE z.theaterid = :theaterid"),
	@NamedQuery(name = "Zipcodes.findTheaterName", query = "SELECT z FROM Zipcodes z WHERE z.zipcode = :zipcode")
})
public class Zipcodes implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Column(name = "ZIPCODE")
	private Integer zipcode;
	@Size(max = 50)
    @Column(name = "THEATERNAME")
	private String theatername;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "THEATERID")
	private Integer theaterid;
	@OneToMany(mappedBy = "theaterid")
	private Collection<Showings> showingsCollection;

	public Zipcodes()
	{
	}

	public Zipcodes(Integer theaterid)
	{
		this.theaterid = theaterid;
	}

	public Integer getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(Integer zipcode)
	{
		this.zipcode = zipcode;
	}

	public String getTheatername()
	{
		return theatername;
	}

	public void setTheatername(String theatername)
	{
		this.theatername = theatername;
	}

	public Integer getTheaterid()
	{
		return theaterid;
	}

	public void setTheaterid(Integer theaterid)
	{
		this.theaterid = theaterid;
	}

	public Collection<Showings> getShowingsCollection()
	{
		return showingsCollection;
	}

	public void setShowingsCollection(Collection<Showings> showingsCollection)
	{
		this.showingsCollection = showingsCollection;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (theaterid != null ? theaterid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Zipcodes))
		{
			return false;
		}
		Zipcodes other = (Zipcodes) object;
		if ((this.theaterid == null && other.theaterid != null) || (this.theaterid != null && !this.theaterid.equals(other.theaterid)))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "entity.Zipcodes[ theaterid=" + theaterid + " ]";
	}
	
}
