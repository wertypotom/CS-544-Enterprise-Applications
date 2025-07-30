import React from 'react'
import './App.css'
import BookForm from './BookForm'
import BookList from './BookList'

export default function App() {
  const [reload, setReload] = React.useState(false)
  return (
    <div className='container'>
      <h1>Book Manager</h1>
      <BookForm onReload={() => setReload(!reload)} />
      <BookList key={reload} />
    </div>
  )
}
