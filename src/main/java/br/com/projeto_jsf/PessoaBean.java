package br.com.projeto_jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	/*public String salvar() {
		daoGeneric.salvar(pessoa);
		pessoa = new Pessoa();
		return "";
	}*/
	
	public String salvar() {
		pessoa = daoGeneric.updateMerge(pessoa);
		carregarLista();
		return "";
	}
	
	public String delete() {
		daoGeneric.deleteById(pessoa);
		pessoa = new Pessoa();
		carregarLista();
		return "";
	}
	
	@PostConstruct
	public void carregarLista() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
}
