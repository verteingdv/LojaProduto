package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Categoria;

public class CategoriaDao extends Dao implements ICategoriaDao{

	@Override
	public List<Categoria> listar()throws Exception{
		open();
		
		stmt = con.prepareStatement("select * from categoria order by nomecategoria");
		rs = stmt.executeQuery();
		List<Categoria> lista = new ArrayList<Categoria>();
		
		while(rs.next()){
			Categoria c = new Categoria(rs.getInt("idcategoria"), 
					rs.getString("nomecategoria"));
			lista.add(c);
		}
		
		close();
		return lista;
	}
	
}
