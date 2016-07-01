package models;

import java.util.Date;

import javax.persistence.*;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@MappedSuperclass
public class Usuario extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Required
	public String login;

	@Required
	public String password;

	@Required
	public Integer perfil; // 0 - Admin; 1 - Cliente

	public String toString() {
		return login;
	}

	public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(Usuario.class);

	public Usuario() {
	}
}