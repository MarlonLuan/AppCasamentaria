package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Pagamento extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public Boolean ativo;

	public String toString() {
		return ativo.toString();
	}

	public static Finder<Long, Pagamento> find = new Finder<Long, Pagamento>(Pagamento.class);

	public Pagamento() {
	}
}