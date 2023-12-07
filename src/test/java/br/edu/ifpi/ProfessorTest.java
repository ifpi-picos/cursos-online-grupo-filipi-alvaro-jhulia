package br.edu.ifpi;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
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
        ProfessorDao professorDAO = new ProfessorDao(conexao);

        String nome = "Rafael";
        String email = "Rafael@ifpi.edu.br";

        Professor novoProfessor = new Professor(nome, email);

        int retorno = professorDAO.cadastrar(novoProfessor);

        assertTrue(retorno > 0);
    }


    @Test     // Teste de associação de professor a curso
    public void associarProfessoresCursos() {
        ProfessorDao professorDAO = new ProfessorDao(conexao);
        CursoDao cursoDAO = new CursoDao(conexao);

        Professor professor = professorDAO.consultarPorId(1);
        Curso curso = cursoDAO.consultarPorId(1);
        curso.setProfessor(professor);

        int retorno = cursoDAO.alterar(curso);

        assertTrue(retorno > 0);
    }


    @Test     // Teste de alteração de informações de professor
    public void alterarInformacoesProfessor() {
        ProfessorDao professorDAO = new ProfessorDao(conexao);

        Professor professor = professorDAO.consultarPorId(1);

        professor.setNome("Jesiel");
        professor.setEmail("Jesiel@ifpi.edu.br");

        int result = professorDAO.alterar(professor);

        assertTrue(result > 0);
    }

    @Test     // Teste para visualizar cursos de um professor
    public void professoresCursos() {
        ProfessorDao professorDAO = new ProfessorDao(conexao);
        
        List<ProfessorCurso> cursos = professorDAO.cursos();

        for(int i = 0; i < cursos.size(); i++) {
            ProfessorCurso professorCurso = cursos.get(i);
            System.out.println("Professor: " + professorCurso.getNome() + " - Curso: " + professorCurso.getCurso());
        }
        
        assertTrue(cursos.size() > 0);
    }

}