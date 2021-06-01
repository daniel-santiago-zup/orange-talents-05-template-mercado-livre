package br.com.zup.zupacademy.daniel.mercadolivre.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idTransacaoGateway;
    @NotNull
    private LocalDateTime instante = LocalDateTime.now();
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTrasacao;
    @ManyToOne
    @NotNull
    @Valid
    private Compra compra;

    @Deprecated
    public Transacao() {}

    public Transacao(Long idTransacaoGateway, StatusTransacao statusTrasacao, Compra compra) {
        this.idTransacaoGateway = idTransacaoGateway;
        this.statusTrasacao = statusTrasacao;
        this.compra = compra;
    }

    public StatusTransacao getStatusTrasacao() {
        return statusTrasacao;
    }
}