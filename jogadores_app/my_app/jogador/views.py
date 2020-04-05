import json
from flask import Blueprint, abort
from flask_restful import Resource, reqparse
from my_app.jogador.models import Jogador
from my_app import api, db

jogador = Blueprint('jogador',__name__)

parser = reqparse.RequestParser()
parser.add_argument('nome',type=str)
parser.add_argument('nickName',type=str)
parser.add_argument('nomeDoTime',type=str)
parser.add_argument('role',type=str)
parser.add_argument('totalDeAbatimentos',type=int)
parser.add_argument('totalDeAssistencias',type=int)
parser.add_argument('totalDeMortes',type=int)
parser.add_argument('totalDePartidasJogadas',type=int)
parser.add_argument('totalDeVitorias',type=int)
parser.add_argument('kda',type=float)
parser.add_argument('porcentagemVitorias',type=float)


@jogador.route("/")
@jogador.route("/home")
def home():
    return "Lista de Jogadores"

class JogadorAPI(Resource):
    def get(self,id=None,page=1):
        if not id:
            jogadores = Jogador.query.paginate(page,10).items
        else:
            jogadores = [Jogador.query.get(id)]
        if not jogadores:
            abort(404)
        res = {}
        for jog in jogadores:
            res[jog.id] = {
                'nome' : jog.nome,
                'nickName' : jog.nickName,
                'nomeDoTime' : jog.nomeDoTime,
                'role' : jog.role,
                'totalDeAbatimentos' : jog.totalDeAbatimentos,
                'totalDeAssistencias' : jog.totalDeAssistencias,
                'totalDeMortes' : jog.totalDeMortes,
                'totalDePartidasJogadas' : jog.totalDePartidasJogadas,
                'totalDeVitorias' : jog.totalDeVitorias,
                'kda' : str(jog.kda),
                'porcentagemVitorias' : str(jog.porcentagemVitorias)
            }
        return json.dumps(res)

    def post(self):
        args = parser.parse_args()
        nome = args['nome']
        nickName = args['nickName']
        nomeDoTime = args['nomeDoTime']
        role = args['role']
        totalDeAbatimentos = args['totalDeAbatimentos']
        totalDeAssistencias = args['totalDeAssistencias']
        totalDeMortes = args['totalDeMortes']
        totalDePartidasJogadas = args['totalDePartidasJogadas']
        totalDeVitorias = args['totalDeVitorias']

        if totalDeMortes > 0:
            kda = (totalDeAssistencias + totalDeAbatimentos)/totalDeMortes
        elif totalDeMortes == 0:
            kda = totalDeAssistencias + totalDeAbatimentos

        porcentagemVitorias = (totalDeVitorias/totalDePartidasJogadas)*100
        
        jog = Jogador(nome,nickName,nomeDoTime,role,totalDeAbatimentos,totalDeAssistencias,totalDeMortes,totalDePartidasJogadas,totalDeVitorias,kda,porcentagemVitorias)
        db.session.add(jog)
        db.session.commit()
        res = {}
        res[jog.id] = {
                'nome' : jog.nome,
                'nickName' : jog.nickName,
                'nomeDoTime' : jog.nomeDoTime,
                'role' : jog.role,
                'totalDeAbatimentos' : jog.totalDeAbatimentos,
                'totalDeAssistencias' : jog.totalDeAssistencias,
                'totalDeMortes' : jog.totalDeMortes,
                'totalDePartidasJogadas' : jog.totalDePartidasJogadas,
                'totalDeVitorias' : jog.totalDeVitorias,
                'kda' : str(jog.kda),
                'porcentagemVitorias' : str(jog.porcentagemVitorias)
        }
        return json.dumps(res)

api.add_resource(
    JogadorAPI,
    '/api/jogador',
    '/api/jogador/<int:id>',
    '/api/jogador/<int:id>/<int:page>'
)


        
