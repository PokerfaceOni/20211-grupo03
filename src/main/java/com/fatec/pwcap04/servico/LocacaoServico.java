package com.fatec.pwcap04.servico;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Locacao;

public interface LocacaoServico {
	public Iterable<Locacao> findAll();
	
	public Locacao findById(Long id);
	
	public void deleteById(Long id);
	
	public List<Locacao> findByPlacaCpf(String placa, String cpf);

	public ModelAndView saveOrUpdate(Locacao locacao);
}
