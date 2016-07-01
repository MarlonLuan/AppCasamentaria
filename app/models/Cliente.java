package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Cliente extends Usuario {

	@Required
	public String nome;

	@Required
	public Date dataNascimento;

	@Required
	public String email;

	@Required
	public String apelido;

	@Required
	public Integer cpf;

	@Required
	public String rg;

	public String toString() {
		return nome;
	}

	public static Finder<Long, Cliente> find = new Finder<Long, Cliente>(Cliente.class);

	public Cliente() {
	}
}