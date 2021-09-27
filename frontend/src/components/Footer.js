import { Link } from 'react-router-dom'
import React from 'react'

const Footer = () => {
  return (
    <div>
      <hr/>
    <footer>
    <img className="small" src="logo.png" alt ="Virtualan" /> 
    <div> Virtualan Software &copy; 2021</div>
      <Link to='/about'>About</Link>
    </footer></div>
  )
}

export default Footer
