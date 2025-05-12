package com.brothers.festas.controller;

import com.brothers.festas.service.ContratoPdfService; // Corrigido para o pacote correto
import com.brothers.festas.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http:localhost:3000/")
@RestController
@RequestMapping("/whatsapp")
public class WhatssAppController {

    @Autowired
    private ContratoPdfService pdfService; // Correto, sem necessidade de declarar a classe aqui

    @Autowired
    private WhatsappService whatsappService;

    @PostMapping("/enviar")
    public String gerarEEnviarContrato(@RequestParam String nomeCliente,
                                       @RequestParam String descricaoContrato,
                                       @RequestParam String numeroWhatsapp) {
        byte[] pdf = pdfService.gerarContratoPdf(nomeCliente, descricaoContrato);
        whatsappService.enviarContratoViaWhatsapp(numeroWhatsapp, pdf, "Contrato_" + nomeCliente + ".pdf");

        return "Contrato enviado com sucesso!";
    }
}
