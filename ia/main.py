from typing import Literal

from fastapi import FastAPI
from pydantic import BaseModel, Field

app = FastAPI(title="Classificador de mensagens")


class Entrada(BaseModel):
    texto: str = Field(min_length=1, max_length=2000)


class Classificacao(BaseModel):
    categoria: Literal["cobranca", "suporte", "cancelamento", "duvida", "elogio", "outro"]
    prioridade: Literal["baixa", "media", "alta", "urgente"]
    sentimento: Literal["positivo", "neutro", "negativo"]
    resumo: str
    sugestao_resposta: str


@app.post("/classificar", response_model=Classificacao)
def classificar(entrada: Entrada) -> Classificacao:
    t = entrada.texto.lower()
    resumo = entrada.texto[:60]

    if "cancel" in t:
        return Classificacao(categoria="cancelamento", prioridade="alta",
                             sentimento="negativo", resumo=resumo,
                             sugestao_resposta="Sentimos muito. Vamos ajudar com o cancelamento.")
    if "cobrad" in t or "fatura" in t or "boleto" in t:
        return Classificacao(categoria="cobranca", prioridade="media",
                             sentimento="negativo", resumo=resumo,
                             sugestao_resposta="Vamos verificar a cobrança e retornamos em breve.")
    if "obrigad" in t or "parabéns" in t:
        return Classificacao(categoria="elogio", prioridade="baixa",
                             sentimento="positivo", resumo=resumo,
                             sugestao_resposta="Obrigado pelo retorno! Ficamos felizes em ajudar.")

    return Classificacao(categoria="suporte", prioridade="media",
                         sentimento="neutro", resumo=resumo,
                         sugestao_resposta="Recebemos sua mensagem e vamos analisar.")