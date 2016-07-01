package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Cliente;
import models.Endereco;
import models.Pedido;

public class PedidosController extends Controller {

	private final Form<Pedido> formPedido = Form.form(Pedido.class);
	List<Cliente> listaClientes = Cliente.find.all();
	Pedido pedido = new Pedido();

	public Result lista() {
		List<Pedido> listaPedidos = Pedido.find.all();
		return ok(views.html.pedidos.lista.render(listaPedidos));
	}

	public Result novo() {
		return ok(views.html.pedidos.formulario.render(formPedido, listaClientes));
	}

	public Result formulario(Long id) {

		final Pedido pedido = Pedido.find.byId(id);

		if (pedido == null) {
			return notFound(String.format("Pedido %s não existe.", id));
		}

		Form<Pedido> formPreenchido = formPedido.fill(pedido);

		return ok(views.html.pedidos.formulario.render(formPreenchido, listaClientes));
	}

	public Result salvar() throws ParseException {
		DynamicForm formEnviado = Form.form().bindFromRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		pedido.cliente = Cliente.find.byId(Long.parseLong(formEnviado.get("cliente")));
		
		pedido.valor = Double.parseDouble(formEnviado.get("valor"));
		pedido.data = sdf.parse(formEnviado.get("data"));
		pedido.status = formEnviado.get("status");
		pedido.pagamento = Boolean.parseBoolean(formEnviado.get("pagamento"));
		
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