package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Categoria extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String nome;

	@Required
	public String descricao;

	public String toString() {
		return nome;
	}

	public static Finder<Long, Categoria> find = new Finder<Long, Categoria>(Categoria.class);

	public Categoria() {
	}
}