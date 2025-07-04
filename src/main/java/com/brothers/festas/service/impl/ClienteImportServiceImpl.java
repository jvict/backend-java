package com.brothers.festas.service.impl;

import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.service.IClienteImportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brothers.festas.repository.ClienteRepository;

import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteImportServiceImpl implements IClienteImportService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void importarExcel(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cliente cliente = new Cliente();
                cliente.setNome(getCell(row, 1));
                cliente.setCelular(getCell(row, 2));
                cliente.setEmail(getCell(row, 3));

                Cell dataCadastroCell = row.getCell(4);
                if (dataCadastroCell != null && DateUtil.isCellDateFormatted(dataCadastroCell)) {
                    Date date = dataCadastroCell.getDateCellValue();
                    cliente.setDataCadastro(
                            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay()
                    );
                }

                // --- Documento (Coluna 5) ---
                String documentoFormatado = null; // Inicializa como null para controle de fluxo
                String documentoRaw = getCell(row, 5); // <--- AQUI! Lendo a célula da coluna 5

                // Somente processa se o valor bruto não for nulo e não for uma string vazia (apenas espaços)
                if (documentoRaw != null && !documentoRaw.trim().isEmpty()) {
                    String documentoLimpo = documentoRaw.replaceAll("[^\\d]", ""); // Remove não-dígitos

                    if (documentoLimpo.length() == 11) { // Provavelmente um CPF
                        documentoFormatado = formatCPF(documentoLimpo);
                    } else if (documentoLimpo.length() >= 9 && documentoLimpo.length() <= 10) { // Provavelmente um RG
                        // Preenche com zero à esquerda se tiver menos de 9 dígitos
                        while (documentoLimpo.length() < 9) {
                            documentoLimpo = "0" + documentoLimpo;
                        }
                        documentoFormatado = formatRG(documentoLimpo);
                    } else if (documentoLimpo.length() < 9) { // Caso seja um número curto que não se encaixa nos padrões
                        while (documentoLimpo.length() < 9) {
                            documentoLimpo = "0" + documentoLimpo;
                        }
                        documentoFormatado = documentoLimpo; // Mantém o valor limpo e preenchido
                    } else {
                        documentoFormatado = documentoLimpo; // Se não se encaixa nos padrões, mas tem dígitos, mantém limpo
                    }
                }

                // **VERIFICAÇÃO PRINCIPAL: PULAR A LINHA SE O DOCUMENTO (da coluna 5) ESTIVER VAZIO OU INVÁLIDO**
                if (documentoFormatado == null || documentoFormatado.trim().isEmpty()) {
                    System.out.println("Aviso: Cliente na linha " + (row.getRowNum() + 1) + " não possui um documento válido (coluna 5). Pulando esta linha.");
                    continue; // Pula para a próxima iteração do loop, ignorando esta linha
                }

                // **NOVA VERIFICAÇÃO: Pular se o documento já existe no banco de dados**
                Optional<Cliente> clienteExistente = clienteRepository.findByDocumento(documentoFormatado);
                if (clienteExistente.isPresent()) {
                    System.out.println("Aviso: Cliente com documento '" + documentoFormatado + "' na linha " + (row.getRowNum() + 1) + " já existe no banco de dados. Pulando esta linha.");
                    continue;
                }

                cliente.setDocumento(documentoFormatado); // Define o documento APENAS se ele for válido
                // --- Fim Documento ---

                //cliente.setDocumento(getCell(row, 5));
                cliente.setCep(getCell(row, 6));
                cliente.setEndereco(getCell(row, 7));
                cliente.setNumero(getCell(row, 8));
                cliente.setComplemento(getCell(row, 9));
                cliente.setBairro(getCell(row, 10));
                cliente.setCidade(getCell(row, 11));
                cliente.setUf(getCell(row, 12));

                clienteRepository.save(cliente);
            }
        } catch (Exception e) {
            throw new ServiceException("Erro ao importar Excel: " + e.getMessage(), e.getMessage());
        }
    }

    private String getCell(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return null;
    }

    private String formatCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf; // Return as is if not an 11-digit CPF
        }
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    private String formatRG(String rg) {
        if (rg == null || rg.length() < 9) {
            return rg; // Return as is if not at least 9 digits
        }
        // Common RG formats in Brazil can vary, this is a common one for 9 digits
        // e.g., XX.XXX.XXX-X
        if (rg.length() == 9) {
            return rg.substring(0, 2) + "." +
                    rg.substring(2, 5) + "." +
                    rg.substring(5, 8) + "-" +
                    rg.substring(8, 9);
        }
        return rg; // If it's more than 9 digits, return it as is or implement more complex logic
    }

}


