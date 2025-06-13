"use client";
import { Alert, AlertTitle } from "@/components/ui/alert";

import CreateLivroModal from "@/components/dashboard-livros/create-livro-modal";
import { CheckCircle2Icon } from "lucide-react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
import DashboardTable from "./dashboard-table";
import { Button } from "@/components/ui/button";

export default function Dashboard() {
  const router = useRouter();

  // Redireciona caso não esteja logado
  useEffect(() => {
    if (process.env.NODE_ENV !== "development") {
      const isLoggedIn = localStorage.getItem("loggedIn");
      if (isLoggedIn !== "true") {
        router.push("/login");
      }
    }
  }, [router]);

  return (
    <div className="p-10 justify-center min-h-screen flex items-center flex-col gap-3">
      <h1 className="text-2xl">Gerenciamento de livros</h1>

      <Alert variant={"default"} className="max-w-sm">
        <CheckCircle2Icon />
        <AlertTitle>Logado com sucesso</AlertTitle>
      </Alert>

      <DashboardTable />

      <CreateLivroModal />

      <div>
        <Button
          className="cursor-pointer"
          variant="outline"
          onClick={() => router.push("/dashboard/clientes")}
        >
          Gerenciar clientes
        </Button>{" "}
        <Button
          className="cursor-pointer"
          variant="outline"
          onClick={() => router.push("/dashboard/emprestimos")}
        >
          Gerenciar empréstimos
        </Button>
      </div>

      <Link href={"/"}>Voltar para página inicial</Link>
    </div>
  );
}
