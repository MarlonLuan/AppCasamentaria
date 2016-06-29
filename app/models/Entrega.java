package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Entrega extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public Date data;

	@Required
	public String status;

	@ManyToOne
	public Cliente cliente;

	public String toString() {
		return data.toString();
	}

	public static Finder<Long, Entrega> find = new Finder<Long, Entrega>(Entrega.class);

	public Entrega() {
	}
}