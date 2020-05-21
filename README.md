# ProjetoIntegradorJava

## Sistema de farmácia

O sistema consiste em um sistema gerenciador de farmácia, onde o administrador vai poder realizar o controle de fermacêuticos, medicamentos e fornecedores.

### Desenvolvedores 
- Victor José de Souza Ledo, 18214290004
- Natan Farias da Silva, 18214290005

### Tecnologias
- Java WEB
- Hibernate
- Maven
- Javascript
- JavaServe Faces (JSF)
- Prime Faces

### Banco de dados 

- MySql

### Para subir as dependencias e rodar o projeto

- Baixar e instalar o maven: https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
- Criar a variavel de ambiente M2_HOME apontar pra pasta extraída do maven
Nas variáveis de ambiente, adicionar $M2_HOME/bin;
---------------
- Configurar o Lombok (Getter e Setter)
 Download do jar: https://projectlombok.org/downloads/lombok-1.18.8.jar
 Colocar na pasta raiz onde está instalado a IDE
 Seguir o tutorial, é bem simples: https://projectlombok.org/setup/eclipse
---------------
- Comando maven pra gerar o war do projeto: mvn clean install
- Lembrar de atualizar o arquivo de configurações da IDE: hibernate.cfg.xml 
 Sempre que adicionar nova entidade
- Configurações do banco de dados
- Tabelas geradas dentro do projeto !
- Mvn build/install para gerar o .war e fazer o deploy no tomcat 7.0