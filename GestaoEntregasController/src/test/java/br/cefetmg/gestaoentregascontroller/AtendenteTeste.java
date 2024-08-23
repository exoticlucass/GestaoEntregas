package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasdao.EmpresaDAO; // Certifique-se de que você tenha um DAO para Empresa
import br.cefetmg.gestaoentregasentidades.Perfil;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Empresa; // Importando a classe Empresa
import java.util.Random;
import java.util.Scanner;

public class AtendenteTeste {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Perfil perfil = new Perfil();        
        Random random = new Random();       
        perfil.setTipoPerfilById(1); // Defina o tipo de perfil conforme necessário
        perfil.setId(random.nextInt(1000)); // ID aleatório para o perfil
        
        Funcionario funcionario = new Funcionario();
        funcionario.setId(random.nextInt(1000)); // ID aleatório para o funcionário
        
        // Coletando dados do funcionário
        System.out.print("Nome do Funcionário: ");
        String nome = scan.nextLine();
        funcionario.setNome(nome);
        
        System.out.print("Senha do Funcionário: ");
        String senha = scan.nextLine();
        funcionario.setSenha(senha);
        
        System.out.print("Telefone do Funcionário: ");
        String telefone = scan.nextLine();
        funcionario.setTelefone(telefone);
        
        // Criando e configurando a empresa
        Empresa empresa = new Empresa();
        System.out.print("Nome da Empresa: ");
        String nomeEmpresa = scan.nextLine();
        empresa.setNome(nomeEmpresa);
        
        System.out.print("CNPJ da Empresa: ");
        String cnpj = scan.nextLine();
        empresa.setCNPJ(cnpj);
        
        System.out.print("CPF do Responsável: ");
        String cpf = scan.nextLine();
        empresa.setCPF(cpf);
        
        System.out.print("Porcentagem de Comissão do Entregador: ");
        double comissao = scan.nextDouble();
        empresa.setPorcentagemComissaoEntregador(comissao);
        
        // Inserindo a empresa no banco de dados
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.inserir(empresa); // Insira a empresa primeiro
        
        // Associando a empresa ao funcionário
        funcionario.setEmpresa(empresa);
        perfil.setFuncionario(funcionario);
        funcionario.setPerfil(perfil);
        
        // Inserindo o funcionário e o perfil
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.inserir(funcionario);
        
        PerfilDAO perfilDAO = new PerfilDAO();
        perfilDAO.inserir(perfil);
        
        System.out.println("Funcionário e Empresa inseridos com sucesso!");
        
        scan.close(); // Fechar o scanner
    }
}
