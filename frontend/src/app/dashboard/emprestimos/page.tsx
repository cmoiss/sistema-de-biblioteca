"use client";

import CreateLivroModal from "@/components/dashboard-livros/create-livro-modal";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import DashboardTable from "./dashboard-table";
import { DashBoardFunctions } from ".";

export default function Dashboard() {
  const { handleRouterPush } = DashBoardFunctions();

  return (
    <div className="p-10 justify-center min-h-screen flex items-center flex-col gap-3">
      <h1 className="text-2xl">Histórico de empréstimos</h1>

      <DashboardTable />

      <CreateLivroModal />

      <div>
        <Button
          className="cursor-pointer"
          variant="outline"
          onClick={handleRouterPush}
        >
          Gerenciar clientes
        </Button>
      </div>

      <Link href={"/"}>Voltar para página inicial</Link>
    </div>
  );
}
