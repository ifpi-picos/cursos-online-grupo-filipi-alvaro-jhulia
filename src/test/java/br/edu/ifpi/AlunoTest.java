package br.edu.ifpi;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.MatriculaDao;
import br.edu.ifpi.dao.NotaDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Estatistica;
import br.edu.ifpi.entidades.Matricula;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.ProfessorCurso;
import br.edu.ifpi.enums.StatusAluno;

public class AlunoTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test
    public void cadastrarAluno() {
        AlunoDao alunoDao = new AlunoDao(conexao);
        MatriculaDao matriculaDao = new MatriculaDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);

        Curso curso = cursoDao.consultarPorId(1);

        Aluno novoAluno = new Aluno("luciana", "luci@gmail.com", StatusAluno.ATIVO);

        int retornoAluno = alunoDao.cadastrar(novoAluno);

        Aluno alunoCadastrado = alunoDao.consultarPorEmail(novoAluno.getEmail());
        Matricula novaMatricula = new Matricula(alunoCadastrado, curso, "Ativa");

        int retornoMatricula = matriculaDao.cadastrar(novaMatricula);

        assertTrue(retornoAluno > 0);
        assertTrue(retornoMatricula > 0);
    }

    @Test
    public void visualizarPerfil() {
        AlunoDao alunoDao = new AlunoDao(conexao);

        Aluno aluno = alunoDao.consultarPorEmail("julieta@ifpi.edu.br");

        System.out.println("Nome do aluno: " + aluno.getNome());
        System.out.println("E-mail: " + aluno.getEmail());
        System.out.println("Status: " + aluno.getStatus());

        assertTrue(aluno != null);
    }

    @Test
    public void realizarMatricula() {
        AlunoDao alunoDao = new AlunoDao(conexao);
        MatriculaDao matriculaDao = new MatriculaDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);

        Curso curso = cursoDao.consultarPorId(7);

        Aluno aluno = alunoDao.consultarPorId(13);
        Matricula novaMatricula = new Matricula(aluno, curso, "Ativa");

        int retornoMatricula = matriculaDao.cadastrar(novaMatricula);

        assertTrue(retornoMatricula > 0);
    }

    @Test
    public void cancelarMatricula() {
        AlunoDao alunoDao = new AlunoDao(conexao);
        MatriculaDao matriculaDao = new MatriculaDao(conexao);

        Aluno aluno = alunoDao.consultarPorEmail("julieta@ifpi.edu.br");

        Matricula matricula = matriculaDao.consultarPorAluno(aluno);
        matricula.setStatus("Cancelada");

        int resultado = matriculaDao.alterar(matricula);

        assertTrue(resultado > 0);
    }

    @Test
    public void estatisticaDesempenho() {
        AlunoDao alunoDao = new AlunoDao(conexao);
        NotaDao notaDao = new NotaDao(conexao);

        Aluno aluno = alunoDao.consultarPorEmail("julieta@ifpi.edu.br");
        Estatistica estatistica = notaDao.getEstatisticaAluno(aluno);

        System.out.println("Maior nota: " + estatistica.getMaiorNota());

        assertTrue(estatistica != null);
    }

    @Test
    public void atualizarAluno() {
        AlunoDao alunoDao = new AlunoDao(conexao);

        Aluno aluno = alunoDao.consultarPorEmail("julieta@ifpi.edu.br");
        aluno.setNome("Michael Jackson");

        int retornoAluno = alunoDao.alterar(aluno);

        assertTrue(retornoAluno > 0);
    }

    @Test
    public void associarProfessoresCursos() {
        ProfessorDao professorDao = new ProfessorDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);

        Professor professor = professorDao.consultarPorId(4);
        Curso curso = cursoDao.consultarPorId(1);
        curso.setProfessor(professor);

        int retorno = cursoDao.alterar(curso);

        assertTrue(retorno > 0);
    }

    @Test
    public void alterarInformacoesProfessor() {
        ProfessorDao professorDao = new ProfessorDao(conexao);

        Professor professor = professorDao.consultarPorId(4);

        professor.setNome("Jesiel");
        professor.setEmail("jesiel@ifpi.edu.br");

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


    @Test
    public void exibirCursosMatriculados() {
        AlunoDao alunoDao = new AlunoDao(conexao);
        
        Aluno aluno = alunoDao.consultarPorId(13);
        
        List<String> cursosMatriculados = alunoDao.CursosMatriculados(aluno);
        
        for (String nomeCurso : cursosMatriculados) {
            System.out.println("Curso Matriculado: " + nomeCurso);
        }
        
        assertTrue("A lista de cursos matriculados deve ser maior que 0", cursosMatriculados.size() > 0);
    }
    

    @Test
    public void exibirCursosConcluidos() {
        AlunoDao alunoDao = new AlunoDao(conexao);
    
        Aluno aluno = alunoDao.consultarPorId(13);
        List<String> cursosConcluidos = alunoDao.getCursosConcluidos(aluno);
    
        for (String nomeCurso : cursosConcluidos) {
            System.out.println("Curso Concluído: " + nomeCurso);
        }
    
        assertTrue("A lista de cursos concluídos deve ser maior que 0", cursosConcluidos.size() > 0);
    }
    
    
    


}