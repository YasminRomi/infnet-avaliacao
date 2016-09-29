package br.com.yasmin.avaliacao.web.mb;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.yasmin.avaliacao.core.constante.Constante;
import br.com.yasmin.avaliacao.core.domain.AutoDeInfracao;
import br.com.yasmin.avaliacao.core.dto.AutoDeInfracaoDTO;
import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.resource.MessageBundle;
import br.com.yasmin.avaliacao.web.controller.AutoDeInfracaoController;
import br.com.yasmin.avaliacao.web.controller.ProcessoController;

/**
 * ManagedBean referente a� funcionalidade de AutoDeInfracao
 * 
 * Classe <code>AutoDeInfracaoManagedBean</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 *
 */
@ManagedBean
@Component
@Scope("session")
public class AutoDeInfracaoManagedBean extends BasicManagedBean<AutoDeInfracao> {
	
	/** processo controller. */
	@Autowired
	private AutoDeInfracaoController autoDeInfracaoController;
	
	/** processo controller. */
	@Autowired
	private ProcessoController processoController;

	/** processo. */
	private AutoDeInfracaoDTO autoDeInfracao;
	
	/** processo. */
	private ProcessoDTO processo;

	/** lista de processos. */
	private List<AutoDeInfracaoDTO> autosDeInfracao;
	
	/** lista de processos. */
	private List<ProcessoDTO> processos;
	
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

		this.autoDeInfracao = new AutoDeInfracaoDTO();
		this.setSelectedDataTableRowObject(new AutoDeInfracaoDTO());

		try {
			this.processos = this.processoController.consultar(this.processo);
			this.autosDeInfracao = this.autoDeInfracaoController.consultar(this.autoDeInfracao);

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.AUTO_DE_INFRACAO_MSG_ERRO_LISTAR));
		}
	}
	
	/**
	 * Pesquisar. Metodo utilizado para carregar o objeto para montagem do
	 * dataTable na tela de pesquisa.
	 */
	public void pesquisar() {

		try {
			
			this.autoDeInfracao.setProcesso(this.processo);
			
			// Efetuar consulta
			this.autosDeInfracao = this.autoDeInfracaoController.consultar(this.autoDeInfracao);
			
			if (this.autosDeInfracao == null || this.autosDeInfracao.size() == 0) {
				super.sendWarningMessage(bundle.getValue(Constante.MSG_ALERTA_LISTAR));
			}

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.AUTO_DE_INFRACAO_MSG_ERRO_LISTAR));
		}
	}
	
	/**
	 * Editar.
	 * 
	 * Metodo para selecao de um processo no dataTable. Apos a selacao,
	 * redireciona para a tela de edicao.
	 * 
	 * @param autoDeInfracao
	 *            auto de infracao
	 * @return the string
	 */
	public String editar(final AutoDeInfracaoDTO autoDeInfracao) {

		this.autoDeInfracao = autoDeInfracao;

		super.setOperationMode(BasicManagedBean.ESTADO_EDICAO);

		return bundle.getValue(Constante.AUTO_DE_INFRACAO_PAGINA_EDITAR);
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
		this.autoDeInfracao = new AutoDeInfracaoDTO();

		super.setOperationMode(BasicManagedBean.ESTADO_INCLUSAO);

		return bundle.getValue(Constante.AUTO_DE_INFRACAO_PAGINA_EDITAR);
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
			
			this.calculaMulta();
			
			// Efetuar a gravação
			this.autoDeInfracaoController.salvar(this.autoDeInfracao);

			// Envio de mensagem
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_SALVAR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_SALVAR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.AUTO_DE_INFRACAO_PAGINA_PESQUISAR);
	}
	
	/**
	 * Excluir.
	 * 
	 * Metodo exclui um auto de infração da base de dados. Apos a exlusao, redireciona
	 * para a tela de pesquisa.
	 * 
	 * @return the string
	 * 
	 */
	public String excluir() {

		try {
			// Efetuar a gravação
			this.autoDeInfracaoController.excluir(this.autoDeInfracao.getIdAutoDeInfracao());
			super.sendSucessMessage(bundle.getValue(Constante.MSG_SUCESSO_EXCLUIR));

		} catch (final ApplicationException e) {
			super.sendErrorMessage(bundle.getValue(Constante.MSG_ERRO_EXCLUIR));
		}

		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.AUTO_DE_INFRACAO_PAGINA_PESQUISAR);
	}
	
	/**
	 * Voltar. Metodo responsavel por voltar a tela de consulta
	 * 
	 * @return string
	 */
	public String voltar() {
		// resetar os dados para a tela de consulta
		this.limpar();

		return bundle.getValue(Constante.AUTO_DE_INFRACAO_PAGINA_PESQUISAR);
	}
	
	/**
	 * Ao renderizar a página, limpa uma linha que foi selecionada e
	 * limpa os dados da pesquisa.
	 * 
	 */
	public void resetSelectedRow() {

		this.setSelectedDataTableRowObject(new AutoDeInfracaoDTO());
		
		this.autoDeInfracao = new AutoDeInfracaoDTO();

	}
	
	/**
	 * Caso a tabela esteja vazia, não renderiza.
	 * 
	 * @return boolean
	 */
	public boolean renderizarDatatable() {

		if (this.autosDeInfracao == null) {
			return false;
		} else {

			if (this.autosDeInfracao.isEmpty()) {
				return false;

			} else {
				return true;
			}
		}
	}
	
	/**
	 * Calcula o valor da multa de acordo com os demais par�metros
	 */
	public void calculaMulta() {
		
		BigDecimal penaBase = new BigDecimal(500);
		BigDecimal multa = penaBase;
		
		BigDecimal receitaBruta = this.autoDeInfracao.getProcesso().getFornecedor().getReceitaBruta();
		
		BigDecimal UFIR = new BigDecimal(3);
		BigDecimal G = new BigDecimal(this.autoDeInfracao.getGravidade());
		BigDecimal At = new BigDecimal(1);
		BigDecimal Ag = new BigDecimal(0);
		
		if (this.autoDeInfracao.isAgravante()) {
			Ag = new BigDecimal(1);
		}
		if (this.autoDeInfracao.isAtenuante()) {
			At = new BigDecimal(0.33);
		}
		multa = penaBase.add(((receitaBruta.subtract(new BigDecimal(120000))).multiply(new BigDecimal(0.1)).add(new BigDecimal(120000))).multiply(UFIR.multiply(Ag.add(At))).multiply(G));
		
		this.autoDeInfracao.setMulta(multa);
	}
	
	/**
	 * Obtém auto de infracao.
	 * 
	 * @return auto de infracao
	 */
	public AutoDeInfracaoDTO getAutoDeInfracao() {
		return this.autoDeInfracao;
	}

	/**
	 * Define auto de infracao.
	 * 
	 * @param autoDeInfracao
	 *            auto de infracao
	 */
	public void setAutoDeInfracao(final AutoDeInfracaoDTO autoDeInfracao) {
		this.autoDeInfracao = autoDeInfracao;
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
	 * Obtém lista de autos de infracao.
	 * 
	 * @return lista de autos de infracao
	 */
	public List<AutoDeInfracaoDTO> getAutosDeInfracao() {
		return this.autosDeInfracao;
	}

	/**
	 * Define lista de autos de infracao.
	 * 
	 * @param autos de infracao
	 *            lista de autos de infracao
	 */
	public void setAutosDeInfracao(final List<AutoDeInfracaoDTO> autosDeInfracao) {
		this.autosDeInfracao = autosDeInfracao;
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

}
