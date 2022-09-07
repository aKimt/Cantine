const boisson = [{
    "nom": "Coca zéro",
    "prix": 0.55
  },{
    "nom": "Eau pétillante",
    "prix": 0.55
  }];

const commande = [{
    "user": "leralb",
    "bureau": "bar",
    "date": new Date(2022,09,15, 12,30,00),
    "nom": "Leroy",
    "prenom": "Albert",
    "sandwich": [
      {
        "sandwich": "Le mauvais gout",
        "supplement": [
          "oeuf dur",
          "mayonnaise"
        ],
        "moins": [
          "cornichons"
        ]
      }
    ],
    "plats": [
      {
        "plat": "Boulets du chef",
        "qte": "2"
      }
    ]
  }]

const platchaud = [{
    "nom": "Boulets du chef",
    "desc": "2 boulets sauce grenadine",
    "composition": [
      {
        "ingredient": "haché de tortue",
        "qte": 200,
        "unite": "grammes"
      },
      {
        "ingredient": "grenadine",
        "qte": 5,
        "unite": "cl"
      },
      {
        "ingredient": "purée de pomme de terres",
        "qte": 125,
        "unite": "grammes"
      },
      {
        "ingredient": "compote de gingembre",
        "qte": 100,
        "unite": "grammes"
      }
    ],
    "prix": 4,
    "dates": [
      new Date(2022,09,15, 08,30,00),
      new Date(2022,09,15, 12,30,00)
    ]
  }]

const sandwich = [{
    "nom": "Le mauvais gout",
    "desc": "préparation mystère, pour les téméraires",
    "composition": [
      {
        "ingredient": "baguette grise",
        "qte": 0.5,
        "unite": "baguette"
      },
      {
        "ingredient": "confiture de fraise",
        "qte": 25,
        "unite": "grammes"
      },
      {
        "ingredient": "hareng saur",
        "qte": 75,
        "unite": "grammes"
      },
      {
        "ingredient": "fromage de Herve",
        "qte": 60,
        "unite": "grammes"
      },
      {
        "ingredient": "cornichons",
        "qte": 50,
        "unite": "grammes"
      },
      {
        "ingredient": "chocolat noir fondu",
        "qte": 125,
        "unite": "grammes"
      }
    ],
    "prix": 3.05
  },{
    "nom": "Suppléments",
    "desc": "",
    "composition": [
      {
        "ingredient": "salade",
        "qte": 4,
        "unite": "feuilles"
      },
      {
        "ingredient": "oeuf dur",
        "qte": 0.5,
        "unite": "oeufs"
      },
      {
        "ingredient": "mayonnaise",
        "qte": 1,
        "unite": "portion"
      },
      {
        "ingredient": "cornichons",
        "qte": 50,
        "unite": "grammes"
      }
    ],
    "prix": 0
  }]

const user = [{
    "login": "leralb",
    "pwd": "encoreunebiere",
    "nom": "Leroy",
    "prenom": "Albert",
    "bureau": "bar"
  }]

  print('Start #################################################################');

  db.createUser(
    {
        user: "root",
        pwd: "password",
        roles: [
            {
                role: "readWrite",
                db: "mangerDB"
            }
        ]
    }
);

  db.createCollection('boisson');
  db.createCollection('commande');
  db.createCollection('platchaud');
  db.createCollection('sandwich');
  db.createCollection('user');

  try {
    db.boisson.insertMany(boisson);
    db.commande.insertMany(commande);
    db.platchaud.insertMany(platchaud);
    db.sandwich.insertMany(sandwich);
    db.user.insertMany(user);
  }
  catch(e){
    print(e)
  }

  print('END #################################################################');