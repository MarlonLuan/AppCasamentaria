package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;

import java.util.List;

import models.Cliente;
import models.Endereco;

public class EnderecosController extends Controller {

	private final Form<Endereco> formEndereco = Form.form(Endereco.class);
	List<Cliente> listaPlanetas = Cliente.find.all();
	Endereco endereco = new Endereco();

	public Result lista() {
		List<Endereco> listaEnderecos = Endereco.find.all();
		return ok(views.html.enderecos.lista.render(listaEnderecos));
	}

	public Result novo() {
		return ok(views.html.enderecos.formulario.render(formEndereco, listaPlanetas));
	}

	public Result formulario(Long id) {

		final Endereco endereco = Endereco.find.byId(id);

		if (endereco == null) {
			return notFound(String.format("Endereço %s não existe.", id));
		}

		Form<Endereco> formPreenchido = formEndereco.fill(endereco);

		return ok(views.html.enderecos.formulario.render(formPreenchido, listaPlanetas));
	}

	public Result salvar() {
		DynamicForm formEnviado = Form.form().bindFromRequest();
		
		endereco.cliente = Cliente.find.byId(Long.parseLong(formEnviado.get("cliente")));
		endereco.logradouro = formEnviado.get("logradouro");
		endereco.numero = Integer.parseInt(formEnviado.get("numero"));
		endereco.complemento = formEnviado.get("complemento");
		endereco.cep = Integer.parseInt(formEnviado.get("cep"));
		endereco.bairro = formEnviado.get("bairro");
		endereco.cidade = formEnviado.get("cidade");
		endereco.uf = formEnviado.get("uf");
		
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