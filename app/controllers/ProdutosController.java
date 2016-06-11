package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Produto;

public class ProdutosController extends Controller {

	private final Form<Produto> formProduto = Form.form(Produto.class);

	public Result lista() {
		List<Produto> listaProdutos = Produto.find.all();
		return ok(views.html.produtos.lista.render(listaProdutos));
	}

	public Result novo() {
		return ok(views.html.produtos.formulario.render(formProduto));
	}

	public Result formulario(Long id) {

		final Produto produto = Produto.find.byId(id);

		if (produto == null) {
			return notFound(String.format("Produto %s não existe.", id));
		}

		Form<Produto> formPreenchido = formProduto.fill(produto);

		return ok(views.html.produtos.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Produto> formEnviado = formProduto.bindFromRequest();
		Produto produto = formEnviado.get();
		if (produto.id != null) {
			produto.update();
			flash("success", String.format("Produto %s atualizado.", produto));
		} else {
			produto.save();
			flash("success", String.format("Produto %s salvo.", produto));
		}
		return redirect(routes.ProdutosController.lista());
	}

	public Result remover(Long id) {

		final Produto produto = Produto.find.byId(id);

		try {
			produto.delete();
			flash("success", String.format("Produto %s removido com sucesso.", produto));
		} catch (Exception e) {
			flash("error", "Erro ao remover produto");
		}

		return redirect(routes.ProdutosController.lista());
	}
}