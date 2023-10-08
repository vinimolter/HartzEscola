package br.com.hartzescola.domain.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record DadosEndereco(
    @NotBlank
    String logradouro, 
    @NotBlank
    String bairro, 
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep, 
    @NotBlank
    String cidade, 
    @NotBlank
    String uf, 
    String complemento, 
    String numero) {
}
