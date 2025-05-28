import { LivroType } from "@/models/livro-schema";

const URL_API = "http://localhost:8080/livros";

export async function createLivro(livro: LivroType) {
  try {
    const response = await fetch(URL_API, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(livro)
    });

    if (!response.ok) {
      throw Error(`HTTP Erro! Status: ${response.status}`);
    }

    const result: LivroType = await response.json();
    console.log(`Sucess: ${result}`);
    return result;
  } catch (error) {
    console.error(`Erro ao buscar dados: ${error}`);
  }
}
