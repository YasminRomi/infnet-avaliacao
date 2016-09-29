package br.com.yasmin.avaliacao.web.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.yasmin.avaliacao.core.constante.Constante;
import br.com.yasmin.avaliacao.core.domain.Processo;
import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.resource.MessageBundle;
import br.com.yasmin.avaliacao.web.controller.FornecedorController;
import br.com.yasmin.avaliacao.web.controller.ProcessoController;

/**
 * ManagedBean referente a�funcionalidade de Processo
 * 
 * Classe <code>ProcessoManagedBean</code>.
 * 
 * @author Processo
 * @version 1.0 (28/09/2016)
 *
 */
@ManagedBean
@Component
@Scope("session")
public class ProcessoManagedBean extends BasicManagedBean<Processo> {
	
	/** processo controller. */
	@Autowired
	private ProcessoController processoController;
	
	/** fornecedor controller. */
	@Autowired
	private FornecedorController fornecedorController;

	/** processo. */
	private ProcessoDTO processo;
	
	/** fornecedor. */
	private FornecedorDTO fornecedor;

	/** lista de processos. */
	private List<ProcessoDTO> processos;
	
	/** lista de fornecedores. */
	private List<FornecedorDTO> fornecedores;
	
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

		this.processo = new ProcessoDTO();
		this.setSelectedDataTableRowObject(new ProcessoDTO());

		try {
			this.processos = this.processoController.consultar(this.processo);
			this.fornecedores = this.fornecedorController.consultar(this.fornecedor);

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.PROCESSO_MSG_ERRO_LISTAR));
		}
	}
	
	/**
	 * Pesquisar. Metodo utilizado para carregar o objeto para montagem do
	 * dataTable na tela de pesquisa.
	 */
	public void pesquisar() {

		try {
			
			this.processo.setFornecedor(this.fornecedor);
			
			// Efetuar consulta
			this.processos = this.processoController.consultar(this.processo);
			
			if (this.processos == null || this.processos.size() == 0) {
				super.sendWarningMessage(bundle.getValue(Constante.MSG_ALERTA_LISTAR));
			}

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.PROCESSO_MSG_ERRO_LISTAR));
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
	public String editar(final ProcessoDTO processo) {

		this.processo = processo;

		super.setOperationMode(BasicManagedBean.ESTADO_EDICAO);

		return bundle.getValue(Constante.PROCESSO_PAGINA_EDITAR);
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
		this.processo = new ProcessoDTO();

		super.setOperationMode(BasicManagedBean.ESTADO_INCLUSAO);

		return bundle.getValue(Constante.PROCESSO_PAGINA_EDITAR);
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
			this.processoController.salvar(this.processo);

			// Envio de mensagem
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_SALVAR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_SALVAR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.PROCESSO_PAGINA_PESQUISAR);
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
			this.processoController.excluir(this.processo.getIdProcesso());
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_EXCLUIR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_EXCLUIR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.PROCESSO_PAGINA_PESQUISAR);
	}
	
	/**
	 * Voltar. Metodo responsavel por voltar a tela de consulta
	 * 
	 * @return string
	 */
	public String voltar() {
		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.PROCESSO_PAGINA_PESQUISAR);
	}
	
	/**
	 * Ao renderizar a página, limpa uma linha que foi selecionada e
	 * limpa os dados da pesquisa.
	 * 
	 */
	public void resetSelectedRow() {
		
		this.setSelectedDataTableRowObject(new ProcessoDTO());

		this.processo = new ProcessoDTO();

	}
	
	/**
	 * Caso a tabela esteja vazia, não renderiza.
	 * 
	 * @return boolean
	 */
	public boolean renderizarDatatable() {

		if (this.processos == null) {
			return false;
		} else {

			if (this.processos.isEmpty()) {
				return false;

			} else {
				return true;
			}
		}
	}
	
	/**
	 * Obtém processo.
	 * 
	 * @return processo
	 */
	public ProcessoDTO getProcesso() {
		return this.processo;
	}

	/**
	 * Define processo.
	 * 
	 * @param processo
	 *            processo
	 */
	public void setProcesso(final ProcessoDTO processo) {
		this.processo = processo;
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
	 * Obtém lista de processos.
	 * 
	 * @return lista de processos
	 */
	public List<ProcessoDTO> getProcessos() {
		return this.processos;
	}

	/**
	 * Define lista de processos.
	 * 
	 * @param processos
	 *            lista de processos
	 */
	public void setProcessos(final List<ProcessoDTO> processos) {
		this.processos = processos;
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
}
