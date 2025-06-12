import { useRouter } from "next/navigation";
import { useEffect } from "react";

export const DashBoardFunctions = () => {
  const router = useRouter();

  useEffect(() => {
    if (process.env.NODE_ENV !== "development") {
      const isLoggedIn = localStorage.getItem("loggedIn");
      if (isLoggedIn !== "true") {
        router.push("/login");
      }
    }
  }, [router]);

  const handleRouterPush = () => {
    router.push("/dashboard/clientes");
  };

  return {
    handleRouterPush
  };
};
