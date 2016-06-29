package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Pedido extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public Double valor;

	@Required
	public Date data;

	@Required
	public String status;

	@ManyToOne
	public Cliente cliente;

	@ManyToOne
	public Pagamento pagamento;

	public String toString() {
		return valor.toString();
	}

	public static Finder<Long, Pedido> find = new Finder<Long, Pedido>(Pedido.class);

	public Pedido() {
	}
}
