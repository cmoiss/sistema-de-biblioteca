"use client";

import { useForm } from "react-hook-form";
import { Input } from "../../ui/input";
import { Label } from "../../ui/label";
import { Textarea } from "../../ui/textarea";
import Gender from "./genero";
import { CreateLivroSchema, LivroType } from "@/models/livro-schema";
import { zodResolver } from "@hookform/resolvers/zod";

export default function CreateLivroForm() {
  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm<LivroType>({
    resolver: zodResolver(CreateLivroSchema)
  });

  const onSubmit = async (data: LivroType) => {
    // await new Promise((resolve) => setTimeout(resolve, 2000)); // Simula um consumo de API
    console.log("Dados do livro entiados:", data);
  };

  return (
    <div>
      <form onSubmit={handleSubmit(onSubmit)} id="create-livro-form">
        <div className="flex flex-col gap-4">
          <div className="flex flex-col gap-2">
            <Label htmlFor="titulo">Título</Label>
            <Input id="titulo" {...register("titulo")} />
            {errors.titulo && (
              <p className="text-red-500 mb-2">{errors.titulo.message}</p>
            )}
          </div>

          <div className="flex flex-col gap-2">
            <Label htmlFor="autor">Autor</Label>
            <Input id="autor" {...register("autor")} />
            {errors.autor && (
              <p className="text-red-500 mb-2">{errors.autor.message}</p>
            )}
          </div>

          <div className="flex flex-col gap-2">
            <Label htmlFor="sinopse">Sinopse</Label>
            <Textarea id="sinopse" {...register("sinopse")} />
            {errors.sinopse && (
              <p className="text-red-500 mb-2">{errors.sinopse.message}</p>
            )}
          </div>

          <div className="flex flex-col">
            <Gender />
          </div>

          <div className="grid grid-cols-5 gap-3 items-center">
            <Label htmlFor="isbn" className="text-right">
              ISBN
            </Label>
            <Input id="isbn" className="col-span-3" {...register("isbn")} />
            {errors.isbn && (
              <p className="text-red-500 col-span-5 mb-2">
                {errors.isbn.message}
              </p>
            )}
          </div>

          <div className="grid grid-cols-5 gap-3 items-center">
            <Label htmlFor="quantidade" className="text-right">
              Quantidade
            </Label>
            <Input
              id="quantidade"
              type="number"
              {...register("quantidade", { valueAsNumber: true })}
            />
            {errors.quantidade && (
              <p className="text-red-500 col-span-5 mb-2">
                {errors.quantidade.message}
              </p>
            )}
          </div>
        </div>
      </form>
    </div>
  );
}
