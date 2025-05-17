import Header from "@/components/Header";

export default function Home() {
  return (
    <div>
      <Header />

      <div className="bg-white-smoke">
        <main></main>
      </div>

      <div>
        <footer>
          Créditos:
          <ul>
            <li>
              <a
                href="https://www.flaticon.com/free-icons/book"
                title="book icons"
              >
                Book icons created by Freepik - Flaticon
              </a>
            </li>
          </ul>
        </footer>
      </div>
    </div>
  );
}
