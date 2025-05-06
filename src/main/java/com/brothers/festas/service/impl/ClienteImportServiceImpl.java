package com.brothers.festas.service.impl;

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

                cliente.setDocumento(getCell(row, 5));
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
            throw new RuntimeException("Erro ao importar Excel: " + e.getMessage(), e);
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
}


