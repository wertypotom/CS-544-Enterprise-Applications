import React, { useEffect, useState } from 'react'
import {
  getAllBooks,
  deleteBook,
  getBookByIsbn,
  searchBookByAuthor,
} from './api'

export default function BookList() {
  const [books, setBooks] = useState([])
  const [isbnQuery, setIsbnQuery] = useState('')
  const [authorQuery, setAuthorQuery] = useState('')

  const fetchBooks = async () => {
    const res = await getAllBooks()
    setBooks(res.data)
  }

  const handleDelete = async (id) => {
    await deleteBook(id)
    fetchBooks()
  }

  const handleIsbnSearch = async () => {
    const res = await getBookByIsbn(isbnQuery)
    setBooks([res.data])
  }

  const handleAuthorSearch = async () => {
    const res = await searchBookByAuthor(authorQuery)
    setBooks(res.data)
  }

  useEffect(() => {
    fetchBooks()
  }, [])

  return (
    <div>
      <h2>Book List</h2>
      <div>
        <input
          placeholder='Search by ISBN'
          value={isbnQuery}
          onChange={(e) => setIsbnQuery(e.target.value)}
        />
        <button onClick={handleIsbnSearch}>Search</button>
        <input
          placeholder='Search by Author'
          value={authorQuery}
          onChange={(e) => setAuthorQuery(e.target.value)}
        />
        <button onClick={handleAuthorSearch}>Search</button>
        <button onClick={fetchBooks}>Reload</button>
      </div>
      <ul>
        {books.map((b) => (
          <li key={b.id}>
            <strong>{b.title}</strong> by {b.author} (ISBN: {b.isbn}, Price: $
            {b.price}){' '}
            <button onClick={() => handleDelete(b.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  )
}
