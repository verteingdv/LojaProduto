package manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import persistence.UsuarioDao;
import entity.Usuario;

@ManagedBean(name="uBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	
	public UsuarioBean() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar(){
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			
			Pattern p1 = Pattern.compile("[A-Za-z 0-9]{3,30}");
			Matcher m1 = p1.matcher( usuario.getLogin() );
			
			if(!m1.matches()){
				fc.addMessage("form1:login", new FacesMessage("O campo deve conter 3 caracteres"));
				return null;
			}
			
			UsuarioDao ud = new UsuarioDao();
			Usuario user = ud.logar(usuario.getLogin(), usuario.getSenha());
			if(user != null){
				usuario = user;
				usuario.setSenha(null);
				
				return "admin/painel";
			}else{
				fc.addMessage("form1", new FacesMessage("Usuario / Senha invalidos!"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String sair(){
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.remove("uBean");
		return "../login.xhtml";
	}
	
	public void filtrar(ComponentSystemEvent event){
		
		try{
			
			if(usuario == null || usuario.getIdUsuario() == null){
				HttpServletRequest request = (HttpServletRequest)
						FacesContext.getCurrentInstance().getExternalContext().getRequest();
				
				FacesContext.getCurrentInstance().getExternalContext()
				.redirect( request.getContextPath() );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
}