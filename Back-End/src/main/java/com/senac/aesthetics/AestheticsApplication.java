package com.senac.aesthetics;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.enums.EstadosBrasileirosEnum;
import com.senac.aesthetics.enums.StatusAgendamentoEnum;
import com.senac.aesthetics.enums.StatusOrdemServicoEnum;
import com.senac.aesthetics.services.AgendamentoService;
import com.senac.aesthetics.services.ClienteService;
import com.senac.aesthetics.services.FuncionarioService;
import com.senac.aesthetics.services.OrdemServicoService;
import com.senac.aesthetics.services.ServicoService;

@SpringBootApplication
public class AestheticsApplication implements ApplicationRunner {

	@Autowired
	ClienteService cs = new ClienteService();

	@Autowired
	FuncionarioService fs = new FuncionarioService();

	@Autowired
	ServicoService ss = new ServicoService();

	@Autowired
	AgendamentoService as = new AgendamentoService();

	@Autowired
	OrdemServicoService oss = new OrdemServicoService();

	public static void main(String[] args) {
		SpringApplication.run(AestheticsApplication.class, args);

		System.out.println("\n############################");
		System.out.println("### Aplicativo Iniciado! ###");
		System.out.println("############################\n");

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Boolean inserirDadosDeTeste = false;

		if (inserirDadosDeTeste) {
			System.out.println("\nInserindo dados de teste...\n");

			Cliente c = new Cliente();
			c.setNome("Cliente TESTE");
			c.setTelefone("(12)34567-8910");
			c.setEmail("teste@teste.com");
			c.setUf(EstadosBrasileirosEnum.GO);
			c.setAlergias("Nenhuma");

			cs.inserir(c);

			Funcionario f = new Funcionario();
			f.setNome("Funcionario TESTE");
			f.setTelefone("(12)34567-8910");
			f.setEmail("teste@teste.com");
			f.setUf(EstadosBrasileirosEnum.GO);
			f.setLogin("login");
			f.setSenha("senha");
			f.setComissao(BigDecimal.valueOf(10.0));

			fs.inserir(f);

			Servico s = new Servico();
			s.setNome("Servico TESTE");
			s.setDescricao("Descrição TESTE");
			s.setPrecoCusto(BigDecimal.valueOf(10.0));
			s.setPrecoVenda(BigDecimal.valueOf(10.0));

			ss.inserir(s);

			Agendamento a = new Agendamento();
			a.setData(new Date());
			a.setHora(new Date());
			a.setStatus(StatusAgendamentoEnum.ABERTO);
			a.setObservacao("Observação TESTE");
			a.setCliente(c);
			a.setRespAgendamento(f);
			a.setServico(s);

			as.inserir(a);

			OrdemServico os = new OrdemServico();
			os.setDataHoraInicio(new Date());
			os.setDataHoraTermino(new Date());
			os.setStatus(StatusOrdemServicoEnum.ABERTO);
			os.setValor(BigDecimal.valueOf(10.0));
			os.setAgendamento(a);
			os.setServico(s);
			os.setRespOS(f);
			os.setExecServico(f);

			oss.inserir(os);

			System.out.println("Valores de teste inseridos!\n");
		}
	}

}
