import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/books'

export const getAllBooks = () => axios.get(BASE_URL)
export const getBookByIsbn = (isbn) => axios.get(`${BASE_URL}?isbn=${isbn}`)
export const searchBookByAuthor = (author) =>
  axios.get(`${BASE_URL}?author=${author}`)
export const addBook = (book) => axios.post(BASE_URL, book)
export const updateBook = (id, book) => axios.put(`${BASE_URL}/${id}`, book)
export const deleteBook = (id) => axios.delete(`${BASE_URL}/${id}`)
