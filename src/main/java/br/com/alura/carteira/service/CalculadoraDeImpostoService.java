package br.com.alura.carteira.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class CalculadoraDeImpostoService {

	// 15% de imposto para transações do tipo venda com valor superior a r$
	// 20.000,00
	public BigDecimal calcular(Transacao transacao) {
		if (transacao.getTipo() == TipoTransacao.COMPRA) {
			return BigDecimal.ZERO;
		}

		BigDecimal valorTransacao = transacao.getPreco().multiply(new BigDecimal(transacao.getQuantidade()));

		// se valores iguais ==> compareTo devolve 0
		// se valorTransacao MENOR 20000 ==> compareTo devolve -1 (numero negativo)
		// se valorTransacao MAIOR 20000 ==> compareTo devolve 1 (numero positivo)
		if (valorTransacao.compareTo(new BigDecimal(20000)) < 0) {
			return BigDecimal.ZERO;
		}

		return valorTransacao.multiply(new BigDecimal("0.15")).setScale(2, RoundingMode.HALF_UP);
	}

}
