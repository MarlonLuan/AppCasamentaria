package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Categoria;

public class CategoriasController extends Controller {

	private final Form<Categoria> formCategoria = Form.form(Categoria.class);

	public Result lista() {
		List<Categoria> listaCategorias = Categoria.find.all();
		return ok(views.html.categorias.lista.render(listaCategorias));
	}

	public Result novo() {
		return ok(views.html.categorias.formulario.render(formCategoria));
	}

	public Result formulario(Long id) {

		final Categoria categoria = Categoria.find.byId(id);

		if (categoria == null) {
			return notFound(String.format("Categoria %s não existe.", id));
		}

		Form<Categoria> formPreenchido = formCategoria.fill(categoria);

		return ok(views.html.categorias.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Categoria> formEnviado = formCategoria.bindFromRequest();
		Categoria categoria = formEnviado.get();
		if (categoria.id != null) {
			categoria.update();
			flash("success", String.format("Categoria %s atualizado.", categoria));
		} else {
			categoria.save();
			flash("success", String.format("Categoria %s salvo.", categoria));
		}
		return redirect(routes.CategoriasController.lista());
	}

	public Result remover(Long id) {

		final Categoria categoria = Categoria.find.byId(id);

		try {
			categoria.delete();
			flash("success", String.format("Categoria %s removido com sucesso.", categoria));
		} catch (Exception e) {
			flash("error", "Erro ao remover categoria");
		}

		return redirect(routes.CategoriasController.lista());
	}
}