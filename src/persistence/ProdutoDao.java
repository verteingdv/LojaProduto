package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import entity.Produto;

public class ProdutoDao extends Dao implements IProdutoDao {

	@Override
	public void cadastrar(Produto produto) throws Exception {
		open();

		stmt = con.prepareStatement("insert into produto values(null, ?,?,?,?,?)");

		stmt.setString(1, produto.getNome());
		stmt.setInt(2, produto.getEstoque());
		stmt.setDouble(3, produto.getValor());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stmt.setString(4, sdf.format(produto.getDataCadastro()));
		stmt.setInt(5, produto.getCategoria().getIdCategoria());

		stmt.execute();

		close();
	}

	@Override
	public List<Produto> buscar(String nome) throws Exception {
		open();
		List<Produto> lista = new ArrayList<Produto>();

		stmt = con.prepareStatement("select * from produto p inner join categoria c "
				+ " on p.id_categoria = c.idcategoria " + " where p.nome like ?");

		stmt.setString(1, nome);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Produto p = new Produto(rs.getInt("idproduto"), rs.getString("nome"), rs.getInt("estoque"),
					rs.getDouble("valor"), rs.getDate("datacadastro"));
			Categoria c = new Categoria(rs.getInt("idcategoria"), rs.getString("nomeCategoria"));
			p.setCategoria(c);
			lista.add(p);
		}
		close();
		return lista;
	}

	@Override
	public void excluir(int id) throws Exception {
		open();
		stmt = con.prepareStatement("delete from produto where idproduto = ?");
		stmt.setInt(1, id);
		stmt.execute();
		close();
	}

	@Override
	public void atualizar(Produto produto) throws Exception {
		open();

		stmt = con.prepareStatement("update produto set nome = ?, estoque = ?, valor = ?, "
				+ " datacadastro = ?, id_categoria = ? where idproduto = ? ");

		stmt.setString(1, produto.getNome());
		stmt.setInt(2, produto.getEstoque());
		stmt.setDouble(3, produto.getValor());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stmt.setString(4, sdf.format(produto.getDataCadastro()));
		stmt.setInt(5, produto.getCategoria().getIdCategoria());
		stmt.setInt(6, produto.getIdProduto());

		stmt.execute();

		close();
	}

	@Override
	public Produto buscarPorId(int id) throws Exception {
		open();

		Produto produto = null;

		stmt = con.prepareStatement("select * from produto p inner join categoria c "
				+ " on p.id_categoria = c.idcategoria " + " where p.idproduto = ?");

		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			produto = new Produto(rs.getInt("idproduto"), rs.getString("nome"), rs.getInt("estoque"),
					rs.getDouble("valor"), rs.getDate("datacadastro"));
			Categoria categoria = new Categoria(rs.getInt("idcategoria"), rs.getString("nomeCategoria"));
			produto.setCategoria(categoria);

		}
		close();
		return produto;
	}
}
