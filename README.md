# ExerciciosApiFlask
series_app - Uma API para registro de informações sobre séries, com os dados:  Titulo da série, Gênero, Total de temporadas, Média no IMDB e Se está ativa.  A API registra novas séries e exibi todas as séries registradas.
 
jogadores_app - Uma API Restful para registrar informações sobre um jogador de E-sports profissional com os dados:Nome do Jogador,Nickname,Nome do time, Role (posição) na qual joga, Total de abatimentos, Total de assistências, Total de mortes, Total de partidas jogadas e Total de vitórias. A API calcula automaticamente:
Proporção de KDA: Total de Assistências e Abatimentos dividido pelo total de morte (K+A/D). Caso o total de mortes seja 0, não dividir.
Porcentagem de vitórias: Total de vitórias dividido pelo total de partidas jogadas multiplicado por 100.
Estes dados devem ser salvos automaticamente pela API e devem ser retornados via JSON.
