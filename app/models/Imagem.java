package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Imagem extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Required
	public byte[] foto;

	@ManyToOne
	public Produto produto;

	public String toString() {
		return foto.toString();
	}

	public static Finder<Long, Imagem> find = new Finder<Long, Imagem>(Imagem.class);

	public Imagem() {
	}
}
