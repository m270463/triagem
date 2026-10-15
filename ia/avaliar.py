import json
import time
import urllib.request
from pathlib import Path

URL = "http://localhost:8000/classificar"
SEED = Path(__file__).resolve().parent.parent / "dados" / "mensagens_seed.json"
CAMPOS = ["categoria", "prioridade", "sentimento"]


def classificar(texto: str) -> dict:
    req = urllib.request.Request(
        URL,
        data=json.dumps({"texto": texto}).encode("utf-8"),
        headers={"Content-Type": "application/json"},
    )
    with urllib.request.urlopen(req, timeout=30) as resp:
        return json.load(resp)


def main():
    mensagens = json.loads(SEED.read_text(encoding="utf-8"))
    acertos = {c: 0 for c in CAMPOS}
    falhas = 0

    for i, m in enumerate(mensagens, 1):
        try:
            r = classificar(m["texto"])
        except Exception as e:
            falhas += 1
            print(f"{i:02d} falha na chamada: {e}")
            continue

        diferencas = []
        for c in CAMPOS:
            if r[c] == m["esperado"][c]:
                acertos[c] += 1
            else:
                diferencas.append(f"{c}: esperado={m['esperado'][c]} obtido={r[c]}")
        if diferencas:
            print(f"{i:02d} {m['texto'][:60]!r}\n     " + "; ".join(diferencas))
        time.sleep(0.5)  # respeita o limite da faixa gratuita

    total = len(mensagens) - falhas
    print(f"\nMensagens avaliadas: {total} (falhas de chamada: {falhas})")
    for c in CAMPOS:
        pct = 100 * acertos[c] / total if total else 0
        print(f"{c:<11} {acertos[c]}/{total} ({pct:.0f}%)")


if __name__ == "__main__":
    main()