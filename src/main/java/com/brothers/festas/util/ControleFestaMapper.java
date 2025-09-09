package com.brothers.festas.util;

import com.brothers.festas.dto.request.*;
import com.brothers.festas.dto.request.controle.CardapioControleRequestDTO;
import com.brothers.festas.dto.request.controle.ControleFestaRequestDTO;
import com.brothers.festas.dto.request.controle.DecoracaoRequestDTO;
import com.brothers.festas.dto.request.controle.ExtraRequestDTO;
import com.brothers.festas.dto.request.controle.GeralRequestDTO;
import com.brothers.festas.dto.response.*;
import com.brothers.festas.dto.response.controle.CardapioControleResponseDTO;
import com.brothers.festas.dto.response.controle.ControleFestaResponseDTO;
import com.brothers.festas.dto.response.controle.DecoracaoResponseDTO;
import com.brothers.festas.dto.response.controle.ExtraResponseDTO;
import com.brothers.festas.dto.response.controle.GeralResponseDTO;
import com.brothers.festas.dto.response.controle.TemaControleResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.*;
import com.brothers.festas.repository.BebidaCadastroRepository;
import com.brothers.festas.repository.CardapioRepository;
import com.brothers.festas.repository.ContratoRepository;
import com.brothers.festas.repository.OficinaRepository;
import com.brothers.festas.repository.TemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControleFestaMapper {
    private final ContratoRepository contratoRepository;
    private final OficinaRepository oficinaRepository;
    private final CardapioRepository cardapioRepository;
    private final BebidaCadastroRepository bebidaCadastroRepository;
    private final TemaRepository temaRepository;

    public ControleFesta toEntity(ControleFestaRequestDTO dto) {
        if (dto == null) return null;

        ControleFesta festa = new ControleFesta();
        festa.setData(dto.getData());
        festa.setHorarioInicio(dto.getHorarioInicio());
        festa.setHorarioFim(dto.getHorarioFim());

        festa.setGeral(toGeralEntity(dto.getGeral()));
        festa.setOficinaControle(toOficinaEntity(dto.getOficinaId()));
        festa.setCardapioControle(toCardapioEntity(dto.getCardapio()));
        festa.setBebida(toBebidaEntity(dto.getBebida()));
        festa.setDecoracao(toDecoracaoEntity(dto.getDecoracao()));
        festa.setExtra(toExtraEntity(dto.getExtra()));


        if (dto.getContratoId() != null) {
            Contrato contrato = contratoRepository.findById(dto.getContratoId())
                    .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
            festa.setContrato(contrato);
        }

        return festa;
    }

    public ControleFestaResponseDTO toResponse(ControleFesta entity) {
        if (entity == null) return null;

        return ControleFestaResponseDTO.builder()
                .id(entity.getId())
                .data(entity.getData())
                .horarioInicio(entity.getHorarioInicio())
                .horarioFim(entity.getHorarioFim())
                .geral(toGeralResponse(entity.getGeral()))
                .oficina(toOficinaResponse(entity.getOficinaControle()))
                .cardapio(toCardapioResponse(entity.getCardapioControle()))
                .bebida(toBebidaResponse(entity.getBebida()))
                .decoracao(toDecoracaoResponse(entity.getDecoracao()))
                .extra(toExtraResponse(entity.getExtra()))
                .idContrato(entity.getContrato() != null ? entity.getContrato().getId() : null)
                .build();
    }

    public OficinaControle toOficinaEntity(Long oficinaId) {
        if (oficinaId == null) {
            return null;
        }

        return oficinaRepository.findById(oficinaId)
                .map(oficina -> new OficinaControle(oficina.getId(), oficina.getDescricao()))
                .orElse(null);
    }

    public OficinaResponseDTO toOficinaResponse(OficinaControle entity) {
        if (entity == null) {
            return null;
        }

        if (entity.getIdOficina() == null) {
            return null;
        }

        return oficinaRepository.findById(entity.getIdOficina())
                .map(oficina -> new OficinaResponseDTO(oficina.getId(), oficina.getDescricao()))
                .orElseThrow(() -> new ServiceException("Oficina não encontrada"));
    }

    public Geral toGeralEntity(GeralRequestDTO dto) {
        if (dto == null) return null;
        Geral geral = new Geral();
        geral.setAutorizadoPostarFotos(dto.isAutorizadoPostarFotos());
        geral.setInstagram(dto.getInstagram());
        geral.setResponsavel(dto.getResponsavel());
        geral.setTelefone(dto.getTelefone());
        geral.setAniversariante(dto.getAniversariante());
        geral.setIdadeAniversariante(dto.getIdadeAniversariante());
        geral.setNumeroAdultos(dto.getNumeroAdultos());
        geral.setCriancas1a3(dto.getCriancas1a3());
        geral.setCriancas4a7(dto.getCriancas4a7());
        geral.setCriancas8a12(dto.getCriancas8a12());
        geral.setAdultosPresentes(dto.getAdultosPresentes());
        geral.setCriancasPresentes(dto.getCriancasPresentes());
        return geral;
    }

    public GeralResponseDTO toGeralResponse(Geral entity) {
        if (entity == null) return null;
        return GeralResponseDTO.builder()
                .autorizadoPostarFotos(entity.isAutorizadoPostarFotos())
                .instagram(entity.getInstagram())
                .responsavel(entity.getResponsavel())
                .telefone(entity.getTelefone())
                .aniversariante(entity.getAniversariante())
                .idadeAniversariante(entity.getIdadeAniversariante())
                .numeroAdultos(entity.getNumeroAdultos())
                .criancas1a3(entity.getCriancas1a3())
                .criancas4a7(entity.getCriancas4a7())
                .criancas8a12(entity.getCriancas8a12())
                .adultosPresentes(entity.getAdultosPresentes())
                .criancasPresentes(entity.getCriancasPresentes())
                .build();
    }

    public CardapioControle toCardapioEntity(CardapioControleRequestDTO dto) {
        if (dto == null) return null;

        if (dto.getIdCardapio() == null) {
            return null;
        }
        CardapioResponseDTO cardapioResponseDTO = cardapioRepository.findById(dto.getIdCardapio())
                .map(cardapio -> new CardapioResponseDTO(cardapio.getId(), cardapio.getDescricao()))
                .orElseThrow(() -> new ServiceException("Cardápio não encontrado"));

        CardapioControle cardapioControle = new CardapioControle();
        cardapioControle.setIdCardapio(dto.getIdCardapio());
        cardapioControle.setDescricaoCardapio(cardapioResponseDTO != null ? cardapioResponseDTO.getDescricao() : null);
        cardapioControle.setAlteracaoCardapio(dto.getAlteracaoCardapio());
        cardapioControle.setAdicionais(dto.getAdicionais());
        cardapioControle.setBolo(dto.getBolo());
        cardapioControle.setLanche(dto.getLanche());
        return cardapioControle;
    }

    public CardapioControleResponseDTO toCardapioResponse(CardapioControle entity) {
        if (entity == null) return null;
        return CardapioControleResponseDTO.builder()
                .idCardapio(entity.getIdCardapio())
                .descricaoCardapio(entity.getDescricaoCardapio())
                .alteracaoCardapio(entity.getAlteracaoCardapio())
                .adicionais(entity.getAdicionais())
                .bolo(entity.getBolo())
                .lanche(entity.getLanche())
                .build();
    }

    public BebidaControle toBebidaEntity(BebidaCadastroRequestDTO dto) {
        if (dto == null) return null;

        if (dto.getIdBebida() == null) {
            return null;
        }

        BebidaCadastro bebidaCadastro = bebidaCadastroRepository.findById(dto.getIdBebida())
                .map(bebida -> new BebidaCadastro(bebida.getIdBebida(), bebida.getDescricao()))
                .orElseThrow(() -> new ServiceException("Bebida não encontrada"));

        BebidaControle bebida = new BebidaControle();
        bebida.setIdBebida(dto.getIdBebida());
        bebida.setDescricao(bebidaCadastro != null ? bebidaCadastro.getDescricao() : null);
        bebida.setLitrosChopp(dto.getLitrosChopp());
        bebida.setConsignado(dto.isConsignado());

        return bebida;
    }

    public BebidaResponseDTO toBebidaResponse(BebidaControle entity) {
        if (entity == null) return null;

        return BebidaResponseDTO.builder()
                .idBebida(entity.getIdBebida())
                .descricao(entity.getDescricao())
                .litrosChopp(entity.getLitrosChopp())
                .consignado(entity.isConsignado())
                .build();
    }


    public Decoracao toDecoracaoEntity(DecoracaoRequestDTO dto) {
        if (dto == null) return null;

        if (dto.getTemaId() == null) {
            return null;
        }
        TemaControleResponseDTO temaControleResponseDTO = temaRepository.findById(dto.getTemaId())
                .map(tema -> new TemaControleResponseDTO(tema.getId(), tema.getDescricao()))
                .orElseThrow(() -> new ServiceException("Tema não encontrado"));

        return getDecoracao(dto, temaControleResponseDTO);
    }

    private static Decoracao getDecoracao(DecoracaoRequestDTO dto, TemaControleResponseDTO temaControleResponseDTO) {
        Decoracao decoracao = new Decoracao();
        decoracao.setTemaId(dto.getTemaId());
        decoracao.setTemaDescricao(temaControleResponseDTO != null ? temaControleResponseDTO.getDescricao() : null);
        decoracao.setPapelaria(dto.getPapelaria());
        decoracao.setDoces(dto.getDoces());
        decoracao.setToalha(dto.getToalha());
        decoracao.setArranjoFlor(dto.isArranjoFlor());
        decoracao.setLed(dto.isLed());
        decoracao.setEscultura(dto.isEscultura());
        decoracao.setBaloes(dto.isBaloes());
        decoracao.setCorBaloes(dto.getCorBaloes());
        return decoracao;
    }

    public DecoracaoResponseDTO toDecoracaoResponse(Decoracao entity) {
        if (entity == null) return null;
        return DecoracaoResponseDTO.builder()
                .temaId(entity.getTemaId())
                .temaDescricao(entity.getTemaDescricao())
                .papelaria(entity.getPapelaria())
                .doces(entity.getDoces())
                .toalha(entity.getToalha())
                .arranjoFlor(entity.isArranjoFlor())
                .led(entity.isLed())
                .escultura(entity.isEscultura())
                .baloes(entity.isBaloes())
                .corBaloes(entity.getCorBaloes())
                .build();
    }

    public Extra toExtraEntity(ExtraRequestDTO dto) {
        if (dto == null) return null;
        Extra extra = new Extra();
        extra.setPersonagem(dto.getPersonagem());
        extra.setFotografo(dto.getFotografo());
        extra.setRetrospectiva(dto.getRetrospectiva());
        extra.setObservacoes(dto.getObservacoes());
        return extra;
    }

    public ExtraResponseDTO toExtraResponse(Extra entity) {
        if (entity == null) return null;
        return ExtraResponseDTO.builder()
                .personagem(entity.getPersonagem())
                .fotografo(entity.getFotografo())
                .retrospectiva(entity.getRetrospectiva())
                .observacoes(entity.getObservacoes())
                .build();
    }
}
