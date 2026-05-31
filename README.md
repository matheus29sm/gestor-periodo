## 📖 Descrição

### Gestor Período

Projeto desenvolvido em **Spring Boot** que recebe duas datas(inicial e final) de um período, 
calcula o intervalo de dias entre elas e gera uma distribuição completa por dia da semana.  

As datas podem ser atualizadas dinamicamente, oferecendo flexibilidade para análises temporais 
e facilitando o gerenciamento de períodos de forma prática e eficiente.

---

### 🚀 Passos de Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/matheus29sm/gestor-periodo.git
   ```
   
2. Entre na pasta do projeto:
   ```bash
    cd gestor-periodo
   ```
  
3. Certifique-se de ter Docker e docker-compose instalados.

4. Suba a aplicação:
    ```bash
    docker-compose up --build -d
    ```
    
5. A aplicação estará disponível em:

    👉 [http://localhost:8080](http://localhost:8080)
    
---

### 📚 Documentação

1. Após subir a aplicação, a documentação estará disponível em:

     👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### 🔧 Tecnologias utilizadas
- Java 17
- Maven 3.9.6
- Spring Boot 3.3.3
- SpringDoc OpenAPI 2.6.0
- Docker
- Docker Compose
