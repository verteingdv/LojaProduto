package persistence;

import entity.Usuario;

public interface IUsuarioDao {

	public void cadastrar(Usuario user) throws Exception;

	public Usuario logar(String login, String senha) throws Exception;
}
