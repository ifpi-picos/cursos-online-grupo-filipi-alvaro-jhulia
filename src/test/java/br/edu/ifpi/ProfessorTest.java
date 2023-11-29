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

    @Test
    public void cadastrarProfessor() {
        ProfessorDao professorDao = new ProfessorDao(conexao);

        String nome = "Fulano";
        String email = "fulano@ifpi.edu.br";

        Professor novoProfessor = new Professor(nome, email);

        int retorno = professorDao.cadastrar(novoProfessor);

        assertTrue(retorno > 0);
    }

    @Test
    public void associarProfessoresCursos() {
        ProfessorDao professorDao = new ProfessorDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);

        Professor professor = professorDao.consultarPorId(7);
        Curso curso = cursoDao.consultarPorId(1);
        curso.setProfessor(professor);

        int retorno = cursoDao.alterar(curso);

        assertTrue(retorno > 0);
    }

    @Test
    public void alterarInformacoesProfessor() {
        ProfessorDao professorDao = new ProfessorDao(conexao);

        Professor professor = professorDao.consultarPorId(7);

        professor.setNome("Rafael");
        professor.setEmail("rafael@ifpi.edu.br");

        int result = professorDao.alterar(professor);

        assertTrue(result > 0);
    }

    @Test
    public void professoresCursos() {
        ProfessorDao professorDao = new ProfessorDao(conexao);
        
        List<ProfessorCurso> cursos = professorDao.cursos();

        for(int i = 0; i < cursos.size(); i++) {
            ProfessorCurso professorCurso = cursos.get(i);
            System.out.println("Professor: " + professorCurso.getNome() + " - Curso: " + professorCurso.getCurso());
        }
        
        assertTrue(cursos.size() > 0);
    }

}