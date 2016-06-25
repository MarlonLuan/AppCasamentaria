package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Endereco;

public class EnderecosController extends Controller {

	private final Form<Endereco> formEndereco = Form.form(Endereco.class);

	public Result lista() {
		List<Endereco> listaEnderecos = Endereco.find.all();
		return ok(views.html.enderecos.lista.render(listaEnderecos));
	}

	public Result novo() {
		return ok(views.html.enderecos.formulario.render(formEndereco));
	}

	public Result formulario(Long id) {

		final Endereco endereco = Endereco.find.byId(id);

		if (endereco == null) {
			return notFound(String.format("Endereço %s não existe.", id));
		}

		Form<Endereco> formPreenchido = formEndereco.fill(endereco);

		return ok(views.html.enderecos.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Endereco> formEnviado = formEndereco.bindFromRequest();
		Endereco endereco = formEnviado.get();
		if (endereco.id != null) {
			endereco.update();
			flash("success", String.format("Endereço %s atualizado.", endereco));
		} else {
			endereco.save();
			flash("success", String.format("Endereço %s salvo.", endereco));
		}
		return redirect(routes.EnderecosController.lista());
	}

	public Result remover(Long id) {

		final Endereco endereco = Endereco.find.byId(id);

		try {
			endereco.delete();
			flash("success", String.format("Endereço %s removido com sucesso.", endereco));
		} catch (Exception e) {
			flash("error", "Erro ao remover endereço");
		}

		return redirect(routes.EnderecosController.lista());
	}
}