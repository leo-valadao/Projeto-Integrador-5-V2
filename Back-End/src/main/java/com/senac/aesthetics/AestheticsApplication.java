package com.senac.aesthetics;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.domains.Pessoa;
import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.domains.enums.EstadosBrasileirosEnum;
import com.senac.aesthetics.domains.enums.StatusAgendamentoEnum;
import com.senac.aesthetics.domains.enums.StatusContaPagarEnum;
import com.senac.aesthetics.domains.enums.StatusContaReceberEnum;
import com.senac.aesthetics.domains.enums.StatusOrdemServicoEnum;
import com.senac.aesthetics.domains.enums.TipoPessoaEnum;
import com.senac.aesthetics.domains.utils.GeradorDocumento;
import com.senac.aesthetics.services.AgendamentoService;
import com.senac.aesthetics.services.ClienteService;
import com.senac.aesthetics.services.ContaPagarService;
import com.senac.aesthetics.services.ContaReceberService;
import com.senac.aesthetics.services.FornecedorService;
import com.senac.aesthetics.services.FuncionarioService;
import com.senac.aesthetics.services.OrdemServicoService;
import com.senac.aesthetics.services.ServicoService;

// TODO: REMOVER A INTERFACE ApplicationRunner AO MIGRAR PARA PRODUÇÃO
@SpringBootApplication
public class AestheticsApplication implements ApplicationRunner {

	// TODO: REMOVER ESTES AUTOWIRED AO MIGRAR PARA PRODUÇÃO
	@Autowired
	AgendamentoService as;

	@Autowired
	ClienteService cs;

	@Autowired
	ContaPagarService cps;

	@Autowired
	ContaReceberService crs;

	@Autowired
	FornecedorService fos;

	@Autowired
	FuncionarioService fus;

	@Autowired
	OrdemServicoService oss;

	@Autowired
	ServicoService ss;

	public static void main(String[] args) {
		SpringApplication.run(AestheticsApplication.class, args);

		System.out.println("\n############################");
		System.out.println("### Aplicativo Iniciado! ###");
		System.out.println("############################\n");

	}

	// TODO: REMOVER O MÉTODO run AO MIGRAR PARA PRODUÇÃO!
	@Override
	public void run(ApplicationArguments args) throws Exception {

		Boolean inserirDadosDeTeste = true;

		if (inserirDadosDeTeste) {
			System.out.println("\nInserindo dados de teste...\n");
			Random r = new Random();
			GeradorDocumento gd = new GeradorDocumento();
			Integer quantidadeDeTestes = 250;

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				Cliente c = new Cliente();
				c.setPessoa(new Pessoa());
				c.getPessoa().setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA);
				c.getPessoa().setNome("Cliente " + i);
				c.getPessoa().setTelefone("(12)34567-8910");
				c.getPessoa().setEmail("teste" + i + "@teste.com");
				c.getPessoa().setEstadoBrasileiro(EstadosBrasileirosEnum.GOIAS);
				c.getPessoa().setCpfOuCnpj(gd.cpf(true));
				c.setAlergias("Nenhuma");

				cs.inserir(c);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				Servico s = new Servico();
				s.setNome("Serviço " + i);
				s.setDescricao("Descrição " + i);
				s.setPrecoCusto(BigDecimal.valueOf(1000000.00));
				s.setPrecoVenda(BigDecimal.valueOf(1000000.00));

				ss.inserir(s);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				Funcionario f = new Funcionario();
				f.setPessoa(new Pessoa());
				f.getPessoa().setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA);
				f.getPessoa().setNome("Funcionário " + i);
				f.getPessoa().setTelefone("(12)34567-8910");
				f.getPessoa().setEmail("teste" + i + "@teste.com");
				f.getPessoa().setEstadoBrasileiro(EstadosBrasileirosEnum.GOIAS);
				f.getPessoa().setCpfOuCnpj(gd.cpf(true));
				f.setLogin("f" + i);
				f.setSenha("f" + i);
				f.setComissao(BigDecimal.valueOf(100.00));

