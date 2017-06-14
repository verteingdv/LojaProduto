package entity;

public class Usuario {

	private Integer idUsuario;
	private String login;
	private String senha;
	private String nome;
	public Usuario(Integer idUsuario, String login, String senha, String nome) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
	}
	public Usuario() {
		super();
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
