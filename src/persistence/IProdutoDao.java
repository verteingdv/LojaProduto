package persistence;

import java.util.List;

import entity.Produto;

public interface IProdutoDao {
	
	public void cadastrar(Produto produto)throws Exception;
	public List<Produto> buscar(String nome)throws Exception;
	public void excluir(int id)throws Exception;
	public void atualizar(Produto produto)throws Exception;
	public Produto buscarPorId(int id)throws Exception;

}
