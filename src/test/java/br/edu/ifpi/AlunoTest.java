package br.edu.ifpi;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.DAO.AlunoDAO;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDAO;
import br.edu.ifpi.DAO.MatriculaDAO;
import br.edu.ifpi.DAO.NotaDAO;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Matricula;
import br.edu.ifpi.enums.StatusAluno;

public class AlunoTest {
    Connection conexao;

    @Before
    public void setup() {
        conexao = Conexao.getConexao();
    }

    @Test    // Teste de cadastro de aluno
    public void cadastrarAluno() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        Aluno novoAluno = new Aluno("deborah", "deborah@gmail.com", StatusAluno.ATIVO);

        int retornoAluno = alunoDAO.cadastrar(novoAluno);
        assertTrue(retornoAluno > 0);
    }


    @Test   // Teste de visualização de perfil de aluno
    public void visualizarPerfil() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        Aluno aluno = alunoDAO.consultarPorEmail("deborah@gmail.com");

        System.out.println("Nome do aluno: " + aluno.getNome());
        System.out.println("E-mail: " + aluno.getEmail());
        System.out.println("Status: " + aluno.getStatus());

        assertTrue(aluno != null);
    }



    @Test // Teste de consulta de todos os alunos
    public void consultarTodos() throws SQLException {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        List<Aluno> alunos = alunoDAO.consultarTodos();

        for (Aluno aluno : alunos) {
            System.out.println("Nome do aluno: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
            System.out.println("Status: " + aluno.getStatus());
        }

        assertTrue(alunos.size() > 0);
    }


    @Test     // Teste para realizar matrícula
    public void realizarMatricula() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);
        MatriculaDAO matriculaDAO = new MatriculaDAO(conexao);
        CursoDAO cursoDAO = new CursoDAO(conexao);

        Curso curso = cursoDAO.consultarPorId(2);

        Aluno aluno = alunoDAO.consultarPorId(9);
        Matricula novaMatricula = new Matricula(aluno, curso, "Ativa");

        int retornoMatricula = matriculaDAO.cadastrar(novaMatricula);

        assertTrue(retornoMatricula > 0);
    }


    @Test        // Teste para cancelar matrícula
    public void cancelarMatricula() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);
        MatriculaDAO matriculaDAO = new MatriculaDAO(conexao);

        Aluno aluno = alunoDAO.consultarPorEmail("deborah@gmail.com");

        Matricula matricula = matriculaDAO.consultarPorAluno(aluno);
        matricula.setStatus("Cancelada");

        int resultado = matriculaDAO.alterar(matricula);

        assertTrue(resultado > 0);
    }


    @Test // Teste para ver as estatisticas de desempenho
    public void estatisticaDesempenho(){
        NotaDAO notaDAO = new NotaDAO(conexao);
        AlunoDAO alunoDAO = new AlunoDAO(conexao);
        Aluno aluno = alunoDAO.consultarPorEmail("carlosHenrique@gmail.com");

        notaDAO.getEstatisticaAluno(aluno);
    }


    @Test     // Teste para atualizar dados do aluno
    public void atualizarAluno() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        Aluno aluno = alunoDAO.consultarPorEmail("luci@gmail.com");
        aluno.setNome("Maria");

        int retornoAluno = alunoDAO.alterar(aluno);

        assertTrue(retornoAluno > 0);
    }


    @Test      // Teste para exibir cursos matriculados
    public void exibirCursosMatriculados() {
        AlunoDAO alunoDAO = new AlunoDAO(conexao);
        
        Aluno aluno = alunoDAO.consultarPorId(10);
        
        List<String> cursosMatriculados = alunoDAO.CursosMatriculados(aluno);
        
        for (String nomeCurso : cursosMatriculados) {
            System.out.println("Curso Matriculado: " + nomeCurso);
        }
        
        assertTrue("A lista de cursos matriculados deve ser maior que 0", cursosMatriculados.size() > 0);
    }
    

    @Test     // Teste para exibir cursos concluídos
    public void exibirCursosConcluidos() {
        AlunoDAO alunoDao = new AlunoDAO(conexao);
        Aluno aluno = alunoDao.consultarPorId(1);
    
        List<String> cursosConcluidos = alunoDao.exibirCursosConcluidos(aluno, conexao);
    
        for (String nomeCurso : cursosConcluidos) {
            System.out.println("Nome do Curso Concluído: " + nomeCurso);
        }
    
        assertTrue("A lista de nomes de cursos concluídos deve ser maior que 0", cursosConcluidos.size() > 0);
    }
    
    
}