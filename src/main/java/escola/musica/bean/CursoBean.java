package escola.musica.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import escola.musica.dao.CursoDAO;
import escola.musica.modelo.Curso;
import escola.musica.modelo.TipoCurso;

@ManagedBean
@SessionScoped
public class CursoBean {

	private Curso curso;
	private List<TipoCurso> tipos = Arrays.asList(TipoCurso.values());
	private List<Curso> cursos = new ArrayList<Curso>();

	public CursoBean() {
		// TODO Auto-generated constructor stub
		cursos = new CursoDAO().getCursos();
		curso = new Curso();
	}

	public String salvar() {
		new CursoDAO().salvar(curso);
		// Adicionando na lista o nome curso.
		cursos = new CursoDAO().getCursos();
		// Limapando o curso depois de adicionar.
		curso = new Curso();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!"));

		return "curso_lista?faces-redirect=true";
	}

	public String editar(Curso curso) {
		this.curso = curso;
		System.out.println(curso);
		return "curso_formulario?faces-redirect=true";
	}

	public void excluir() {
		new CursoDAO().exlcuir(curso);
		// Adicionando na lista o nome curso.
		cursos = new CursoDAO().getCursos();
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Curso " + curso.getNome() + " excluido!"));
	}

	public String getDateNow() {
		return new SimpleDateFormat().format(new Date());
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<TipoCurso> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoCurso> tipos) {
		this.tipos = tipos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
