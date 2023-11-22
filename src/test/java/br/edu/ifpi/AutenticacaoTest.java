package br.edu.ifpi;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Professor;

public class AutenticacaoTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test
    public void falhaAutenticaoAluno() {
        AlunoDao alunoDao = new AlunoDao(conexao);

        String emailTentativa = "usuario@naoexiste.com";

        Aluno aluno = alunoDao.consultarPorEmail(emailTentativa);

        assertNull(aluno);
    }

    @Test
    public void autenticarAluno() {
        AlunoDao alunoDao = new AlunoDao(conexao);

        String emailTentativa = "jhulia@gmail.com";

        Aluno aluno = alunoDao.consultarPorEmail(emailTentativa);

        assertTrue(aluno != null);
        assertTrue(aluno.getEmail().equals(emailTentativa));
    }

    @Test
    public void autenticarProfessor() {
        ProfessorDao professorDao = new ProfessorDao(conexao);

        String emailTentativa = "jesiel@ifpi.edu.br";

        Professor professor = professorDao.consultarPorEmail(emailTentativa);

        assertTrue(professor != null);
        assertTrue(professor.getEmail().equals(emailTentativa));
    }
}