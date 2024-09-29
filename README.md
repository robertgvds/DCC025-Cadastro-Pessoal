# Cadastro Pessoal

Este projeto é um trabalho da disciplina de **Orientação a Objetos** (DCC025) da UFJF. O objetivo é desenvolver um sistema de cadastro pessoal que permite aos usuários registrar e gerenciar informações de forma eficiente e organizada.

## Funcionalidades

- Cadastro de usuários com validação de dados
- Edição e remoção de registros
- Exibição de lista de usuários cadastrados
- Busca por registros específicos

## Tecnologias Utilizadas

- Java
- Swing para a interface gráfica
- Estruturas de dados para gerenciar os cadastros

![Captura de tela 2024-09-29 002536](https://github.com/user-attachments/assets/3b9595d4-7e46-4a90-81d8-b529c88f3f71)

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
DCC025-Cadastro-Pessoal/
├── src/br/ufjf/robert/
│   ├── Main.java
│   ├── controler/
│   ├── exceptions/
│   ├── model/
│   └── view/
└── README.md
![Captura de tela 2024-09-29 002536](https://github.com/user-attachments/assets/f803c63e-c172-4bf6-9272-49d66de73749)
```

## Como Executar o Projeto

1. Clone o repositório:
```
   git clone https://github.com/robertgvds/DCC025-Cadastro-Pessoal.git
```

2. Navegue até o diretório do projeto:
```
  cd DCC025-Cadastro-Pessoal
```

3. Build
```
   -> mvn clean install
```

4. Run
```
-> java -jar target/app-1-jar-with-dependencies.jar.
```

## Download
É necessário alguns recursos em sua máquina: - JDK 17 - Apache Maven 3.6.3 ou superior

Sobre JDK:
- Pode ser instalado diretamente pelo site da [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

Sobre o Maven:
- Pode ser instalado diretamente pelo site da [Maven](https://maven.apache.org/download.cgi).

## Instalação
Para o JDK, é necesário seguir os passos do executável e adicionar o caminho do diretório \bin nas variáveis de ambiente Path
Para o Maven, é preciso mover o conteúdo do zip para Arquivos de Programas e em seguida copiar o caminho do diretório \bin e adicionar as variáveis de ambiente Path

## Referências
Download e Instalação do JDK e Maven
