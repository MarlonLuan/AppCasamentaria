package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Cliente;
import models.Usuario;

public class UsuariosController extends Controller {

	private final Form<Usuario> formUsuario = Form.form(Usuario.class);

	public Result lista() {
		return new ClientesController().lista();
	}

	public Result novo() {
		return new ClientesController().novo();
	}

	public Result formulario(Long id) {
		return new ClientesController().formulario(id);
	}

	public Result salvar() {

		Form<Usuario> formEnviado = formUsuario.bindFromRequest();
		Usuario Usuario = formEnviado.get();
		if (Usuario.id != null) {
			Usuario.update();
			flash("success", String.format("Usuário %s atualizado.", Usuario));
		} else {
			Usuario.save();
			flash("success", String.format("Usuário %s salvo.", Usuario));
		}
		return redirect(routes.ClientesController.lista());
	}

	public Result remover(Long id) {
		return new ClientesController().remover(id);
	}

	public Result index() {

		return ok(views.html.login.formulario.render(formUsuario));
	}

	public Result entrar() {
		Form<Usuario> formEnviado = formUsuario.bindFromRequest();
		Usuario usuario = formEnviado.get();

		Cliente userSystem = Cliente.find.where().eq("login", usuario.login).findUnique();
		if (userSystem != null) {
			if (usuario.login.equals(userSystem.login) && usuario.password.equals(userSystem.password)) {

				session("login", usuario.login);
				session("perfil", Integer.toString(usuario.perfil));

				return ok(views.html.index.render("HOME"));
			} else {
				flash("error", String.format("A senha do Usuário %s está incorreta!!!", usuario.login));
			}
		} else {
			flash("error", String.format("Usuário %s não existe no sistema!!!", usuario.login));
		}

		return redirect(routes.UsuariosController.index());
	}

	public Result sair() {

		if (!session().isEmpty()) {

			session().remove("login");
			session().remove("perfil");

			flash("success", "Você não está mais logado no sistema!!!");
		}

		return redirect(routes.Application.index());
	}
}