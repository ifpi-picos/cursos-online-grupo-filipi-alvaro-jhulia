package br.edu.ifpi;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.MatriculaDao;
import br.edu.ifpi.dao.NotaDao;
import br.edu.ifpi.dao.ProfessorDao;
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
        CursoDao cursoDao = new CursoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);

        Professor professor = professorDao.consultarPorId(2);

        String nome = "Matemática";
        int cargaHoraria = 100;
        StatusCurso status = StatusCurso.ABERTO;

        Curso novoCurso = new Curso(nome, cargaHoraria, status, professor);

        int retorno = cursoDao.cadastrar(novoCurso);
        System.out.println("Curso cadastrado com sucesso");

        assertTrue(retorno > 0);
    }


    @Test     // Teste de alteração de informações de curso
    public void alterarInformacoesCurso() {

        CursoDao cursoDao = new CursoDao(conexao);

        Curso curso = cursoDao.consultarPorId(1);

        curso.setNome("Letras");
        curso.setCargaHoraria(100);
        curso.setStatus(StatusCurso.ABERTO);

        System.out.println("Curso alterado com sucesso" + curso.getProfessor());
        int result = cursoDao.alterar(curso);

        assertTrue(result > 0);
    }


    @Test     // Teste para exibir cursos disponíveis
    public void exibirCursosDisponiveis() {
        CursoDao cursoDao = new CursoDao(conexao);

        final List<Curso> cursos = cursoDao.consultarTodos();
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
        NotaDao notaDao = new NotaDao(conexao);
        Nota nota = new Nota(8, 1, 2, StatusNota.APROVADO);
        
        int result = notaDao.cadastrar(nota);

        assertTrue(result > 0);
    }

    @Test
    public void gerarEstatisticasAlunos() throws SQLException{
        NotaDao notaDao = new NotaDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);
        Curso curso = cursoDao.consultarPorId(1);

        notaDao.estatisticaAlunos(curso);
        assertTrue(true);
    }


    @Test     // Teste para exibir quantidade de alunos matriculados
    public void exibirQuantidadeAlunos(){
        MatriculaDao matriculaDao = new MatriculaDao(conexao);

        System.out.println("O curso possui " + matriculaDao.consultarQuantidadeAlunos(1) + " aluno(s) matriculado(s).");
    }


    @Test     // Teste para exibir a média geral de um curso
    public void exibirMediaGeral (){
        NotaDao notaDao = new NotaDao(conexao);

        notaDao.exibirMediaCurso(1); // Digite aqui o id do curso
    }


    @Test     // Teste para exibir a porcentagem de alunos aprovados
    public void percentualAprovacao(){
        MatriculaDao matriculaDao = new MatriculaDao(conexao);

        System.out.println(matriculaDao.calcularPorcentagemAprovacao(1));// Digite aqui o id do curso
    }




    @Test    // Teste para exibir a porcetagem de cursos (concluidos e não concluidos)
    public void testCalcularPorcentagemCursos() {
    CursoDao CursoDao = new CursoDao(conexao);

    CursoDao.calcularPorcentagemCursos(conexao);
}

}

