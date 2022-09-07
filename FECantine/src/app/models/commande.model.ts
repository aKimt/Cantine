export interface Commande{
    id: any,
    user: string,
    bur: string,
    date: Date,
    nom: string,
    prenom: string,
    boissons: CmdProduit[],
    platsChauds: CmdProduit[],
    sandwichs: CmdSandwich[]
  }

interface CmdProduit{
    nom: string,
    qte: number
}

interface CmdSandwich extends CmdProduit{
    supplement: string[],
    moins: string[]
}