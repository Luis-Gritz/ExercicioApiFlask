from my_app import db

class Console(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100))
    year = db.Column(db.Integer)
    price = db.Column(db.Float(asdecimal=True))
    status = db.Column(db.String(100))
    numberGames = db.Column(db.Integer)
    

    def __init__(self,name,year,price,status,numberGames):
        self.name = name
        self.year = year
        self.price = price
        self.status = status
        self.numberGames = numberGames


    def __repr__(self):
        return 'Console {0}'.format(self.id)