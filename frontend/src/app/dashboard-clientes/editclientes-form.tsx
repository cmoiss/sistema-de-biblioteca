import { updateBook } from "@/api-consumer/livro-consumer";
// import Gender from "@/components/home/create-livro/genero";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
// import { Textarea } from "@/components/ui/textarea";
import { Book } from "@/models/book";
import { FormLivroSchema, RequestLivroType } from "@/models/livro-schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";

interface EditBookProps {
  book: Book;
  onSuccess?: () => void;
}

export default function EditBookForm({ book, onSuccess }: EditBookProps) {
  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm<RequestLivroType>({
    resolver: zodResolver(FormLivroSchema),
    defaultValues: {
      titulo: book.titulo,
      autor: book.autor,
      sinopse: book.sinopse,
      isbn: book.isbn,
      quantidade: book.quantidadeTotal
    }
  });

  const submit = (updatedBook: RequestLivroType) => {
    updateBook(book.id, updatedBook);
    if (onSuccess) onSuccess();
  };

  return (
    <div>
      <form onSubmit={handleSubmit(submit)} id="create-livro-form">
        <div className="flex flex-col gap-4">
          <div className="flex flex-col gap-2">
            <Label htmlFor="titulo">Nome</Label>
            <Input id="titulo" {...register("titulo")} />
            {errors.titulo && (
              <p className="text-red-500 mb-2">{errors.titulo.message}</p>
            )}
          </div>

          <div className="grid grid-cols-5 gap-3 items-center">
            <Label htmlFor="isbn" className="text-right">
              CPF
            </Label>
            <Input id="isbn" className="col-span-3" {...register("isbn")} />
            {errors.isbn && (
              <p className="text-red-500 col-span-5 mb-2">
                {errors.isbn.message}
              </p>
            )}
          </div>

          <div className="flex flex-col gap-2">
            <Label htmlFor="autor">E-mail</Label>
            <Input id="autor" {...register("autor")} />
            {errors.autor && (
              <p className="text-red-500 mb-2">{errors.autor.message}</p>
            )}
          </div>
          
          <div className="flex flex-col gap-2">
            <Label htmlFor="autor">Senha</Label>
            <Input id="autor" {...register("autor")} />
            {errors.autor && (
              <p className="text-red-500 mb-2">{errors.autor.message}</p>
            )}
          </div>



        </div>
      </form>
    </div>
  );
}
