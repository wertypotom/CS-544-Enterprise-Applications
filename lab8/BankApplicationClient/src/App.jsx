import { useEffect, useState } from 'react'
import axios from 'axios'
import './App.css'
import CreateAccountForm from './components/CreateAccountForm'
import DepositWithdrawForm from './components/DepositWithdrawForm'
import TransferForm from './components/TransferForm'
import AccountList from './components/AccountList'

const API_BASE = 'http://localhost:8080/api/account'

function App() {
  const [accounts, setAccounts] = useState([])

  const fetchAccounts = async () => {
    const res = await axios.get(API_BASE)
    setAccounts(res.data)
  }

  useEffect(() => {
    fetchAccounts()
  }, [])

  return (
    <div className='container'>
      <h1>🏦 Bank Account Manager</h1>
      <CreateAccountForm onSuccess={fetchAccounts} />
      <DepositWithdrawForm onSuccess={fetchAccounts} />
      <TransferForm onSuccess={fetchAccounts} />
      <AccountList accounts={accounts} />
    </div>
  )
}

export default App
