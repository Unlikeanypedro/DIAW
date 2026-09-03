# ClimaAPI

API REST desenvolvida com Spring Boot para consultar o clima de uma cidade utilizando as APIs da [Open-Meteo](https://open-meteo.com/).

## Tecnologias e Dependências

- Java
- Spring Boot
- Maven

## Endpoints

- `GET /clima` - retorna o clima atual de Belo Horizonte
- `GET /clima/{cidade}` - retorna o clima atual de uma cidade

> [!NOTE]
> Utilize **+** no lugar dos **espaços** (ex: `/clima/Belo+Horizonte`)
