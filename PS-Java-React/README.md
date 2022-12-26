# Processo Seletivo Java


## Backend da api

Para documentar a api eu usei o swagger, o link da documentação pode ser encontrado em:


http://localhost:8080/swagger-ui/index.html#/


- Pelo swagger pode-se visualizar a documentação e, é possível realizar alguns testes



- A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.

Endpoint : http://localhost:8080/api/banco/conta/1


- Caso não seja informado nenhum filtro, retornar  todos os dados de transferência.

Endpoint : http://localhost:8080/api/banco/transferencia/buscarTransferenciaPorConta/1?sort=asc



- Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.

Endpoint : http://localhost:8080/api/banco/transferencia/buscarTransferenciaPorPeriodo/1/inicio/2019-01-01/fim/2020-06-08?sort=asc


- Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.

 Endpoint : http://localhost:8080/api/banco/transferencia/buscarPorOperador/beltrano?sort=asc


- Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.

Endpoint :  http://localhost:8080/api/banco/transferencia/buscarPorFiltro/beltrano/inicio/2019-05-04/fim/2020-06-08?sort=asc


