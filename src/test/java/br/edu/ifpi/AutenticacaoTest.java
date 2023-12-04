package br.edu.ifpi;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.DAO.AlunoDAO;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.ProfessorDAO;
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
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        String emailTentativa = "usuario@naoexiste.com";

        Aluno aluno = alunoDAO.consultarPorEmail(emailTentativa);

        assertNull(aluno);
    }

    @Test
    public void autenticarAluno() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        String emailTentativa = "jhulia@gmail.com";

        Aluno aluno = alunoDAO.consultarPorEmail(emailTentativa);

        assertTrue(aluno != null);
        assertTrue(aluno.getEmail().equals(emailTentativa));
    }


    @Test
    public void autenticarProfessor() {
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);

        String emailTentativa = "Rafael@ifpi.edu.br";

        Professor professor = professorDAO.consultarPorEmail(emailTentativa);

        assertTrue(professor != null);
        assertTrue(professor.getEmail().equals(emailTentativa));
    }
}