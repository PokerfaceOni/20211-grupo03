package com.fatec.pwcap04.controller;

import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.pwcap04.servico.ClienteServico;
import com.fatec.pwcap04.servico.LocacaoServico;
import com.fatec.pwcap04.servico.VeiculoServico;
import com.fatec.pwcap04.model.Cliente;
import com.fatec.pwcap04.model.Locacao;
import com.fatec.pwcap04.model.Veiculo;

@Controller
@RequestMapping(path = "/sig")
public class LocacaoController {
	Logger logger = LogManager.getLogger(LocacaoController.class);
	@Autowired
	LocacaoServico servico;
	
	@Autowired
	private ClienteServico clienteServico;
	
	@Autowired
	private VeiculoServico veiculoServico;
	
	@GetMapping("/locaDev")
	public ModelAndView retornaFormDeConsultaTodasLocacoes() {
		ModelAndView modelAndView = new ModelAndView("locaDev");
		modelAndView.addObject("locacoes", servico.findAll());
		return modelAndView;
	}
	
	@GetMapping("/locacoes/cadastrarLocacao")
	public ModelAndView retornaFormDeCadastroDe(Locacao locacao) {
		ModelAndView mv = new ModelAndView("cadastrarLocacao");
		mv.addObject("locacao", locacao);
		return mv;
	}
	
	/*@PostMapping("/locaDev")
	public ModelAndView save(@Valid Locacao locacao, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("locaDev");
		if (result.hasErrors()) {
			
			logger.info(">>>>>> 1. conotroller cadastrar locação chamado pela view com erro ");
			modelAndView.addObject("message", "Dados invalidos!");
			modelAndView.setViewName("cadastrarLocacao");
			return modelAndView;
			
		} else {
			
			logger.info(">>>>>> 1. conotroller cadastrar locação chamado pela view"); 
			modelAndView = servico.saveOrUpdate(locacao);
		}
		return modelAndView;
	}*/
	
	
	@PostMapping("/locaDev")
	public ModelAndView save(@Valid Locacao locacao, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("locaDev");
		if (result.hasErrors()) {
			
			logger.info(">>>>>> 1. conotroller cadastrar locação chamado pela view com erro ");
			modelAndView.addObject("message", "Dados invalidos!");
			modelAndView.setViewName("cadastrarLocacao");
			return modelAndView;
			
		} else {
			
			logger.info(">>>>>> 1. conotroller cadastrar locação chamado pela view"); 
			modelAndView = servico.saveOrUpdate(locacao);
			
		}try {
			
			Veiculo veiculo = null;
			Cliente cliente = null;
			
			veiculo = veiculoServico.findByPlaca(locacao.getVeiculo());
			cliente = clienteServico.findByCpf(locacao.getCliente());
			
			List<Locacao> locacoes = servico.findByPlacaCpf(locacao.getVeiculo(), locacao.getCliente());
			boolean locacaoEmAberto = false;
			for(Locacao umaLocacao : locacoes) {
				if(umaLocacao.getDataDevo() == null) {
					locacaoEmAberto = true;
				}
			}
			
			if((veiculo != null && cliente != null && locacoes == null) || (veiculo != null && cliente != null && locacaoEmAberto == false)) {
				locacao.setDataLoca(null);
				servico.saveOrUpdate(locacao);
				modelAndView.addObject("message", "Locação registrada");
			} else {
				logger.info(">>>>>> não achou veiculo/cliente no db");
				modelAndView.addObject("message","Veiculo/Cliente não localizado ou locação em aberto");
			}			
			
		} catch(Exception e) {
			logger.info("erro não esperado no cadastro de locação =======> "+e.getMessage());
		}
		return modelAndView;
	}
	
	@GetMapping("/locaDev/{id}")
	public ModelAndView excluirNoFormDeConsultaLocacao(@PathVariable("id") Long id) {
		servico.deleteById(id);
		logger.info(">>>>>> 2. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("locaDev");
		modelAndView.addObject("locacoes", servico.findAll());
		return modelAndView;
	}
	
}
