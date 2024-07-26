"use client";
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import FooterComponent from './MyComponents/FooterComponent.js';
import HeaderComponent from './MyComponents/HeaderComponent.js';
import ListTodo from "./MyComponents/ListTodo.js";
import TodoComponent from './MyComponents/TodoComponent.js';
export default function App(){
  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
        <Routes>
          {/*http://localhost:8080*/}
          <Route path='/' element={<ListTodo/>}></Route>
          {/*http://localhost:8080/todos*/}
          <Route path='/todos' element={<ListTodo/>}></Route>
          {/*http://localhost:8080/todos*/}
          <Route path='/add-todo' element={<TodoComponent/>}></Route>
          {/*http://localhost:8080/update-todos*/}
          <Route path='/update-todo/:id'element={<TodoComponent/>}></Route>
        </Routes>
      <FooterComponent/>
    </BrowserRouter>

    </>
    //use charts
  );
}
