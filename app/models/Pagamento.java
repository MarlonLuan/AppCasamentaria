package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import play.data.validation.Constraints.Required;

@Entity
public class Pagamento extends Model {

	@Id
	@GeneratedValue
	public Long id;

	public Boolean ativo;

	public String toString() {
		return ativo.toString();
	}

	public static Finder<Long, Pagamento> find = new Finder<Long, Pagamento>(Pagamento.class);

	public Pagamento() {
	}
}