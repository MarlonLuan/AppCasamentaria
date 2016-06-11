package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Cliente;

public class ClientesController extends Controller {

	private final Form<Cliente> formCliente = Form.form(Cliente.class);

	public Result lista() {
		List<Cliente> listaClientes = Cliente.find.all();
		return ok(views.html.clientes.lista.render(listaClientes));
	}

	public Result novo() {
		return ok(views.html.clientes.formulario.render(formCliente));
	}

	public Result formulario(Long id) {

		final Cliente cliente = Cliente.find.byId(id);

		if (cliente == null) {
			return notFound(String.format("Cliente %s não existe.", id));
		}

		Form<Cliente> formPreenchido = formCliente.fill(cliente);

		return ok(views.html.clientes.formulario.render(formPreenchido));
	}

	public Result salvar() {

		Form<Cliente> formEnviado = formCliente.bindFromRequest();
		Cliente cliente = formEnviado.get();
		if (cliente.id != null) {
			cliente.update();
			flash("success", String.format("Cliente %s atualizado.", cliente));
		} else {
			cliente.save();
			flash("success", String.format("Cliente %s salvo.", cliente));
		}
		return redirect(routes.ClientesController.lista());
	}

	public Result remover(Long id) {

		final Cliente cliente = Cliente.find.byId(id);

		try {
			cliente.delete();
			flash("success", String.format("Cliente %s removido com sucesso.", cliente));
		} catch (Exception e) {
			flash("error", "Erro ao remover cliente");
		}

		return redirect(routes.ClientesController.lista());
	}
}