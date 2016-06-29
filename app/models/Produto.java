package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Produto extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public String nome;

	@Required
	public String descricao;

	@Required
	public Float peso;

	@Required
	public Float tamanho;

	@Required
	public Float preco;

	@ManyToOne
	public Categoria categoria;

	@ManyToOne
	public Pedido pedido;

	public String toString() {
		return nome;
	}

	public static Finder<Long, Produto> find = new Finder<Long, Produto>(Produto.class);

	public Produto() {
	}
}