# 📚 LiterAlura - Catálogo de Livros e Autores em Java com Spring Boot

Este projeto é um catálogo de livros e autores, desenvolvido em Java com o framework Spring Boot. Ele opera como uma aplicação de console, focada na busca de dados de uma API externa (Gutendex), persistência desses dados em um banco de dados e apresentação das informações ao usuário através de um menu interativo.

---

## 📌 Sobre o Projeto

O **LiterAlura** foi desenvolvido com o objetivo de praticar e consolidar conhecimentos em:

* **Consumo de APIs externas**: Integração com a **Gutendex API** para buscar informações sobre livros e seus autores.
* **Persistência de dados**: Utilização do **Spring Data JPA** para armazenar e gerenciar dados de `Livro` e `Autor` em um banco de dados (configurável, como H2 ou PostgreSQL).
* **Modelagem de dados**: Criação de entidades (`Livro`, `Autor`) e Data Transfer Objects (DTOs/Records) para mapeamento dos dados da API.
* **Interação via console**: Desenvolvimento de um menu de usuário para realizar buscas, listagens e filtros.
* **Tratamento de exceções**: Lidar com entradas inválidas do usuário e possíveis erros na comunicação com a API.
* **Serialização e desserialização de dados (JSON)**: Utilização de Jackson para mapear JSON da API Gutendex para objetos Java.
* **Consultas personalizadas**: Implementação de queries específicas para buscar autores vivos em determinado ano ou livros por idioma.

---

## 🚀 Tecnologias Utilizadas

<div>
 <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
 <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
 <img src="https://img.shields.io/badge/H2_Database-2DB2DD?style=for-the-badge&logo=h2&logoColor=white">
</div>

---

## 🧱 Estrutura do Projeto

````
📁 LiterAlura/
├── 📁 .idea/                      (Arquivos de configuração da IDE)
├── 📁 .mvn/                       (Arquivos do Maven Wrapper)
├── 📁 src/
│   └── 📁 main/
│       └── 📁 java/
│           └── 📁 br/
│               └── 📁 com/
│                   └── 📁 alura/
│                       └── 📁 LiterAlura/
│                           ├── 📁 Principal/            (Classe Principal com o menu de interação do console)
│                           ├── 📁 model/                (Classes de modelo de dados: Autor, Livro, e Records para DTOs da API)
│                           ├── 📁 repository/           (Interfaces de repositório Spring Data JPA para Autor e Livro)
│                           ├── 📁 service/              (Classes de serviço e utilitários: ConsumoAPI, ConverteDados, IConverteDados)
│                           └── 📄 LiterAluraApplication.java (Classe principal da aplicação Spring Boot)
│       └── 📁 resources/
│           └── 📄 application.properties (Configurações do banco de dados)
├── 📄 .gitignore
├── 📄 LiterAlura.iml              (Arquivo de módulo da IDE)
├── 📄 pom.xml                     (Configurações de dependências Maven)
└── 📄 README.md
````

---

## ⚙️ Como Usar

Para executar este projeto em sua máquina local, siga os passos abaixo:

1.  **Pré-requisitos**:
    * Java 17 ou superior.
    * Maven.
    * Uma IDE (IntelliJ IDEA, Eclipse, VS Code com plugins Java).
    * (Opcional) PostgreSQL ou outro banco de dados relacional. Se não usar, o Spring Boot configurará um H2 em memória por padrão.

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/AbnerVital/LiterAlura.git]
    ```

3.  **Navegue até o diretório do projeto:**
    ```bash
    cd LiterAlura
    ```

4.  **Configuração do Banco de Dados (Opcional, se não usar H2 em memória)**:
    * Abra o arquivo `src/main/resources/application.properties`.
    * Configure as propriedades do seu banco de dados (ex: PostgreSQL):
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        spring.jpa.hibernate.ddl-auto=update # Ou 'create', 'create-drop' conforme sua necessidade
        spring.jpa.show-sql=true
        ```
    * A API Gutendex não requer chave de API, então não há configuração adicional para isso.

5.  **Compile e execute o projeto:**

    * **Via IDE (Recomendado)**:
        1.  Abra o projeto na sua IDE (ex: IntelliJ IDEA -> `File` -> `Open` -> selecione a pasta do projeto).
        2.  Aguarde a IDE resolver as dependências do Maven.
        3.  Execute a classe `LiterAluraApplication.java`.

    * **Via Terminal (com Maven)**:
        ```bash
        mvn spring-boot:run
        ```

6.  **Interação**:
    * Após a execução, um menu interativo será exibido no console. Siga as opções para buscar livros, listar livros e autores registrados, e filtrar autores por ano ou livros por idioma.

---

## 📈 Melhorias Futuras

* Implementar testes unitários para os serviços e repositórios.
* Adicionar mais opções de filtro e ordenação para livros e autores.
* Melhorar o tratamento de erros para entradas inválidas do usuário e respostas da API.
* Implementar paginação para listagens grandes de livros e autores.
* Considerar uma interface gráfica (JavaFX, Swing) ou até mesmo uma API RESTful para o projeto, se a complexidade justificar.

---

## 👨‍💻 Autor

| [<img src="https://avatars.githubusercontent.com/u/102125924?v=4" width=115><br><sub>Abner Vital</sub>](https://github.com/AbnerVital) |
| :------------------------------------------------------------------------------------------------------------------------------------: |

-----

## 📫 Contato

  * **LinkedIn**: [@Abner Vital](https://www.linkedin.com/in/abner-vital-233730141/)
  * **GitHub**: [@AbnerVital](https://github.com/AbnerVital)

-----

> Desenvolvido durante os estudos na [Alura](https://www.alura.com.br/)

-----

