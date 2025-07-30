import { useState } from 'react'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api/account'

function DepositWithdrawForm({ onSuccess }) {
  const [form, setForm] = useState({
    accountNumber: '',
    amount: '',
    type: 'usd',
    operation: 'deposit',
  })

  const handleSubmit = async () => {
    const url = `${API_BASE}/${form.accountNumber}/${form.operation}?type=${form.type}`
    const method = form.operation === 'deposit' ? 'post' : 'put'
    await axios[method](url, {
      amount: parseFloat(form.amount),
    })
    setForm({ ...form, amount: '' })
    onSuccess()
  }

  return (
    <section>
      <h2>Deposit / Withdraw</h2>
      <select
        value={form.operation}
        onChange={(e) => setForm({ ...form, operation: e.target.value })}
      >
        <option value='deposit'>Deposit</option>
        <option value='withdraw'>Withdraw</option>
      </select>
      <input
        placeholder='Account Number'
        value={form.accountNumber}
        onChange={(e) => setForm({ ...form, accountNumber: e.target.value })}
      />
      <input
        type='number'
        placeholder='Amount'
        value={form.amount}
        onChange={(e) => setForm({ ...form, amount: e.target.value })}
      />
      <select
        value={form.type}
        onChange={(e) => setForm({ ...form, type: e.target.value })}
      >
        <option value='usd'>USD</option>
        <option value='euro'>EURO</option>
      </select>
      <button onClick={handleSubmit}>Submit</button>
    </section>
  )
}

export default DepositWithdrawForm
