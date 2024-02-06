package br.com.cubosacademi.apirest.apirest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity // entidade no banco de dados
public class Produto {
    // as caracteristicas desse produto

    @Getter
    @Id
    // eu quero que java gere automaticamente o id
    // o generatedvalue eu passo a estrategia de como eu quero que gere esse id
    @GeneratedValue(strategy = GenerationType.AUTO) // vai gerar automaticamente
    private Integer id;

    @Getter
    @Setter // ja cria automaticamente get e set
    @Column(nullable = false) // estou falando que coluna nome precisa ser obrigatório no banco de dados
    @NotBlank(message = "O campo nome é obrigatório") // com spring validation aqui consigo verificar oque precisa no
                                                      // campo para validar
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false)
    @Min(value = 10, message = "O campo valor é obrigatório e no minimo 10 centavos")
    // posso passar valor minimo que precisa, exemplo minimo 10 centavos
    private Integer valor;

    @Getter
    @Setter
    @Column()
    private String descricao;
}
