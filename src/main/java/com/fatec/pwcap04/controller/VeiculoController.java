package com.fatec.pwcap04.controller;

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
import com.fatec.pwcap04.servico.VeiculoServico;
import com.fatec.pwcap04.model.Veiculo;

@Controller
@RequestMapping(path = "/sig")
public class VeiculoController {
	Logger logger = LogManager.getLogger(VeiculoController.class);
	@Autowired
	VeiculoServico servico;
	
	@GetMapping("/veiculos")
	public ModelAndView retornaFormDeConsultaTodosVeiculos() {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		modelAndView.addObject("veiculos", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/veiculo/cadastrarVeiculo")
	public ModelAndView retornaFormDeCadastroDe(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView("cadastrarVeiculo");
		mv.addObject("veiculo", veiculo);
		return mv;
	}
	
	@GetMapping("/veiculos/{placa}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarVeiculo(@PathVariable("placa") String placa) {
		ModelAndView modelAndView = new ModelAndView("atualizarVeiculo");
		modelAndView.addObject("veiculo", servico.findByPlaca(placa)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/veiculo/{id}")
	public ModelAndView excluirNoFormDeConsultaVeiculo(@PathVariable("id") Long id) {
		servico.deleteById(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		modelAndView.addObject("veiculos", servico.findAll());
		return modelAndView;
	}
	
	@PostMapping("/veiculos")
	public ModelAndView save(@Valid Veiculo veiculo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarVeiculo");
		} else {
			modelAndView = servico.saveOrUpdate(veiculo);
		}
		return modelAndView;
	}
	
	@PostMapping("/veiculos/{id}")
	public ModelAndView atualizaVeiculo(@PathVariable("id") Long id, @Valid Veiculo veiculo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		if (result.hasErrors()) {
			veiculo.setId(id);
			return new ModelAndView("atualizarVeiculo");
		}
		// programacao defensiva - deve-se verificar se o Veiculo existe antes de
		// atualizar
		Veiculo umVeiculo = servico.findById(id);
		umVeiculo.setPlaca(veiculo.getPlaca());
		umVeiculo.setCor(veiculo.getCor());
		umVeiculo.setDisponibilidade(veiculo.getDisponibilidade());
		umVeiculo.setMarca(veiculo.getMarca());
		umVeiculo.setModelo(veiculo.getModelo());
		umVeiculo.setDescAdicio(veiculo.getDescAdicio());
		modelAndView = servico.saveOrUpdate(umVeiculo);

		return modelAndView;
	}
	
}
