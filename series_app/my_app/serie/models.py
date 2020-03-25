from my_app import db

class Serie(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    titulo = db.Column(db.String(100))
    genero = db.Column(db.String(100))
    totalDeTemporadas = db.Column(db.Integer)
    mediaNoIMDB = db.Column(db.Float(asdecimal=True))
    situacao = db.Column(db.String(100))


    def __init__(self,titulo,genero,totalDeTemporadas,mediaNoIMDB,situacao):
        self.titulo = titulo
        self.genero = genero
        self.totalDeTemporadas = totalDeTemporadas
        self.mediaNoIMDB = mediaNoIMDB
        self.situacao = situacao

    def __repr__(self):
        return 'Serie {0}'.format(self.id)
