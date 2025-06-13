import { Loan } from "@/models/loan";
import { useState } from "react";

export default function useTable() {
  const [loans, setLoansState] = useState<Loan[]>([]);

  const getLoans = () => {
    return loans;
  };

  const setLoans = (newLoans: Loan[]) => {
    setLoansState(newLoans);
  };

  const handleReturnLoan = (loanId: number) => {};

  return { getLoans, setLoans, handleReturnLoan };
}
