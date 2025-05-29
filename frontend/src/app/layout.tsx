import ReactQueryProvider from "@/utils/provider/ReactQueryProvider";
import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  icons: {
    icon: "/open-book.png"
  },
  title: "E-Library",
  description: "Interface web para sistema de biblioteca virtual"
};

export default function RootLayout({
  children
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body className="bg-white-smoke">
        <ReactQueryProvider>{children}</ReactQueryProvider>
      </body>
    </html>
  );
}
