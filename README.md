[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fabsiqueira/spring-boot-app) 
# spring-boot-app
# Spring Security 
1 - Apenas adicionar a dependência spring-security já restringe o acesso

2 - Spring Security permite proteger métodos

   - Não utilizar o Controller para segurança, pois ele só vai proteger aquela rota (brecha na segurança)
   - Utilizar SegurancaService pois ele utilizará em todos os locais que o método for chamado

3- Autenticando usando o Banco de Dados