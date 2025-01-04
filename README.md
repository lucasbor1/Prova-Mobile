# Aplicativo Android - Prova

Este projeto consiste na criação de um aplicativo Android, desenvolvido em Java, que consome uma API de conselhos aleatórios, armazena os dados recebidos em um banco de dados SQLite e exibe as informações para o usuário.

## Funcionalidades

1. **Consumo de WebService:**
   - O aplicativo utiliza a API [Advice Slip](https://api.adviceslip.com/advice) para obter conselhos aleatórios.
   - Cada solicitação retorna um JSON contendo o conselho e um identificador único.

2. **Armazenamento de Dados:**
   - Os dados recebidos da API são armazenados em um banco de dados SQLite local.
   - O nome do banco e a tabela são definidos de acordo com a estrutura dos dados recebidos.

3. **Interface do Usuário:**
   - Exibe os conselhos armazenados em uma lista.
   - Possibilidade de solicitar novos conselhos e atualizar a interface.

### Banco de Dados SQLite
- A estrutura do banco de dados é baseada no JSON retornado pela API:
  ```json
  {
      "slip": {
          "id": 123,
          "advice": "Always keep learning."
      }
  }
  ```
- Tabela no SQLite:
  - Nome da Tabela: `Advice`
  - Colunas:
    - `id` (INTEGER, chave primária)
    - `advice` (TEXT)

## Como Executar o Projeto

1. Clone o repositório para sua máquina local.
2. Abra o projeto no Android Studio.
3. Certifique-se de que o emulador ou dispositivo conectado tenha acesso à internet.
4. Compile e execute o projeto.

## Dependências e Ferramentas
- **Linguagem:** Java
- **Banco de Dados:** SQLite
- **API:** [Advice Slip API](https://api.adviceslip.com/advice)
- **SDK:** Min SDK 27, Target SDK 34

## Screenshots

### Tela Inicial
![Tela Inicial](https://github.com/user-attachments/assets/34b7d811-9a4a-411d-ac8b-928b9f2829ac)

### Buscar Conselho
![Buscar Conselho](https://github.com/user-attachments/assets/e95cbd4c-e8c7-46d4-9eac-16cbbaef2e0b)

### Salvar Conselho / Buscar Conselho
![Salvar Conselho / Buscar Conselho](https://github.com/user-attachments/assets/9f624bf0-c744-4ae4-aeb2-14c7ed888ef3)

### Lista de Conselhos
![Lista de Conselhos](https://github.com/user-attachments/assets/18b5871d-02ea-4f08-aaae-9158a6fbf12a)

### Deletar Conselho
![Deletar Conselho](https://github.com/user-attachments/assets/d2949581-bb2c-47b4-aa04-758d09926e1c)

## Licença
Este projeto é destinado exclusivamente para fins acadêmicos.

