package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Telefone extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public Integer ddd;

	@Required
	public Integer numero;

	@Required
	@ManyToOne
	public Cliente cliente;

	public String toString() {
		return "(" + ddd.toString() + ") " + numero.toString();
	}

	public static Finder<Long, Telefone> find = new Finder<Long, Telefone>(Telefone.class);

	public Telefone() {
	}
}