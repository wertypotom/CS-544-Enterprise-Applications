import React, { useState } from 'react'
import { addBook, updateBook } from './api'

export default function BookForm({ onReload }) {
  const [book, setBook] = useState({
    title: '',
    author: '',
    isbn: '',
    price: '',
  })
  const [id, setId] = useState('')

  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (id) {
      await updateBook(id, book)
    } else {
      await addBook(book)
    }
    onReload()
    setBook({ title: '', author: '', isbn: '', price: '' })
    setId('')
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        name='title'
        value={book.title}
        onChange={handleChange}
        placeholder='Title'
        required
      />
      <input
        name='author'
        value={book.author}
        onChange={handleChange}
        placeholder='Author'
        required
      />
      <input
        name='isbn'
        value={book.isbn}
        onChange={handleChange}
        placeholder='ISBN'
        required
      />
      <input
        value={id}
        onChange={(e) => setId(e.target.value)}
        placeholder='ID (for update)'
      />
      <input
        name='price'
        value={book.price}
        onChange={handleChange}
        placeholder='Price'
        type='number'
        required
      />
      <button type='submit'>{id ? 'Update' : 'Add'} Book</button>
    </form>
  )
}
