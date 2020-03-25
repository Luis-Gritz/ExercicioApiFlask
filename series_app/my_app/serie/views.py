import json
from flask import Blueprint, abort
from flask_restful import Resource, reqparse
from my_app.serie.models import Serie
from my_app import api, db

serie = Blueprint('serie',__name__)

parser = reqparse.RequestParser()
parser.add_argument('titulo',type=str)
parser.add_argument('genero',type=str)
parser.add_argument('totalDeTemporadas',type=int)
parser.add_argument('mediaNoIMDB',type=float)
parser.add_argument('situacao',type=str)


@serie.route("/")
@serie.route("/home")
def home():
    return "Catálogo de Séries"

class SerieAPI(Resource):
    def get(self,id=None,page=1):
        if not id:
            series = Serie.query.paginate(page,10).items
        else:
            series = [Serie.query.get(id)]
        if not series:
            abort(404)
        res = {}
        for se in series:
            res[se.id] = {
                'titulo' : se.titulo,
                'genero' : se.genero,
                'totalDeTemporadas' : se.totalDeTemporadas,
                'mediaNoIMDB' : str(se.mediaNoIMDB),
                'situacao' : se.situacao
            }
        return json.dumps(res)

    def post(self):
        args = parser.parse_args()
        titulo = args['titulo']
        genero = args['genero']
        totalDeTemporadas = args['totalDeTemporadas']
        mediaNoIMDB = args['mediaNoIMDB']
        situacao = args['situacao']


        se = Serie(titulo,genero,totalDeTemporadas,mediaNoIMDB,situacao)
        db.session.add(se)
        db.session.commit()
        res = {}
        res[se.id] = {
            'titulo' : se.titulo,
            'genero' : se.genero,
            'totalDeTemporadas' : se.totalDeTemporadas,
            'mediaNoIMDB' : str(se.mediaNoIMDB),
            'situacao' : se.situacao
        }
        return json.dumps(res)

api.add_resource(
    SerieAPI,
    '/api/serie',
    '/api/serie/<int:id>',
    '/api/serie/<int:id>/<int:page>'
)


        
