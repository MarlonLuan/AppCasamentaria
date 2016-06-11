package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Pagamento;

public class PagamentosController extends Controller {

	private final Form<Pagamento> formPagamento = Form.form(Pagamento.class);

	public Result lista() {
		List<Pagamento> listaPagamentos = Pagamento.find.all();
		return ok(views.html.pagamentos.lista.render(listaPagamentos));
	}

	public Result novo() {
		return ok(views.html.pagamentos.formulario.render(formPagamento));
	}

	public Result formulario(Long id) {

		final Pagamento pagamento = Pagamento.find.byId(id);

		if (pagamento == null) {
			return notFound(String.format("Pagamento %s não existe.", id));
		}

		Form<Pagamento> formPreenchido = formPagamento.fill(pagamento);

		return ok(views.html.pagamentos.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Pagamento> formEnviado = formPagamento.bindFromRequest();
		Pagamento pagamento = formEnviado.get();
		if (pagamento.id != null) {
			pagamento.update();
			flash("success", String.format("Pagamento %s atualizado.", pagamento));
		} else {
			pagamento.save();
			flash("success", String.format("Pagamento %s salvo.", pagamento));
		}
		return redirect(routes.PagamentosController.lista());
	}

	public Result remover(Long id) {

		final Pagamento pagamento = Pagamento.find.byId(id);

		try {
			pagamento.delete();
			flash("success", String.format("Pagamento %s removido com sucesso.", pagamento));
		} catch (Exception e) {
			flash("error", "Erro ao remover pagamento");
		}

		return redirect(routes.PagamentosController.lista());
	}
}