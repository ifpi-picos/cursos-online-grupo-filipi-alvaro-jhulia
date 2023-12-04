package br.edu.ifpi;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDAO;
import br.edu.ifpi.DAO.ProfessorDAO;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.ProfessorCurso;

public class ProfessorTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test     // Teste de cadastro de professor
    public void cadastrarProfessor() {
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);

        String nome = "Rafael";
        String email = "Rafael@ifpi.edu.br";

        Professor novoProfessor = new Professor(nome, email);

        int retorno = professorDAO.cadastrar(novoProfessor);

        assertTrue(retorno > 0);
    }


    @Test     // Teste de associação de professor a curso
    public void associarProfessoresCursos() {
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);
        CursoDAO cursoDAO = new CursoDAO(conexao);

        Professor professor = professorDAO.consultarPorId(4);
        Curso curso = cursoDAO.consultarPorId(1);
        curso.setProfessor(professor);

        int retorno = cursoDAO.alterar(curso);

        assertTrue(retorno > 0);
    }


    @Test     // Teste de alteração de informações de professor
    public void alterarInformacoesProfessor() {
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);

        Professor professor = professorDAO.consultarPorId(4);

        professor.setNome("David");
        professor.setEmail("David@ifpi.edu.br");

        int result = professorDAO.alterar(professor);

        assertTrue(result > 0);
    }

    @Test     // Teste para visualizar cursos de um professor
    public void professoresCursos() {
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);
        
        List<ProfessorCurso> cursos = professorDAO.cursos();

        for(int i = 0; i < cursos.size(); i++) {
            ProfessorCurso professorCurso = cursos.get(i);
            System.out.println("Professor: " + professorCurso.getNome() + " - Curso: " + professorCurso.getCurso());
        }
        
        assertTrue(cursos.size() > 0);
    }

}