package com.fatec.pwcap04.servico;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Locacao;
import com.fatec.pwcap04.model.LocacaoRepository;

@Service
public class LocacaoServicoI implements LocacaoServico{
	Logger logger = LogManager.getLogger(LocacaoServicoI.class);
	
	@Autowired
	private LocacaoRepository repository;
	
	public Iterable<Locacao> findAll() {
		return repository.findAll();
	}
	
	public Locacao findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}
	
	@Override
	public List<Locacao> findByPlacaCpf(String placa, String cpf) {
		return repository.findByPlacaCpf(placa, cpf);
	}
	
	public ModelAndView saveOrUpdate(Locacao locacao) {
		ModelAndView modelAndView = new ModelAndView("locaDev");
		try {
			locacao.setDataLoca(new DateTime()); 
			repository.save(locacao);
			logger.info(">>>>>> 4. comando save executado ");
			modelAndView.addObject("locacoes", repository.findAll());
			 
		} catch (Exception e) {
			modelAndView.setViewName("cadastrarLocacao");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - locação já cadastrado.");
				logger.info(">>>>>> 5. locação ja cadastrada ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}

	
}
