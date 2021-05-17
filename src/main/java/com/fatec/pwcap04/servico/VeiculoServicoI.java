package com.fatec.pwcap04.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Veiculo;
import com.fatec.pwcap04.model.VeiculoRepository;

@Service
public class VeiculoServicoI implements VeiculoServico {
	Logger logger = LogManager.getLogger(VeiculoServicoI.class);
	@Autowired
	private VeiculoRepository repository;

	public Iterable<Veiculo> findAll() {
		return repository.findAll();
	}

	public Veiculo findByPlaca(String placa) {
		return repository.findByPlaca(placa);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}

	public Veiculo findById(Long id) {
		return repository.findById(id).get();
	}

	public ModelAndView saveOrUpdate(Veiculo veiculo) {
		ModelAndView modelAndView = new ModelAndView("consultarVeiculo");
		try {
			
			//String placa = obtemEndereco(veiculo.getPlaca());
			 //if (placa != "") {
			//	 veiculo.setPlaca(placa);
			 repository.save(veiculo);
			 logger.info(">>>>>> 4. comando save executado ");
			 modelAndView.addObject("veiculos", repository.findAll());
			// } 
			 
		} catch (Exception e) {
			modelAndView.setViewName("cadastrarVeiculo");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - veiculo já cadastrado.");
				logger.info(">>>>>> 5. veiculo ja cadastrado ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}
}
