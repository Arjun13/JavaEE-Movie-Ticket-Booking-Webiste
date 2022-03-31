/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
@Entity
@Table(name = "DESCRIPTION")
@NamedQueries(
{
	@NamedQuery(name = "Description.findAll", query = "SELECT d FROM Description d"),
	@NamedQuery(name = "Description.findByMovieid", query = "SELECT d FROM Description d WHERE d.movieid = :movieid"),
	@NamedQuery(name = "Description.findByMoviename", query = "SELECT d FROM Description d WHERE d.moviename = :moviename"),
	@NamedQuery(name = "Description.findByDescription", query = "SELECT d FROM Description d WHERE d.description = :description"),
	@NamedQuery(name = "Description.findIndividualMovieName", query = "SELECT d.moviename from Description d where d.movieid = :counter")
})
public class Description implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIEID")
	private Integer movieid;
	@Size(max = 50)
    @Column(name = "MOVIENAME")
	private String moviename;
	@Size(max = 1000)
    @Column(name = "DESCRIPTION")
	private String description;
	@OneToMany(mappedBy = "movieid")
	private Collection<Showings> showingsCollection;

	public Description()
	{
	}

	public Description(Integer movieid)
	{
		this.movieid = movieid;
	}

	public Integer getMovieid()
	{
		return movieid;
	}

	public void setMovieid(Integer movieid)
	{
		this.movieid = movieid;
	}

	public String getMoviename()
	{
		return moviename;
	}

	public void setMoviename(String moviename)
	{
		this.moviename = moviename;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
		hash += (movieid != null ? movieid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Description))
		{
			return false;
		}
		Description other = (Description) object;
		if ((this.movieid == null && other.movieid != null) || (this.movieid != null && !this.movieid.equals(other.movieid)))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "entity.Description[ movieid=" + movieid + " ]";
	}
	
}
