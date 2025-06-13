"use client";

import CreateLivroModal from "@/components/dashboard-livros/create-livro-modal";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import DashboardTable from "./table/dashboard-table";
import { DashBoardFunctions } from ".";

export default function Dashboard() {
  const { handleRouterPushLivros, handleRouterPushClientes } =
    DashBoardFunctions();

  return (
      <div className="p-10 justify-center min-h-screen flex items-center flex-col gap-3">
        <header>
          <h1 className="text-2xl">Histórico de empréstimos</h1>
        </header>
        <main>
          <DashboardTable />
        </main>
        <footer className="flex flex-col items-center gap-3">
          <div className="space-x-2">
            <Button
              className="cursor-pointer"
              variant="outline"
              onClick={handleRouterPushLivros}
            >
              Gerenciar livros
            </Button>
            <Button
              className="cursor-pointer"
              variant="outline"
              onClick={handleRouterPushClientes}
            >
              Gerenciar clientes
            </Button>
          </div>
          <Link href={"/"}>Voltar para página inicial</Link>
        </footer>
      </div>
  );
}
