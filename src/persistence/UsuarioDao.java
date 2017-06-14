package persistence;

import entity.Usuario;

public class UsuarioDao extends Dao implements IUsuarioDao {

	@Override
	public void cadastrar(Usuario user) throws Exception {

		open();

		stmt = con.prepareStatement("insert into usuario values(null, ?,?,?)");
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getLogin());
		stmt.setString(3, user.getSenha());

		stmt.execute();

		close();
	}

	@Override
	public Usuario logar(String login, String senha) throws Exception {
		open();

		stmt = con.prepareStatement("select * from usuario where login = ? and senha = ?");
		stmt.setString(1, login);
		stmt.setString(2, senha);

		rs = stmt.executeQuery();
		Usuario user = null;

		if (rs.next()) {
			user = new Usuario(rs.getInt("idusuario"), rs.getString("login"), rs.getString("senha"),
					rs.getString("nome"));
		}

		close();
		return user;
	}

	public static void main(String[] args) {

		try {

			Usuario user = new Usuario();
			user.setLogin("adm");
			user.setNome("Guilherme Vertein");
			user.setSenha("123");

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.cadastrar(user);

			System.out.println("Usuario cadastrado com sucesso!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
