/*
	O BANCO É CRIADO PELO JPA NO SPRING BOOT AUTOMATICAMENTE, APENAS RODE O CONTAINER, DE USE AGENDA E POPULE O BANCO COM OS DADOS
*/

use agenda;

INSERT INTO tb_clientes 
(cpf, data_nascimento, endereco, nome) 
VALUES 
('525.727.438-43', '2002-10-11', 'Rua Brasilia', 'Matheus'),
('312.512.745-12', '2012-07-07', 'Rua São Paulo', 'Tomas'),
('525.123.438-43', '2007-09-03', 'Rua Rio de Janeiro', 'Juliana'),
('315.727.322-43', '2013-10-26', 'Rua Minas Gerais', 'Fabio');


INSERT INTO tb_contatos (observacao, tipo, valor, cliente_id) VALUES 
('Ligar no período da tarde', 'Telefone', '11976543210', 2),
('', 'E-mail', 'cliente01@email.com', 4),
('Pediu para enviar o orçamento', 'E-mail', 'orcamentos@empresa.com.br', 1),
('Número principal', 'Telefone', '11988887777', 3),
('Contato da esposa', 'Telefone', '11955554444', 2),
('', 'E-mail', 'teste.dev@muralis.com', 1),
('Não atende de manhã', 'Telefone', '21999998888', 4),
('', 'Telefone', '11933332222', 3),
('E-mail secundário', 'E-mail', 'secundario.cli@hotmail.com', 2),
('Retornar semana que vem', 'Telefone', '31987651234', 1),
('', 'E-mail', 'contato@provedor.net', 4),
('Prefere contato via WhatsApp', 'Telefone', '11911112222', 2),
('Caixa postal na primeira tentativa', 'Telefone', '11944445555', 3),
('', 'E-mail', 'usuario_teste_14@gmail.com', 1),
('E-mail corporativo', 'E-mail', 'diretoria@negocios.com', 4),
('Contato muito bom! Recomendo', 'Telefone', '11934567890', 2),
('', 'Telefone', '41999990000', 3),
('Enviar boleto por aqui', 'E-mail', 'financeiro@cliente.com.br', 1),
('Só atende após as 18h', 'Telefone', '11966667777', 4),
('', 'E-mail', 'marketing@vendas.com', 2),
('Telefone fixo da portaria', 'Telefone', '1133334444', 3),
('Reclamou do prazo', 'E-mail', 'reclamacoes@site.com', 1),
('', 'Telefone', '11922223333', 4),
('E-mail pessoal', 'E-mail', 'pessoal.abc@yahoo.com', 2),
('Pediu urgência', 'Telefone', '11900001111', 3),
('', 'E-mail', 'info@sistema.com', 1),
('Número bloqueado anteriormente', 'Telefone', '11977778888', 4),
('Melhor canal de comunicação', 'E-mail', 'comunicacao@agencia.br', 2),
('', 'Telefone', '11955556666', 3),
('Confirmou os dados', 'E-mail', 'cadastro@valido.com', 1);