import { useState } from 'react'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api/account'

function TransferForm({ onSuccess }) {
  const [form, setForm] = useState({
    from: '',
    to: '',
    amount: '',
    description: '',
  })

  const handleTransfer = async () => {
    await axios.put(`${API_BASE}/${form.from}/transfer/${form.to}`, {
      amount: parseFloat(form.amount),
      description: form.description,
    })
    setForm({ from: '', to: '', amount: '', description: '' })
    onSuccess()
  }

  return (
    <section>
      <h2>Transfer Funds</h2>
      <input
        placeholder='From Account'
        value={form.from}
        onChange={(e) => setForm({ ...form, from: e.target.value })}
      />
      <input
        placeholder='To Account'
        value={form.to}
        onChange={(e) => setForm({ ...form, to: e.target.value })}
      />
      <input
        type='number'
        placeholder='Amount'
        value={form.amount}
        onChange={(e) => setForm({ ...form, amount: e.target.value })}
      />
      <input
        placeholder='Description'
        value={form.description}
        onChange={(e) => setForm({ ...form, description: e.target.value })}
      />
      <button onClick={handleTransfer}>Transfer</button>
    </section>
  )
}

export default TransferForm
