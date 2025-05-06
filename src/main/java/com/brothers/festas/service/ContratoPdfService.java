package com.brothers.festas.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ContratoPdfService {

    public byte[] gerarContratoPdf(String nomeCliente, String descricaoContrato) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Contrato de Serviço"));
            document.add(new Paragraph("Cliente: " + nomeCliente));
            document.add(new Paragraph("Descrição: " + descricaoContrato));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}
