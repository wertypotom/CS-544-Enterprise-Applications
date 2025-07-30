function AccountList({ accounts }) {
  return (
    <section>
      <h2>All Accounts</h2>
      <ul>
        {accounts.map((acc) => (
          <li key={acc.accountNumber}>
            <strong>{acc.customer.name}</strong> – #{acc.accountNumber} – 💰 $
            {acc.balance.toFixed(2)}
          </li>
        ))}
      </ul>
    </section>
  )
}

export default AccountList
