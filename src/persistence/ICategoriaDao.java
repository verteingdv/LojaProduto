package persistence;

import java.util.List;

import entity.Categoria;

public interface ICategoriaDao {
	
	public List<Categoria> listar()throws Exception;

}