				fus.inserir(f);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				Agendamento a = new Agendamento();
				a.setData(new Date());
				a.setHora(new Date());
				Integer num = r.nextInt(3) + 1;
				if (num == 1) {
					a.setStatus(StatusAgendamentoEnum.ABERTO);
				} else if (num == 2) {
					a.setStatus(StatusAgendamentoEnum.CANCELADO);
				} else {
					a.setStatus(StatusAgendamentoEnum.CONFIRMADO);
				}
				a.setObservacao("Cliente chato >:/");
				a.setCliente(cs.obterPorId(Long.valueOf(i)));
				a.setFuncionario(fus.obterPorId(Long.valueOf(i)));
				a.setServico(ss.obterPorId(Long.valueOf(i)));

				as.inserir(a);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				OrdemServico os = new OrdemServico();
				os.setDataHoraInicio(new Date());
				os.setDataHoraTermino(new Date());
				Integer num = r.nextInt(4) + 1;
				if (num == 1) {
					os.setStatus(StatusOrdemServicoEnum.ABERTO);
				} else if (num == 2) {
					os.setStatus(StatusOrdemServicoEnum.CANCELADO);
				} else if (num == 3) {
					os.setStatus(StatusOrdemServicoEnum.CONCLUIDO);
				} else {
					os.setStatus(StatusOrdemServicoEnum.EM_EXECUCAO);
				}

				os.setValor(BigDecimal.valueOf(100.00));
				os.setAgendamento(as.obterPorId(Long.valueOf(i)));
				os.setServico(ss.obterPorId(Long.valueOf(i)));
				os.setResponsavelPelaOS(fus.obterPorId(Long.valueOf(i)));
				os.setExecutorServico(fus.obterPorId(Long.valueOf(i)));

				oss.inserir(os);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				ContaReceber cr = new ContaReceber();
				cr.setDataEmissao(new Date(122, 5, 7, 15, 10));
				cr.setDataVencimento(new Date(124, 5, 7, 15, 10));
				cr.setValor(BigDecimal.valueOf(100.00));
				cr.setValorRecebido(BigDecimal.valueOf(100.00));
				cr.setDataRecebimento(new Date());
				Integer num = r.nextInt(3) + 1;
				if (num == 1) {
					cr.setStatus(StatusContaReceberEnum.ABERTO);
				} else if (num == 2) {
					cr.setStatus(StatusContaReceberEnum.CANCELADO);
				} else {
					cr.setStatus(StatusContaReceberEnum.RECEBIDO);
				}
				cr.setOrdemServico(oss.obterPorId(Long.valueOf(i)));
				cr.setCliente(cs.obterPorId(Long.valueOf(i)));

				crs.inserir(cr);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				Fornecedor f = new Fornecedor();
				f.setPessoa(new Pessoa());
				f.getPessoa().setTipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA);
				f.getPessoa().setNome("Fornecedor " + i);
				f.getPessoa().setTelefone("(12)34567-8910");
				f.getPessoa().setEmail("teste" + i + "@teste.com");
				f.getPessoa().setEstadoBrasileiro(EstadosBrasileirosEnum.GOIAS);
				f.getPessoa().setCpfOuCnpj(gd.cnpj(true));
				f.setEndereco("Endereço " + i);

				fos.inserir(f);
			}

			for (int i = 1; i <= quantidadeDeTestes; i++) {
				ContaPagar cp = new ContaPagar();
				cp.setDataEmissao(new Date(122, 5, 7, 15, 10));
				cp.setDataVencimento(new Date(124, 5, 7, 15, 10));
				cp.setValor(BigDecimal.valueOf(100.00));
				cp.setValorPago(BigDecimal.valueOf(100.00));
				cp.setDataPagamento(new Date());
				Integer num = r.nextInt(3) + 1;
				if (num == 1) {
					cp.setStatus(StatusContaPagarEnum.ABERTO);
				} else if (num == 2) {
					cp.setStatus(StatusContaPagarEnum.CANCELADO);
				} else {
					cp.setStatus(StatusContaPagarEnum.PAGO);
				}
				cp.setFornecedor(fos.obterPorId(Long.valueOf(i)));

				cps.inserir(cp);
			}

			System.out.println("Valores de teste inseridos!\n");
		}
	}

}
