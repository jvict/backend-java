INSERT INTO tb_role(nome) VALUES('ADMIN');
INSERT INTO tb_role(nome) VALUES('USER');

INSERT INTO tb_cliente (nome, celular, email, documento, cep, endereco, numero, complemento, bairro, cidade, uf, status, data_cadastro)
VALUES
    ('Maria Silva', '(11) 98765-4321', 'maria.silva@email.com', '123.456.789-00', '01001-000', 'Rua Augusta', '123', 'Apto 45', 'Centro', 'São Paulo', 'SP', true, '2025-04-25T22:29:32.370691'),
    ('João Oliveira', '(11) 91234-5678', 'joao.oliveira@email.com', '987.654.321-00', '04567-890', 'Avenida Paulista', '1500', 'Sala 303', 'Bela Vista', 'São Paulo', 'SP', true, '2025-04-25T22:30:15.123456'),
    ('Ana Santos', '(21) 99876-5432', 'ana.santos@email.com', '456.789.123-00', '22040-010', 'Rua Barata Ribeiro', '50', 'Bloco B', 'Copacabana', 'Rio de Janeiro', 'RJ', true, '2025-04-25T22:31:05.789012'),
    ('Carlos Ferreira', '(31) 98765-1234', 'carlos.ferreira@email.com', '789.123.456-00', '30130-110', 'Avenida Afonso Pena', '1000', '', 'Centro', 'Belo Horizonte', 'MG', true, '2025-04-25T22:32:47.456789'),
    ('Patrícia Lima', '(41) 99632-8521', 'patricia.lima@email.com', '321.654.987-00', '80010-010', 'Rua XV de Novembro', '700', 'Conjunto 5', 'Centro', 'Curitiba', 'PR', true, '2025-04-25T22:33:22.234567'),
    ('Roberto Almeida', '(51) 98523-7410', 'roberto.almeida@email.com', '654.987.321-00', '90010-270', 'Rua dos Andradas', '500', '', 'Centro Histórico', 'Porto Alegre', 'RS', true, '2025-04-25T22:34:10.345678'),
    ('Fernanda Costa', '(81) 99874-5612', 'fernanda.costa@email.com', '159.753.852-00', '50030-150', 'Avenida Boa Viagem', '3500', 'Apto 1201', 'Boa Viagem', 'Recife', 'PE', true, '2025-04-25T22:35:05.456789'),
    ('Marcelo Souza', '(71) 98745-6321', 'marcelo.souza@email.com', '258.963.741-00', '40010-020', 'Avenida Sete de Setembro', '1500', 'Sala 505', 'Campo Grande', 'Salvador', 'BA', true, '2025-04-25T22:36:18.567890'),
    ('Juliana Martins', '(62) 99856-7412', 'juliana.martins@email.com', '753.159.852-00', '74115-010', 'Avenida 85', '1000', 'Quadra 10', 'Setor Marista', 'Goiânia', 'GO', true, '2025-04-25T22:37:30.678901'),
    ('Lucas Pereira', '(85) 98523-6974', 'lucas.pereira@email.com', '963.852.741-00', '60170-250', 'Avenida Beira Mar', '2500', 'Apto 1802', 'Meireles', 'Fortaleza', 'CE', true, '2025-04-25T22:38:45.789012');
