package com.fatec.pwcap04.servico;

import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Veiculo;

public interface VeiculoServico {
	public Iterable<Veiculo> findAll();

	public Veiculo findByPlaca(String placa);

	public void deleteById(Long id);

	public Veiculo findById(Long id);

	public ModelAndView saveOrUpdate(Veiculo veiculo);
}
