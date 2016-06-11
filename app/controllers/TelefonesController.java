package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Telefone;

public class TelefonesController extends Controller {

	private final Form<Telefone> formTelefone = Form.form(Telefone.class);

	public Result lista() {
		List<Telefone> listaTelefones = Telefone.find.all();
		return ok(views.html.telefones.lista.render(listaTelefones));
	}

	public Result novo() {
		return ok(views.html.telefones.formulario.render(formTelefone));
	}

	public Result formulario(Long id) {

		final Telefone telefone = Telefone.find.byId(id);

		if (telefone == null) {
			return notFound(String.format("Telefone %s não existe.", id));
		}

		Form<Telefone> formPreenchido = formTelefone.fill(telefone);

		return ok(views.html.telefones.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Telefone> formEnviado = formTelefone.bindFromRequest();
		Telefone telefone = formEnviado.get();
		if (telefone.id != null) {
			telefone.update();
			flash("success", String.format("Telefone %s atualizado.", telefone));
		} else {
			telefone.save();
			flash("success", String.format("Telefone %s salvo.", telefone));
		}
		return redirect(routes.TelefonesController.lista());
	}

	public Result remover(Long id) {

		final Telefone telefone = Telefone.find.byId(id);

		try {
			telefone.delete();
			flash("success", String.format("Telefone %s removido com sucesso.", telefone));
		} catch (Exception e) {
			flash("error", "Erro ao remover telefone");
		}

		return redirect(routes.TelefonesController.lista());
	}
}