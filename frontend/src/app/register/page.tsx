import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";

import { fromTheme } from "tailwind-merge";

export default function RegisterPage() {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <Card>
        <CardHeader>
          <CardTitle>Crie sua conta</CardTitle>
          <CardDescription>Preencha todos os campos</CardDescription>
        </CardHeader>
        <CardContent className="">
          <p>Digite seu nome de usuário</p>
          <Input type="default" placeholder="Nome"/>
          <p>Digite seu email</p>
          <Input type="email" placeholder="Email"/>
          <p>Digite sua senha</p>
          <Input type="password" placeholder="Senha"/>
          <p>Confirme sua senha</p>
          <Input type="password" placeholder="Confirme a senha"/>
        </CardContent>
        <CardFooter>
          <Button variant={"default"}>Registrar</Button>
        </CardFooter>
      </Card>

    </div>
  );
}