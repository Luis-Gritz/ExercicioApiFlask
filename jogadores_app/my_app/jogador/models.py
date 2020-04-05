from my_app import db

class Jogador(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nome = db.Column(db.String(100))
    nickName = db.Column(db.String(100))
    nomeDoTime = db.Column(db.String(100))
    role = db.Column(db.String(100))
    totalDeAbatimentos = db.Column(db.Integer)
    totalDeAssistencias = db.Column(db.Integer)
    totalDeMortes = db.Column(db.Integer)
    totalDePartidasJogadas = db.Column(db.Integer)
    totalDeVitorias = db.Column(db.Integer)
    kda = db.Column(db.Float(asdecimal=True))
    porcentagemVitorias = (db.Float(asdecimal=True))


    def __init__(self,nome,nickName,nomeDoTime,role,totalDeAbatimentos,totalDeAssistencias,totalDeMortes,totalDePartidasJogadas,totalDeVitorias,kda,porcentagemVitorias):
        self.nome = nome
        self.nickName = nickName
        self.nomeDoTime = nomeDoTime
        self.role = role
        self.totalDeAbatimentos = totalDeAbatimentos
        self.totalDeAssistencias = totalDeAssistencias
        self.totalDeMortes = totalDeMortes
        self.totalDePartidasJogadas = totalDePartidasJogadas
        self.totalDeVitorias = totalDeVitorias
        self.kda = kda
        self.porcentagemVitorias = porcentagemVitorias



    def __repr__(self):
        return 'Jogador {0}'.format(self.id)
