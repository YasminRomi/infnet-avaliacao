package br.com.yasmin.avaliacao.web.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.constante.Constante;
import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.resource.MessageBundle;
import br.com.yasmin.avaliacao.web.controller.EnderecoController;
import br.com.yasmin.avaliacao.web.controller.FornecedorController;

/**
 * ManagedBean referente a� funcionalidade de Fornecedor
 * 
 * Classe <code>FornecedorManagedBean</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 *
 */
@ManagedBean
@Component
@Scope("session")
public class FornecedorManagedBean extends BasicManagedBean<Fornecedor> {
	
	/** fornecedor controller. */
	@Autowired
	private FornecedorController fornecedorController;

	/** endereco controller. */
	@Autowired
	private EnderecoController enderecoController;

	/** fornecedor. */
	private FornecedorDTO fornecedor;
	
	/** endereco. */
	private EnderecoDTO endereco;

	/** lista de fornecedores. */
	private List<FornecedorDTO> fornecedores;
	
	/** lista de enderecos. */
	private List<EnderecoDTO> enderecos;
	
	/** bundle. */
	@Autowired
	private MessageBundle bundle;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void init() {

		this.limpar();
	}

	/**
	 * Limpar.
	 * 
	 * O metodo tem como objetivo limpar os objetos para um novo acesso
	 */
	public void limpar(){

		super.setOperationMode(BasicManagedBean.ESTADO_CONSULTA);

		this.fornecedor = new FornecedorDTO();
		this.setSelectedDataTableRowObject(new FornecedorDTO());

		try {
			this.fornecedores = this.fornecedorController.consultar(this.fornecedor);
			this.enderecos = this.enderecoController.consultar(this.endereco);

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.FORNECEDOR_MSG_ERRO_LISTAR));
		}
	}
	
	/**
	 * Pesquisar. Metodo utilizado para carregar o objeto para montagem do
	 * dataTable na tela de pesquisa.
	 */
	public void pesquisar() {

		try {
			
			this.fornecedor.setEndereco(this.endereco);
			
			// Efetuar consulta
			this.fornecedores = this.fornecedorController.consultar(this.fornecedor);
			
			if (this.fornecedores == null || this.fornecedores.size() == 0) {
				super.sendWarningMessage(bundle.getValue(Constante.MSG_ALERTA_LISTAR));
			}

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.FORNECEDOR_MSG_ERRO_LISTAR));
		}
	}
	
	/**
	 * Editar.
	 * 
	 * Metodo para selecao de um processo no dataTable. Apos a selacao,
	 * redireciona para a tela de edicao.
	 * 
	 * @param processo
	 *            processo
	 * @return the string
	 */
	public String editar(final FornecedorDTO fornecedor) {

		this.fornecedor = fornecedor;

		super.setOperationMode(BasicManagedBean.ESTADO_EDICAO);

		return bundle.getValue(Constante.FORNECEDOR_PAGINA_EDITAR);
	}

	/**
	 * Incluir.
	 * 
	 * Metodo utilizado pelo commandButton Incluir na tela de pesquisa.
	 * Redireciona para a tela de edicao
	 * 
	 * @return the string
	 */
	public String incluir() {
		this.fornecedor = new FornecedorDTO();

		super.setOperationMode(BasicManagedBean.ESTADO_INCLUSAO);

		return bundle.getValue(Constante.FORNECEDOR_PAGINA_EDITAR);
	}
	
	/**
	 * Salvar.
	 * 
	 * Esse metodo tem como objetivo, salvar a atualizacao ou inclusao de uma
	 * empresa. Apos o salvar, redireciona para a tela de pesquisa.
	 * 
	 * @return the string
	 */
	public String salvar() {

		try {
			// Efetuar a gravação
			this.fornecedorController.salvar(this.fornecedor);

			// Envio de mensagem
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_SALVAR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_SALVAR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.FORNECEDOR_PAGINA_PESQUISAR);
	}
	
	/**
	 * Excluir.
	 * 
	 * Metodo exclui uma empresa da base de dados. Apos a exlusao, redireciona
	 * para a tela de pesquisa.
	 * 
	 * @return the string
	 * 
	 */
	public String excluir() {

		try {
			// Efetuar a gravação
			this.fornecedorController.excluir(this.fornecedor.getIdFornecedor());
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_EXCLUIR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_EXCLUIR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.FORNECEDOR_PAGINA_PESQUISAR);
	}
	
	/**
	 * Voltar. Metodo responsavel por voltar a tela de consulta
	 * 
	 * @return string
	 */
	public String voltar() {
		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.FORNECEDOR_PAGINA_PESQUISAR);
	}
	
	/**
	 * Ao selecionar uma pessoa na tabela, redireciona para
	 * a tela de edição.
	 * 
	 */
	public void itemSelecionado() {
		super.onRowSelect(this.editar((FornecedorDTO) this.getSelectedDataTableRowObject()));
	}
	
	/**
	 * Ao renderizar a página, limpa uma linha que foi selecionada e
	 * limpa os dados da pesquisa.
	 * 
	 */
	public void resetSelectedRow() {
		
		this.setSelectedDataTableRowObject(new FornecedorDTO());

		this.fornecedor = new FornecedorDTO();

	}
	
	/**
	 * Caso a tabela esteja vazia, não renderiza.
	 * 
	 * @return boolean
	 */
	public boolean renderizarDatatable() {

		if (this.fornecedores == null) {
			return false;
		} else {

			if (this.fornecedores.isEmpty()) {
				return false;

			} else {
				return true;
			}
		}
	}
	
	/**
	 * Obtém fornecedor.
	 * 
	 * @return fornecedor
	 */
	public FornecedorDTO getFornecedor() {
		return this.fornecedor;
	}
	
	/**
	 * Define fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 */
	public void setFornecedor(final FornecedorDTO fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	/**
	 * Obtém endereco.
	 * 
	 * @return endereco
	 */
	public EnderecoDTO getEndereco() {
		return this.endereco;
	}
	
	/**
	 * Define endereco.
	 * 
	 * @param endereco
	 *            endereco
	 */
	public void setEndereco(final EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	/**
	 * Obtém lista de fornecedores.
	 * 
	 * @return lista de fornecedores
	 */
	public List<FornecedorDTO> getFornecedores() {
		return this.fornecedores;
	}

	/**
	 * Define lista de fornecedores.
	 * 
	 * @param fornecedores
	 *            lista de fornecedores
	 */
	public void setFornecedores(final List<FornecedorDTO> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	/**
	 * Obtém lista de enderecos.
	 * 
	 * @return lista de enderecos
	 */
	public List<EnderecoDTO> getEnderecos() {
		return this.enderecos;
	}

	/**
	 * Define lista de enderecos.
	 * 
	 * @param enderecos
	 *            lista de enderecos
	 */
	public void setEnderecos(final List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

}
