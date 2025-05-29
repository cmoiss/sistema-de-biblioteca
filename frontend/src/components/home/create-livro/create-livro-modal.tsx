import { Button } from "../../ui/button";
import {
  DialogHeader,
  DialogFooter,
  Dialog,
  DialogTrigger,
  DialogContent,
  DialogTitle,
  DialogDescription,
  DialogClose,
} from "../../ui/dialog";
import CreateLivroForm from "./create-livro-form";

export default function CreateLivroModal() {
  return (
    <div>
      <Dialog>
        <DialogTrigger asChild>
          <Button className="cursor-pointer">Cadastrar livro</Button>
        </DialogTrigger>

        <DialogContent className="overflow-y-auto max-h-[90vh]">
          <DialogHeader>
            <DialogTitle>Cadastrar livro</DialogTitle>
            <DialogDescription>
              Insira os dados nos campos abaixo.
            </DialogDescription>
          </DialogHeader>

          <CreateLivroForm />

          <DialogFooter>
            <DialogClose>
              <Button variant={"ghost"} type="button">
                Cancelar
              </Button>
            </DialogClose>
            <Button type="submit" form="create-livro-form">Salvar</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
