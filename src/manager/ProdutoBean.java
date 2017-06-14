package manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import persistence.Dao;
import persistence.ProdutoDao;
import entity.Categoria;
import entity.Produto;
import entity.Usuario;

@ManagedBean(name = "pBean")
@SessionScoped
public class ProdutoBean {

	private Produto produto;
	private Categoria categoria;
	private List<Produto> lista;
	private Produto produto2;

	@ManagedProperty(value = "#{uBean.usuario}")
	private Usuario usuario;

	public ProdutoBean() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	private void init() {
		produto = new Produto();
		categoria = new Categoria();
		lista = new ArrayList<Produto>();
		produto2 = new Produto();
	}

	public Produto getProduto2() {
		return produto2;
	}

	public void setProduto2(Produto produto2) {
		this.produto2 = produto2;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String cadastrar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
	
			produto.setCategoria(categoria);

			new ProdutoDao().cadastrar(produto);

			System.out.println(usuario.getNome());
			System.out.println(usuario.getIdUsuario());

			fc.addMessage("formproduto", new FacesMessage(produto.getNome() + ", cadastrado com sucesso!"));
			
			produto = new Produto();
		} catch (Exception e) {
			e.printStackTrace();
			fc.addMessage("formproduto", new FacesMessage("Erro: " + e.getMessage()));
		}
		return null;
	}

	public void buscar(ActionEvent event) {
		try {
			ProdutoDao pd = new ProdutoDao();
			lista = pd.buscar(produto.getNome() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String detalhes() {
		try {
			produto2 = new ProdutoDao().buscarPorId(produto.getIdProduto());
			produto = new Produto();
			if (produto2 == null)
				return "buscar";

			return "detalhes";
		} catch (Exception e) {
			e.printStackTrace();
			return "buscar";
		}
	}

	public String excluir() {
		try {
			new ProdutoDao().excluir(produto2.getIdProduto());
			FacesContext.getCurrentInstance().addMessage("form2", new FacesMessage("Produto excluido com sucesso!"));
			init();
			produto2 = new Produto();
			produto2.setCategoria(new Categoria());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "buscar";
	}

	public String editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			ProdutoDao pd = new ProdutoDao();
			pd.atualizar(produto2);
			init();
			fc.addMessage("form2", new FacesMessage("Produto editado com sucesso!"));
		} catch (Exception e) {
			e.printStackTrace();
			fc.addMessage("form2", new FacesMessage("Produto nao editado"));
		}
		return "buscar";
	}

	public String relatorio() {
		try {

			InputStream arquivo = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/relatorio.jasper");

			String nomeusuario = usuario.getNome().toUpperCase();
			Map<String, Object> valores = new HashMap<String, Object>();

			valores.put("nomeusuario", nomeusuario);
			Dao dao = new Dao();
			dao.open();
			byte[] pdf = JasperRunManager.runReportToPdf(arquivo, valores, dao.con);

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();

			ServletOutputStream out = response.getOutputStream();
			out.write(pdf);
			out.flush();

			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
