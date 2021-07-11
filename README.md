# LYRA

## Definições
	O estilo de arquitetura implementado no sistema é orientado a eventos e microserviço. Foi escolhido a aborgadem de um sistema Coreógrafo. Para definir os componentes do sistema foi submetido ao processo da Workflow.

### Dominio
- Atividade
	- Titulo
	- Descrição
	- Prazo final
	- Lista de documentos(Respostas)
	- Média

- Documento
	- Nome do estudante
	- Situação de plágio
	- Data de submissão
	- Nota

### Eventos
- Após o cadastro, enviar notificações sobre o trabalho para o aluno.
- Quando a correção estiver acabada, iniciar o processo de detecção de plágio.
	- Caso tenha sido detectado plágio anular o trabalho.
	- Caso não, será corrigido e estabelecido o resultado.
- Criar relatório ao final da correção.
- Enviar notificação de submissão, quando o aluno enviar o trabalho.

### Micro serviços e suas responsabilidades
- Professor
	- Criar tarefa
	- Enviar notificação de tarefa cadastrada para o aluno
	- Receber relatorio

- Aluno
	- Enviar resposta
	- Receber notificação de tarefa cadastrada para o aluno

- Analisador
	- Correção das atividades
	- Notificar

### Virtualização
	Foi criado imagens personalizadas para cada serviço que estão descritas em dockerfile e temos um docker-compose que iria gerenciar tudo isso.

### Como usar?
	Para facilitar foi criado um make que faz tudo para você!
	Basta digita "make run", ou então, caso queira ver mais possibilidades make help.

### O Workspace para testes
	O workspace Insomnia foi colocado internamente no projeto, caso queira testar basta importar.
 
