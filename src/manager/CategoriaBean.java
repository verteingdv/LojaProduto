package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import persistence.CategoriaDao;
import entity.Categoria;

@ManagedBean(name = "cBean")
@RequestScoped
public class CategoriaBean {

	private List<SelectItem> listaCategoria;

	public CategoriaBean() {

	}

	public List<SelectItem> getListaCategoria() {
		try {
			listaCategoria = new ArrayList<SelectItem>();
			for (Categoria c : new CategoriaDao().listar()) {
				SelectItem si = new SelectItem(c.getIdCategoria(), c.getNomeCategoria());
				listaCategoria.add(si);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCategoria;
	}

	public void setListaCategoria(List<SelectItem> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
}
