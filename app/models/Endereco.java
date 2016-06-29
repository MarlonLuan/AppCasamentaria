package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Endereco extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String logradouro;

	@Required
	public Integer numero;

	public String complemento;

	@Required
	public Integer cep;

	@Required
	public String bairro;

	@Required
	public String cidade;

	@Required
	public String uf;

	@ManyToOne
	public Cliente cliente;

	public String toString() {
		return logradouro;
	}

	public static Finder<Long, Endereco> find = new Finder<Long, Endereco>(Endereco.class);

	public Endereco() {
	}
}