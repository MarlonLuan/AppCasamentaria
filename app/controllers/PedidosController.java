package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Pedido;

public class PedidosController extends Controller {

	private final Form<Pedido> formPedido = Form.form(Pedido.class);

	public Result lista() {
		List<Pedido> listaPedidos = Pedido.find.all();
		return ok(views.html.pedidos.lista.render(listaPedidos));
	}

	public Result novo() {
		return ok(views.html.pedidos.formulario.render(formPedido));
	}

	public Result formulario(Long id) {

		final Pedido pedido = Pedido.find.byId(id);

		if (pedido == null) {
			return notFound(String.format("Pedido %s não existe.", id));
		}

		Form<Pedido> formPreenchido = formPedido.fill(pedido);

		return ok(views.html.pedidos.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Pedido> formEnviado = formPedido.bindFromRequest();
		Pedido pedido = formEnviado.get();
		if (pedido.id != null) {
			pedido.update();
			flash("success", String.format("Pedido %s atualizado.", pedido));
		} else {
			pedido.save();
			flash("success", String.format("Pedido %s salvo.", pedido));
		}
		return redirect(routes.PedidosController.lista());
	}

	public Result remover(Long id) {

		final Pedido pedido = Pedido.find.byId(id);

		try {
			pedido.delete();
			flash("success", String.format("Pedido %s removido com sucesso.", pedido));
		} catch (Exception e) {
			flash("error", "Erro ao remover pedido");
		}

		return redirect(routes.PedidosController.lista());
	}
}