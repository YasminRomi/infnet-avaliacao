package br.com.yasmin.avaliacao.web.mb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * 
 * Classe <code>BasicManagedBean</code>
 * 
 * @author yasminfarias
 *
 * @param <ENTITY>
 */
public abstract class BasicManagedBean<ENTITY> {
	
	/**
	 * Representa o modo de operação de uma página / funcionalidade.
	 */
	private String operationMode;

	/** The Constant ESTADO_CONSULTA. */
	protected static final String ESTADO_CONSULTA = "consulta";

	/** The Constant ESTADO_EDITACAO. */
	protected static final String ESTADO_EDICAO = "editar";

	/** The Constant ESTADO_INCLUSAO. */
	protected static final String ESTADO_INCLUSAO = "incluir";

	/** The Constant GRAVACAO. */
	protected static final String GRAVACAO = "Gravacao";

	/** The Constant LEITURA. */
	protected static final String LEITURA = "Leitura";

	/** uploaded files. */
	private List<UploadedFile> uploadedFiles;
	
	/** selected object. */ 
    private Object selectedDataTableRowObject;
	
	/**
	 * Construtor da classe.
	 */
	public BasicManagedBean() {
		this.setOperationMode(ESTADO_CONSULTA);
	}
	
	/**
	 * Ao selecionar uma item na tabela, redireciona para a tela de edição.
	 * 
	 * @param value
	 *            value
	 */
	public void onRowSelect(String value) {
		final ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		configurableNavigationHandler.performNavigation(value);
	}
	
	/**
	 * Permite gerar uma mensagem de sucesso.
	 * 
	 * @param message
	 *            - mensagem a ser apresentada.
	 */
	protected void sendSucessMessage(final String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		RequestContext.getCurrentInstance().update("frmPrincipal:msgs");
	}
	
	/**
	 * Permite gerar uma mensagem de alerta.
	 * 
	 * @param message
	 *            - mensagem a ser apresentada.
	 */
	protected void sendWarningMessage(final String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
		RequestContext.getCurrentInstance().update("frmPrincipal:msgs");
	}
	
	/**
	 * Permite gerar uma mensagem de erro.
	 * 
	 * @param message
	 *            - mensagem a ser apresentada.
	 */
	protected void sendErrorMessage(final String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		RequestContext.getCurrentInstance().update("frmPrincipal:msgs");
	}
	
	/**
	 * Upload file.
	 * 
	 * @param event
	 *            event
	 */
 	public void uploadFile(final FileUploadEvent event){
		if (this.uploadedFiles == null) {
			this.uploadedFiles = new ArrayList<UploadedFile>();
		}
		this.uploadedFiles.add(event.getFile());		
	 }
 	
 	/**
	 * Download file.
	 * 
	 * @param file
	 *            file
	 * @param mimeType
	 *            mime type
	 * @param name
	 *            name
	 * @return default streamed content
	 */
	 public DefaultStreamedContent downloadFile(byte[] file, String mimeType, String name){
		InputStream stream = new ByteArrayInputStream(file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return new DefaultStreamedContent(stream, externalContext.getMimeType(mimeType), name);
	}
	 
	 /**
	 * Clear uploaded files.
	 */
	protected void clearUploadedFiles(){
		this.uploadedFiles.clear();
	}
	
	/**
	 * Habilitar estado.
	 * 
	 * habilitar o botão excluir a partir do estado de Edicao;
	 * 
	 * @return true, if successful
	 */
	public boolean isHabilitarEstado() {
		if (this.getOperationMode() != null
				&& this.getOperationMode().equalsIgnoreCase(
						BasicManagedBean.ESTADO_EDICAO)) {

			return true;
		}
		return false;
	}
	
	/**
	 * Recupera o modo de operação da funcionalidade.
	 * 
	 * @return modo de operação.
	 */
	public String getOperationMode() {
		return this.operationMode;
	}

	/**
	 * Informa o modo de operaÃ§Ã£o da funcionalidade.
	 * 
	 * @param operationMode
	 *            - modo de operaÃ§Ã£o.
	 */
	public void setOperationMode(final String operationMode) {
		this.operationMode = operationMode;
	}
	
	/**
	 * Obtem uploaded files.
	 * 
	 * @return the uploaded files
	 */
	protected List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

	/**
	 * Define uploaded files.
	 * 
	 * @param uploadedFiles
	 *            the new uploaded files
	 */
	protected void setUploadedFiles(List<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	
	/** 
     * Obtem selected object. 
     *  
     * @return the selected object 
     */ 
    public Object getSelectedDataTableRowObject() { 
        return selectedDataTableRowObject; 
    } 
 
    /** 
     * Define selected object. 
     *  
     * @param selectedDataTableRowObject 
     *            the new selected object 
     */ 
    public void setSelectedDataTableRowObject(Object selectedDataTableRowObject) { 
        this.selectedDataTableRowObject = selectedDataTableRowObject; 
    }  

}
