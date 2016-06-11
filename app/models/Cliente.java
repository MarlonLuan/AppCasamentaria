package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import play.data.validation.Constraints.Required;

@Entity
public class Cliente extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String nome;

	@Required
	public Date dataNascimento;

	@Required
	public String email;

	@Required
	public String apelido;

	@Required
	public String cpf;

	@Required
	public String rg;

	public String toString() {
		return nome;
	}

	public static Finder<Long, Cliente> find = new Finder<Long, Cliente>(Cliente.class);

	public Cliente() {
	}
}