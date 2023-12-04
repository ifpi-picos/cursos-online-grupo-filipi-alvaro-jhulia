package br.edu.ifpi;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.DAO.MatriculaDAO;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDAO;
// import br.edu.ifpi.DAO.MatriculaDAO;
import br.edu.ifpi.DAO.NotaDAO;
import br.edu.ifpi.DAO.ProfessorDAO;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Nota;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.enums.StatusCurso;
import br.edu.ifpi.enums.StatusNota;

public class CursoTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test      // Teste de cadastro de curso
    public void cadastrarCurso() {
        CursoDAO cursoDAO = new CursoDAO(conexao);
        ProfessorDAO professorDAO = new ProfessorDAO(conexao);

        Professor professor = professorDAO.consultarPorId(2);

        String nome = "Matemática";
        int cargaHoraria = 100;
        StatusCurso status = StatusCurso.ABERTO;

        Curso novoCurso = new Curso(nome, cargaHoraria, status, professor);

        int retorno = cursoDAO.cadastrar(novoCurso);
        System.out.println("Curso cadastrado com sucesso");

        assertTrue(retorno > 0);
    }


    @Test     // Teste de alteração de informações de curso
    public void alterarInformacoesCurso() {

        CursoDAO cursoDAO = new CursoDAO(conexao);

        Curso curso = cursoDAO.consultarPorId(1);

        curso.setNome("Letras");
        curso.setCargaHoraria(100);
        curso.setStatus(StatusCurso.ABERTO);

        System.out.println("Curso alterado com sucesso" + curso.getProfessor());
        int result = cursoDAO.alterar(curso);

        assertTrue(result > 0);
    }


    @Test     // Teste para exibir cursos disponíveis
    public void exibirCursosDisponiveis() {
        CursoDAO cursoDAO = new CursoDAO(conexao);

        final List<Curso> cursos = cursoDAO.consultarTodos();
        int result = 0;

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getStatus() == StatusCurso.ABERTO) {
                result++;
            }
        }

        System.out.println("Cursos disponíveis");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println("Curso: " + cursos.get(i).getNome());
        }
        System.out.println("========== FIM ==========");

        assertTrue(result > 0);
    }

    @Test     // Teste para registrar nota de um aluno no curso e já ver se o aluno foi aprovado ou não
    public void registrarNota() {
        NotaDAO notaDAO = new NotaDAO(conexao);
        Nota nota = new Nota(4, 1, 1, StatusNota.REPROVADO);
        
        int result = notaDAO.cadastrar(nota);

        assertTrue(result > 0);
    }

    @Test
    public void gerarEstatisticasAlunos() throws SQLException{
        NotaDAO notaDAO = new NotaDAO(conexao);
        CursoDAO cursoDAO = new CursoDAO(conexao);
        Curso curso = cursoDAO.consultarPorId(1);

        notaDAO.estatisticaAlunos(curso);
        assertTrue(true);
    }


    @Test     // Teste para exibir quantidade de alunos matriculados
    public void exibirQuantidadeAlunos(){
        MatriculaDAO matriculaDao = new MatriculaDAO(conexao);

        System.out.println("O curso possui " + matriculaDao.consultarQuantidadeAlunos(1) + "matriculados.");
    }


    @Test     // Teste para exibir a média geral de um curso
    public void exibirMediaGeral (){
        NotaDAO notaDao = new NotaDAO(conexao);

        notaDao.exibirMediaCurso(1);
    }


    @Test     // Teste para exibir a porcentagem de alunos aprovados
    public void percentualAprovacao(){
        MatriculaDAO matriculaDao = new MatriculaDAO(conexao);

        System.out.println(matriculaDao.calcularPorcentagemAprovacao(1));
    }




    @Test    // Teste para exibir a porcetagem de cursos (concluidos e não concluidos)
    public void testCalcularPorcentagemCursos() {
    CursoDAO CursoDAO = new CursoDAO(conexao);

    CursoDAO.calcularPorcentagemCursos(conexao);
}

}

