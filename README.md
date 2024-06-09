## Ms avaliador de creditor 

## Usagem/Exemplos

Realizar avaliacao do cliente

Method: POST
```
  http://localhost:8080/avaliacoes-credito
```
Body (raw)
```JSON
{
    "cpf": "xxx.xxx.xxx-xx",
    "renda": 5000.0,
    "limiteBasico": 150.0
}
```
Pegar a situacao do cliente

Method: GET
```
http://localhost:8080/avaliacoes-credito/situacao-cliente?cpf=xxx.xxx.xxx-xx
```
Body (raw)
```JSON
{
    "cliente": {
        "id": 6,
        "nome": "Pedro henrique",
        "idade": 18
    },
    "cartoes": []
}
```
Solicitar um cartao

Method: POST
```
http://localhost:8080/solicitacao-cartao
```
Body (pretty)
```JSON
{
    "id": 1,
    "cpf": "1041.834.829-21",
    "endereco": "rua iguatemi n 160",
    "limiteLiberado":  9000.00
}
```

Body (raw)
```JSON
{
    "protocolo": "54sd4asd45afeieow"
}
```