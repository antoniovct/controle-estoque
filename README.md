![](https://cdn-icons-png.flaticon.com/128/5735/5735178.png)
# CONTROLE DE ESTOQUE 
API REST de um sistema simples de controle de estoque. Contando com as seguintes regras de negócio:

- Inserção/Atualização/Exclusão/Visualização de Produtos.
- Geração e Visualização de pedidos de venda.
- Relatório de estoque, contendo a quantidade total de produtos, bem como o valor total e os produtos que estão com estoque baixo se houver.
- Registro de transação sobre entrada e saída de produtos.
- Criação de usuários do sistema, bem como controle de Autenticação e Autorização.

## Exemplos de como realizar as requisições para a API:
Primeiramente é necessário cadastrar um usuário no sistema, realizando uma requisição do tipo POST para a url `http://localhost:8080/cadastro`, informando os 
seguintes dados:
```
{
    "usuario": "username",
    "senha": "password",
    "tipoUsuario": "ADMIN ou USER"
}
```
O tipo de usuário "USER", tem limitações se comparado ao tipo "ADMIN". Somente o tipo "ADMIN" consegue realizar todas as operações CRUD da API,
o tipo "USER" só consegue listar os produtos, os pedidos de venda e o relatório do estoque.

Logo após cadastrar um usuário, é necessário fazer o login no sistema através da url `http://localhost:8080/login` e informar os dados do usuário(POST):
```
{
    "usuario": "username",
    "senha": "password"
}
``` 
No corpo da resposta da requisição retornará o token necessário para autenticação, onde deverá ser colocado no Header 
de toda requisição.

### Produtos
* Cadastrar produtos(POST):`http://localhost:8080/produto`
````
{
    "descricao":"produto",
    "precoCusto": 0,
    "precoVenda": 0,
    "quantidadeEstoque": 0,
    "categoria": ""
}
````
* Entrada de produtos(POST):`http://localhost:8080/entrada`
```
{
    "codigo": 0,
    "quantidade": 0
}
```
* Listar produtos(GET):`http://localhost:8080/produto`
```
response

[
    {
        "codigo": 1,
        "descricao": "TV",
        "precoCusto": 1000.0,
        "precoVenda": 2000.0,
        "quantidadeEstoque": 500,
        "categoria": "ELETRONICOS"
    },
    {
        "codigo": 2,
        "descricao": "Iphone",
        "precoCusto": 2000.0,
        "precoVenda": 3000.0,
        "quantidadeEstoque": 100,
        "categoria": "CELULARES"
    }
]
```
* Listar produto por código(GET):`http://localhost:8080/produto/1`
```
response

{
    "codigo": 1,
    "descricao": "TV",
    "precoCusto": 1000.0,
    "precoVenda": 2000.0,
    "quantidadeEstoque": 500,
    "categoria": "ELETRONICOS"
}
```
* Atualizar produto(PUT):`http://localhost:8080/produto/1`
```
{
    "quantidadeEstoque": 2000
}
```
* Excluir produto(DELETE):`http://localhost:8080/produto/1`

### Pedido de Venda
* Criar pedido(POST):`http://localhost:8080/pedido-de-venda`
OBS: Não é necessário preencher o corpo da requisição.
* Inserir itens no pedido(POST):`http://localhost:8080/pedido-de-venda/1/produtos/1`
```
{
    "quantidade": 1
}
```
* Listar todos os pedidos de venda(GET):`http://localhost:8080/pedido-de-venda`
```
response

[
    {
        "numero": 1,
        "data": "10/04/2024",
        "produtos": [
            {
                "id": 1,
                "codigoProduto": 1,
                "descricao": "TV",
                "quantidade": 1,
                "valorUnitario": 2000.0,
                "valorTotal": 2000.0
            },
            {
                "id": 2,
                "codigoProduto": 2,
                "descricao": "Iphone",
                "quantidade": 1,
                "valorUnitario": 3000.0,
                "valorTotal": 3000.0
            }
        ],
        "valorTotal": 5000.0
    }
]
```
* Listar pedido de venda por número(GET):`http://localhost:8080/pedido-de-venda/1`
* Atualizar itens do pedido(PUT):`http://localhost:8080/pedido-de-venda/1`
````
{
    "id": 1,
    "quantidade": 400,
    "valorUnitario": 100
}
````
* Excluir pedido de venda(DELETE):`http://localhost:8080/pedido-de-venda/1`
* Excluir item pedido de venda(DELETE):`http://localhost:8080/pedido-de-venda/1/produtos/1`

### Relatório

* Emitir relatório do estoque(GET):`http://localhost:8080/relatorio-estoque`
```
{
    "totalProdutos": 2,
    "valorTotalEstoque": 1295000.0,
    "produtosEstoqueBaixo": [
        {
            "codigo": 2,
            "descricao": "Iphone",
            "precoCusto": 2000.0,
            "precoVenda": 3000.0,
            "quantidadeEstoque": 99,
            "estoqueBaixo": true,
            "categoria": "CELULARES"
        }
    ]
}
```
* Listar transações(GET):`http://localhost:8080/transacao`
```
[
    {
        "id": 1,
        "data": "09/04/2024 21:28:07",
        "codigoProduto": 1,
        "quantidade": 500,
        "tipoTransacao": "ENTRADA"
    },
    {
        "id": 2,
        "data": "10/04/2024 08:56:03",
        "codigoProduto": 2,
        "quantidade": 100,
        "tipoTransacao": "ENTRADA"
    },
    {
        "id": 3,
        "data": "10/04/2024 13:47:33",
        "codigoProduto": 1,
        "quantidade": 1,
        "tipoTransacao": "SAIDA"
    },
    {
        "id": 4,
        "data": "10/04/2024 13:47:38",
        "codigoProduto": 2,
        "quantidade": 1,
        "tipoTransacao": "SAIDA"
    }
]
```






