import { useState } from 'react'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api/account'

function CreateAccountForm({ onSuccess }) {
  const [form, setForm] = useState({ accountNumber: '', customerName: '' })

  const handleCreate = async () => {
    await axios.post(API_BASE, form)
    setForm({ accountNumber: '', customerName: '' })
    onSuccess()
  }

  return (
    <section>
      <h2>Create Account</h2>
      <input
        placeholder='Account Number'
        value={form.accountNumber}
        onChange={(e) => setForm({ ...form, accountNumber: e.target.value })}
      />
      <input
        placeholder='Customer Name'
        value={form.customerName}
        onChange={(e) => setForm({ ...form, customerName: e.target.value })}
      />
      <button onClick={handleCreate}>Create</button>
    </section>
  )
}

export default CreateAccountForm
