
import br.cefetmg.gestaoentregasdao.*;
import br.cefetmg.gestaoentregasentidades.*;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aluno
 */
public class TesteEmpresa {
    public static void main(String[] args) throws SQLException {
        FuncionarioDAO dao =  new FuncionarioDAO();
        dao.testeConexao();
        
    }
}
