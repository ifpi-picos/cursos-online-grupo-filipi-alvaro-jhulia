package br.edu.ifpi;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.NotaDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Estatistica;
import br.edu.ifpi.entidades.Nota;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.enums.StatusCurso;

public class CursoTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test
    public void cadastrarCurso() {
        CursoDao cursoDao = new CursoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);

        Professor professor = professorDao.consultarPorId(4);

        String nome = "Matemática";
        int cargaHoraria = 60;
        StatusCurso status = StatusCurso.ABERTO;

        Curso novoCurso = new Curso(nome, cargaHoraria, status, professor);

        int retorno = cursoDao.cadastrar(novoCurso);

        assertTrue(retorno > 0);
    }

    @Test
    public void alterarInformacoesCurso() {
        CursoDao cursoDao = new CursoDao(conexao);

        Curso curso = cursoDao.consultarPorId(6);

        curso.setCargaHoraria(60);

        int result = cursoDao.alterar(curso);

        assertTrue(result > 0);
    }

    @Test
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

    @Test
    public void registrarNota() {
        CursoDao cursoDao = new CursoDao(conexao);
        NotaDao notaDao = new NotaDao(conexao);
        AlunoDao alunoDao = new AlunoDao(conexao);

        Curso curso = cursoDao.consultarPorId(6);
        Aluno aluno = alunoDao.consultarPorId(13);
        Nota nota = new Nota(10, aluno.getId(), curso.getId());

        int result = notaDao.cadastrar(nota);

        assertTrue(result > 0);
    }

    @Test
    public void gerarEstatisticasAlunos() {
        NotaDao notaDao = new NotaDao(conexao);
        
        final Estatistica estatistica = notaDao.estatisticaAlunos();

        System.out.println("O aluno com maior nota é "+estatistica.getAlunoId()+". Nota: " + estatistica.getMaiorNota());

        assertTrue(estatistica.getMaiorNota() > 0);
    }
}