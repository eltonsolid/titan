package br.com.elementi.core;

import java.util.Map;

import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.exception.CheckException;
import br.com.elementi.core.exception.MessageException;
import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.exception.NotNullException;
import br.com.elementi.core.exception.NotUniqueFoundException;
import br.com.elementi.core.tools.MessageTemplate;

import com.google.common.collect.Maps;

public class CoreMessage implements MessageTemplate {

	@Override
	public Map<String, String> properties() {
		Map<String, String> values = Maps.newHashMap();
		values.put("titan", "Titan");
		values.put("id", "Id");
		values.put("ok", "Ok");
		values.put("created", "Criado");
		values.put("updated", "Atualizado");
		values.put("active", "Ativar");
		values.put("edit", "Editar");
		values.put("employ", "Empresa");
		values.put("personType", "Tipo de Pessoa");
		values.put("juridic", "Juridica");
		values.put("fisic", "Fisica");
		values.put("masculino", "Masculino");
		values.put("feminino", "Feminino");
		values.put("confirm", "Confirmar");
		values.put("update", "Atualizar");
		values.put("cancel", "Cancelar");
		values.put("back", "Voltar");
		values.put("search", "Pesquisar");
		values.put("name", "Nome");
		values.put("password", "Password");
		values.put("passwordInfo", "Por favor, informe um password");
		values.put("passwordWeak", "Fraco");
		values.put("passwordGood", "Bom");
		values.put("passwordstrong", "Forte");
		values.put("document", "Documento");
		values.put("cpf", "Cpf");
		values.put("cnpj", "Cnpj");
		values.put("email", "Email");
		values.put("gene", "Genero");
		values.put("age", "Idade");
		values.put("agemask", "Idade dd/mm/yyyy");
		values.put("select", "Selecione");
		values.put("loggedUser", "Usuario Logado");
		values.put("levelAccess", "Nivel de Acesso");
		values.put("year", "Ano");
		values.put("month", "Mês");
		values.put("day", "Dia");
		return values;
	}

	@Override
	public Map<Enum<?>, String> enumerates() {
		Map<Enum<?>, String> values = Maps.newHashMap();
		return values;
	}

	@Override
	public Map<Class<? extends DomainException>, String> exceptions() {
		Map<Class<? extends DomainException>, String> values = Maps.newHashMap();
		values.put(MessageException.class, "{0}");
		values.put(CheckException.class, "Os campos a seguir, devem ser informados: {0}");
		values.put(NotNullException.class, "Null Pointer: {0}");
		values.put(NotFoundException.class, "Não foi possivel encontrar {0}");
		values.put(NotUniqueFoundException.class, "Foi encontrado mais de um registro para {0}");
		return values;
	}

}
