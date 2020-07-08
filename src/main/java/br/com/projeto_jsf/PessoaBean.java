package br.com.projeto_jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.repositorio.DaoPessoa;
import br.com.repositorio.IDaoPessoa;

@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private DaoPessoa daoPessoa = new DaoPessoa();
	
	/*public String salvar() {
		daoGeneric.salvar(pessoa);
		pessoa = new Pessoa();
		return "";
	}*/
	
	public String logar() {
		
		Pessoa pessoaUser = daoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		
		if(pessoaUser != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
			
			return "primeiraPagina.jsf";
		}
		
		return "index.jsf";
	}
	
	public Boolean acesso(String perfil) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externContext.getSessionMap().get("usuarioLogado");
		
		return pessoaUser.getPerfil().equalsIgnoreCase(perfil);
	}
	
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
